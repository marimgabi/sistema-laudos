package com.example.validator.generic;

public abstract class BaseValidator<T> implements Validator<T>{

    protected void validateNotNull(Object value, String filedName){
        if(value == null)
            throw new IllegalArgumentException("O campo '" + filedName + "' não pode ser nulo!");
    }

    protected void validateNotEmpty(String value, String filedName){
        if(value == null || value.trim().isEmpty())
            throw new IllegalArgumentException("O campo '" + filedName + "' é obrigatório!");
    }

    protected void validateLength(String value, String fieldName, int min, int max) {
        if (value == null) return;

        if (value.length() < min) {
            throw new IllegalArgumentException("Campo '" + fieldName + "' deve ter no mínimo " + min + " caracteres.");
        }

        if (value.length() > max) {
            throw new IllegalArgumentException("Campo '" + fieldName + "' deve ter no máximo " + max + " caracteres.");
        }
    }

}
