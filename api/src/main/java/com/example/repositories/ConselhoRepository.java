package com.example.repositories;

import com.example.Enums.EnumStatus;
import com.example.Enums.EnumTipoMedico;
import com.example.entities.Conselho;
import com.example.entities.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConselhoRepository extends JpaRepository<Conselho, Integer> {

    @Query("""
            SELECT c FROM Conselho c
                WHERE c.numero = :numero
                  AND c.estado = :estado
                  AND c.tipo = :tipo
                  AND c.status = :status
            """)
    Optional<Conselho> findByNumeroAndEstadoAndTipoAndStatus(
            @Param("numero") String numero,
            @Param("estado") Estado estado,
            @Param("tipo") String tipo,
            @Param("status") EnumStatus status
    );

}
