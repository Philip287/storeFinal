package com.suprun.store.dao.impl;

import com.suprun.store.dao.OrderDao;
import com.suprun.store.entity.Order;
import com.suprun.store.exception.DaoException;

import java.util.List;
import java.util.Optional;

public class OrderDaoImpl implements OrderDao {

    private static OrderDao instance;

    private static final String INSERT = """
            INSERT INTO orders(order_status, id_user)
            VALUES(?, ?);
            """;

    private static final String UPDATE = """
            UPDATE orders
            SET order_status = ?, id_user = ?
            WHERE order_id = ?;
            """;

    private static final String DELETE = """
            UPDATE orders
            SET deleted = 1
            WHERE order_id = ?;
            """;

    private static final String SELECT_BY_ID = """
            SELECT order_id, order_status, id_user
            FROM orders
            WHERE order_id = ? AND deleted <> 1;
            """;

    private static final String SELECT_MULTIPLE_BY_ID = """
            SELECT order_id, order_status, id_user
            FROM orders
            WHERE order_id LIKE CONCAT('%', ?, '%') AND deleted <> 1
            ORDER BY order_id
            LIMIT ?, ?;
            """;

    private static final String SELECT_COUNT_BY_ID = """
            SELECT COUNT(order_id)
            FROM orders
            WHERE order_id LIKE CONCAT ('%', ?, '%') AND deleted <> 1;
            """;

    private static final String SELECT_ALL = """
            SELECT order_id, order_status, id_user
            FROM orders
            WHERE deleted <> 1
            ORDER BY order_id
            LIMIT ?, ?;
            """;
    private static final String SELECT_COUNT_ALL = """
            SELECT COUNT(order_id)
            FROM orders
            WHERE deleted <> 1;
            """;

    private static final String SELECT_MULTIPLE_BY_ORDER_STATUS = """
            SELECT order_id, order_status, id_user
            FROM orders
            WHERE order_status LIKE ? AND deleted <> 1
            ORDER BY order_id
            LIMIT ?, ?;
            """;

    private static final String SELECT_COUNT_BY_ORDER_STATUS = """
            SELECT COUNT(order_id)
            FROM orders
            WHERE order_status LIKE ? AND deleted <> 1;
            """;

    private static final String SELECT_MULTIPLE_BY_USER_ID = """
            SELECT order_id, order_status, id_user
            FROM orders
            WHERE id_user LIKE CONCAT('%', ?, '%') AND deleted <> 1
            ORDER BY order_id
            LIMIT ?, ?;
            """;

    private static final String SELECT_COUNT_BY_USER_ID = """
            SELECT COUNT(order_id)
            FROM orders
            WHERE id_user LIKE CONCAT ('%', ?, '%') AND deleted <> 1;
            """;

    private static final String SELECT_MULTIPLE_BY_USER_ID_FOR_ACTIVE_ORDER = """
            SELECT order_id, order_status, id_user
            FROM orders
            WHERE id_user LIKE CONCAT ('%', ?, '%') AND order_status = <> 'COMPLETED' AND deleted <> 1
            ORDER BY order_id
            LIMIT ?, ?;
            """;

    private static final String SELECT_COUNT_BY_USER_ID_FOR_ACTIVE_ORDER = """
            SELECT COUNT(order_id)
            FROM orders
            WHERE id_user LIKE CONCAT ('%', ?, '%') AND order_status = <> 'COMPLETED' AND deleted <> 1;
            """;

    private static final String SELECT_ALL_FOR_ACTIVE_ORDER = """
            SELECT order_id, order_status, id_user
            FROM orders
            WHERE deleted <> 1 AND order_status <> 'COMPLETED'
            ORDER BY order_id
            LIMIT ?, ?;
            """;

    private static final String SELECT_COUNT_ALL_FOR_ACTIVE_ORDER = """
            SELECT COUNT (order_id)
            FROM orders
            WHERE deleted <> 1 AND order_status <> 'COMPLETED';
            """;

