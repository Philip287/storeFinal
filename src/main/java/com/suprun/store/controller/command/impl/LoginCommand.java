package com.suprun.store.controller.command.impl;

import com.suprun.store.controller.command.CommandResult;
import com.suprun.store.entity.User;
import com.suprun.store.controller.command.AppRole;
import com.suprun.store.controller.command.Command;
import com.suprun.store.exception.ServiceException;
import com.suprun.store.service.UserService;
import com.suprun.store.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

import static com.suprun.store.controller.command.AppRole.NOT_CONFIRMED;
import static com.suprun.store.controller.command.PagePath.*;
import static com.suprun.store.controller.command.RequestAttribute.*;
import static com.suprun.store.controller.command.SessionAttribute.*;
import static jakarta.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;

public class LoginCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    public CommandResult execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);

        try {
            Optional<User> optionalUser = userService.login(login, password);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                session.setAttribute(USER_ID, user.getEntityId());
                session.setAttribute(USER_LOGIN, user.getLogin());
                session.setAttribute(USER_EMAIL, user.getEmail());

                User.UserRole role = user.getRole();
                User.UserStatus status = user.getStatus();
                switch (status) {
                    case NOT_CONFIRMED -> {
                        session.setAttribute(USER_ROLE, NOT_CONFIRMED);
                        return CommandResult.createRedirectResult(INDEX_URL);
                    }
                    case CONFIRMED -> {
                        session.setAttribute(USER_ROLE, AppRole.valueOf(role.toString()));
                        return CommandResult.createRedirectResult(INDEX_URL);
                    }
                    case DELETED -> {
                        String redirectUrl = LOGIN_URL + AMPERSAND + LOGIN_ERROR + EQUALS_SIGN + true;
                        return CommandResult.createErrorResult(SC_INTERNAL_SERVER_ERROR);
                    }
                    default -> {
                        LOGGER.error("Invalid user status: " + status);
                        return CommandResult.createErrorResult(SC_INTERNAL_SERVER_ERROR);
                    }
                }
            } else {
                String redirectUrl = LOGIN_URL
                        + AMPERSAND + LOGIN_ERROR + EQUALS_SIGN + true;
                return CommandResult.createRedirectResult(redirectUrl);
            }
        } catch (ServiceException e) {
            LOGGER.error("An error occurred during login command execution", e);
            return CommandResult.createErrorResult(SC_INTERNAL_SERVER_ERROR);
        }
    }
}
