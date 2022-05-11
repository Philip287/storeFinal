package com.suprun.store.dao.validator.impl;

import com.suprun.store.dao.validator.Validator;

import java.util.regex.Pattern;

public class NameValidator implements Validator<String> {

    private static final String VALID_NAME_REGEX = "[a-zA-Z0-9\s\\-]{1,30}";

    private static Validator<String> instance;

    public static Validator<String> getInstance() {
        if (instance == null) {
            instance = new NameValidator();
        }
        return instance;
    }

    @Override
    public boolean validate(String name) {
        return Pattern.matches(VALID_NAME_REGEX, name);
    }
}
