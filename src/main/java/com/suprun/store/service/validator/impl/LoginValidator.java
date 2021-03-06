package com.suprun.store.service.validator.impl;

import com.suprun.store.service.validator.Validator;

import java.util.regex.Pattern;

public class LoginValidator implements Validator<String> {

    private static final String VALID_LOGIN_REGEX = "^\\p{Alnum}{6,20}$";

    private static Validator<String> instance;

    private LoginValidator(){

    }

    public static Validator<String> getInstance() {
        if (instance == null) {
            instance = new LoginValidator();
        }
        return instance;
    }

    @Override
    public boolean validate(String login) {
        return Pattern.matches(VALID_LOGIN_REGEX, login);
    }
}
