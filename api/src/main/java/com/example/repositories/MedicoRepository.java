package com.example.repositories;

import com.example.Enums.EnumStatus;
import com.example.entities.Laudo;
import com.example.entities.Medico;
import com.example.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Integer> {

    @Query("""
            SELECT m FROM Medico m
                WHERE (m.user.username = :username OR m.user.email = :email)
                AND m.status = :status
                AND m.user.status = :status
            """)
    Optional<Medico> findByUser(@Param("username") String username,
                                @Param("email") String email,
                                @Param("status") EnumStatus status);

    Optional<Medico> findByUserAndStatus(User user, EnumStatus status);

    Optional<Medico> findByUser(User user);

    Optional<Medico> findByIdAndStatus(Integer id, EnumStatus status);

    List<Medico> findAllByStatus(EnumStatus status);

}
