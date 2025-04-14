package com.example.Enums;

public enum EnumSexo implements EnumString{

    FEMININO("F", "Feminino"),
    MASCULINO("M", "Masculino");


    private final String valor;
    private final String descricao;


    EnumSexo(String valor, String descricao) {
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
