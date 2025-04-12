package com.example.Enums;

import lombok.Getter;

@Getter
public enum EnumStatus implements EnumString{

    ATIVO("A", "Ativo"),
    INATIVO("I", "Ativo");


    private final String valor;
    private final String descricao;


    EnumStatus(String valor, String descricao) {
        this.valor = valor;
        this.descricao = descricao;
    }


    @Override
    public String getValue() {
        return this.valor;
    }


    @Override
    public String toString() {
        return descricao;
    }

}
