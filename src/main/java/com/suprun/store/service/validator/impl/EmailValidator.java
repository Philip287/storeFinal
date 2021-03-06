package com.suprun.store.service.validator.impl;

import com.suprun.store.service.validator.Validator;

import java.util.regex.Pattern;

public class EmailValidator implements Validator<String> {

    private static final String VALID_EMAIL_REGEX = "^[\\w.]{3,30}@[\\w.]{3,20}$";

    private static Validator<String> instance;

    private EmailValidator(){

    }

    public static Validator<String> getInstance() {
        if (instance == null) {
            instance = new EmailValidator();
        }
        return instance;
    }

    @Override
    public boolean validate(String email) {
        return Pattern.matches(VALID_EMAIL_REGEX, email);
    }
}
