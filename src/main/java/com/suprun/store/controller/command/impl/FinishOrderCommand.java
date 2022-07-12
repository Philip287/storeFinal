package com.suprun.store.controller.command.impl;

import com.suprun.store.controller.command.Command;
import com.suprun.store.controller.command.CommandResult;
import com.suprun.store.entity.Order;
import com.suprun.store.entity.User;
import com.suprun.store.exception.ServiceException;
import com.suprun.store.service.OrderService;
import com.suprun.store.service.UserService;
import com.suprun.store.service.impl.OrderServiceImpl;
import com.suprun.store.service.impl.UserServiceImpl;
import com.suprun.store.util.MailUtil;
import com.suprun.store.util.impl.MailUtilImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

import static com.suprun.store.controller.command.PagePath.ADMIN_ORDERS_URL;
import static com.suprun.store.controller.command.RequestAttribute.*;
import static jakarta.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
import static jakarta.servlet.http.HttpServletResponse.SC_NOT_FOUND;

public class FinishOrderCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private final OrderService orderService = OrderServiceImpl.getInstance();
    private final UserService userService = UserServiceImpl.getInstance();
    private final MailUtil mailUtil = MailUtilImpl.getInstance();

    @Override
    public CommandResult execute(HttpServletRequest request) {
        long entityId = Long.parseLong(request.getParameter(ENTITY_ID));
        long userId = Long.parseLong(request.getParameter(ID_USER));
        Order.OrderStatus orderStatus = Order.OrderStatus.valueOf(request.getParameter(ORDER_STATUS));

        try {
            Optional<Order> optionalOrder = orderService.findById(entityId);
            Optional<User> optionalUser = userService.findById(userId);
            if (optionalOrder.isPresent()) {
                Order order = optionalOrder.get();
                Order updateOrder = Order.builder().of(order)
                        .setOrderStatus(orderStatus)
                        .setUserId(userId)
                        .build();
                orderService.update(updateOrder);
                User user = optionalUser.get();
                mailUtil.sendOrderCompletedMail(user.getEmail(), order.getEntityId(),
                        request.getScheme(), request.getServerName());
                return CommandResult.createRedirectResult(ADMIN_ORDERS_URL);
            } else {
                return CommandResult.createErrorResult(SC_NOT_FOUND);
            }
        } catch (ServiceException e) {
            LOGGER.error("An error occurred during update order command execution", e);
            return CommandResult.createErrorResult(SC_INTERNAL_SERVER_ERROR);
        }
    }
}
