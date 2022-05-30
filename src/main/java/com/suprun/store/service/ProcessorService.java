package com.suprun.store.service;

import com.suprun.store.entity.Processor;
import com.suprun.store.exception.ServiceException;
import com.suprun.store.service.criteria.ProcessorFilterCriteria;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.Optional;

/**
 * {@code Processor} interface provides functionality that allows to manipulate {@link Processor}
 * entities without relying to exact database.
 *
 * @author Philip Suprun
 */
public interface ProcessorService {

    /**
     * Find {@link Processor} entity by its unique id.
     *
     * @param id unique id of pickup.
     * @return {@code Processor} entity wrapped with {@link Optional}.
     * @throws ServiceException if an error occurred executing the method.
     */
    Optional<Processor> findById(long id) throws ServiceException;

    /**
     * Insert values to create a new {@link Processor} entity in the DB.
     *
     * @param name {@code Processor} name.
     * @return {@code long} value that represents id of created entity.
     * @throws ServiceException if an error occurred executing the method.
     */
    long insert(String name) throws ServiceException;

    /**
     * Update a {@link Processor} entity in the DB.
     *
     * @param processor the {@code Processor} entity.
     * @throws ServiceException if an error occurred executing the method.
     */
    void update(Processor processor) throws ServiceException;

    /**
     * Delete a {@link Processor} entity with specified id from the data store.
     *
     * @param id unique id of the {@code Processor} entity to delete.
     * @throws ServiceException if an error occurred executing the method.
     */
    void delete(long id) throws ServiceException;

    /**
     * Find {@link Processor} entities that satisfy certain criteria.
     *
     * @param start    lower bound index from which the result collection will start.
     * @param length   of the result collection.
     * @param criteria instance of {@link ProcessorFilterCriteria} that specifies a criteria for filtering.
     * @param keyword  stored entities will be filtered by.
     * @return {@link Pair} of {@code long} value and {@link List} of {@code Processor} entities
     * that represent count and collection of all found entities satisfied filter criteria and filter value
     * @throws ServiceException if an error occurred executing the method.
     */
    Pair<Long, List<Processor>> filter(int start, int length, ProcessorFilterCriteria criteria, String keyword)
            throws ServiceException;
}
