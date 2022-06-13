package com.suprun.store.controller.command;

public class PagePath {
    // JSP paths
    public static final String INDEX_JSP = "/pages/common/index.jsp";
    public static final String LOGIN_JSP = "/pages/common/login.jsp";
    public static final String FORGOT_PASSWORD_JSP = "/pages/common/forgot_password.jsp";
    public static final String PASSWORD_CHANGE_JSP = "/pages/common/password_change.jsp";
    public static final String PROFILE_JSP = "/pages/common/profile.jsp";
    public static final String REGISTER_JSP = "/pages/common/register.jsp";
    public static final String ADMIN_USERS_JSP = "/pages/admin/user/users.jsp";
    public static final String ADMIN_EDIT_USER_JSP = "/pages/admin/user/edit_user.jsp";
    public static final String ADMIN_CREATE_USER_JSP = "/pages/admin/user/create_user.jsp";
    public static final String ADMIN_CREATE_DEVICE_JSP = "/pages/admin/device/create_device.jsp";
    public static final String ADMIN_EDIT_DEVICE_JSP = "/pages/admin/device/edit_device.jsp";
    public static final String ADMIN_DEVICES_JSP = "/pages/admin/devices/devices.jsp";
    public static final String ADMIN_ORDER_JSP = "/pages/admin/order/orders.jsp";
    public static final String ADMIN_CREATE_ORDER_JSP = "/pages/admin/order/create_order.jsp";
    public static final String ADMIN_EDIT_ORDER_JSP = "/pages/admin/Order/edit_order.jsp";

    // URLs
    public static final String LOGIN_URL = "/controller?command=go_to_login_page";
    public static final String INDEX_URL = "/controller?command=go_to_index_page";
    public static final String REGISTER_URL = "/controller?command=go_to_register_page";
    public static final String PASSWORD_CHANGE_URL = "/controller?command=go_to_password_change_page";
    public static final String FORGOT_PASSWORD_URL = "/controller?command=go_to_forgot_password_page";
    public static final String PROFILE_URL = "/controller?command=go_to_profile_page";
    public static final String ADMIN_EDIT_USER_URL = "/controller?command=go_to_edit_user_page";
    public static final String ADMIN_USERS_URL = "/controller?command=go_to_users_page";
    public static final String ADMIN_CREATE_USER_URL = "/controller?command=go_to_create_user_page";
    public static final String ADMIN_DEVICES_URL = "/controller?command=go_to_devices_page";
    public static final String ADMIN_CREATE_DEVICE_URL = "/controller?command=go_to_create_device_page";
    public static final String ADMIN_EDIT_DEVICE_URL = "/controller?command=go_to_edit_device_page";
    public static final String ADMIN_ORDER_URL = "/controller?command=go_to_order_page";
    public static final String ADMIN_CREATE_ORDER_URL = "/controller?command=go_to_create_order_page";
    public static final String ADMIN_ORDERS_URL = "/controller?command=go_to_orders_page";


    // misc
    public static final String AMPERSAND = "&";
    public static final String EQUALS_SIGN = "=";

    private PagePath() {

    }
}
