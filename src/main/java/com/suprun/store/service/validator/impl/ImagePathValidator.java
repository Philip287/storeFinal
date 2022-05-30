package com.suprun.store.service.validator.impl;

import com.suprun.store.service.validator.Validator;
import jakarta.servlet.http.Part;
import org.apache.commons.lang3.StringUtils;

public class ImagePathValidator implements Validator<Part> {

    private static Validator<Part> instance;

    private static final String JPEG_MIME = "image/jpeg";
    private static final String PNG_MIME = "image/png";

    public static Validator<Part> getInstance() {
        if (instance == null) {
            instance = new ImagePathValidator();
        }
        return instance;
    }

    @Override
    public boolean validate(Part part) {
        boolean valid = false;
        String type = part.getContentType();
        if (StringUtils.isEmpty(part.getSubmittedFileName())
                || StringUtils.equals(type, JPEG_MIME)
                || StringUtils.equals(type, PNG_MIME)) {
            valid = true;
        }
        return valid;
    }
}
