package com.suprun.store.service.criteria;

/**
 * {@code KeyboardFilterCriteria} enum is used to choose a filter strategy
 * in {@link com.suprun.store.service.KeyboardService#filter} method.
 * @author Philip Suprun
 */
public enum KeyboardFilterCriteria {
    NONE,
    ID,
    NAME,
    PRICE,
    PICTURE_PATH,
    DESCRIPTION
}
