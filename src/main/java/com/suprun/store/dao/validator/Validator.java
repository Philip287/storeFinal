package com.suprun.store.dao.validator;

public interface Validator<T> {
    String SERVICE_EXCEPTION = "service exception";

    boolean validate(T t);
}
