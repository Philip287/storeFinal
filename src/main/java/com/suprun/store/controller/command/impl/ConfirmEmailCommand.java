package com.suprun.store.controller.command.impl;

import com.suprun.store.entity.User;
import com.suprun.store.controller.command.Command;
import com.suprun.store.controller.command.CommandResult;
import com.suprun.store.exception.ServiceException;
import com.suprun.store.service.UserService;
import com.suprun.store.service.impl.UserServiceImpl;
import com.suprun.store.util.TokenUtil;
import com.suprun.store.util.impl.TokenUtilImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.Optional;

import static com.suprun.store.entity.User.UserStatus.CONFIRMED;
import static com.suprun.store.controller.command.PagePath.*;
import static com.suprun.store.controller.command.RequestAttribute.*;
import static com.suprun.store.util.impl.TokenUtilImpl.EMAIL_CLAIM;
import static com.suprun.store.util.impl.TokenUtilImpl.ID_CLAIM;
import static jakarta.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
import static jakarta.servlet.http.HttpServletResponse.SC_NOT_FOUND;


public class ConfirmEmailCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger();
    private final UserService userService = UserServiceImpl.getInstance();
    private final TokenUtil tokenUtil = TokenUtilImpl.getInstance();

    @Override
    public CommandResult execute(HttpServletRequest request) {
        try {
            String token = request.getParameter(TOKEN);
            Map<String, Object> tokenContent = tokenUtil.parseToken(token);
            long userId = ((Double) tokenContent.get(ID_CLAIM)).longValue();
            String email = (String) tokenContent.get(EMAIL_CLAIM);

            Optional<User> optionalUser = userService.findById(userId);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                if (StringUtils.equals(email, user.getEmail()) && user.getStatus() != CONFIRMED) {
                    User updatedUser = (User) User.builder().of(user)
                            .setStatus(CONFIRMED)
                            .build();
                    userService.update(updatedUser);

                    String redirectUrl = INDEX_URL
                            + AMPERSAND + EMAIL_CONFIRMATION_SUCCESS + EQUALS_SIGN + true;
                    return CommandResult.createRedirectResult(redirectUrl);
                }
            }
            LOGGER.error("Got invalid token");
            return CommandResult.createErrorResult(SC_NOT_FOUND);
        } catch (ServiceException e) {
            LOGGER.error("An error occurred during confirm email command execution", e);
            return CommandResult.createErrorResult(SC_INTERNAL_SERVER_ERROR);
        }
    }
}
