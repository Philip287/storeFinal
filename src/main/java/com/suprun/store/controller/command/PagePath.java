package com.suprun.store.controller.command;

public class PagePath {
    // JSP paths
    public static final String INDEX_JSP = "/pages/common/index.jsp";
    public static final String LOGIN_JSP = "/pages/common/login.jsp";

    // URLs
    public static final String LOGIN_URL = "/controller?command=go_to_login_page";
    public static final String INDEX_URL = "/controller?command=go_to_index_page";

    // misc
    public static final String AMPERSAND = "&";
    public static final String EQUALS_SIGN = "=";

    private PagePath() {

    }
}
