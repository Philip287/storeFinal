package com.suprun.store.controller.command.impl;

import com.suprun.store.controller.command.Command;
import com.suprun.store.controller.command.CommandResult;
import jakarta.servlet.http.HttpServletRequest;

import static com.suprun.store.controller.command.PagePath.CARD_JSP;

public class GoToCardPageCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request) {
        return CommandResult.createForwardResult(CARD_JSP);
    }
}
