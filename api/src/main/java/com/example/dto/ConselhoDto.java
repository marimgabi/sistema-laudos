package com.example.dto;

import lombok.Data;

@Data
public class ConselhoDto {

    private Integer id;

    private String tipo;

    private String numero;

    private EstadoDto estado;
}
