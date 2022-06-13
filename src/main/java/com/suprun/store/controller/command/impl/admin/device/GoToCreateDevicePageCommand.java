package com.suprun.store.controller.command.impl.admin.device;

import com.suprun.store.controller.command.Command;
import com.suprun.store.controller.command.CommandResult;
import jakarta.servlet.http.HttpServletRequest;

import static com.suprun.store.controller.command.PagePath.ADMIN_CREATE_DEVICE_JSP;

public class GoToCreateDevicePageCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request) {
        return CommandResult.createForwardResult(ADMIN_CREATE_DEVICE_JSP);
    }
}
