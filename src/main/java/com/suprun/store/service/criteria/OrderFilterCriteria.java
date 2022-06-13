package com.suprun.store.service.criteria;


/**
 * {@code OrderFilterCriteria} enum is used to choose a filter strategy
 * in {@link com.suprun.store.service.OrderService#filter} method.
 * * {@link com.suprun.store.service.OrderService#filterForActiveOrder} methods.
 *
 * @author Philip Suprun
 */
public enum OrderFilterCriteria {
    NONE,
    ID,
    ORDER_STATUS,
    ID_USER
}
