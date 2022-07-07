package com.suprun.store.controller.command.impl;

import com.suprun.store.controller.command.Command;
import com.suprun.store.controller.command.CommandResult;
import jakarta.servlet.http.HttpServletRequest;

import static com.suprun.store.controller.command.PagePath.USER_PRODUCTS_CATALOG_JSP;

public class GoToProductsCatalogPage implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request) {
        return CommandResult.createForwardResult(USER_PRODUCTS_CATALOG_JSP);
    }
}
