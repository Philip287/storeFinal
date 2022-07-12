package com.suprun.store.controller.command;

import com.suprun.store.controller.command.impl.*;
import com.suprun.store.controller.command.impl.admin.device.*;

import com.suprun.store.controller.command.impl.admin.order.*;
import com.suprun.store.controller.command.impl.admin.user.*;
import com.suprun.store.controller.command.impl.ajax.GetDevicesCommand;
import com.suprun.store.controller.command.impl.ajax.GetDevicesHasOrdersCommand;
import com.suprun.store.controller.command.impl.ajax.GetOrdersCommand;
import com.suprun.store.controller.command.impl.ajax.GetUsersCommand;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

import static com.suprun.store.controller.command.AppRole.*;

/**
 * {@code CommandProvider} class contains all mappings between URLs and {@link Command} instances.
 * Also it stores set of {@link AppRole} enum elements as a part of
 * {@link Pair} with {@link Command} instances describing which roles can access specified URL command.
 *
 * @author Philip Suprun
 */
public class CommandProvider {
    public static final Logger LOGGER = LogManager.getLogger();
    private static final Map<CommandType, Pair<Command, Set<AppRole>>> commandMap = new EnumMap<>(CommandType.class);

    private CommandProvider(){

    }

    static {
        // GoTo commands
        commandMap.put(CommandType.GO_TO_INDEX_PAGE, Pair.of(new GoToIndexPageCommand(),
                new HashSet<>(Arrays.asList(GUEST, NOT_CONFIRMED, CLIENT, MANAGER, ADMIN))));
        commandMap.put(CommandType.GO_TO_LOGIN_PAGE, Pair.of(new GoToLoginPageCommand(),
                new HashSet<>(Collections.singletonList(GUEST))));
        commandMap.put(CommandType.GO_TO_REGISTER_PAGE, Pair.of(new GoToRegisterPageCommand(),
                new HashSet<>(Collections.singletonList(GUEST))));
        commandMap.put(CommandType.GO_TO_USERS_PAGE, Pair.of(new GoToUsersPageCommand(),
                new HashSet<>(Collections.singletonList(ADMIN))));
        commandMap.put(CommandType.GO_TO_CREATE_USER_PAGE, Pair.of(new GoToCreateUserPageCommand(),
                new HashSet<>(Collections.singletonList(ADMIN))));
        commandMap.put(CommandType.GO_TO_EDIT_USER_PAGE, Pair.of(new GoToEditUserPageCommand(),
                new HashSet<>(Collections.singletonList(ADMIN))));
        commandMap.put(CommandType.GO_TO_PROFILE_PAGE, Pair.of(new GoToProfilePageCommand(),
                new HashSet<>(Arrays.asList(NOT_CONFIRMED, CLIENT, MANAGER, ADMIN))));
        commandMap.put(CommandType.GO_TO_FORGOT_PASSWORD_PAGE, Pair.of(new GoToForgotPasswordPageCommand(),
                new HashSet<>(Collections.singletonList(GUEST))));
        commandMap.put(CommandType.GO_TO_PASSWORD_CHANGE_PAGE, Pair.of(new GoToPasswordChangePageCommand(),
                new HashSet<>(Arrays.asList(GUEST, CLIENT, MANAGER, ADMIN))));
        commandMap.put(CommandType.GO_TO_DEVICES_PAGE, Pair.of(new GoToDevicesPageCommand(),
                new HashSet<>(Arrays.asList(CLIENT, MANAGER, ADMIN))));
        commandMap.put(CommandType.GO_TO_PRODUCTS_CATALOG_PAGE, Pair.of(new GoToProductsCatalogPage(),
                new HashSet<>(Arrays.asList(CLIENT, MANAGER, ADMIN))));
        commandMap.put(CommandType.GO_TO_CREATE_DEVICE_PAGE, Pair.of(new GoToCreateDevicePageCommand(),
                new HashSet<>(Arrays.asList(MANAGER, ADMIN))));
        commandMap.put(CommandType.GO_TO_DEVICE_PAGE, Pair.of(new GoToDevicePageCommand(),
                new HashSet<>(Arrays.asList(CLIENT, MANAGER, ADMIN))));
        commandMap.put(CommandType.GO_TO_EDIT_DEVICE_PAGE, Pair.of(new GoToEditDevicePageCommand(),
                new HashSet<>(Arrays.asList(MANAGER, ADMIN))));
        commandMap.put(CommandType.GO_TO_ORDERS_PAGE, Pair.of(new GoToOrdersPageCommand(),
                new HashSet<>(Arrays.asList(MANAGER, ADMIN))));
        commandMap.put(CommandType.GO_TO_ORDER_PAGE, Pair.of(new GoToOrderPageCommand(),
                new HashSet<>(Arrays.asList(CLIENT, MANAGER, ADMIN))));
        commandMap.put(CommandType.GO_TO_MY_ORDERS_PAGE, Pair.of(new GoToMyOrdersPageCommand(),
                new HashSet<>(Arrays.asList(CLIENT, MANAGER, ADMIN))));
        commandMap.put(CommandType.GO_TO_CARD_PAGE, Pair.of(new GoToCardPageCommand(),
                new HashSet<>(Arrays.asList(CLIENT, MANAGER, ADMIN))));

        // other commands
        commandMap.put(CommandType.REGISTER, Pair.of(new RegisterCommand(),
                new HashSet<>(Collections.singletonList(GUEST))));
        commandMap.put(CommandType.LOGIN, Pair.of(new LoginCommand(),
                new HashSet<>(Collections.singletonList(GUEST))));
        commandMap.put(CommandType.LOGOUT, Pair.of(new LogoutCommand(),
                new HashSet<>(Arrays.asList(AppRole.NOT_CONFIRMED, CLIENT, AppRole.MANAGER, ADMIN))));
        commandMap.put(CommandType.CONFIRM_EMAIL, Pair.of(new ConfirmEmailCommand(),
                new HashSet<>(Arrays.asList(GUEST, NOT_CONFIRMED, CLIENT, MANAGER, ADMIN))));
        commandMap.put(CommandType.GET_USERS, Pair.of(new GetUsersCommand(),
                new HashSet<>(Arrays.asList(MANAGER, ADMIN))));
        commandMap.put(CommandType.CREATE_USER, Pair.of(new CreateUserCommand(),
                new HashSet<>(Collections.singletonList(ADMIN))));
        commandMap.put(CommandType.UPDATE_USER, Pair.of(new UpdateUserCommand(),
                new HashSet<>(Collections.singletonList(ADMIN))));
        commandMap.put(CommandType.DELETE_USER, Pair.of(new DeleteUserCommand(),
                new HashSet<>(Collections.singletonList(ADMIN))));
        commandMap.put(CommandType.SEND_CONFIRMATION_LINK, Pair.of(new SendConfirmationLinkCommand(),
                new HashSet<>(Arrays.asList(GUEST, NOT_CONFIRMED))));
        commandMap.put(CommandType.SEND_PASSWORD_CHANGE_LINK, Pair.of(new SendPasswordChangeLinkCommand(),
                new HashSet<>(Arrays.asList(GUEST, NOT_CONFIRMED))));
        commandMap.put(CommandType.PASSWORD_CHANGE, Pair.of(new PasswordChangeCommand(),
                new HashSet<>(Arrays.asList(GUEST, NOT_CONFIRMED))));
        commandMap.put(CommandType.UPDATE_PROFILE, Pair.of(new UpdateProfileCommand(),
                new HashSet<>(Arrays.asList(NOT_CONFIRMED, CLIENT, MANAGER, ADMIN))));
        commandMap.put(CommandType.GET_DEVICES, Pair.of(new GetDevicesCommand(),
                new HashSet<>(Arrays.asList(CLIENT, MANAGER, ADMIN))));
        commandMap.put(CommandType.CREATE_DEVICE, Pair.of(new CreateDeviceCommand(),
                new HashSet<>(Arrays.asList(MANAGER, ADMIN))));
        commandMap.put(CommandType.UPDATE_DEVICE, Pair.of(new UpdateDeviceCommand(),
                new HashSet<>(Arrays.asList(MANAGER, ADMIN))));
        commandMap.put(CommandType.DELETE_DEVICE, Pair.of(new DeleteDeviceCommand(),
                new HashSet<>(Arrays.asList(MANAGER, ADMIN))));
        commandMap.put(CommandType.GET_ORDERS, Pair.of(new GetOrdersCommand(),
                new HashSet<>(Arrays.asList(CLIENT, MANAGER, ADMIN))));
        commandMap.put(CommandType.CREATE_ORDER_AND_DEVICE_HAS_ORDER, Pair.of(new CreateOrderAndDeviceHasOrderCommand(),
                new HashSet<>(Arrays.asList(CLIENT, MANAGER, ADMIN))));
        commandMap.put(CommandType.UPDATE_ORDER, Pair.of(new UpdateOrderCommand(),
                new HashSet<>(Arrays.asList(MANAGER, ADMIN))));
        commandMap.put(CommandType.DELETE_ORDER, Pair.of(new DeleteOrderCommand(),
                new HashSet<>(Arrays.asList(MANAGER, ADMIN))));
        commandMap.put(CommandType.GET_DEVICES_HAS_ORDERS, Pair.of(new GetDevicesHasOrdersCommand(),
                new HashSet<>(Arrays.asList(CLIENT, MANAGER, ADMIN))));
        commandMap.put(CommandType.FINISH_ORDER, Pair.of(new FinishOrderCommand(),
                new HashSet<>(Arrays.asList(MANAGER, ADMIN))));

    }

    /**
     * Get command by command attribute value from URL.
     *
     * @param command is a value of command attribute.
     * @return {@link Pair} of {@link Command} instance and set of {@link AppRole} enum elements.
     */
    public static Optional<Pair<Command, Set<AppRole>>> getCommand(String command) {
        CommandType type;
        try {
            type = CommandType.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            LOGGER.error("Unknown command type ", e);
            return Optional.empty();
        }
        return Optional.ofNullable(commandMap.get(type));
    }

}