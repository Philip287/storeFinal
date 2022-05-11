package com.suprun.store.controller.command.impl;

import com.suprun.store.controller.command.Command;
import com.suprun.store.controller.command.CommandResult;
import jakarta.servlet.http.HttpServletRequest;

public class LoginCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request) {
        return null;
    }
}
