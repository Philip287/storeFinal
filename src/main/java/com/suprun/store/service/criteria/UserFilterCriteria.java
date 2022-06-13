package com.suprun.store.service.criteria;

import com.suprun.store.service.UserService;

/**
 * {@code UserFilterCriteria} enum is used to choose a filter strategy
 * in {@link UserService#filter} method.
 *
 * @author Philip Suprun
 */

public enum UserFilterCriteria {
    NONE,
    ID,
    EMAIL,
    LOGIN,
    ROLE,
    STATUS
}
