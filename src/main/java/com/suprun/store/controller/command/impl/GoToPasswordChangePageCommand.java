package com.suprun.store.controller.command.impl;

import com.suprun.store.controller.command.Command;
import com.suprun.store.controller.command.CommandResult;
import com.suprun.store.exception.ServiceException;
import com.suprun.store.service.UserService;
import com.suprun.store.service.impl.UserServiceImpl;
import com.suprun.store.util.TokenUtil;
import com.suprun.store.util.impl.TokenUtilImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

import static com.suprun.store.controller.command.PagePath.PASSWORD_CHANGE_JSP;
import static com.suprun.store.controller.command.RequestAttribute.*;
import static com.suprun.store.util.impl.TokenUtilImpl.EMAIL_CLAIM;
import static jakarta.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
import static jakarta.servlet.http.HttpServletResponse.SC_NOT_FOUND;

public class GoToPasswordChangePageCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private final TokenUtil tokenUtil = TokenUtilImpl.getInstance();
    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    public CommandResult execute(HttpServletRequest request) {
        try {
            String token = request.getParameter(TOKEN);
            Map<String, Object> tokenContent = tokenUtil.parseToken(TOKEN);
            String email = (String) tokenContent.get(EMAIL_CLAIM);

            if (!userService.isEmailUnique(email)) {
                request.setAttribute(EMAIL, email);
                return CommandResult.createForwardResult(PASSWORD_CHANGE_JSP);
            } else {
                LOGGER.error("Got invalid token");
                return CommandResult.createErrorResult(SC_NOT_FOUND);
            }
        } catch (ServiceException e) {
            LOGGER.error("An error occurred during go to password change page command execution", e);
            return CommandResult.createErrorResult(SC_INTERNAL_SERVER_ERROR);
        }
    }
}