    public static OrderDao getInstance() {
        if (instance == null) {
            instance = new OrderDaoImpl();
        }
        return instance;
    }

    @Override
    public long insert(Order entity) throws DaoException {
        return QueryExecutor.createExecutor().executeInsert(
                INSERT,
                entity.getOrderStatus().toString(),
                entity.getUserId()
        );
    }

    @Override
    public void update(Order entity) throws DaoException {
        QueryExecutor.createExecutor().executeUpdateOrDelete(
                UPDATE,
                entity.getOrderStatus().toString(),
                entity.getUserId(),
                entity.getEntityId()
        );
    }

    @Override
    public void delete(long id) throws DaoException {
        QueryExecutor.createExecutor().executeUpdateOrDelete(DELETE, id);
    }

    @Override
    public Optional<Order> selectById(long id) throws DaoException {
        return QueryExecutor.createExecutor().executeSelect(SELECT_BY_ID, this, id);
    }

    @Override
    public List<Order> selectById(int offset, int length, String keyword) throws DaoException {
        return QueryExecutor.createExecutor()
                .executeSelectMultiple(SELECT_MULTIPLE_BY_ID, this, keyword, offset, length);
    }

    @Override
    public long selectCountById(String keyword) throws DaoException {
        return QueryExecutor.createExecutor().executeSelectCount(SELECT_COUNT_BY_ID, keyword);
    }

    @Override
    public List<Order> selectAll(int offset, int length) throws DaoException {
        return QueryExecutor.createExecutor().executeSelectMultiple(SELECT_ALL, this, offset, length);
    }

    @Override
    public long selectCountAll() throws DaoException {
        return QueryExecutor.createExecutor().executeSelectCount(SELECT_COUNT_ALL);
    }

    @Override
    public List<Order> selectByUserId(int offset, int length, String keyword) throws DaoException {
        return QueryExecutor.createExecutor()
                .executeSelectMultiple(SELECT_MULTIPLE_BY_USER_ID, this, keyword, offset, length);
    }

    @Override
    public long selectCountByUserId(String keyword) throws DaoException {
        return QueryExecutor.createExecutor()
                .executeSelectCount(SELECT_COUNT_BY_USER_ID, keyword);
    }

    @Override
    public List<Order> selectByOrderStatus(int offset, int length, String keyword) throws DaoException {
        return QueryExecutor.createExecutor()
                .executeSelectMultiple(SELECT_MULTIPLE_BY_ORDER_STATUS, this, keyword, offset, length);
    }

    @Override
    public long selectCountByOrderStatus(String keyword) throws DaoException {
        return QueryExecutor.createExecutor().executeSelectCount(SELECT_COUNT_BY_ORDER_STATUS, keyword);
    }

    @Override
    public List<Order> selectByUserIdForActiveOrder(int offset, int length, String keyword) throws DaoException {
        return QueryExecutor.createExecutor()
                .executeSelectMultiple(SELECT_MULTIPLE_BY_USER_ID_FOR_ACTIVE_ORDER, this, keyword, offset, length);
    }

    @Override
    public long selectCountByUserIdForActiveOrder(String keyword) throws DaoException {
        return QueryExecutor.createExecutor().executeSelectCount(SELECT_COUNT_BY_USER_ID_FOR_ACTIVE_ORDER, keyword);
    }

    @Override
    public List<Order> selectAllForActiveOrder(int offset, int length) throws DaoException {
        return QueryExecutor.createExecutor()
                .executeSelectMultiple(SELECT_ALL_FOR_ACTIVE_ORDER, this, offset, length);
    }

    @Override
    public long selectCountAllForActiveOrder() throws DaoException {
        return QueryExecutor.createTransactionExecutor().executeSelectCount(SELECT_COUNT_ALL_FOR_ACTIVE_ORDER);
    }
}
