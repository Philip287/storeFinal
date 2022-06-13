package com.suprun.store.service;


import com.suprun.store.entity.DeviceHasOrder;
import com.suprun.store.exception.ServiceException;
import com.suprun.store.service.criteria.DeviceHasOrderFilterCriteria;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.Optional;

/**
 * {@code DeviceHasOrderService} interface provides functionality that allows to manipulate {@link DeviceHasOrder}
 * entities without relying to exact database.
 *
 * @author Philip Suprun
 */
public interface DeviceHasOrderService {

    /**
     * Find {@link DeviceHasOrder} entity by its id_order.
     *
     * @param id id of order.
     * @return {@code DeviceHasOrder} entity wrapped with {@link Optional}.
     * @throws ServiceException if an error occurred executing the method.
     */
    Optional<DeviceHasOrder> findById(long id) throws ServiceException;

    /**
     * Insert values to create a new {@link DeviceHasOrder} entity in the DB.
     *
     * @param deviceId {@code DeviceHasOrder} deviceId.
     * @param orderId  {@code DeviceHasOrder} orderId.
     * @param number   {@code DeviceHasOrder} number.
     * @return {@code long} value that represents id of created entity.
     * @throws ServiceException if an error occurred executing the method.
     */
    long insert(long deviceId, long orderId, long number) throws ServiceException;

    /**
     * Update a {@link DeviceHasOrder} entity in the DB.
     *
     * @param deviceHasOrder the {@code DeviceHasOrder} entity.
     * @throws ServiceException if an error occurred executing the method.
     */
    void update(DeviceHasOrder deviceHasOrder) throws ServiceException;

    /**
     * Delete a {@link DeviceHasOrder} entity with specified id from the DB.
     *
     * @param id unique id of the {@code DeviceHasOrder} entity to delete.
     * @throws ServiceException if an error occurred executing the method.
     */
    void delete(long id) throws ServiceException;

    /**
     * Find {@link DeviceHasOrder} entities that satisfy certain criteria.
     *
     * @param start    lower bound index from which the result collection will start.
     * @param length   of the result collection.
     * @param criteria instance of {@link DeviceHasOrderFilterCriteria} that specifies a criteria for filtering.
     * @param keyword  stored entities will be filtered by.
     * @return {@link Pair} of {@code long} value and {@link List} of {@code Body} entities
     * that represent count and collection of all found entities satisfied filter criteria and filter value
     * @throws ServiceException if an error occurred executing the method.
     */
    Pair<Long, List<DeviceHasOrder>> filter(int start, int length, DeviceHasOrderFilterCriteria criteria, String keyword)
            throws ServiceException;
}
