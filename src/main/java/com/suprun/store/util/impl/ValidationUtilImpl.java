package com.suprun.store.util.impl;

import com.suprun.store.exception.ServiceException;
import com.suprun.store.service.UserService;
import com.suprun.store.service.impl.UserServiceImpl;
import com.suprun.store.util.ValidationUtil;
import org.apache.commons.lang3.tuple.Pair;

import static com.suprun.store.entity.controller.command.PagePath.AMPERSAND;
import static com.suprun.store.entity.controller.command.PagePath.EQUALS_SIGN;
import static com.suprun.store.entity.controller.command.RequestAttribute.DUPLICATE_EMAIL_ERROR;
import static com.suprun.store.entity.controller.command.RequestAttribute.DUPLICATE_LOGIN_ERROR;


public class ValidationUtilImpl implements ValidationUtil {

    private static final UserService userService = UserServiceImpl.getInstance();
    private static ValidationUtil instance;

    public static ValidationUtil getInstance() {
        if (instance == null) {
            instance = new ValidationUtilImpl();
        }
        return instance;
    }

    @Override
    public Pair<Boolean, String> isUpdatedUserDuplicate(String email, String login, String redirectUrl,
                                                        boolean emailsMatch, boolean loginsMatch) throws ServiceException {
        boolean duplicate = false;
        if (!emailsMatch && !userService.isEmailUnique(email)) {
            duplicate = true;
            redirectUrl += AMPERSAND + DUPLICATE_EMAIL_ERROR + EQUALS_SIGN + true;
        }
        if (!loginsMatch && !userService.isLoginUnique(login)) {
            duplicate = true;
            redirectUrl += AMPERSAND + DUPLICATE_LOGIN_ERROR + EQUALS_SIGN + true;
        }
        return Pair.of(duplicate, redirectUrl);
    }

    @Override
    public Pair<Boolean, String> isUserDuplicate(String email, String login, String redirectUrl) throws ServiceException {
        boolean duplicate = false;
        if (!userService.isEmailUnique(email)) {
            duplicate = true;
            redirectUrl = AMPERSAND + DUPLICATE_EMAIL_ERROR + EQUALS_SIGN + true;
        }
        if (!userService.isLoginUnique(login)) {
            duplicate = true;
            redirectUrl = AMPERSAND + DUPLICATE_LOGIN_ERROR + EQUALS_SIGN + true;
        }
        return Pair.of(duplicate, redirectUrl);
    }
}
