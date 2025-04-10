package com.example.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "laudo")
@Getter
@Setter
public class Laudo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "data_laudo")
    private Date dataLaudo;

    @Column(name = "nome_paciente")
    private String nomePaciente;

    @Column(name = "sexo")
    private String sexo;

    @Column(name = "data_nascimento")
    private Date dataNascimento;

    @ManyToOne
    @JoinColumn(name = "id_medico_solicitante")
    private Medico medicoSolicitante;

    @ManyToOne
    @JoinColumn(name = "id_medico_executante")
    private Medico medicoExecutante;

    @Column(name = "conteudo", columnDefinition = "TEXT")
    private String conteudo;

    @Column(name = "data_inclusao")
    private Date dataInclusao;

    @Column(name = "data_alteracao")
    private Date dataAlteracao;

    @Column(name = "status")
    private String status;
}

