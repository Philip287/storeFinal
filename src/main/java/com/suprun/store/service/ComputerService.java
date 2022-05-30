package com.suprun.store.service;

import com.suprun.store.entity.Computer;
import com.suprun.store.entity.Computer.OrderStatus;
import com.suprun.store.exception.ServiceException;
import com.suprun.store.service.criteria.ComputerFilterCriteria;
import org.apache.commons.lang3.tuple.Pair;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * {@code ComputerService} interface provides functionality that allows to manipulate {@link Computer}
 * entities without relying to exact database.
 *
 * @author Philip Suprun
 */
public interface ComputerService {
    /**
     * Find {@link Computer} entity by its unique id.
     *
     * @param id unique id of computer.
     * @return {@code Computer} entity wrapped with {@link Optional}.
     * @throws ServiceException if an error occurred executing the method.
     */
    Optional<Computer> findById(long id) throws ServiceException;

    /**
     * Insert values to create a new {@link Computer} entity in the DB.
     *
     * @param status        {@code Computer} status.
     * @param name          {@code Computer} name.
     * @param totalCoast    {@code Computer} totalCoast.
     * @param userId        {@code Computer} userId.
     * @param processorId   {@code Computer} processorId.
     * @param videoCardId   {@code Computer} videoCardId.
     * @param motherboardId {@code Computer} motherboardId.
     * @param ramId         {@code Computer} ramId.
     * @param powerSupplyId {@code Computer} powerSupplyId.
     * @param keyboardId    {@code Computer} keyboardId.
     * @param monitorId     {@code Computer} monitorId.
     * @param mouseId       {@code Computer} mouseId.
     * @param hullId        {@code Computer} hullId.
     * @param hddId         {@code Computer} hddId.
     * @param speakerId     {@code Computer} speakerId.
     * @return {@code long} value that represents id of created entity.
     * @throws ServiceException if an error occurred executing the method.
     */
    long insert(OrderStatus status, String name, BigDecimal totalCoast, long userId, long processorId,
                long videoCardId, long motherboardId, long ramId, long powerSupplyId, long keyboardId, long monitorId,
                long mouseId, long hullId, long hddId, long speakerId) throws ServiceException;

    /**
     * Update a {@link Computer} entity in the DB.
     *
     * @param computer the {@code Computer} entity.
     * @throws ServiceException if an error occurred executing the method.
     */
    void update(Computer computer) throws ServiceException;

    /**
     * Delete a {@link Computer} entity with specified id from the DB.
     *
     * @param id unique id of the {@code Computer} entity to delete.
     * @throws ServiceException if an error occurred executing the method.
     */
    void delete(long id) throws ServiceException;

    /**
     * Find {@link Computer} entities that satisfy certain criteria.
     *
     * @param start    lower bound index from which the result collection will start.
     * @param length   of the result collection.
     * @param criteria instance of {@link ComputerFilterCriteria} that specifies a criteria for filtering.
     * @param keyword  stored entities will be filtered by.
     * @return {@link Pair} of {@code long} value and {@link List} of {@code Body} entities
     * that represent count and collection of all found entities satisfied filter criteria and filter value
     * @throws ServiceException if an error occurred executing the method.
     */
    Pair<Long, List<Computer>> filter(int start, int length, ComputerFilterCriteria criteria, String keyword)
            throws ServiceException;

    //// FIXME: 26/05/2022

    /**
     * Find {@link Computer} entities that satisfy certain criteria with active {@link OrderStatus}.
     *
     * @param start    lower bound index from which the result collection will start.
     * @param length   of the result collection.
     * @param criteria instance of {@link ComputerFilterCriteria} that specifies a criteria for filtering.
     * @param keyword  stored entities will be filtered by.
     * @return {@link Pair} of {@code long} value and {@link List} of {@code Body} entities
     * that represent count and collection of all found entities satisfied filter criteria and filter value
     * @throws ServiceException if an error occurred executing the method.
     */
    Pair<Long, List<Computer>> filterForActiveOrder(int start, int length, ComputerFilterCriteria criteria, String keyword)
            throws ServiceException;

    /**
     * Find {@link Computer} entities that satisfy certain criteria and have the same user id.
     *
     * @param start   lower bound index from which the result collection will start.
     * @param length  of the result collection.
     * @param keyword stored entities will be filtered by.
     * @param userId  id stored entities will be filtered by.
     * @return {@link Pair} of {@code long} value and {@link List} of {@code Computer} entities
     * that represent count and collection of all found entities satisfied filter criteria and filter value
     * @throws ServiceException if an error occurred executing the method.
     */
    Pair<Long, List<Computer>> filterByNameForUser(int start, int length, String keyword, String userId)
            throws ServiceException;
}
