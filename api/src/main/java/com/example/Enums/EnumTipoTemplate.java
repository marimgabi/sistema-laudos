package com.example.Enums;

public enum EnumTipoTemplate implements EnumString{
    TEXTO("TEXTO", "Texto plano"),
    HTML("HTML", "Html");


    private final String valor;
    private final String descricao;


    EnumTipoTemplate(String valor, String descricao) {
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
