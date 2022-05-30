package com.suprun.store.service;

import com.suprun.store.entity.Monitor;
import com.suprun.store.exception.ServiceException;
import com.suprun.store.service.criteria.MonitorFilterCriteria;
import org.apache.commons.lang3.tuple.Pair;
import java.util.List;
import java.util.Optional;

/**
 * {@code MonitorService} interface provides functionality that allows to manipulate {@link Monitor}
 * entities without relying to exact database.
 *
 * @author Philip Suprun
 */
public interface MonitorService {
    /**
     * Find {@link Monitor} entity by its unique id.
     *
     * @param id unique id of pickup.
     * @return {@code Monitor} entity wrapped with {@link Optional}.
     * @throws ServiceException if an error occurred executing the method.
     */
    Optional<Monitor> findById(long id) throws ServiceException;

    /**
     * Insert values to create a new {@link Monitor} entity in the DB.
     *
     * @param name {@code Monitor} name.
     * @return {@code long} value that represents id of created entity.
     * @throws ServiceException if an error occurred executing the method.
     */
    long insert(String name) throws ServiceException;

    /**
     * Update a {@link Monitor} entity in the DB.
     *
     * @param monitor the {@code Monitor} entity.
     * @throws ServiceException if an error occurred executing the method.
     */
    void update(Monitor monitor) throws ServiceException;

    /**
     * Delete a {@link Monitor} entity with specified id from the data store.
     *
     * @param id unique id of the {@code Monitor} entity to delete.
     * @throws ServiceException if an error occurred executing the method.
     */
    void delete(long id) throws ServiceException;

    /**
     * Find {@link Monitor} entities that satisfy certain criteria.
     *
     * @param start    lower bound index from which the result collection will start.
     * @param length   of the result collection.
     * @param criteria instance of {@link MonitorFilterCriteria} that specifies a criteria for filtering.
     * @param keyword  stored entities will be filtered by.
     * @return {@link Pair} of {@code long} value and {@link List} of {@code Monitor} entities
     * that represent count and collection of all found entities satisfied filter criteria and filter value
     * @throws ServiceException if an error occurred executing the method.
     */
    Pair<Long, List<Monitor>> filter(int start, int length, MonitorFilterCriteria criteria, String keyword)
            throws ServiceException;
}
