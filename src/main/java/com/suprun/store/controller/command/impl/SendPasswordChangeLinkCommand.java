package com.suprun.store.controller.command.impl;

import com.suprun.store.controller.command.Command;
import com.suprun.store.controller.command.CommandResult;
import com.suprun.store.exception.ServiceException;
import com.suprun.store.service.UserService;
import com.suprun.store.service.impl.UserServiceImpl;
import com.suprun.store.util.MailUtil;
import com.suprun.store.util.impl.MailUtilImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.suprun.store.controller.command.PagePath.*;
import static com.suprun.store.controller.command.RequestAttribute.*;
import static jakarta.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;

public class SendPasswordChangeLinkCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger();
    private final MailUtil mailUtil = MailUtilImpl.getInstance();
    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    public CommandResult execute(HttpServletRequest request) {
        String email = request.getParameter(EMAIL);

        try{
            if(!userService.isEmailUnique(email)){
                mailUtil.sendPasswordChangeMail(email, request.getScheme(), request.getServerName());

                String redirectUrl = INDEX_URL + AMPERSAND + PASSWORD_CHANGE_TOKEN + EQUALS_SIGN + true;
                return CommandResult.createRedirectResult(redirectUrl);

            } else {
                String redirectUrl = FORGOT_PASSWORD_URL
                        + AMPERSAND + FORGOT_PASSWORD_ERROR + EQUALS_SIGN + true;
                return CommandResult.createRedirectResult(redirectUrl);
            }
        }catch (ServiceException e){
            LOGGER.error("An error occurred during send password change link command execution", e);
            return CommandResult.createErrorResult(SC_INTERNAL_SERVER_ERROR);
        }
    }
}
