package com.suprun.store.service.criteria;

/**
 * {@code MouseFilterCriteria} enum is used to choose a filter strategy
 * in {@link com.suprun.store.service.MouseService#filter} method.
 *
 * @author Philip Suprun
 */
public enum MouseFilterCriteria {
    NONE,
    ID,
    NAME,
    PRICE,
    PICTURE_PATH,
    DESCRIPTION
}
