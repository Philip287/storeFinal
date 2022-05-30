package com.suprun.store.service.criteria;

/**
 * {@code UserFilterCriteria} enum is used to choose a filter strategy
 * in {@link com.suprun.store.service.UserService#filter} method.
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
