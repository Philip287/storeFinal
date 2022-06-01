package com.suprun.store.service.criteria;

import com.suprun.store.service.HardDiskService;

/**
 * {@code RamFilterCriteria} enum is used to choose a filter strategy
 * in {@link HardDiskService#filter} method.
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
