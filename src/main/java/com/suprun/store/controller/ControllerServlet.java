package com.suprun.store.controller;

import com.suprun.store.controller.command.AppRole;
import com.suprun.store.controller.command.Command;
import com.suprun.store.controller.command.CommandProvider;
import com.suprun.store.controller.command.CommandResult;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;

import static com.suprun.store.controller.command.RequestAttribute.COMMAND;
import static com.suprun.store.controller.command.SessionAttribute.USER_ROLE;
import static jakarta.servlet.http.HttpServletResponse.*;

/**
 * {@code ControllerServlet} class is a subclass of {@link HttpServlet} class.
 * It processes all requests to {@code /controller} url after filtering.
 *
 * @author Philip Suprun
 */
@WebServlet(urlPatterns = "/controller")
@MultipartConfig(
        fileSizeThreshold = 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 10
)
public class ControllerServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String commandParameter = request.getParameter(COMMAND);
        Optional<Pair<Command, Set<AppRole>>> optionalPair = CommandProvider.getCommand(commandParameter);

        if (optionalPair.isPresent()) {
            Pair<Command, Set<AppRole>> pair = optionalPair.get();
            Set<AppRole> appRoles = pair.getRight();

            AppRole currentUserRole = (AppRole) request.getSession().getAttribute(USER_ROLE);

            if (appRoles.contains(currentUserRole)) {
                CommandResult commandResult = pair.getLeft().execute(request);
                CommandResult.RouteType routeType = commandResult.getRouteType();

                switch (routeType) {
                    case FORWARD -> {
                        String forwardPath = commandResult.getRoute();
                        request.getRequestDispatcher(forwardPath).forward(request, response);
                    }
                    case REDIRECT -> {
                        String redirectUrl = commandResult.getRoute();
                        response.sendRedirect(redirectUrl);
                    }
                    case ERROR -> {
                        int errorCode = commandResult.getErrorCode();
                        response.sendError(errorCode);
                    }
                    case JSON -> {
                        String jsonResponse = commandResult.getRoute();
                        response.getWriter().write(jsonResponse);
                    }
                    default -> {
                        LOGGER.error("Invalid route type: " + routeType.name());
                        response.sendError(SC_INTERNAL_SERVER_ERROR);
                    }
                }
            } else {
                response.sendError(SC_FORBIDDEN);
            }
        } else {
            LOGGER.error("Specified command not found");
            response.sendError(SC_NOT_FOUND);
        }
    }
}
