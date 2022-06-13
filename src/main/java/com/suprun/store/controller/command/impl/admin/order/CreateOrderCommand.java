package com.suprun.store.controller.command.impl.admin.order;

import com.suprun.store.controller.command.Command;
import com.suprun.store.controller.command.CommandResult;
import com.suprun.store.entity.Order.OrderStatus;
import com.suprun.store.exception.ServiceException;
import com.suprun.store.service.OrderService;
import com.suprun.store.service.impl.OrderServiceImpl;
import com.suprun.store.service.validator.Validator;
import com.suprun.store.service.validator.impl.NameValidator;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

import static com.suprun.store.controller.command.PagePath.*;
import static com.suprun.store.controller.command.RequestAttribute.*;
import static jakarta.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;

public class CreateOrderCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger();
    private final OrderService orderService = OrderServiceImpl.getInstance();

    @Override
    public CommandResult execute(HttpServletRequest request) {

        String name = request.getParameter(NAME);
        OrderStatus orderStatus = OrderStatus.valueOf(request.getParameter(ORDER_STATUS));
        long userId = Long.parseLong(request.getParameter(ID_USER));
        try {
            orderService.insert(orderStatus, userId);
            return CommandResult.createRedirectResult(ADMIN_ORDER_URL);

        } catch (ServiceException e) {
            LOGGER.error("An error occurred during create order command execution", e);
            return CommandResult.createErrorResult(SC_INTERNAL_SERVER_ERROR);
        }
    }
}
