package com.suprun.store.service;

import com.suprun.store.entity.Speaker;
import com.suprun.store.exception.ServiceException;
import com.suprun.store.service.criteria.SpeakerFilterCriteria;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.Optional;

/**
 * {@code SpeakerService} interface provides functionality that allows to manipulate {@link Speaker}
 * entities without relying to exact database.
 *
 * @author Philip Suprun
 */
public interface SpeakerService {

    /**
     * Find {@link Speaker} entity by its unique id.
     *
     * @param id unique id of pickup.
     * @return {@code Speaker} entity wrapped with {@link Optional}.
     * @throws ServiceException if an error occurred executing the method.
     */
    Optional<Speaker> findById(long id) throws ServiceException;

    /**
     * Insert values to create a new {@link Speaker} entity in the DB.
     *
     * @param name {@code Speaker} name.
     * @return {@code long} value that represents id of created entity.
     * @throws ServiceException if an error occurred executing the method.
     */
    long insert(String name) throws ServiceException;

    /**
     * Update a {@link Speaker} entity in the DB.
     *
     * @param speaker the {@code Speaker} entity.
     * @throws ServiceException if an error occurred executing the method.
     */
    void update(Speaker speaker) throws ServiceException;

    /**
     * Delete a {@link Speaker} entity with specified id from the data store.
     *
     * @param id unique id of the {@code Speaker} entity to delete.
     * @throws ServiceException if an error occurred executing the method.
     */
    void delete(long id) throws ServiceException;

    /**
     * Find {@link Speaker} entities that satisfy certain criteria.
     *
     * @param start    lower bound index from which the result collection will start.
     * @param length   of the result collection.
     * @param criteria instance of {@link SpeakerFilterCriteria} that specifies a criteria for filtering.
     * @param keyword  stored entities will be filtered by.
     * @return {@link Pair} of {@code long} value and {@link List} of {@code Speaker} entities
     * that represent count and collection of all found entities satisfied filter criteria and filter value
     * @throws ServiceException if an error occurred executing the method.
     */
    Pair<Long, List<Speaker>> filter(int start, int length, SpeakerFilterCriteria criteria, String keyword)
            throws ServiceException;
}