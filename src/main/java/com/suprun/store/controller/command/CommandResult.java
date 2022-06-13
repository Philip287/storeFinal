package com.suprun.store.controller.command;

public class CommandResult {

    public enum RouteType {
        FORWARD,
        REDIRECT,
        JSON,
        ERROR
    }

    private final String route;
    private final Integer errorCode;
    private final RouteType routeType;

    private CommandResult(String route, Integer errorCode, RouteType routeType) {
        this.route = route;
        this.errorCode = errorCode;
        this.routeType = routeType;
    }

    public static CommandResult createForwardResult(String route) {
        return new CommandResult(route, null, RouteType.FORWARD);
    }

    public static CommandResult createRedirectResult(String route) {
        return new CommandResult(route, null, RouteType.REDIRECT);
    }

    public static CommandResult createJsonResult(String route) {
        return new CommandResult(route, null, RouteType.JSON);
    }

    public static CommandResult createErrorResult(Integer errorCode) {
        return new CommandResult(null, errorCode, RouteType.ERROR);
    }

    public String getRoute() {
        return route;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public RouteType getRouteType() {
        return routeType;
    }
}
