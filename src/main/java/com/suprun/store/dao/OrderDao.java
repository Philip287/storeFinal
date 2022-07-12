package com.suprun.store.dao;

import com.suprun.store.entity.Order;
import com.suprun.store.entity.Order.OrderStatus;
import com.suprun.store.exception.DaoException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static com.suprun.store.dao.TableColum.*;
import static com.suprun.store.entity.Order.builder;

/**
 * {@code OrderDao} interface extends {@link BaseDao}.
 * It provides means of manipulating stored {@link Order} entities.
 *
 * @author Philip Suprun
 */
public interface OrderDao extends BaseDao<Order> {

    /**
     * Select multiple {@link Order} entities with user id specified by keyword.
     *
     * @param offset  amount of records that will be skipped from start in the query result.
     * @param length  amount of records that will appear in the query result.
     * @param keyword specifies what pattern should entity's field match.
     * @return {@link List} of queried entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    List<Order> selectByUserId(int offset, int length, String keyword) throws DaoException;

    /**
     * Select total count of all stored {@link Order} entities with user id specified by keyword.
     *
     * @param keyword specifies what pattern should entity's field match.
     * @return {@code long} value that represents number of found entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    long selectCountByUserId(String keyword) throws DaoException;


    /**
     * Select multiple {@link Order} entities with order status specified by keyword.
     *
     * @param offset  amount of records that will be skipped from start in the query result.
     * @param length  amount of records that will appear in the query result.
     * @param keyword specifies what pattern should entity's field match.
     * @return {@link List} of queried entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    List<Order> selectByOrderStatus(int offset, int length, String keyword) throws DaoException;

    /**
     * Select total count of all stored {@link Order} entities with order status specified by keyword.
     *
     * @param keyword specifies what pattern should entity's field match.
     * @return {@code long} value that represents number of found entities.
     * @throws DaoException if an error occurred while processing the query.
     */
    long selectCountByOrderStatus(String keyword) throws DaoException;

    @Override
    default Order buildEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return (Order) builder()
                .setUserId(resultSet.getLong(ID_USER))
                .setOrderStatus(OrderStatus.valueOf(resultSet.getString(ORDER_STATUS)))
                .setEntityId(resultSet.getLong(ORDER_ID))
                .build();
    }
}
