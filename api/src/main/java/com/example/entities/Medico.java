package com.example.entities;

import com.example.Enums.EnumStatus;
import com.example.Enums.EnumTipoMedico;
import com.example.Enums.converter.EnumStatusConverter;
import com.example.Enums.converter.EnumTipoMedicoConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "medico")
@Getter
@Setter
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "tipo")
    @Convert(converter = EnumTipoMedicoConverter.class)
    private EnumTipoMedico tipo;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_conselho")
    private Conselho conselho;

    @ManyToMany
    @JoinTable(
            name = "medico_template",
            joinColumns = @JoinColumn(name = "id_medico"),
            inverseJoinColumns = @JoinColumn(name = "id_template")
    )
    private Set<Template> templates = new HashSet<>();

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

