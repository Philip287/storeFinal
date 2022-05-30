package com.suprun.store.entity.controller.command;

import com.suprun.store.entity.controller.command.impl.*;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

import static com.suprun.store.entity.controller.command.AppRole.*;

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
        commandMap.put(CommandType.GO_TO_INDEX_PAGE, Pair.of(new GoToIndexPageCommand(),
                new HashSet<>(Arrays.asList(GUEST, NOT_CONFIRMED, CLIENT, MANAGER, ADMIN))));
        commandMap.put(CommandType.GO_TO_LOGIN_PAGE, Pair.of(new GoToLoginPageCommand(),
                new HashSet<>(Collections.singletonList(GUEST))));
        commandMap.put(CommandType.GO_TO_REGISTER_PAGE, Pair.of(new GoToRegisterPageCommand(),
                new HashSet<>(Collections.singletonList(GUEST))));
        // other commands
        commandMap.put(CommandType.REGISTER, Pair.of(new RegisterCommand(),
                new HashSet<>(Collections.singletonList(GUEST))));
        commandMap.put(CommandType.LOGIN, Pair.of(new LoginCommand(),
                new HashSet<>(Collections.singletonList(GUEST))));
        commandMap.put(CommandType.LOGOUT, Pair.of(new LogoutCommand(),
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