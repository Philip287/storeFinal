package com.suprun.store.service.validator.impl;

import com.suprun.store.service.validator.Validator;

import java.util.regex.Pattern;

public class PasswordValidator implements Validator<String> {

    private static final String VALID_PASSWORD_REGEX = "^(?=.*\\p{Alpha})(?=.*\\d)[\\p{Alnum}]{8,32}$";

    private static Validator<String> instance;

    private PasswordValidator(){

    }

    public static Validator<String> getInstance() {
        if (instance == null) {
            instance = new PasswordValidator();
        }
        return instance;
    }

    @Override
    public boolean validate(String password) {
        return Pattern.matches(VALID_PASSWORD_REGEX, password);
    }
}
