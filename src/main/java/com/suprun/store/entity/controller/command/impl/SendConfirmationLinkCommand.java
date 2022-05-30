package com.suprun.store.entity.controller.command.impl;

import com.suprun.store.entity.controller.command.Command;
import com.suprun.store.entity.controller.command.CommandResult;
import com.suprun.store.exception.ServiceException;
import com.suprun.store.util.MailUtil;
import com.suprun.store.util.impl.MailUtilImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.suprun.store.entity.controller.command.PagePath.*;
import static com.suprun.store.entity.controller.command.RequestAttribute.EMAIL_CONFIRMATION_TOKEN;
import static com.suprun.store.entity.controller.command.SessionAttribute.USER_EMAIL;
import static com.suprun.store.entity.controller.command.SessionAttribute.USER_ID;
import static jakarta.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;

public class SendConfirmationLinkCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger();
    private final MailUtil mailUtil = MailUtilImpl.getInstance();

    @Override
    public CommandResult execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String email = String.valueOf(session.getAttribute(USER_EMAIL));
        long userId = Long.parseLong(session.getAttribute(USER_ID).toString());

        try {
            mailUtil.SendConfirmationMail(userId, email, request.getScheme(), request.getServerName());

            String redirectUrl = INDEX_URL + AMPERSAND + EMAIL_CONFIRMATION_TOKEN + EQUALS_SIGN + true;
            return CommandResult.createRedirectResult(redirectUrl);
        } catch (ServiceException e) {
            LOGGER.error("An error occurred during send confirmation link command execution", e);
            return CommandResult.createErrorResult(SC_INTERNAL_SERVER_ERROR);
        }
    }
}
