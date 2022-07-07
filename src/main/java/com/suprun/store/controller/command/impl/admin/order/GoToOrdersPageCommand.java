package com.suprun.store.controller.command.impl.admin.order;

import com.suprun.store.controller.command.Command;
import com.suprun.store.controller.command.CommandResult;
import jakarta.servlet.http.HttpServletRequest;

import static com.suprun.store.controller.command.PagePath.ADMIN_ORDERS_JSP;

public class GoToOrdersPageCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request) {
        return CommandResult.createForwardResult(ADMIN_ORDERS_JSP);
    }
}
