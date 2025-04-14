package com.example.dto.login;

import com.example.Enums.EnumTipoMedico;
import lombok.Data;

@Data
public class MedicoLoginDto {

    private Integer id;

    private String nome;

    private EnumTipoMedico tipo;
}
