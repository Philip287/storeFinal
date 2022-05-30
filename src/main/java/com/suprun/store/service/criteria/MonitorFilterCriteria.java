package com.suprun.store.service.criteria;

/**
 * {@code MonitorFilterCriteria} enum is used to choose a filter strategy
 * in {@link com.suprun.store.service.MonitorService#filter} method.
 *
 * @author Philip Suprun
 */
public enum MonitorFilterCriteria {
    NONE,
    ID,
    NAME,
    PRICE,
    PICTURE_PATH,
    DESCRIPTION
}
