package com.suprun.store.controller.command.impl.admin.device;

import com.suprun.store.controller.command.Command;
import com.suprun.store.controller.command.CommandResult;
import com.suprun.store.exception.ServiceException;
import com.suprun.store.service.DeviceService;
import com.suprun.store.service.impl.DeviceServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.suprun.store.controller.command.PagePath.ADMIN_DEVICES_URL;
import static com.suprun.store.controller.command.RequestAttribute.ENTITY_ID;
import static jakarta.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;

public class DeleteDeviceCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger();
    private final DeviceService deviceService = DeviceServiceImpl.getInstance();

    @Override
    public CommandResult execute(HttpServletRequest request) {
        long entityId = Long.parseLong(request.getParameter(ENTITY_ID));

        try {
            deviceService.delete(entityId);
            return CommandResult.createRedirectResult(ADMIN_DEVICES_URL);
        } catch (ServiceException e) {
            LOGGER.error("An error occurred during delete device command execution", e);
            return CommandResult.createErrorResult(SC_INTERNAL_SERVER_ERROR);
        }
    }
}
