package com.suprun.store.dao;

import com.suprun.store.entity.AbstractEntity;
import com.suprun.store.entity.DeviceHasOrder;
import com.suprun.store.exception.DaoException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static com.suprun.store.dao.TableColum.*;
import static com.suprun.store.entity.DeviceHasOrder.builder;

/**
 * {@code DeviceHasOrderDao} interface extends {@link BaseDao}.
 * It provides means of manipulating stored {@link DeviceHasOrder} entities.
 *
 * @author Philip Suprun
 */
public interface DeviceHasOrderDao extends BaseDao<DeviceHasOrder> {

    /**
     * Select multiple {@link DeviceHasOrder} entities with number specified by keyword.
     *
     * @param offset  amount of records that will be skipped from start in the query result.
     * @param length  amount of records that will appear in the query result.
     * @param keyword specifies what pattern should entity's field match.
     * @return {@link List} of queried entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    List<DeviceHasOrder> selectByNumber(int offset, int length, String keyword) throws DaoException;

    /**
     * Select total count of all stored {@link DeviceHasOrder} entities with number specified by keyword.
     *
     * @param keyword specifies what pattern should entity's field match.
     * @return {@code long} value that represents number of found entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    long selectCountByNumber(String keyword) throws DaoException;

    /**
     * Select multiple {@link DeviceHasOrder} entities with deviceId specified by keyword.
     *
     * @param offset  amount of records that will be skipped from start in the query result.
     * @param length  amount of records that will appear in the query result.
     * @param keyword specifies what pattern should entity's field match.
     * @return {@link List} of queried entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    List<DeviceHasOrder> selectByDeviceId(int offset, int length, String keyword) throws DaoException;

    /**
     * Select total count of all stored {@link DeviceHasOrder} entities with deviceId specified by keyword.
     *
     * @param keyword specifies what pattern should entity's field match.
     * @return {@code long} value that represents number of found entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    long selectCountByDeviceId(String keyword) throws DaoException;


    @Override
    default DeviceHasOrder buildEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return builder()
                .setOrderId(resultSet.getLong(ID_ORDER))
                .setDeviceId(resultSet.getLong(ID_DEVICE))
                .setNumber(resultSet.getLong(NUMBER))
                .build();
    }
}
