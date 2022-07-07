package com.suprun.store.controller.command.impl.admin.device;

import com.suprun.store.controller.command.Command;
import com.suprun.store.controller.command.CommandResult;
import com.suprun.store.entity.Device;
import com.suprun.store.exception.ServiceException;
import com.suprun.store.service.DeviceService;
import com.suprun.store.service.impl.DeviceServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

import static com.suprun.store.controller.command.PagePath.ADMIN_EDIT_DEVICE_JSP;
import static com.suprun.store.controller.command.RequestAttribute.DEVICE;
import static com.suprun.store.controller.command.RequestAttribute.ENTITY_ID;
import static jakarta.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
import static jakarta.servlet.http.HttpServletResponse.SC_NOT_FOUND;


public class GoToEditDevicePageCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger();
    private final DeviceService deviceService = DeviceServiceImpl.getInstance();


    @Override
    public CommandResult execute(HttpServletRequest request) {
        long entityId = Long.parseLong(request.getParameter(ENTITY_ID));

        try {
            Optional<Device> optionalDevice = deviceService.findById(entityId);
            if (optionalDevice.isPresent()) {
                Device device = optionalDevice.get();
                request.setAttribute(DEVICE, device);
                return CommandResult.createForwardResult(ADMIN_EDIT_DEVICE_JSP);
            } else {
                LOGGER.error("Requested device not found, id = ", entityId);
                return CommandResult.createErrorResult(SC_NOT_FOUND);
            }
        } catch (ServiceException e) {
            LOGGER.error("An error occurred during go to edit device page command execution", e);
            return CommandResult.createErrorResult(SC_INTERNAL_SERVER_ERROR);
        }
    }
}
