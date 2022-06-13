package com.suprun.store.service.validator.impl;

import com.suprun.store.service.validator.Validator;

import java.util.regex.Pattern;

public class NumberValidator implements Validator<Number> {

    private static final String VALID_NUMBER_REGEX = "\\d{1,3}";

    private static Validator<Number> instance;

    public static Validator<Number> getInstance() {
        if (instance == null) {
            instance = new NumberValidator();
        }
        return instance;
    }

    @Override
    public boolean validate(Number number) {
        return Pattern.matches(VALID_NUMBER_REGEX, (CharSequence) number);
    }
}
