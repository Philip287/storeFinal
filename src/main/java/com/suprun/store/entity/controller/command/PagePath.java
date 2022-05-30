package com.suprun.store.entity.controller.command;

public class PagePath {
    // JSP paths
    public static final String INDEX_JSP = "/pages/common/index.jsp";
    public static final String LOGIN_JSP = "/pages/common/login.jsp";
    public static final String FORGOT_PASSWORD_JSP = "/pages/common/forgot_password.jsp";
    public static final String PASSWORD_CHANGE_JSP = "/pages/common/password_change.jsp";
    public static final String PROFILE_JSP = "/pages/common/profile.jsp";
    public static final String REGISTER_JSP = "/pages/common/register.jsp";

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

    // misc
    public static final String AMPERSAND = "&";
    public static final String EQUALS_SIGN = "=";

    private PagePath() {

    }
}
