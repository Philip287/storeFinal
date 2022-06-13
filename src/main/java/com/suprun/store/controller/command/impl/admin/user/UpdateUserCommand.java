package com.suprun.store.controller.command.impl.admin.user;

import com.suprun.store.entity.User;
import com.suprun.store.entity.User.UserRole;
import com.suprun.store.entity.User.UserStatus;
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
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

import static com.suprun.store.controller.command.PagePath.*;
import static com.suprun.store.controller.command.RequestAttribute.*;
import static jakarta.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
import static jakarta.servlet.http.HttpServletResponse.SC_NOT_FOUND;

public class UpdateUserCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger();
    private final UserService userService = UserServiceImpl.getInstance();
    private final ValidationUtil validationUtil = ValidationUtilImpl.getInstance();
    private final Validator<String> emailValidator = EmailValidator.getInstance();
    private final Validator<String> loginValidator = LoginValidator.getInstance();
    private final Validator<String> passwordValidator = PasswordValidator.getInstance();

    @Override
    public CommandResult execute(HttpServletRequest request) {
        String email = request.getParameter(EMAIL);
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        UserRole role = UserRole.valueOf(request.getParameter(ROLE));
        UserStatus status = UserStatus.valueOf(request.getParameter(STATUS));
        long entityId = Long.parseLong(request.getParameter(ENTITY_ID));

        try {
            Optional<User> optionalUser = userService.findById(entityId);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                String previousEmail = user.getEmail();
                String previousLogin = user.getLogin();

                boolean emailsMatch = StringUtils.equals(email, previousEmail);
                boolean loginsMatch = StringUtils.equals(login, previousLogin);
                boolean passwordEmpty = StringUtils.isEmpty(password);

                boolean valid = emailsMatch || emailValidator.validate(email)
                        && loginsMatch || loginValidator.validate(login)
                        && passwordEmpty || passwordValidator.validate(password);

                if (valid) {
                    Pair<Boolean, String> pair = validationUtil.isUpdatedUserDuplicate(email, login,
                            ADMIN_EDIT_USER_URL, emailsMatch, loginsMatch);
                    if (pair.getLeft()) {
                        return CommandResult.createRedirectResult(pair.getRight());
                    }

                    User updatedUser = (User) User.builder().of(user)
                            .setEmail(email)
                            .setLogin(login)
                            .setRole(role)
                            .setStatus(status)
                            .build();
                    if (passwordEmpty) {
                        userService.update(updatedUser);
                    } else {
                        userService.updateWithPassword(updatedUser, password);
                    }
                    return CommandResult.createRedirectResult(ADMIN_USERS_URL);
                } else {
                    String redirectUrl = ADMIN_EDIT_USER_URL
                            + AMPERSAND + ENTITY_ID + EQUALS_SIGN + entityId
                            + AMPERSAND + VALIDATION_ERROR + EQUALS_SIGN + true;
                    return CommandResult.createRedirectResult(redirectUrl);
                }
            } else {
                LOGGER.error("Requested user not found, id = " + entityId);
                return CommandResult.createErrorResult(SC_NOT_FOUND);
            }
        } catch (ServiceException e) {
            LOGGER.error("An error occurred during update user command execution", e);
            return CommandResult.createErrorResult(SC_INTERNAL_SERVER_ERROR);
        }
    }
}
