package com.suprun.store.controller.command.impl.admin.device;

import com.suprun.store.controller.command.Command;
import com.suprun.store.controller.command.CommandResult;
import com.suprun.store.entity.Device;
import com.suprun.store.exception.ServiceException;
import com.suprun.store.service.DeviceService;
import com.suprun.store.service.impl.DeviceServiceImpl;
import com.suprun.store.service.validator.Validator;
import com.suprun.store.service.validator.impl.ImagePathValidator;
import com.suprun.store.service.validator.impl.NameValidator;
import com.suprun.store.util.ImageUploadUtil;
import com.suprun.store.util.impl.ImageUploadUtilImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Optional;

import static com.suprun.store.controller.command.PagePath.*;
import static com.suprun.store.controller.command.RequestAttribute.*;
import static com.suprun.store.controller.command.RequestAttribute.TYPE;
import static com.suprun.store.entity.Device.builder;
import static jakarta.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
import static jakarta.servlet.http.HttpServletResponse.SC_NOT_FOUND;

public class UpdateDeviceCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger();
    private final DeviceService deviceService = DeviceServiceImpl.getInstance();
    private final Validator<String> nameValidator = NameValidator.getInstance();
    private final ImageUploadUtil imageUploadUtil = ImageUploadUtilImpl.getInstance();
    private final Validator<Part> imagePartValidator = ImagePathValidator.getInstance();

    @Override
    public CommandResult execute(HttpServletRequest request) {
        String name = request.getParameter(NAME);
        BigDecimal price = BigDecimal.valueOf(Long.parseLong(request.getParameter(PRICE)));
        String description = request.getParameter(DESCRIPTION);
        Device.Type type = Device.Type.valueOf(request.getParameter(TYPE));
        long entityId = Long.parseLong(request.getParameter(ENTITY_ID));

        try {
            Optional<Device> optionalDevice = deviceService.findById(entityId);
            if (optionalDevice.isPresent()) {
                Part part = request.getPart(PICTURE_PATH);
                Device device = optionalDevice.get();
                String previousName = device.getName();
                boolean valid = StringUtils.equals(name, previousName) || nameValidator.validate(name)
                        && imagePartValidator.validate(part);
                return getCommandResult(name, price, description, type, entityId, part, device, valid);
            } else {
                LOGGER.error("Requested device not found, id = " + entityId);
                return CommandResult.createErrorResult(SC_NOT_FOUND);
            }
        } catch (ServiceException e) {
            LOGGER.error("An error occurred during update device command execution", e);
            return CommandResult.createErrorResult(SC_INTERNAL_SERVER_ERROR);
        } catch (ServletException | IOException e) {
            LOGGER.error("An error occurred during uploading file", e);
            return CommandResult.createErrorResult(SC_INTERNAL_SERVER_ERROR);
        }

    }

    private CommandResult getCommandResult(String name, BigDecimal price, String description, Device.Type type, long entityId, Part part, Device device, boolean valid) throws ServiceException {
        if (valid) {
            String picturePath = imageUploadUtil.uploadImage(part);
            Device updateDevice;
            if (imageUploadUtil.isDefaultPicturePath(picturePath)) {
                updateDevice = builder().of(device)
                        .setName(name)
                        .setPrice(price)
                        .setDescription(description)
                        .setType(type)
                        .build();
            } else {
                updateDevice = builder().of(device)
                        .setName(name)
                        .setPrice(price)
                        .setDescription(description)
                        .setPicturePath(picturePath)
                        .setType(type)
                        .build();
            }
            deviceService.update(updateDevice);
            return CommandResult.createRedirectResult(ADMIN_DEVICES_URL);
        } else {
            String redirectUrl = ADMIN_EDIT_DEVICE_URL
                    + AMPERSAND + ENTITY_ID + EQUALS_SIGN + entityId
                    + AMPERSAND + VALIDATION_ERROR + EQUALS_SIGN + true;
            return CommandResult.createRedirectResult(redirectUrl);
        }
    }
}
