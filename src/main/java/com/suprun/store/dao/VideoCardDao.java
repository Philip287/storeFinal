package com.suprun.store.dao;

import com.suprun.store.entity.VideoCard;
import com.suprun.store.exception.DaoException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static com.suprun.store.dao.TableColum.*;

/**
 * {@code VideoCardDao} interface extends {@link BaseDao}.
 * It provides means of manipulating stored {@link VideoCard} entities.
 *
 * @author Philip Suprun
 */
public interface VideoCardDao extends BaseDao<VideoCard> {

    /**
     * Select multiple {@link VideoCard} entities with name specified by keyword.
     *
     * @param offset  amount of records that will be skipped from start in the query result.
     * @param length  amount of records that will appear in the query result.
     * @param keyword specifies what pattern should entity's field match.
     * @return {@link List} of queried entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    List<VideoCard> selectByName(int offset, int length, String keyword) throws DaoException;

    /**
     * Select total count of all stored {@link VideoCard} entities with name specified by keyword.
     *
     * @param keyword specifies what pattern should entity's field match.
     * @return {@code long} value that represents number of found entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    long selectCountByName(String keyword) throws DaoException;

    /**
     * Select multiple {@link VideoCard} entities with price specified by keyword.
     *
     * @param offset  amount of records that will be skipped from start in the query result.
     * @param length  amount of records that will appear in the query result.
     * @param keyword specifies what pattern should entity's field match.
     * @return {@link List} of queried entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    List<VideoCard> selectByPrice(int offset, int length, String keyword) throws DaoException;

    /**
     * Select total count of all stored {@link VideoCard} entities with price specified by keyword.
     *
     * @param keyword specifies what pattern should entity's field match.
     * @return {@code long} value that represents number of found entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    long selectCountByPrice(String keyword) throws DaoException;

    /**
     * Select multiple {@link VideoCard} entities with description specified by keyword.
     *
     * @param offset  amount of records that will be skipped from start in the query result.
     * @param length  amount of records that will appear in the query result.
     * @param keyword specifies what pattern should entity's field match.
     * @return {@link List} of queried entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    List<VideoCard> selectByDescription(int offset, int length, String keyword) throws DaoException;

    /**
     * Select total count of all stored {@link VideoCard} description with price specified by keyword.
     *
     * @param keyword specifies what pattern should entity's field match.
     * @return {@code long} value that represents number of found entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    long selectCountByDescription(String keyword) throws DaoException;

    /**
     * Select multiple {@link VideoCard} entities with picturePath specified by keyword.
     *
     * @param offset  amount of records that will be skipped from start in the query result.
     * @param length  amount of records that will appear in the query result.
     * @param keyword specifies what pattern should entity's field match.
     * @return {@link List} of queried entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    List<VideoCard> selectByPicturePath(int offset, int length, String keyword) throws DaoException;

    /**
     * Select total count of all stored {@link VideoCard} description with picturePath specified by keyword.
     *
     * @param keyword specifies what pattern should entity's field match.
     * @return {@code long} value that represents number of found entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    long selectCountByPicturePath(String keyword) throws DaoException;

    @Override
    default VideoCard buildEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return (VideoCard) VideoCard.builder()
                .setName(resultSet.getString(VIDEO_CARD_NAME))
                .setDescription(resultSet.getString(VIDEO_CARD_DESCRIPTION))
                .setPicturePath(resultSet.getString(VIDEO_CARD_PICTURE_PATH))
                .setPrice(resultSet.getBigDecimal(VIDEO_CARD_PRICE))
                .setEntityId(resultSet.getLong(VIDEO_CARD_ID))
                .build();
    }
}
