package com.suprun.store.service;

import com.suprun.store.entity.Hull;
import com.suprun.store.exception.ServiceException;
import com.suprun.store.service.criteria.HullFilterCriteria;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.Optional;

/**
 * {@code HullService} interface provides functionality that allows to manipulate {@link Hull}
 * entities without relying to exact database.
 *
 * @author Philip Suprun
 */
public interface HullService {
    /**
     * Find {@link Hull} entity by its unique id.
     *
     * @param id unique id of pickup.
     * @return {@code Hull} entity wrapped with {@link Optional}.
     * @throws ServiceException if an error occurred executing the method.
     */
    Optional<Hull> findById(long id) throws ServiceException;

    /**
     * Insert values to create a new {@link Hull} entity in the DB.
     *
     * @param name {@code Hull} name.
     * @return {@code long} value that represents id of created entity.
     * @throws ServiceException if an error occurred executing the method.
     */
    long insert(String name) throws ServiceException;

    /**
     * Update a {@link Hull} entity in the DB.
     *
     * @param hull the {@code Hull} entity.
     * @throws ServiceException if an error occurred executing the method.
     */
    void update(Hull hull) throws ServiceException;

    /**
     * Delete a {@link Hull} entity with specified id from the data store.
     *
     * @param id unique id of the {@code Hull} entity to delete.
     * @throws ServiceException if an error occurred executing the method.
     */
    void delete(long id) throws ServiceException;

    /**
     * Find {@link Hull} entities that satisfy certain criteria.
     *
     * @param start    lower bound index from which the result collection will start.
     * @param length   of the result collection.
     * @param criteria instance of {@link HullFilterCriteria} that specifies a criteria for filtering.
     * @param keyword  stored entities will be filtered by.
     * @return {@link Pair} of {@code long} value and {@link List} of {@code Hull} entities
     * that represent count and collection of all found entities satisfied filter criteria and filter value
     * @throws ServiceException if an error occurred executing the method.
     */
    Pair<Long, List<Hull>> filter(int start, int length, HullFilterCriteria criteria, String keyword)
            throws ServiceException;
}
