package com.example.entities;

import com.example.Enums.EnumStatus;
import com.example.Enums.converter.EnumStatusConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "estado")
@Getter
@Setter
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "sigla", nullable = false, length = 2)
    private String sigla;

    @Column(name = "data_inclusao")
    private Date dataInclusao;

    @Column(name = "data_alteracao")
    private Date dataAlteracao;

    @Column(name = "status")
    @Convert(converter = EnumStatusConverter.class)
    private EnumStatus status;

    @PrePersist
    private void prePersist(){
        this.dataInclusao = new Date();
    }

    @PreUpdate
    private void preUpdate(){
        this.dataAlteracao = new Date();
    }
}
