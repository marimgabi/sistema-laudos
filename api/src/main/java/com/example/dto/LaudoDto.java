package com.example.dto;

import com.example.Enums.EnumSexo;
import lombok.Data;

import java.util.Date;

@Data
public class LaudoDto {

    private Integer id;

    private Date dataLaudo;

    private String nomePaciente;

    private EnumSexo sexo;

    private Date dataNascimento;

    private MedicoDto medicoSolicitante;

    private MedicoDto medicoExecutante;

    private String conteudo;

}
