package com.suprun.store.service.criteria;

/**
 * {@code PowerSupplyFilterCriteria} enum is used to choose a filter strategy
 * in {@link com.suprun.store.service.PowerSupplyService#filter} method.
 *
 * @author Philip Suprun
 */
public enum PowerSupplyFilterCriteria {
    NONE,
    ID,
    NAME,
    PRICE,
    PICTURE_PATH,
    DESCRIPTION
}
