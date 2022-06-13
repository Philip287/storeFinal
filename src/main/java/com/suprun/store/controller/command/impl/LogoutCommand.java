package com.suprun.store.controller.command.impl;

import com.suprun.store.controller.command.AppRole;
import com.suprun.store.controller.command.Command;
import com.suprun.store.controller.command.CommandResult;
import com.suprun.store.controller.command.SessionAttribute;
import jakarta.servlet.http.HttpServletRequest;

import static com.suprun.store.controller.command.PagePath.INDEX_URL;


public class LogoutCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request) {
        request.getSession().removeAttribute(SessionAttribute.USER_ID);
        request.getSession().removeAttribute(SessionAttribute.USER_LOGIN);
        request.getSession().removeAttribute(SessionAttribute.USER_EMAIL);
        request.getSession().setAttribute(SessionAttribute.USER_ROLE, AppRole.GUEST);
        return CommandResult.createRedirectResult(INDEX_URL);
    }
}
