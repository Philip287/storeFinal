package com.suprun.store.service.criteria;

/**
 * {@code RamFilterCriteria} enum is used to choose a filter strategy
 * in {@link com.suprun.store.service.HddService#filter} method.
 *
 * @author Philip Suprun
 */
public enum RamFilterCriteria {
    NONE,
    ID,
    NAME,
    PRICE,
    PICTURE_PATH,
    DESCRIPTION
}
