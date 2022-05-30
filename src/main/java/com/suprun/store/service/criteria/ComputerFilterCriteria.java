package com.suprun.store.service.criteria;

/**
 * {@code ComputerFilterCriteria} enum is used to choose a filter strategy
 * in {@link com.suprun.store.service.ComputerService#filter} and
 * {@link com.suprun.store.service.ComputerService#filterForActiveOrder} methods.
 *
 * @author Philip Suprun
 */
public enum ComputerFilterCriteria {
    NONE,
    USER_ID,
    ID,
    NAME,
    ORDER_STATUS,
    TOTAL_COST,
    PROCESSOR_ID,
    VIDEO_CARD_ID,
    MOTHERBOARD_ID,
    RAM_ID,
    POWER_SUPPLY_ID,
    KEYBOARD_ID,
    MONITOR_ID,
    MOUSE_ID,
    HULL_ID,
    HDD_ID,
    SPEAKER_ID
}
