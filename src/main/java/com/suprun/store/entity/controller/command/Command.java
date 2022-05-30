package com.suprun.store.entity.controller.command;

import jakarta.servlet.http.HttpServletRequest;

/**
 * {@code Command} interface implementors are mapped to URLs
 * and allowed application roles in {@link CommandProvider} class.
 * This approach lets to move all business logic from
 * {@link com.suprun.store.entity.controller.ControllerServlet} class to separate classes.
 *
 * @author Philip Suprun
 */
@FunctionalInterface
public interface Command {
    /**
     * Execute command.
     *
     * @param request is an instance of {@link HttpServletRequest} from controller.
     * @return {@link CommandResult} instance.
     */
    CommandResult execute(HttpServletRequest request);
}
