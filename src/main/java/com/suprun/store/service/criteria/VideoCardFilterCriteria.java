package com.suprun.store.service.criteria;

/**
 * {@code VideoCardFilterCriteria} enum is used to choose a filter strategy
 * in {@link com.suprun.store.service.VideoCardService#filter} method.
 *
 * @author Philip Suprun
 */
public enum VideoCardFilterCriteria {
    NONE,
    ID,
    NAME,
    PRICE,
    PICTURE_PATH,
    DESCRIPTION
}
