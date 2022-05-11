package com.suprun.store.controller.command;

import com.suprun.store.controller.command.impl.GoToIndexPageCommand;
import com.suprun.store.controller.command.impl.LoginCommand;
import com.suprun.store.controller.command.impl.LogoutCommand;
import com.suprun.store.controller.command.impl.RegisterCommand;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

import static com.suprun.store.controller.command.AppRole.*;

public class CommandProvider {
    public static final Logger logger = LogManager.getLogger();
    private static final Map<CommandType, Pair<Command, Set<AppRole>>> commandMap = new EnumMap<>(CommandType.class);

    static {
        commandMap.put(CommandType.GO_TO_INDEX_PAGE, Pair.of(new GoToIndexPageCommand(),
                new HashSet<>(Arrays.asList(GUEST, NOT_CONFIRMED, CLIENT, MANAGER, ADMIN))));

        // other commands
        commandMap.put(CommandType.REGISTER, Pair.of(new RegisterCommand(),
                new HashSet<>(Collections.singletonList(GUEST))));
        commandMap.put(CommandType.LOGIN, Pair.of(new LoginCommand(),
                new HashSet<>(Collections.singletonList(GUEST))));
        commandMap.put(CommandType.LOGOUT, Pair.of(new LogoutCommand(),
                new HashSet<>(Arrays.asList(NOT_CONFIRMED, CLIENT, MANAGER, ADMIN))));
    }

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