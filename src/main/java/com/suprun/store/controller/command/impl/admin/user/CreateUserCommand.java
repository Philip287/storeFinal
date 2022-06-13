package com.suprun.store.controller.command.impl.admin.user;

import com.suprun.store.entity.User.UserStatus;
import com.suprun.store.entity.User.UserRole;
import com.suprun.store.controller.command.Command;
import com.suprun.store.controller.command.CommandResult;
import com.suprun.store.exception.ServiceException;
import com.suprun.store.service.UserService;
import com.suprun.store.service.impl.UserServiceImpl;
import com.suprun.store.service.validator.Validator;
import com.suprun.store.service.validator.impl.EmailValidator;
import com.suprun.store.service.validator.impl.LoginValidator;
import com.suprun.store.service.validator.impl.PasswordValidator;
import com.suprun.store.util.ValidationUtil;
import com.suprun.store.util.impl.ValidationUtilImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.suprun.store.controller.command.PagePath.*;
import static com.suprun.store.controller.command.RequestAttribute.*;
import static jakarta.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;

public class CreateUserCommand implements Command {

    private static final Logger logger = LogManager.getLogger();
    private final Validator<String> emailValidator = EmailValidator.getInstance();
    private final Validator<String> loginValidator = LoginValidator.getInstance();
    private final Validator<String> passwordValidator = PasswordValidator.getInstance();
    private final UserService userService = UserServiceImpl.getInstance();
    private final ValidationUtil validationUtil = ValidationUtilImpl.getInstance();

    @Override
    public CommandResult execute(HttpServletRequest request) {
        String email = request.getParameter(EMAIL);
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        UserRole role = UserRole.valueOf(request.getParameter(ROLE));
        UserStatus status = UserStatus.valueOf(request.getParameter(STATUS));

        try {
            boolean valid = emailValidator.validate(email)
                    && loginValidator.validate(login)
                    && passwordValidator.validate(password);

            if (valid) {
                Pair<Boolean, String> pair = validationUtil.isUserDuplicate(email, login, ADMIN_CREATE_USER_URL);
                if (pair.getLeft()) {
                    return CommandResult.createRedirectResult(pair.getRight());
                }

                userService.register(email, login, password, role, status);
                return CommandResult.createRedirectResult(ADMIN_USERS_URL);
            } else {
                String redirectUrl = ADMIN_CREATE_USER_URL
                        + AMPERSAND + VALIDATION_ERROR + EQUALS_SIGN + true;
                return CommandResult.createRedirectResult(redirectUrl);
            }
        } catch (ServiceException e) {
            logger.error("An error occurred during create user command execution", e);
            return CommandResult.createErrorResult(SC_INTERNAL_SERVER_ERROR);
        }
    }
}
