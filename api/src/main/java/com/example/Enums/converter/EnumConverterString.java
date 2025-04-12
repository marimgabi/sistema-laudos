package com.example.Enums.converter;

import com.example.Enums.EnumString;
import jakarta.persistence.AttributeConverter;

import java.util.Arrays;

public abstract class EnumConverterString<T extends Enum<T> & EnumString> implements AttributeConverter<T, String> {


    private final Class<T> enumClass;


    protected EnumConverterString(Class<T> enumClass) {
        this.enumClass = enumClass;
    }


    @Override
    public String convertToDatabaseColumn(T enumValue) {
        return (enumValue != null) ? enumValue.getValue() : null;
    }


    @Override
    public T convertToEntityAttribute(String valor) {
        return (valor != null)
                ? Arrays.stream(enumClass.getEnumConstants())
                .filter(e -> e.getValue().equals(valor))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Valor inválido para a Enum " + enumClass.getSimpleName() + ": " + valor))
                : null;
    }
}

