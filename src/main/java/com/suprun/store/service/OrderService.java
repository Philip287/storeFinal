package com.suprun.store.service;

import com.suprun.store.entity.Order;
import com.suprun.store.entity.Order.OrderStatus;
import com.suprun.store.exception.ServiceException;
import com.suprun.store.service.criteria.OrderFilterCriteria;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.Optional;

/**
 * {@code OrderService} interface provides functionality that allows to manipulate {@link Order}
 * entities without relying to exact database.
 *
 * @author Philip Suprun
 */
public interface OrderService {

    /**
     * Find {@link Order} entity by its unique id.
     *
     * @param id unique id of guitar.
     * @return {@code Order} entity wrapped with {@link Optional}.
     * @throws ServiceException if an error occurred executing the method.
     */
    Optional<Order> findById(long id) throws ServiceException;

    /**
     * Insert values to create a new {@link Order} entity in the DB.
     *
     * @param status {@code Order} status.
     * @param userId {@code Order} userId.
     * @return {@code long} value that represents id of created entity.
     * @throws ServiceException if an error occurred executing the method.
     */
    long insert(OrderStatus status, Long userId) throws ServiceException;

    /**
     * Update a {@link Order} entity in the DB.
     *
     * @param order the {@code Order} entity.
     * @throws ServiceException if an error occurred executing the method.
     */
    void update(Order order) throws ServiceException;

    /**
     * Delete a {@link Order} entity with specified id from the DB.
     *
     * @param id unique id of the {@code Order} entity to delete.
     * @throws ServiceException if an error occurred executing the method.
     */
    void delete(long id) throws ServiceException;

    /**
     * Find {@link Order} entities that satisfy certain criteria.
     *
     * @param start    lower bound index from which the result collection will start.
     * @param length   of the result collection.
     * @param criteria instance of {@link OrderFilterCriteria} that specifies a criteria for filtering.
     * @param keyword  stored entities will be filtered by.
     * @return {@link Pair} of {@code long} value and {@link List} of {@code Body} entities
     * that represent count and collection of all found entities satisfied filter criteria and filter value
     * @throws ServiceException if an error occurred executing the method.
     */
    Pair<Long, List<Order>> filter(int start, int length, OrderFilterCriteria criteria, String keyword)
            throws ServiceException;

}
