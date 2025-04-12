package com.example.entities;

import com.example.Enums.EnumStatus;
import com.example.Enums.EnumTipoMedico;
import com.example.Enums.converter.EnumStatusConverter;
import com.example.Enums.converter.EnumTipoMedicoConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

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

    @Column(name = "data_inclusao")
    private Date dataInclusao;

    @Column(name = "data_alteracao")
    private Date dataAlteracao;

    @Column(name = "status")
    @Convert(converter = EnumStatusConverter.class)
    private EnumStatus status;
}

