package com.dista.cours.validation.api;

public interface Validator<T> {
    boolean validate(T objectToValidate);
}
