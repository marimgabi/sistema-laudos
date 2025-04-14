package com.example.validator.generic;

public interface Validator<T> {

    void validate(T entity);

}
