package com.suprun.store.controller.command;

import com.suprun.store.controller.command.impl.*;
import com.suprun.store.controller.command.impl.admin.user.*;
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
    public static final Logger logger = LogManager.getLogger();
    private static final Map<CommandType, Pair<Command, Set<AppRole>>> commandMap = new EnumMap<>(CommandType.class);

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
                new HashSet<>(Collections.singletonList(GUEST))));
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
                new HashSet<>(Arrays.asList(CLIENT, MANAGER, ADMIN))));
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
            logger.error("Unknown command type", e);
            return Optional.empty();
        }
        return Optional.ofNullable(commandMap.get(type));
    }

}