package com.suprun.store.dao;

import com.suprun.store.entity.Monitor;
import com.suprun.store.exception.DaoException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static com.suprun.store.dao.TableColum.*;


/**
 * {@code KeyboardDao} interface extends {@link BaseDao}.
 * It provides means of manipulating stored {@link Monitor} entities.
 *
 * @author Philip Suprun
 */
public interface MonitorDao extends BaseDao<Monitor> {
    /**
     * Select multiple {@link Monitor} entities with name specified by keyword.
     *
     * @param offset  amount of records that will be skipped from start in the query result.
     * @param length  amount of records that will appear in the query result.
     * @param keyword specifies what pattern should entity's field match.
     * @return {@link List} of queried entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    List<Monitor> selectByName(int offset, int length, String keyword) throws DaoException;

    /**
     * Select total count of all stored {@link Monitor} entities with name specified by keyword.
     *
     * @param keyword specifies what pattern should entity's field match.
     * @return {@code long} value that represents number of found entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    long selectCountByName(String keyword) throws DaoException;

    /**
     * Select multiple {@link Monitor} entities with price specified by keyword.
     *
     * @param offset  amount of records that will be skipped from start in the query result.
     * @param length  amount of records that will appear in the query result.
     * @param keyword specifies what pattern should entity's field match.
     * @return {@link List} of queried entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    List<Monitor> selectByPrice(int offset, int length, String keyword) throws DaoException;

    /**
     * Select total count of all stored {@link Monitor} entities with price specified by keyword.
     *
     * @param keyword specifies what pattern should entity's field match.
     * @return {@code long} value that represents number of found entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    long selectCountByPrice(String keyword) throws DaoException;

    /**
     * Select multiple {@link Monitor} entities with description specified by keyword.
     *
     * @param offset  amount of records that will be skipped from start in the query result.
     * @param length  amount of records that will appear in the query result.
     * @param keyword specifies what pattern should entity's field match.
     * @return {@link List} of queried entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    List<Monitor> selectByDescription(int offset, int length, String keyword) throws DaoException;

    /**
     * Select total count of all stored {@link Monitor} description with price specified by keyword.
     *
     * @param keyword specifies what pattern should entity's field match.
     * @return {@code long} value that represents number of found entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    long selectCountByDescription(String keyword) throws DaoException;

    /**
     * Select multiple {@link Monitor} entities with picturePath specified by keyword.
     *
     * @param offset  amount of records that will be skipped from start in the query result.
     * @param length  amount of records that will appear in the query result.
     * @param keyword specifies what pattern should entity's field match.
     * @return {@link List} of queried entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    List<Monitor> selectByPicturePath(int offset, int length, String keyword) throws DaoException;

    /**
     * Select total count of all stored {@link Monitor} description with picturePath specified by keyword.
     *
     * @param keyword specifies what pattern should entity's field match.
     * @return {@code long} value that represents number of found entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    long selectCountByPicturePath(String keyword) throws DaoException;

    @Override
    default Monitor buildEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return (Monitor) Monitor.builder()
                .setName(resultSet.getString(MONITOR_NAME))
                .setDescription(resultSet.getString(MONITOR_DESCRIPTION))
                .setPicturePath(resultSet.getString(MONITOR_PICTURE_PATH))
                .setPrice(resultSet.getBigDecimal(MONITOR_PRICE))
                .setEntityId(resultSet.getLong(MONITOR_ID))
                .build();
    }
}
