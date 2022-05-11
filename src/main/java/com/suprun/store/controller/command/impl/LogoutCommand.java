package com.suprun.store.controller.command.impl;

import com.suprun.store.controller.command.AppRole;
import com.suprun.store.controller.command.Command;
import com.suprun.store.controller.command.CommandResult;
import jakarta.servlet.http.HttpServletRequest;

import static com.suprun.store.controller.command.PagePath.INDEX_URL;
import static com.suprun.store.controller.command.SessionAttribute.*;


public class LogoutCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request) {
        request.getSession().removeAttribute(USER_ID);
        request.getSession().removeAttribute(USER_LOGIN);
        request.getSession().removeAttribute(USER_EMAIL);
        request.getSession().setAttribute(USER_ROLE, AppRole.GUEST);
        return CommandResult.createRedirectResult(INDEX_URL);
    }
}
