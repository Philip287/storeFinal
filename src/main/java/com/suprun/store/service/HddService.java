package com.suprun.store.service;

import com.suprun.store.entity.Hdd;
import com.suprun.store.exception.ServiceException;
import com.suprun.store.service.criteria.HddFilterCriteria;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.Optional;

/**
 * {@code HddService} interface provides functionality that allows to manipulate {@link Hdd}
 * entities without relying to exact database.
 *
 * @author Philip Suprun
 */
public interface HddService {
    /**
     * Find {@link Hdd} entity by its unique id.
     *
     * @param id unique id of pickup.
     * @return {@code Hdd} entity wrapped with {@link Optional}.
     * @throws ServiceException if an error occurred executing the method.
     */
    Optional<Hdd> findById(long id) throws ServiceException;

    /**
     * Insert values to create a new {@link Hdd} entity in the DB.
     *
     * @param name {@code Hdd} name.
     * @return {@code long} value that represents id of created entity.
     * @throws ServiceException if an error occurred executing the method.
     */
    long insert(String name) throws ServiceException;

    /**
     * Update a {@link Hdd} entity in the DB.
     *
     * @param hdd the {@code Hdd} entity.
     * @throws ServiceException if an error occurred executing the method.
     */
    void update(Hdd hdd) throws ServiceException;

    /**
     * Delete a {@link Hdd} entity with specified id from the data store.
     *
     * @param id unique id of the {@code Hdd} entity to delete.
     * @throws ServiceException if an error occurred executing the method.
     */
    void delete(long id) throws ServiceException;

    /**
     * Find {@link Hdd} entities that satisfy certain criteria.
     *
     * @param start lower bound index from which the result collection will start.
     * @param length of the result collection.
     * @param criteria instance of {@link HddFilterCriteria} that specifies a criteria for filtering.
     * @param keyword stored entities will be filtered by.
     * @return {@link Pair} of {@code long} value and {@link List} of {@code Hdd} entities
     * that represent count and collection of all found entities satisfied filter criteria and filter value
     * @throws ServiceException if an error occurred executing the method.
     */
    Pair<Long, List<Hdd>> filter(int start, int length, HddFilterCriteria criteria, String keyword)
            throws ServiceException;
}
