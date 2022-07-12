package com.suprun.store.dao;

/**
 * {@code TableColumn} class contains constant strings that represent DB tables column names.
 *
 * @author Philip Suprun
 */
public final class TableColum {
    private TableColum() {

    }

    // users TABLE
    public static final String USER_ID = "user_id";
    public static final String USER_EMAIL = "email";
    public static final String USER_LOGIN = "login";
    public static final String USER_PASSWORD_HASH = "password_hash";
    public static final String USER_SALT = "salt";
    public static final String USER_ROLE = "role";
    public static final String USER_STATUS = "status";

    //devices TABLE
    public static final String DEVICE_ID = "device_id";
    public static final String DEVICE_NAME = "name";
    public static final String DEVICE_PRICE = "price";
    public static final String DEVICE_PICTURE_PATH = "picture_path";
    public static final String DEVICE_DESCRIPTION = "description";
    public static final String DEVICE_TYPE = "type";

    //orders TABLE
    public static final String ORDER_ID = "order_id";
    public static final String ID_USER = "id_user";
    public static final String ORDER_STATUS = "order_status";

    //devises_has_orders table
    public static final String ID_DEVICE = "id_device";
    public static final String ID_ORDER = "id_order";
    public static final String NUMBER = "number";

}
