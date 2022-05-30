package com.suprun.store.service;

import com.suprun.store.entity.VideoCard;
import com.suprun.store.exception.ServiceException;
import com.suprun.store.service.criteria.VideoCardFilterCriteria;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.Optional;

/**
 * {@code VideoCardService} interface provides functionality that allows to manipulate {@link VideoCard}
 * entities without relying to exact database.
 *
 * @author Philip Suprun
 */
public interface VideoCardService {

    /**
     * Find {@link VideoCard} entity by its unique id.
     *
     * @param id unique id of pickup.
     * @return {@code VideoCard} entity wrapped with {@link Optional}.
     * @throws ServiceException if an error occurred executing the method.
     */
    Optional<VideoCard> findById(long id) throws ServiceException;

    /**
     * Insert values to create a new {@link VideoCard} entity in the DB.
     *
     * @param name {@code VideoCard} name.
     * @return {@code long} value that represents id of created entity.
     * @throws ServiceException if an error occurred executing the method.
     */
    long insert(String name) throws ServiceException;

    /**
     * Update a {@link VideoCard} entity in the DB.
     *
     * @param videoCard the {@code VideoCard} entity.
     * @throws ServiceException if an error occurred executing the method.
     */
    void update(VideoCard videoCard) throws ServiceException;

    /**
     * Delete a {@link VideoCard} entity with specified id from the data store.
     *
     * @param id unique id of the {@code VideoCard} entity to delete.
     * @throws ServiceException if an error occurred executing the method.
     */
    void delete(long id) throws ServiceException;

    /**
     * Find {@link VideoCard} entities that satisfy certain criteria.
     *
     * @param start    lower bound index from which the result collection will start.
     * @param length   of the result collection.
     * @param criteria instance of {@link VideoCardFilterCriteria} that specifies a criteria for filtering.
     * @param keyword  stored entities will be filtered by.
     * @return {@link Pair} of {@code long} value and {@link List} of {@code VideoCard} entities
     * that represent count and collection of all found entities satisfied filter criteria and filter value
     * @throws ServiceException if an error occurred executing the method.
     */
    Pair<Long, List<VideoCard>> filter(int start, int length, VideoCardFilterCriteria criteria, String keyword)
            throws ServiceException;
}
