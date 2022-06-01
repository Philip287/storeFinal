package com.suprun.store.dao;

/**
 * {@code TableColumn} class contains constant strings that represent DB tables column names.
 *
 * @author Philip Suprun
 */
public final class TableColum {
    private void TableColumn() {

    }

    // users TABLE
    public static final String USER_ID = "user_id";
    public static final String USER_EMAIL = "email";
    public static final String USER_LOGIN = "login";
    public static final String USER_PASSWORD_HASH = "password_hash";
    public static final String USER_SALT = "salt";
    public static final String USER_ROLE = "role";
    public static final String USER_STATUS = "status";

    //computer TABLE
    public static final String COMPUTER_ID = "computer_id";
    public static final String ID_USER = "id_user";
    public static final String TOTAL_COST = "total_cost";
    public static final String ORDER_STATUS = "order_status";
    public static final String ID_PROCESSOR = "id_processor";
    public static final String ID_VIDEO_CARD = "id_video_card";
    public static final String ID_MOTHERBOARD = "id_motherboard";
    public static final String ID_RAM = "id-ram";
    public static final String ID_POWER_SUPPLY = "id_power_supply";
    public static final String ID_KEYBOARD = "id_keyboard";
    public static final String ID_MONITOR = "id_monitor";
    public static final String ID_MOUSE = "id_mouse";
    public static final String ID_HULL = "id_hull";
    public static final String ID_HDD = "id_hdd";
    public static final String ID_SPEAKER = "id_speaker";
    public static final String COMPUTER_NAME = "name";


    // processor TABLE
    public static final String PROCESSOR_ID = "processor_id";
    public static final String PROCESSOR_NAME = "name";
    public static final String PROCESSOR_PRICE = "price";
    public static final String PROCESSOR_PICTURE_PATH = "picture_path";
    public static final String PROCESSOR_DESCRIPTION = "description";

    // videoCards TABLE
    public static final String VIDEO_CARD_ID = "video_card_id";
    public static final String VIDEO_CARD_NAME = "name";
    public static final String VIDEO_CARD_PRICE = "price";
    public static final String VIDEO_CARD_PICTURE_PATH = "picture_path";
    public static final String VIDEO_CARD_DESCRIPTION = "description";

    //motherboards TABLE
    public static final String MOTHERBOARD_ID = "motherboard_id";
    public static final String MOTHERBOARD_NAME = "name";
    public static final String MOTHERBOARD_PRICE = "price";
    public static final String MOTHERBOARD_PICTURE_PATH = "picture_path";
    public static final String MOTHERBOARD_DESCRIPTION = "description";

    //rams TABLE
    public static final String RAM_ID = "ram_id";
    public static final String RAM_NAME = "name";
    public static final String RAM_PRICE = "price";
    public static final String RAM_PICTURE_PATH = "picture_path";
    public static final String RAM_DESCRIPTION = "description";

    //powerSupply TABLE
    public static final String POWER_SUPPLY_ID = "power_supply_id";
    public static final String POWER_SUPPLY_NAME = "name";
    public static final String POWER_SUPPLY_PRICE = "price";
    public static final String POWER_SUPPLY_PICTURE_PATH = "picture_path";
    public static final String POWER_SUPPLY_DESCRIPTION = "description";

    //keyboards TABLE
    public static final String KEYBOARD_ID = "keyboard_id";
    public static final String KEYBOARD_NAME = "name";
    public static final String KEYBOARD_PRICE = "price";
    public static final String KEYBOARD_PICTURE_PATH = "picture_path";
    public static final String KEYBOARD_DESCRIPTION = "description";

    //monitors TABLE
    public static final String MONITOR_ID = "monitor_id";
    public static final String MONITOR_NAME = "name";
    public static final String MONITOR_PRICE = "price";
    public static final String MONITOR_PICTURE_PATH = "picture_path";
    public static final String MONITOR_DESCRIPTION = "description";

    //speakers TABLE
    public static final String SPEAKER_ID = "speaker_id";
    public static final String SPEAKER_NAME = "name";
    public static final String SPEAKER_PRICE = "price";
    public static final String SPEAKER_PICTURE_PATH = "picture_path";
    public static final String SPEAKER_DESCRIPTION = "description";

    //mouses TABLE
    public static final String MOUSE_ID = "mouse_id";
    public static final String MOUSE_NAME = "name";
    public static final String MOUSE_PRICE = "price";
    public static final String MOUSE_PICTURE_PATH = "picture_path";
    public static final String MOUSE_DESCRIPTION = "description";

    //hardDisks TABLE
    public static final String HDD_ID = "hdd_id";
    public static final String HDD_NAME = "name";
    public static final String HDD_PRICE = "price";
    public static final String HDD_PICTURE_PATH = "picture_path";
    public static final String HDD_DESCRIPTION = "description";

    //hulls TABLE
    public static final String HULL_ID = "hull_id";
    public static final String HULL_NAME = "name";
    public static final String HULL_PRICE = "price";
    public static final String HULL_PICTURE_PATH = "picture_path";
    public static final String HULL_DESCRIPTION = "description";
}
