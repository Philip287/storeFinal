package com.suprun.store.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.suprun.store.entity.Hdd;
import com.suprun.store.exception.DaoException;

import static com.suprun.store.dao.TableColum.*;

/**
 * {@code HddDao} interface extends {@link BaseDao}.
 * It provides means of manipulating stored {@link Hdd} entities.
 *
 * @author Philip Suprun
 */
public interface HddDao extends BaseDao<Hdd> {

    /**
     * Select multiple {@link Hdd} entities with name specified by keyword.
     *
     * @param offset amount of records that will be skipped from start in the query result.
     * @param length amount of records that will appear in the query result.
     * @param keyword specifies what pattern should entity's field match.
     * @return {@link List} of queried entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    List<Hdd> selectByName(int offset, int length, String keyword) throws DaoException;

    /**
     * Select total count of all stored {@link Hdd} entities with name specified by keyword.
     *
     * @param keyword specifies what pattern should entity's field match.
     * @return {@code long} value that represents number of found entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    long selectCountByName(String keyword) throws DaoException;

    /**
     * Select multiple {@link Hdd} entities with price specified by keyword.
     *
     * @param offset amount of records that will be skipped from start in the query result.
     * @param length amount of records that will appear in the query result.
     * @param keyword specifies what pattern should entity's field match.
     * @return {@link List} of queried entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    List<Hdd> selectByPrice(int offset, int length, String keyword) throws DaoException;

    /**
     * Select total count of all stored {@link Hdd} entities with price specified by keyword.
     *
     * @param keyword specifies what pattern should entity's field match.
     * @return {@code long} value that represents number of found entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    long selectCountByPrice(String keyword) throws DaoException;

    /**
     * Select multiple {@link Hdd} entities with description specified by keyword.
     *
     * @param offset amount of records that will be skipped from start in the query result.
     * @param length amount of records that will appear in the query result.
     * @param keyword specifies what pattern should entity's field match.
     * @return {@link List} of queried entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    List<Hdd> selectByDescription(int offset, int length, String keyword) throws DaoException;

    /**
     * Select total count of all stored {@link Hdd} description with price specified by keyword.
     *
     * @param keyword specifies what pattern should entity's field match.
     * @return {@code long} value that represents number of found entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    long selectCountByDescription(String keyword) throws DaoException;

    /**
     * Select multiple {@link Hdd} entities with picturePath specified by keyword.
     *
     * @param offset amount of records that will be skipped from start in the query result.
     * @param length amount of records that will appear in the query result.
     * @param keyword specifies what pattern should entity's field match.
     * @return {@link List} of queried entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    List<Hdd> selectByPicturePath(int offset, int length, String keyword) throws DaoException;

    /**
     * Select total count of all stored {@link Hdd} description with picturePath specified by keyword.
     *
     * @param keyword specifies what pattern should entity's field match.
     * @return {@code long} value that represents number of found entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    long selectCountByPicturePath(String keyword) throws DaoException;


    @Override
    default Hdd buildEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return (Hdd) Hdd.builder()
                .setName(resultSet.getString(HDD_NAME))
                .setDescription(resultSet.getString(HDD_DESCRIPTION))
                .setPrice(resultSet.getBigDecimal(HDD_PRICE))
                .setPicturePath(resultSet.getString(HDD_PICTURE_PATH))
                .setEntityId(resultSet.getLong(HDD_ID))
                .build();
    }
}
