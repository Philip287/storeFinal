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
            System.out.println("commandParameter " + commandParameter); // FIXME: 06/07/2022
            Set<AppRole> appRoles = pair.getRight();
            AppRole currentUserRole = (AppRole) request.getSession().getAttribute(USER_ROLE);
            if (appRoles.contains(currentUserRole)) {
                CommandResult commandResult = pair.getLeft().execute(request);
                System.out.println("commandResult " + commandResult);// FIXME: 06/07/2022
                CommandResult.RouteType routeType = commandResult.getRouteType();
                System.out.println("routeType " + routeType); // FIXME: 06/07/2022

                switch (routeType) {
                    case FORWARD -> {
                        String forwardPath = commandResult.getRoute();
                        request.getRequestDispatcher(forwardPath).forward(request, response);
                        System.out.println("7. forwardPath " + forwardPath); // FIXME: 06/07/2022 
                    }
                    case REDIRECT -> {
                        String redirectUrl = commandResult.getRoute();
                        response.sendRedirect(request.getContextPath() + redirectUrl);
                        System.out.println("8. redirectUrl " + redirectUrl); // FIXME: 06/07/2022 
                    }
                    case ERROR -> {
                        int errorCode = commandResult.getErrorCode();
                        response.sendError(errorCode);
                        System.out.println("9. errorCode " + errorCode); // FIXME: 06/07/2022 
                    }
                    case JSON -> {
                        String jsonResponse = commandResult.getRoute();
                        response.getWriter().write(jsonResponse);
                        System.out.println("10. jsonResponse " + jsonResponse); // FIXME: 06/07/2022 
                    }
                    default -> {
                        System.out.println("11. default "); // FIXME: 06/07/2022 
                        LOGGER.error("Invalid route type: " + routeType.name());
                        response.sendError(SC_INTERNAL_SERVER_ERROR);

                    }
                }
            } else {
                response.sendError(SC_FORBIDDEN);
                System.out.println("12. else "); // FIXME: 06/07/2022 

            }
        } else {
            LOGGER.error("Specified command not found");
            response.sendError(SC_NOT_FOUND);
        }
    }
}
