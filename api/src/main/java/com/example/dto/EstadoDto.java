package com.example.dto;

import lombok.Data;

import java.util.Date;

@Data
public class EstadoDto {
    private Integer id;
    private String nome;
    private String sigla;
    private Date dataInclusao;
    private Date dataAlteracao;
    private String status;
}
