package com.suprun.store.service;

import com.suprun.store.entity.PowerSupply;
import com.suprun.store.exception.ServiceException;
import com.suprun.store.service.criteria.PowerSupplyFilterCriteria;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.Optional;

/**
 * {@code PowerSupplyService} interface provides functionality that allows to manipulate {@link PowerSupply}
 * entities without relying to exact database.
 *
 * @author Philip Suprun
 */
public interface PowerSupplyService {
    /**
     * Find {@link PowerSupply} entity by its unique id.
     *
     * @param id unique id of pickup.
     * @return {@code PowerSupply} entity wrapped with {@link Optional}.
     * @throws ServiceException if an error occurred executing the method.
     */
    Optional<PowerSupply> findById(long id) throws ServiceException;

    /**
     * Insert values to create a new {@link PowerSupply} entity in the DB.
     *
     * @param name {@code PowerSupply} name.
     * @return {@code long} value that represents id of created entity.
     * @throws ServiceException if an error occurred executing the method.
     */
    long insert(String name) throws ServiceException;

    /**
     * Update a {@link PowerSupply} entity in the DB.
     *
     * @param powerSupply the {@code PowerSupply} entity.
     * @throws ServiceException if an error occurred executing the method.
     */
    void update(PowerSupply powerSupply) throws ServiceException;

    /**
     * Delete a {@link PowerSupply} entity with specified id from the data store.
     *
     * @param id unique id of the {@code PowerSupply} entity to delete.
     * @throws ServiceException if an error occurred executing the method.
     */
    void delete(long id) throws ServiceException;

    /**
     * Find {@link PowerSupply} entities that satisfy certain criteria.
     *
     * @param start    lower bound index from which the result collection will start.
     * @param length   of the result collection.
     * @param criteria instance of {@link PowerSupplyFilterCriteria} that specifies a criteria for filtering.
     * @param keyword  stored entities will be filtered by.
     * @return {@link Pair} of {@code long} value and {@link List} of {@code PowerSupply} entities
     * that represent count and collection of all found entities satisfied filter criteria and filter value
     * @throws ServiceException if an error occurred executing the method.
     */
    Pair<Long, List<PowerSupply>> filter(int start, int length, PowerSupplyFilterCriteria criteria, String keyword)
            throws ServiceException;
}
