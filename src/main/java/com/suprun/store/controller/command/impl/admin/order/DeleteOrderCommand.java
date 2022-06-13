package com.suprun.store.controller.command.impl.admin.order;

import com.suprun.store.controller.command.Command;
import com.suprun.store.controller.command.CommandResult;
import com.suprun.store.exception.ServiceException;
import com.suprun.store.service.OrderService;
import com.suprun.store.service.impl.OrderServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.suprun.store.controller.command.PagePath.ADMIN_ORDER_URL;
import static com.suprun.store.controller.command.RequestAttribute.ENTITY_ID;
import static jakarta.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;

public class DeleteOrderCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger();
    private final OrderService orderService = OrderServiceImpl.getInstance();

    @Override
    public CommandResult execute(HttpServletRequest request) {
        Long entityId = Long.parseLong(request.getParameter(ENTITY_ID));

        try {
            orderService.delete(entityId);
            return CommandResult.createRedirectResult(ADMIN_ORDER_URL);
        } catch (ServiceException e) {
            LOGGER.error("An error occurred during delete body command execution", e);
            return CommandResult.createErrorResult(SC_INTERNAL_SERVER_ERROR);
        }
    }
}
