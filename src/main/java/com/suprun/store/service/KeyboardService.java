package com.suprun.store.service;

import com.suprun.store.entity.Keyboard;
import com.suprun.store.exception.ServiceException;
import com.suprun.store.service.criteria.KeyboardFilterCriteria;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.Optional;

/**
 * {@code KeyboardService} interface provides functionality that allows to manipulate {@link Keyboard}
 * entities without relying to exact database.
 *
 * @author Philip Suprun
 */
public interface KeyboardService {
    /**
     * Find {@link Keyboard} entity by its unique id.
     *
     * @param id unique id of pickup.
     * @return {@code Keyboard} entity wrapped with {@link Optional}.
     * @throws ServiceException if an error occurred executing the method.
     */
    Optional<Keyboard> findById(long id) throws ServiceException;

    /**
     * Insert values to create a new {@link Keyboard} entity in the DB.
     *
     * @param name {@code Keyboard} name.
     * @return {@code long} value that represents id of created entity.
     * @throws ServiceException if an error occurred executing the method.
     */
    long insert(String name) throws ServiceException;

    /**
     * Update a {@link Keyboard} entity in the DB.
     *
     * @param keyboard the {@code Keyboard} entity.
     * @throws ServiceException if an error occurred executing the method.
     */
    void update(Keyboard keyboard) throws ServiceException;

    /**
     * Delete a {@link Keyboard} entity with specified id from the data store.
     *
     * @param id unique id of the {@code Keyboard} entity to delete.
     * @throws ServiceException if an error occurred executing the method.
     */
    void delete(long id) throws ServiceException;

    /**
     * Find {@link Keyboard} entities that satisfy certain criteria.
     *
     * @param start    lower bound index from which the result collection will start.
     * @param length   of the result collection.
     * @param criteria instance of {@link KeyboardFilterCriteria} that specifies a criteria for filtering.
     * @param keyword  stored entities will be filtered by.
     * @return {@link Pair} of {@code long} value and {@link List} of {@code Keyboard} entities
     * that represent count and collection of all found entities satisfied filter criteria and filter value
     * @throws ServiceException if an error occurred executing the method.
     */
    Pair<Long, List<Keyboard>> filter(int start, int length, KeyboardFilterCriteria criteria, String keyword)
            throws ServiceException;
}
