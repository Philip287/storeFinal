package com.suprun.store.entity.controller.command.impl;

import com.suprun.store.entity.controller.command.Command;
import com.suprun.store.entity.controller.command.CommandResult;
import jakarta.servlet.http.HttpServletRequest;

import static com.suprun.store.entity.controller.command.PagePath.FORGOT_PASSWORD_JSP;

public class GoToForgotPasswordPageCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request) {
        return CommandResult.createForwardResult(FORGOT_PASSWORD_JSP);
    }
}
