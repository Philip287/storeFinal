package com.suprun.store.service;

import com.suprun.store.entity.Device;
import com.suprun.store.entity.Device.Type;
import com.suprun.store.exception.ServiceException;
import com.suprun.store.service.criteria.DeviceFilterCriteria;
import org.apache.commons.lang3.tuple.Pair;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * {@code DeviceService} interface provides functionality that allows to manipulate {@link Device}
 * entities without relying to exact database.
 *
 * @author Philip Suprun
 */
public interface DeviceService {

    /**
     * Find {@link Device} entity by its unique id.
     *
     * @param id unique id of device.
     * @return {@code Guitar} entity wrapped with {@link Optional}.
     * @throws ServiceException if an error occurred executing the method.
     */
    Optional<Device> findById(long id) throws ServiceException;

    /**
     * Insert values to create a new {@link Device} entity in the DB.
     *
     * @param name   {@code Device} name.
     * @param price  {@code Device} price.
     * @param picturePath  {@code Device} picturePath.
     * @param description {@code Device} description.
     * @param type {@code Device} type.
     * @return {@code long} value that represents id of created entity.
     * @throws ServiceException if an error occurred executing the method.
     */
    long insert(String name, BigDecimal price, String picturePath, String description, Type type) throws ServiceException;

    /**
     * Update a {@link Device} entity in the DB.
     *
     * @param device the {@code Device} entity.
     * @throws ServiceException if an error occurred executing the method.
     */
    void update(Device device) throws ServiceException;

    /**
     * Delete a {@link Device} entity with specified id from the DB.
     *
     * @param id unique id of the {@code Device} entity to delete.
     * @throws ServiceException if an error occurred executing the method.
     */
    void delete(long id) throws ServiceException;

    /**
     * Find {@link Device} entities that satisfy certain criteria.
     *
     * @param start    lower bound index from which the result collection will start.
     * @param length   of the result collection.
     * @param criteria instance of {@link DeviceFilterCriteria} that specifies a criteria for filtering.
     * @param keyword  stored entities will be filtered by.
     * @return {@link Pair} of {@code long} value and {@link List} of {@code Body} entities
     * that represent count and collection of all found entities satisfied filter criteria and filter value
     * @throws ServiceException if an error occurred executing the method.
     */
    Pair<Long, List<Device>> filter(int start, int length, DeviceFilterCriteria criteria, String keyword)
            throws ServiceException;
}
