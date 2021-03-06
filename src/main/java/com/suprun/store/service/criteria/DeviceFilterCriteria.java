package com.suprun.store.service.criteria;

/**
 * {@code DeviceFilterCriteria} enum is used to choose a filter strategy
 * in {@link com.suprun.store.service.DeviceService#filter}
 *
 * @author Philip Suprun
 */
public enum DeviceFilterCriteria {
    NONE,
    NAME,
    PRICE,
    PICTURE_PATH,
    DESCRIPTION,
    TYPE,
    ID
}
