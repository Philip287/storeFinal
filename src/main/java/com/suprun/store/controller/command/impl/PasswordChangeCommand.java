package com.suprun.store.controller.command.impl;

import com.suprun.store.entity.User;
import com.suprun.store.controller.command.Command;
import com.suprun.store.controller.command.CommandResult;
import com.suprun.store.exception.ServiceException;
import com.suprun.store.service.UserService;
import com.suprun.store.service.impl.UserServiceImpl;
import com.suprun.store.service.validator.Validator;
import com.suprun.store.service.validator.impl.PasswordValidator;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

import static com.suprun.store.controller.command.PagePath.*;
import static com.suprun.store.controller.command.RequestAttribute.*;
import static jakarta.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
import static jakarta.servlet.http.HttpServletResponse.SC_NOT_FOUND;

public class PasswordChangeCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger();
    private final Validator<String> passwordValidator = PasswordValidator.getInstance();
    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    public CommandResult execute(HttpServletRequest request) {
        String token = request.getParameter(TOKEN);
        String email = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);

        try {
            boolean valid = passwordValidator.validate(password);
            if (valid) {
                Optional<User> optionalUser = userService.findByEmail(email);
                if (optionalUser.isPresent()) {
                    User user = optionalUser.get();
                    userService.updateWithPassword(user, password);

                    String redirectUrl = INDEX_URL
                            + AMPERSAND + PASSWORD_CHANGE_SUCCESS + EQUALS_SIGN + true;
                    return CommandResult.createRedirectResult(redirectUrl);
                } else {
                    LOGGER.error("User with email given in token is not found");
                    return CommandResult.createErrorResult(SC_NOT_FOUND);
                }
            } else {
                String redirectUrl = PASSWORD_CHANGE_URL + AMPERSAND + TOKEN + EQUALS_SIGN + token
                        + AMPERSAND + VALIDATION_ERROR + true;
                return CommandResult.createRedirectResult(redirectUrl);
            }
        } catch (ServiceException e) {
            LOGGER.error("An error occurred during password change command execution", e);
            return CommandResult.createErrorResult(SC_INTERNAL_SERVER_ERROR);
        }
    }
}
