package com.suprun.store.controller.command.impl.admin.order;

import com.suprun.store.controller.command.Command;
import com.suprun.store.controller.command.CommandResult;
import com.suprun.store.entity.DeviceHasOrder;
import com.suprun.store.entity.Order;
import com.suprun.store.exception.ServiceException;
import com.suprun.store.service.DeviceHasOrderService;
import com.suprun.store.service.OrderService;
import com.suprun.store.service.impl.DeviceHasOrderServiceImpl;
import com.suprun.store.service.impl.OrderServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

import static com.suprun.store.controller.command.PagePath.ADMIN_EDIT_ORDER_JSP;
import static com.suprun.store.controller.command.RequestAttribute.*;
import static jakarta.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
import static jakarta.servlet.http.HttpServletResponse.SC_NOT_FOUND;

public class GoToEditOrderPageCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger();
    private final OrderService orderService = OrderServiceImpl.getInstance();
    private final DeviceHasOrderService deviceHasOrderService = DeviceHasOrderServiceImpl.getInstance();


    @Override
    public CommandResult execute(HttpServletRequest request) {
        long entityId = Long.parseLong(request.getParameter(ENTITY_ID));

        try {
            Optional<DeviceHasOrder> optionalDeviceHasOrder = deviceHasOrderService.findById(entityId);

            Optional<Order> optionalOrder = orderService.findById(entityId);
            if (optionalOrder.isPresent() || optionalDeviceHasOrder.isPresent()) {
                Order order = optionalOrder.get();
                request.setAttribute(ORDER, order);
                DeviceHasOrder deviceHasOrder = optionalDeviceHasOrder.get();
                request.setAttribute(DEVICE_HAS_ORDER, deviceHasOrder);
                return CommandResult.createForwardResult(ADMIN_EDIT_ORDER_JSP);
            } else {
                LOGGER.error("Requested order not found, id = " + entityId);
                return CommandResult.createErrorResult(SC_NOT_FOUND);
            }
        } catch (ServiceException e) {
            LOGGER.error("An error occurred during go to edit order page command execution", e);
            return CommandResult.createErrorResult(SC_INTERNAL_SERVER_ERROR);
        }
    }
}
