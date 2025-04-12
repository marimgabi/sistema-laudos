package com.example.Enums;

public enum EnumTipoMedico implements EnumString{

    EXECUTANTE("EXECUTANTE", "Executante"),
    SOLICITANTE("SOLICITANTE", "Solicitante");


    private final String valor;
    private final String descricao;


    EnumTipoMedico(String valor, String descricao) {
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
