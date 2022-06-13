package com.suprun.store.controller.command.impl.admin.device;

import com.suprun.store.controller.command.Command;
import com.suprun.store.controller.command.CommandResult;
import com.suprun.store.entity.Device.Type;
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
import jakarta.servlet.http.Part;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.math.BigDecimal;

import static com.suprun.store.controller.command.PagePath.*;
import static com.suprun.store.controller.command.RequestAttribute.*;
import static jakarta.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;

public class CreateDeviceCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private final Validator<String> nameValidator = NameValidator.getInstance();
    private final Validator<Part> imagePartValidator = ImagePathValidator.getInstance();
    private final ImageUploadUtil imageUploadUtil = ImageUploadUtilImpl.getInstance();
    private final DeviceService deviceService = DeviceServiceImpl.getInstance();

    @Override
    public CommandResult execute(HttpServletRequest request) {
        String name = request.getParameter(NAME);
        BigDecimal price = BigDecimal.valueOf(Long.parseLong(request.getParameter(PRICE)));
        String description = request.getParameter(DESCRIPTION);
        Type type = Type.valueOf(request.getParameter(TYPE));
        try {
            Part part = request.getPart(PICTURE_PATH);

            boolean valid = nameValidator.validate(name)
                    && imagePartValidator.validate(part);

            if (valid) {
                String picturePath = imageUploadUtil.uploadImage(part);

                deviceService.insert(name, price, picturePath, description, type);
                return CommandResult.createRedirectResult(ADMIN_DEVICES_URL);
            } else {
                String redirectUrl = ADMIN_CREATE_DEVICE_URL + AMPERSAND +
                        VALIDATION_ERROR + EQUALS_SIGN + true;
                return CommandResult.createRedirectResult(redirectUrl);
            }

        } catch (ServiceException e) {
            LOGGER.error("An error occurred during create device command execution", e);
            return CommandResult.createErrorResult(SC_INTERNAL_SERVER_ERROR);
        } catch (ServletException | IOException e) {
            LOGGER.error("An error occurred during uploading file", e);
            return CommandResult.createErrorResult(SC_INTERNAL_SERVER_ERROR);
        }
    }
}
