package com.suprun.store.controller.command.impl;

import com.suprun.store.controller.command.Command;
import com.suprun.store.controller.command.CommandResult;
import jakarta.servlet.http.HttpServletRequest;

import static com.suprun.store.controller.command.PagePath.FINISH_ORDER_JSP;

public class GoToFinishOrderPageCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request) {
        return CommandResult.createForwardResult(FINISH_ORDER_JSP);
    }
}
