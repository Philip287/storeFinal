package com.suprun.store.service.criteria;

/**
 * {@code HullFilterCriteria} enum is used to choose a filter strategy
 * in {@link com.suprun.store.service.HullService#filter} method.
 *
 * @author Philip Suprun
 */
public enum HullFilterCriteria {
    NONE,
    ID,
    NAME,
    PRICE,
    PICTURE_PATH,
    DESCRIPTION
}
