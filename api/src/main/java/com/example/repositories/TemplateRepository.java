package com.example.repositories;

import com.example.Enums.EnumStatus;
import com.example.entities.Template;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TemplateRepository extends JpaRepository<Template, Integer> {

    List<Template> findByMedicosIdAndStatus(Integer medicoId, EnumStatus status);

    Optional<Template> findByIdAndStatus(Integer id, EnumStatus status);

}
