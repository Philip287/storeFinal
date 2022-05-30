package com.suprun.store.service.criteria;

/**
 * {@code ProcessorFilterCriteria} enum is used to choose a filter strategy
 * in {@link com.suprun.store.service.ProcessorService#filter} method.
 *
 * @author Philip Suprun
 */
public enum ProcessorFilterCriteria {
    NONE,
    ID,
    NAME,
    PRICE,
    PICTURE_PATH,
    DESCRIPTION
}
