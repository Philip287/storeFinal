package com.suprun.store.controller.command.impl;

import com.suprun.store.controller.command.Command;
import com.suprun.store.controller.command.CommandResult;
import com.suprun.store.controller.command.PagePath;
import jakarta.servlet.http.HttpServletRequest;

public class GoToPasswordPageCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request){
        return CommandResult.createForwardResult(PagePath.FORGOT_PASSWORD_JSP);
    }
}
