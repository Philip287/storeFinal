package com.suprun.store.service.criteria;

/**
 * {@code OrderFilterCriteria} enum is used to choose a filter strategy
 * in {@link com.suprun.store.service.DeviceHasOrderService#filter} method.
 *
 * @author Philip Suprun
 */
public enum DeviceHasOrderFilterCriteria {
    NONE,
    ID_DEVICE,
    ID_ORDER,
    ID,
    NUMBER
}
