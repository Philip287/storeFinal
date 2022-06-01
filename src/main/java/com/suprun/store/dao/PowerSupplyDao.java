package com.suprun.store.dao;

import com.suprun.store.entity.PowerSupply;
import com.suprun.store.exception.DaoException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static com.suprun.store.dao.TableColum.*;

/**
 * {@code PowerSupplyDao} interface extends {@link BaseDao}.
 * It provides means of manipulating stored {@link PowerSupply} entities.
 *
 * @author Philip Suprun
 */
public interface PowerSupplyDao extends BaseDao<PowerSupply> {

    /**
     * Select multiple {@link PowerSupply} entities with name specified by keyword.
     *
     * @param offset  amount of records that will be skipped from start in the query result.
     * @param length  amount of records that will appear in the query result.
     * @param keyword specifies what pattern should entity's field match.
     * @return {@link List} of queried entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    List<PowerSupply> selectByName(int offset, int length, String keyword) throws DaoException;

    /**
     * Select total count of all stored {@link PowerSupply} entities with name specified by keyword.
     *
     * @param keyword specifies what pattern should entity's field match.
     * @return {@code long} value that represents number of found entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    long selectCountByName(String keyword) throws DaoException;

    /**
     * Select multiple {@link PowerSupply} entities with price specified by keyword.
     *
     * @param offset  amount of records that will be skipped from start in the query result.
     * @param length  amount of records that will appear in the query result.
     * @param keyword specifies what pattern should entity's field match.
     * @return {@link List} of queried entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    List<PowerSupply> selectByPrice(int offset, int length, String keyword) throws DaoException;

    /**
     * Select total count of all stored {@link PowerSupply} entities with price specified by keyword.
     *
     * @param keyword specifies what pattern should entity's field match.
     * @return {@code long} value that represents number of found entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    long selectCountByPrice(String keyword) throws DaoException;

    /**
     * Select multiple {@link PowerSupply} entities with description specified by keyword.
     *
     * @param offset  amount of records that will be skipped from start in the query result.
     * @param length  amount of records that will appear in the query result.
     * @param keyword specifies what pattern should entity's field match.
     * @return {@link List} of queried entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    List<PowerSupply> selectByDescription(int offset, int length, String keyword) throws DaoException;

    /**
     * Select total count of all stored {@link PowerSupply} description with price specified by keyword.
     *
     * @param keyword specifies what pattern should entity's field match.
     * @return {@code long} value that represents number of found entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    long selectCountByDescription(String keyword) throws DaoException;

    /**
     * Select multiple {@link PowerSupply} entities with picturePath specified by keyword.
     *
     * @param offset  amount of records that will be skipped from start in the query result.
     * @param length  amount of records that will appear in the query result.
     * @param keyword specifies what pattern should entity's field match.
     * @return {@link List} of queried entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    List<PowerSupply> selectByPicturePath(int offset, int length, String keyword) throws DaoException;

    /**
     * Select total count of all stored {@link PowerSupply} description with picturePath specified by keyword.
     *
     * @param keyword specifies what pattern should entity's field match.
     * @return {@code long} value that represents number of found entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    long selectCountByPicturePath(String keyword) throws DaoException;

    @Override
    default PowerSupply buildEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return (PowerSupply) PowerSupply.builder()
                .setName(resultSet.getString(POWER_SUPPLY_NAME))
                .setDescription(resultSet.getString(POWER_SUPPLY_DESCRIPTION))
                .setPicturePath(resultSet.getString(POWER_SUPPLY_PICTURE_PATH))
                .setPrice(resultSet.getBigDecimal(POWER_SUPPLY_PRICE))
                .setEntityId(resultSet.getLong(POWER_SUPPLY_ID))
                .build();
    }
}
