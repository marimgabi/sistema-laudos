package com.example.entities;

import com.example.Enums.EnumStatus;
import com.example.Enums.EnumTipoTemplate;
import com.example.Enums.converter.EnumStatusConverter;
import com.example.Enums.converter.EnumTipoTemplateConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "template")
@Getter
@Setter
public class Template {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "conteudo", columnDefinition = "TEXT")
    private String conteudo;

    @Column(name = "tipo")
    @Convert(converter = EnumTipoTemplateConverter.class)
    private EnumTipoTemplate tipo;

    @ManyToMany(mappedBy = "templates")
    private Set<Medico> medicos = new HashSet<>();

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

