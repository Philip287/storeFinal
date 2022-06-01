package com.suprun.store.dao;

import com.suprun.store.entity.Computer;
import com.suprun.store.entity.Computer.OrderStatus;
import com.suprun.store.exception.DaoException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static com.suprun.store.dao.TableColum.*;
import static com.suprun.store.entity.Computer.builder;

/**
 * {@code ComputerDao} interface extends {@link BaseDao}.
 * It provides means of manipulating stored {@link Computer} entities.
 *
 * @author Philip Suprun
 */
public interface ComputerDao extends BaseDao<Computer> {
    /**
     * Select multiple entities {@link Computer} with name specified by keyword.
     *
     * @param offset  amount of records that will be skipped from start in the query result.
     * @param length  amount of records that will appear in the query result.
     * @param keyword specifies what pattern should entity's field match.
     * @return {@link List} of queried entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    List<Computer> selectByName(int offset, int length, String keyword) throws DaoException;

    /**
     * Select total count of all stored {@link Computer} entities with name specified by keyword.
     *
     * @param keyword specifies what pattern should entity's field match.
     * @return {@code long} value that represents number of found entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    long selectCountByName(String keyword) throws DaoException;

    /**
     * Select multiple {@link Computer} entities with speaker id specified by keyword.
     *
     * @param offset  amount of records that will be skipped from start in the query result.
     * @param length  amount of records that will appear in the query result.
     * @param keyword specifies what pattern should entity's field match.
     * @return {@link List} of queried entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    List<Computer> selectBySpeakerId(int offset, int length, String keyword) throws DaoException;

    /**
     * Select total count of all stored {@link Computer} entities with speaker id specified by keyword.
     *
     * @param keyword specifies what pattern should entity's field match.
     * @return {@code long} value that represents number of found entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    long selectCountBySpeakerId(String keyword) throws DaoException;

    /**
     * Select multiple {@link Computer} entities with hdd id specified by keyword.
     *
     * @param offset  amount of records that will be skipped from start in the query result.
     * @param length  amount of records that will appear in the query result.
     * @param keyword specifies what pattern should entity's field match.
     * @return {@link List} of queried entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    List<Computer> selectByHddId(int offset, int length, String keyword) throws DaoException;

    /**
     * Select total count of all stored {@link Computer} entities with hdd id specified by keyword.
     *
     * @param keyword specifies what pattern should entity's field match.
     * @return {@code long} value that represents number of found entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    long selectCountByHddId(String keyword) throws DaoException;

    /**
     * Select multiple {@link Computer} entities with hull id specified by keyword.
     *
     * @param offset  amount of records that will be skipped from start in the query result.
     * @param length  amount of records that will appear in the query result.
     * @param keyword specifies what pattern should entity's field match.
     * @return {@link List} of queried entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    List<Computer> selectByHullId(int offset, int length, String keyword) throws DaoException;

    /**
     * Select total count of all stored {@link Computer} entities with hull id specified by keyword.
     *
     * @param keyword specifies what pattern should entity's field match.
     * @return {@code long} value that represents number of found entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    long selectCountByHullId(String keyword) throws DaoException;

    /**
     * Select multiple {@link Computer} entities with mouse id specified by keyword.
     *
     * @param offset  amount of records that will be skipped from start in the query result.
     * @param length  amount of records that will appear in the query result.
     * @param keyword specifies what pattern should entity's field match.
     * @return {@link List} of queried entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    List<Computer> selectByMouseId(int offset, int length, String keyword) throws DaoException;

    /**
     * Select total count of all stored {@link Computer} entities with mouse id specified by keyword.
     *
     * @param keyword specifies what pattern should entity's field match.
     * @return {@code long} value that represents number of found entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    long selectCountByMouseId(String keyword) throws DaoException;

    /**
     * Select multiple {@link Computer} entities with monitor id specified by keyword.
     *
     * @param offset  amount of records that will be skipped from start in the query result.
     * @param length  amount of records that will appear in the query result.
     * @param keyword specifies what pattern should entity's field match.
     * @return {@link List} of queried entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    List<Computer> selectByMonitorId(int offset, int length, String keyword) throws DaoException;

    /**
     * Select total count of all stored {@link Computer} entities with monitor id specified by keyword.
     *
     * @param keyword specifies what pattern should entity's field match.
     * @return {@code long} value that represents number of found entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    long selectCountByMonitorId(String keyword) throws DaoException;

    /**
     * Select multiple {@link Computer} entities with keyboard id specified by keyword.
     *
     * @param offset  amount of records that will be skipped from start in the query result.
     * @param length  amount of records that will appear in the query result.
     * @param keyword specifies what pattern should entity's field match.
     * @return {@link List} of queried entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    List<Computer> selectByKeyboardId(int offset, int length, String keyword) throws DaoException;

    /**
     * Select total count of all stored {@link Computer} entities with keyboard id specified by keyword.
     *
     * @param keyword specifies what pattern should entity's field match.
     * @return {@code long} value that represents number of found entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    long selectCountByKeyboardId(String keyword) throws DaoException;

    /**
     * Select multiple {@link Computer} entities with powerSupply id specified by keyword.
     *
     * @param offset  amount of records that will be skipped from start in the query result.
     * @param length  amount of records that will appear in the query result.
     * @param keyword specifies what pattern should entity's field match.
     * @return {@link List} of queried entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    List<Computer> selectByPowerSupplyId(int offset, int length, String keyword) throws DaoException;

    /**
     * Select total count of all stored {@link Computer} entities with powerSupply id specified by keyword.
     *
     * @param keyword specifies what pattern should entity's field match.
     * @return {@code long} value that represents number of found entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    long selectCountByPowerSupplyId(String keyword) throws DaoException;

    /**
     * Select multiple {@link Computer} entities with ram id specified by keyword.
     *
     * @param offset  amount of records that will be skipped from start in the query result.
     * @param length  amount of records that will appear in the query result.
     * @param keyword specifies what pattern should entity's field match.
     * @return {@link List} of queried entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    List<Computer> selectByRamId(int offset, int length, String keyword) throws DaoException;

    /**
     * Select total count of all stored {@link Computer} entities with ram id specified by keyword.
     *
     * @param keyword specifies what pattern should entity's field match.
     * @return {@code long} value that represents number of found entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    long selectCountByRamId(String keyword) throws DaoException;

    /**
     * Select multiple {@link Computer} entities with motherboard id specified by keyword.
     *
     * @param offset  amount of records that will be skipped from start in the query result.
     * @param length  amount of records that will appear in the query result.
     * @param keyword specifies what pattern should entity's field match.
     * @return {@link List} of queried entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    List<Computer> selectByMotherBoardId(int offset, int length, String keyword) throws DaoException;

    /**
     * Select total count of all stored {@link Computer} entities with motherBoard id specified by keyword.
     *
     * @param keyword specifies what pattern should entity's field match.
     * @return {@code long} value that represents number of found entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    long selectCountByMotherBoardId(String keyword) throws DaoException;

    /**
     * Select multiple {@link Computer} entities with videoCard id specified by keyword.
     *
     * @param offset  amount of records that will be skipped from start in the query result.
     * @param length  amount of records that will appear in the query result.
     * @param keyword specifies what pattern should entity's field match.
     * @return {@link List} of queried entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    List<Computer> selectByVideoCardId(int offset, int length, String keyword) throws DaoException;

    /**
     * Select total count of all stored {@link Computer} entities with videoCard id specified by keyword.
     *
     * @param keyword specifies what pattern should entity's field match.
     * @return {@code long} value that represents number of found entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    long selectCountByVideoCardId(String keyword) throws DaoException;

    /**
     * Select multiple {@link Computer} entities with processor id specified by keyword.
     *
     * @param offset  amount of records that will be skipped from start in the query result.
     * @param length  amount of records that will appear in the query result.
     * @param keyword specifies what pattern should entity's field match.
     * @return {@link List} of queried entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    List<Computer> selectByProcessorId(int offset, int length, String keyword) throws DaoException;

    /**
     * Select total count of all stored {@link Computer} entities with processor id specified by keyword.
     *
     * @param keyword specifies what pattern should entity's field match.
     * @return {@code long} value that represents number of found entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    long selectCountByProcessorId(String keyword) throws DaoException;

    /**
     * Select multiple {@link Computer} entities with totalCoast specified by keyword.
     *
     * @param offset  amount of records that will be skipped from start in the query result.
     * @param length  amount of records that will appear in the query result.
     * @param keyword specifies what pattern should entity's field match.
     * @return {@link List} of queried entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    List<Computer> selectByTotalCoast(int offset, int length, String keyword) throws DaoException;

    /**
     * Select total count of all stored {@link Computer} entities with totalCoast specified by keyword.
     *
     * @param keyword specifies what pattern should entity's field match.
     * @return {@code long} value that represents number of found entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    long selectCountByTotalCoast(String keyword) throws DaoException;

    /**
     * Select multiple {@link Computer} entities with user id specified by keyword.
     *
     * @param offset  amount of records that will be skipped from start in the query result.
     * @param length  amount of records that will appear in the query result.
     * @param keyword specifies what pattern should entity's field match.
     * @return {@link List} of queried entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    List<Computer> selectByUserId(int offset, int length, String keyword) throws DaoException;

    /**
     * Select total count of all stored {@link Computer} entities with user id specified by keyword.
     *
     * @param keyword specifies what pattern should entity's field match.
     * @return {@code long} value that represents number of found entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    long selectCountByUserId(String keyword) throws DaoException;

    /**
     * Select multiple {@link Computer} entities with order status specified by keyword.
     *
     * @param offset  amount of records that will be skipped from start in the query result.
     * @param length  amount of records that will appear in the query result.
     * @param keyword specifies what pattern should entity's field match.
     * @return {@link List} of queried entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    List<Computer> selectByOrderStatus(int offset, int length, String keyword) throws DaoException;

    /**
     * Select total count of all stored {@link Computer} entities with order status specified by keyword.
     *
     * @param keyword specifies what pattern should entity's field match.
     * @return {@code long} value that represents number of found entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    long selectCountByOrderStatus(String keyword) throws DaoException;

    /**
     * Select multiple {@link Computer} entities with user id specified by parameter and name specified by keyword.
     *
     * @param offset  amount of records that will be skipped from start in the query result.
     * @param length  amount of records that will appear in the query result.
     * @param keyword specifies what pattern should entity's field match.
     * @param userId  specifies user id.
     * @return {@link List} of queried entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    List<Computer> selectByNameForUser(int offset, int length, String keyword, String userId) throws DaoException;

    /**
     * Select total count of all stored {@link Computer} entities
     * with user id specified by parameter and name specified by keyword.
     *
     * @param keyword specifies what pattern should entity's field match.
     * @return {@code long} value that represents number of found entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    long selectCountByNameForUser(String keyword, String userId) throws DaoException;

    /**
     * Select all {@link Computer} entities with active {@link OrderStatus}.
     *
     * @param offset amount of records that will be skipped from start in the query result.
     * @param length amount of records that will appear in the query result.
     * @return {@link List} of queried entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    List<Computer> selectAllForActiveOrder(int offset, int length) throws DaoException;

    /**
     * Select total count of all stored {@link Computer} entities with active {@link OrderStatus}.
     *
     * @return {@code long} value that represents number of found entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    long selectCountAllForActiveOrder() throws DaoException;

    /**
     * Select multiple {@link Computer} entities with name specified by keyword with active {@link OrderStatus}.
     *
     * @param offset  amount of records that will be skipped from start in the query result.
     * @param length  amount of records that will appear in the query result.
     * @param keyword specifies what pattern should entity's field match.
     * @return {@link List} of queried entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    List<Computer> selectByNameForActiveOrder(int offset, int length, String keyword) throws DaoException;

    /**
     * Select total count of all stored {@link Computer} entities
     * with name specified by keyword with active {@link OrderStatus}.
     *
     * @param keyword specifies what pattern should entity's field match.
     * @return {@code long} value that represents number of found entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    long selectCountByNameForActiveOrder(String keyword) throws DaoException;

    /**
     * Select multiple {@link Computer} entities with speaker specified by keyword with active {@link OrderStatus}.
     *
     * @param offset  amount of records that will be skipped from start in the query result.
     * @param length  amount of records that will appear in the query result.
     * @param keyword specifies what pattern should entity's field match.
     * @return {@link List} of queried entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    List<Computer> selectBySpeakerIdForActiveOrder(int offset, int length, String keyword) throws DaoException;

    /**
     * Select total count of all stored {@link Computer} entities
     * with speaker specified by keyword with active {@link OrderStatus}.
     *
     * @param keyword specifies what pattern should entity's field match.
     * @return {@code long} value that represents number of found entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    long selectCountBySpeakerIdForActiveOrder(String keyword) throws DaoException;

    /**
     * Select multiple {@link Computer} entities with hdd specified by keyword with active {@link OrderStatus}.
     *
     * @param offset  amount of records that will be skipped from start in the query result.
     * @param length  amount of records that will appear in the query result.
     * @param keyword specifies what pattern should entity's field match.
     * @return {@link List} of queried entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    List<Computer> selectByHddIdForActiveOrder(int offset, int length, String keyword) throws DaoException;

    /**
     * Select total count of all stored {@link Computer} entities
     * with hdd specified by keyword with active {@link OrderStatus}.
     *
     * @param keyword specifies what pattern should entity's field match.
     * @return {@code long} value that represents number of found entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    long selectCountByHddIdForActiveOrder(String keyword) throws DaoException;

    /**
     * Select multiple {@link Computer} entities with hull
     *
     * @param offset  amount of records that will be skipped from start in the query result.
     * @param length  amount of records that will appear in the query result.
     * @param keyword specifies what pattern should entity's field match.
     * @return {@link List} of queried entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    List<Computer> selectByHullIdForActiveOrder(int offset, int length, String keyword) throws DaoException;

    /**
     * Select total count of all stored {@link Computer} entities
     * with hull specified by keyword with active {@link OrderStatus}.
     *
     * @param keyword specifies what pattern should entity's field match.
     * @return {@code long} value that represents number of found entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    long selectCountByHullIdForActiveOrder(String keyword) throws DaoException;

    /**
     * Select multiple {@link Computer} entities with mouse
     *
     * @param offset  amount of records that will be skipped from start in the query result.
     * @param length  amount of records that will appear in the query result.
     * @param keyword specifies what pattern should entity's field match.
     * @return {@link List} of queried entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    List<Computer> selectByMouseIdForActiveOrder(int offset, int length, String keyword) throws DaoException;

    /**
     * Select total count of all stored {@link Computer} entities
     * with mouse specified by keyword with active {@link OrderStatus}.
     *
     * @param keyword specifies what pattern should entity's field match.
     * @return {@code long} value that represents number of found entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    long selectCountByMouseIdForActiveOrder(String keyword) throws DaoException;

    /**
     * Select multiple {@link Computer} entities with monitor
     *
     * @param offset  amount of records that will be skipped from start in the query result.
     * @param length  amount of records that will appear in the query result.
     * @param keyword specifies what pattern should entity's field match.
     * @return {@link List} of queried entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    List<Computer> selectByMonitorIdForActiveOrder(int offset, int length, String keyword) throws DaoException;

    /**
     * Select total count of all stored {@link Computer} entities
     * with monitor specified by keyword with active {@link OrderStatus}.
     *
     * @param keyword specifies what pattern should entity's field match.
     * @return {@code long} value that represents number of found entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    long selectCountByMonitorIdForActiveOrder(String keyword) throws DaoException;

    /**
     * Select multiple {@link Computer} entities with keyboard
     *
     * @param offset  amount of records that will be skipped from start in the query result.
     * @param length  amount of records that will appear in the query result.
     * @param keyword specifies what pattern should entity's field match.
     * @return {@link List} of queried entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    List<Computer> selectByKeyboardIdForActiveOrder(int offset, int length, String keyword) throws DaoException;

    /**
     * Select total count of all stored {@link Computer} entities
     * with keyboard specified by keyword with active {@link OrderStatus}.
     *
     * @param keyword specifies what pattern should entity's field match.
     * @return {@code long} value that represents number of found entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    long selectCountByKeyboardIdForActiveOrder(String keyword) throws DaoException;

    /**
     * Select multiple {@link Computer} entities with powerSupply
     *
     * @param offset  amount of records that will be skipped from start in the query result.
     * @param length  amount of records that will appear in the query result.
     * @param keyword specifies what pattern should entity's field match.
     * @return {@link List} of queried entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    List<Computer> selectByPowerSupplyIdForActiveOrder(int offset, int length, String keyword) throws DaoException;

    /**
     * Select total count of all stored {@link Computer} entities
     * with powerSupply specified by keyword with active {@link OrderStatus}.
     *
     * @param keyword specifies what pattern should entity's field match.
     * @return {@code long} value that represents number of found entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    long selectCountByPowerSupplyIdForActiveOrder(String keyword) throws DaoException;

    /**
     * Select multiple {@link Computer} entities with ram
     *
     * @param offset  amount of records that will be skipped from start in the query result.
     * @param length  amount of records that will appear in the query result.
     * @param keyword specifies what pattern should entity's field match.
     * @return {@link List} of queried entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    List<Computer> selectByRamIdForActiveOrder(int offset, int length, String keyword) throws DaoException;

    /**
     * Select total count of all stored {@link Computer} entities
     * with ram specified by keyword with active {@link OrderStatus}.
     *
     * @param keyword specifies what pattern should entity's field match.
     * @return {@code long} value that represents number of found entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    long selectCountByRamIdForActiveOrder(String keyword) throws DaoException;

    /**
     * Select multiple {@link Computer} entities with motherBoard
     *
     * @param offset  amount of records that will be skipped from start in the query result.
     * @param length  amount of records that will appear in the query result.
     * @param keyword specifies what pattern should entity's field match.
     * @return {@link List} of queried entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    List<Computer> selectByMotherBoardIdForActiveOrder(int offset, int length, String keyword) throws DaoException;

    /**
     * Select total count of all stored {@link Computer} entities
     * with motherBoard specified by keyword with active {@link OrderStatus}.
     *
     * @param keyword specifies what pattern should entity's field match.
     * @return {@code long} value that represents number of found entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    long selectCountByMotherBoardIdForActiveOrder(String keyword) throws DaoException;

    /**
     * Select multiple {@link Computer} entities with videoCard
     *
     * @param offset  amount of records that will be skipped from start in the query result.
     * @param length  amount of records that will appear in the query result.
     * @param keyword specifies what pattern should entity's field match.
     * @return {@link List} of queried entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    List<Computer> selectByVideoCardIdForActiveOrder(int offset, int length, String keyword) throws DaoException;

    /**
     * Select total count of all stored {@link Computer} entities
     * with videoCard specified by keyword with active {@link OrderStatus}.
     *
     * @param keyword specifies what pattern should entity's field match.
     * @return {@code long} value that represents number of found entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    long selectCountByVideoCardIdForActiveOrder(String keyword) throws DaoException;

    /**
     * Select multiple {@link Computer} entities with processor
     *
     * @param offset  amount of records that will be skipped from start in the query result.
     * @param length  amount of records that will appear in the query result.
     * @param keyword specifies what pattern should entity's field match.
     * @return {@link List} of queried entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    List<Computer> selectByProcessorIdForActiveOrder(int offset, int length, String keyword) throws DaoException;

    /**
     * Select total count of all stored {@link Computer} entities
     * with processor specified by keyword with active {@link OrderStatus}.
     *
     * @param keyword specifies what pattern should entity's field match.
     * @return {@code long} value that represents number of found entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    long selectCountByProcessorIdForActiveOrder(String keyword) throws DaoException;

    /**
     * Select multiple {@link Computer} entities with totalCoast
     *
     * @param offset  amount of records that will be skipped from start in the query result.
     * @param length  amount of records that will appear in the query result.
     * @param keyword specifies what pattern should entity's field match.
     * @return {@link List} of queried entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    List<Computer> selectByTotalCoastForActiveOrder(int offset, int length, String keyword) throws DaoException;

    /**
     * Select total totalCoast of all stored {@link Computer} entities
     * with processor specified by keyword with active {@link OrderStatus}.
     *
     * @param keyword specifies what pattern should entity's field match.
     * @return {@code long} value that represents number of found entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    long selectCountByTotalCoastForActiveOrder(String keyword) throws DaoException;

    /**
     * Select multiple {@link Computer} entities with user id specified by keyword with active {@link OrderStatus}.
     *
     * @param offset  amount of records that will be skipped from start in the query result.
     * @param length  amount of records that will appear in the query result.
     * @param keyword specifies what pattern should entity's field match.
     * @return {@link List} of queried entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    List<Computer> selectByUserIdForActiveOrder(int offset, int length, String keyword) throws DaoException;

    /**
     * Select total count of all stored {@link Computer} entities
     * with user id specified by keyword with active {@link OrderStatus}.
     *
     * @param keyword specifies what pattern should entity's field match.
     * @return {@code long} value that represents number of found entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    long selectCountByUserIdForActiveOrder(String keyword) throws DaoException;

    @Override
    default Computer buildEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return (Computer) builder()
                .setName(resultSet.getString(COMPUTER_NAME))
                .setProcessorId(resultSet.getLong(ID_PROCESSOR))
                .setVideoCardId(resultSet.getLong(ID_VIDEO_CARD))
                .setMotherboardId(resultSet.getLong(ID_MOTHERBOARD))
                .setRamId(resultSet.getLong(ID_RAM))
                .setPowerSupplyId(resultSet.getLong(ID_POWER_SUPPLY))
                .setKeyboardId(resultSet.getLong(ID_KEYBOARD))
                .setMonitorId(resultSet.getLong(ID_MONITOR))
                .setMouseId(resultSet.getLong(ID_MOUSE))
                .setHullId(resultSet.getLong(ID_HULL))
                .setHddId(resultSet.getLong(ID_HDD))
                .setSpeakerId(resultSet.getLong(ID_SPEAKER))
                .setTotalCoast(resultSet.getBigDecimal(TOTAL_COST))
                .setUserId(resultSet.getLong(ID_USER))
                .setOrderStatus(OrderStatus.valueOf(resultSet.getString(ORDER_STATUS)))
                .setEntityId(resultSet.getLong(COMPUTER_ID))
                .build();
    }
}
