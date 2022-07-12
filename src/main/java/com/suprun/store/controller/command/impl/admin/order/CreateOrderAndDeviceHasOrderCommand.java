package com.suprun.store.controller.command.impl.admin.order;

import com.suprun.store.controller.command.Command;
import com.suprun.store.controller.command.CommandResult;
import com.suprun.store.entity.Card;
import com.suprun.store.entity.DeviceHasOrder;
import com.suprun.store.entity.Order.OrderStatus;
import com.suprun.store.exception.ServiceException;
import com.suprun.store.service.DeviceHasOrderService;
import com.suprun.store.service.OrderService;
import com.suprun.store.service.impl.DeviceHasOrderServiceImpl;
import com.suprun.store.service.impl.OrderServiceImpl;
import com.suprun.store.util.JsonParserUtil;
import com.suprun.store.util.impl.JsonParserUtilImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.List;

import static com.suprun.store.controller.command.PagePath.*;
import static com.suprun.store.controller.command.RequestAttribute.*;
import static jakarta.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;

public class CreateOrderAndDeviceHasOrderCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger();
    private final DeviceHasOrderService deviceHasOrderService = DeviceHasOrderServiceImpl.getInstance();
    private final OrderService orderService = OrderServiceImpl.getInstance();
    JsonParserUtil jsonParserUtil = JsonParserUtilImpl.getInstance();

    @Override
    public CommandResult execute(HttpServletRequest request) {
        Card card = jsonParserUtil.parse((request.getParameter(JSON_CARD)));
        long userId = card.getOrder().getUserId();


        try {
            Long orderId = orderService.insert(OrderStatus.ORDERED, userId);

            List<DeviceHasOrder> deviceHasOrdersList = card.getDeviceHasOrder();
            for (DeviceHasOrder element : deviceHasOrdersList) {
                long deviceId = element.getDeviceId();
                long number = element.getNumber();
                deviceHasOrderService.insert(deviceId, orderId, number);

            }
            return CommandResult.createRedirectResult(ADMIN_ORDER_URL);
        } catch (ServiceException e) {
            LOGGER.error("An error occurred during CreateOrderAndDeviceHasOrderCommand", e);
            return CommandResult.createErrorResult(SC_INTERNAL_SERVER_ERROR);
        }

    }

}

