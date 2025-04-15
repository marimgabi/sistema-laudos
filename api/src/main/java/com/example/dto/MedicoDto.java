package com.example.dto;

import com.example.Enums.EnumTipoMedico;
import lombok.Data;

@Data
public class MedicoDto {

    private Integer id;

    private String nome;

    private EnumTipoMedico tipo;

    private ConselhoDto conselho;

    private UserResumeDto user;

}
