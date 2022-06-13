package com.suprun.store.controller.command.impl.admin.user;

import com.suprun.store.controller.command.AppRole;
import com.suprun.store.controller.command.Command;
import com.suprun.store.controller.command.CommandResult;
import com.suprun.store.controller.listener.HttpSessionAttributeListenerImpl;
import com.suprun.store.exception.ServiceException;
import com.suprun.store.service.UserService;
import com.suprun.store.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

import static com.suprun.store.controller.command.PagePath.ADMIN_USERS_URL;
import static com.suprun.store.controller.command.RequestAttribute.ENTITY_ID;
import static com.suprun.store.controller.command.SessionAttribute.*;
import static jakarta.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;

public class DeleteUserCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger();
    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    public CommandResult execute(HttpServletRequest request) {
        long entityId = Long.parseLong(request.getParameter(ENTITY_ID));

        try {
            userService.delete(entityId);
            Optional<HttpSession> optionalHttpSession = HttpSessionAttributeListenerImpl.findSession(entityId);
            if (optionalHttpSession.isPresent()) {
                HttpSession session = optionalHttpSession.get();
                session.removeAttribute(USER_ID);
                session.removeAttribute(USER_LOGIN);
                session.removeAttribute(USER_EMAIL);
                session.setAttribute(USER_ROLE, AppRole.GUEST);
            }
            return CommandResult.createRedirectResult(ADMIN_USERS_URL);
        } catch (ServiceException e) {
            LOGGER.error("An error occurred during delete user command execution", e);
            return CommandResult.createErrorResult(SC_INTERNAL_SERVER_ERROR);
        }
    }
}
