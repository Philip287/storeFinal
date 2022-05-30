package com.suprun.store.service;

import com.suprun.store.entity.Ram;
import com.suprun.store.exception.ServiceException;
import com.suprun.store.service.criteria.RamFilterCriteria;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.Optional;

/**
 * {@code RamService} interface provides functionality that allows to manipulate {@link Ram}
 * entities without relying to exact database.
 *
 * @author Philip Suprun
 */
public interface RamService {

    /**
     * Find {@link Ram} entity by its unique id.
     *
     * @param id unique id of pickup.
     * @return {@code Ram} entity wrapped with {@link Optional}.
     * @throws ServiceException if an error occurred executing the method.
     */
    Optional<Ram> findById(long id) throws ServiceException;

    /**
     * Insert values to create a new {@link Ram} entity in the DB.
     *
     * @param name {@code Ram} name.
     * @return {@code long} value that represents id of created entity.
     * @throws ServiceException if an error occurred executing the method.
     */
    long insert(String name) throws ServiceException;

    /**
     * Update a {@link Ram} entity in the DB.
     *
     * @param ram the {@code Ram} entity.
     * @throws ServiceException if an error occurred executing the method.
     */
    void update(Ram ram) throws ServiceException;

    /**
     * Delete a {@link Ram} entity with specified id from the data store.
     *
     * @param id unique id of the {@code Ram} entity to delete.
     * @throws ServiceException if an error occurred executing the method.
     */
    void delete(long id) throws ServiceException;

    /**
     * Find {@link Ram} entities that satisfy certain criteria.
     *
     * @param start    lower bound index from which the result collection will start.
     * @param length   of the result collection.
     * @param criteria instance of {@link RamFilterCriteria} that specifies a criteria for filtering.
     * @param keyword  stored entities will be filtered by.
     * @return {@link Pair} of {@code long} value and {@link List} of {@code Ram} entities
     * that represent count and collection of all found entities satisfied filter criteria and filter value
     * @throws ServiceException if an error occurred executing the method.
     */
    Pair<Long, List<Ram>> filter(int start, int length, RamFilterCriteria criteria, String keyword)
            throws ServiceException;
}
