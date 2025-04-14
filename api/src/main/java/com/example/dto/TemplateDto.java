package com.example.dto;

import com.example.Enums.EnumTipoTemplate;

import lombok.Data;

@Data
public class TemplateDto {

    private Integer id;

    private String descricao;

    private String conteudo;

    private EnumTipoTemplate tipo;
}
