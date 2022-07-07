package com.suprun.store.controller.command;


/**
 * {@code CommandType} enum stores elements that are later mapped to corresponding {@link Command} instances.
 *
 * @author Philip Suprun
 */
public enum CommandType {
    // GoTo commands
    GO_TO_INDEX_PAGE,
    GO_TO_LOGIN_PAGE,
    GO_TO_REGISTER_PAGE,
    GO_TO_TOKEN_SENT_PAGE,
    GO_TO_TOKEN_SUCCESS_PAGE,
    GO_TO_USERS_PAGE,
    GO_TO_CREATE_USER_PAGE,
    GO_TO_EDIT_USER_PAGE,
    GO_TO_PROFILE_PAGE,
    GO_TO_FORGOT_PASSWORD_PAGE,
    GO_TO_PASSWORD_CHANGE_PAGE,
    GO_TO_DEVICES_PAGE,
    GO_TO_DEVICE_PAGE,
    GO_TO_CREATE_DEVICE_PAGE,
    GO_TO_EDIT_DEVICE_PAGE,
    GO_TO_ORDERS_PAGE,
    GO_TO_CREATE_ORDER_PAGE,
    GO_TO_EDIT_ORDER_PAGE,
    GO_TO_PRODUCTS_CATALOG_PAGE,
    GO_TO_ORDER_PAGE,
    GO_TO_MY_ORDERS_PAGE,
    GO_TO_CARD_PAGE,

    // other commands
    REGISTER,
    LOGIN,
    LOGOUT,
    CONFIRM_EMAIL,
    GET_USERS,
    CREATE_USER,
    UPDATE_USER,
    DELETE_USER,
    SEND_CONFIRMATION_LINK,
    SEND_PASSWORD_CHANGE_LINK,
    PASSWORD_CHANGE,
    UPDATE_PROFILE,
    TAKE_ORDER,
    FINISH_ORDER,
    GET_DEVICES,
    CREATE_DEVICE,
    UPDATE_DEVICE,
    DELETE_DEVICE,
    GET_ORDERS,
    CREATE_ORDER_AND_DEVICE_HAS_ORDER,
    UPDATE_ORDER,
    DELETE_ORDER,
    GET_DEVICES_HAS_ORDERS,

}
