package com.example.repositories;

import com.example.Enums.EnumStatus;
import com.example.entities.Laudo;
import com.example.entities.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LaudoRepository extends JpaRepository<Laudo, Integer> {

    Optional<Laudo> findById(Integer id);

    List<Laudo> findByMedicoSolicitanteAndStatus(Medico medicoSolicitante, EnumStatus status);

    List<Laudo> findByMedicoExecutanteAndStatus(Medico medicoExecutante, EnumStatus status);

    List<Laudo> findAllByStatus(EnumStatus status);

    Optional<Laudo> findAllByIdAndStatus(Integer id, EnumStatus status);

}
