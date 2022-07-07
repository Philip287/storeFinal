package com.suprun.store.dao.impl;

import com.suprun.store.dao.DeviceHasOrderDao;
import com.suprun.store.entity.DeviceHasOrder;
import com.suprun.store.exception.DaoException;

import java.util.List;
import java.util.Optional;

public class DeviceHasOrderDaoImpl implements DeviceHasOrderDao {

    private static DeviceHasOrderDao instance;

    private static final String INSERT = """
            INSERT INTO devices_has_orders(id_device, id_order, number)
            VALUES(?, ?, ?);
            """;

    private static final String UPDATE = """
            UPDATE devices_has_orders
            SET number = ?
            WHERE id_device = ? AND id_order = ?;
            """;

    private static final String DELETE = """
            UPDATE devices_has_orders
            SET deleted = 1
            WHERE AND id_order = ?;
            """;

    private static final String SELECT_BY_ORDER_ID = """
            SELECT id_device, id_order, number
            FROM devices_has_orders
            WHERE id_order = ?;
            """;

    private static final String SELECT_MULTIPLE_BY_ORDER_ID = """
            SELECT id_device, id_order, number
            FROM devices_has_orders
            WHERE id_order LIKE CONCAT('%', ?, '%') AND deleted <> 1
            ORDER BY id_order
            LIMIT ?, ?;
            """;

    private static final String SELECT_COUNT_BY_ORDER_ID = """
            SELECT COUNT(id_order)
            FROM devices_has_orders
            WHERE id_order LIKE CONCAT('%', ?, '%') AND deleted <> 1;
            """;

    private static final String SELECT_ALL = """
            SELECT id_device, id_order, number
            FROM devices_has_orders
            WHERE deleted <> 1
            ORDER BY id_order
            LIMIT ?, ?;
            """;

    private static final String SELECT_COUNT_ALL = """
            SELECT COUNT(id_order)
            FROM devices_has_orders
            WHERE deleted <> 1;
            """;

    private static final String SELECT_MULTIPLE_BY_NUMBER = """
            SELECT id_device, id_order, number
            FROM devices_has_orders
            WHERE number LIKE CONCAT('%', ?, '%') AND deleted <> 1
            ORDER BY id_order
            LIMIT ?, ?;
            """;

    private static final String SELECT_COUNT_BY_NUMBER = """
            SELECT COUNT(id_order)
            FROM devices_has_orders
            WHERE number LIKE CONCAT('%', ?, '%') AND deleted <> 1;
            """;

    private static final String SELECT_MULTIPLE_BY_DEVICE_ID = """
            SELECT id_device, id_order, number
            FROM devices_has_orders
            WHERE id_device LIKE CONCAT('%', ?, '%') AND deleted <> 1
            ORDER BY id_order
            LIMIT ?, ?;
            """;

    private static final String SELECT_COUNT_BY_DEVICE_ID = """
              SELECT COUNT (id_order)
                    FROM devices_has_orders
                    WHERE id_device LIKE CONCAT('%', ?, '%') AND deleted <> 1;
            """;


    public static DeviceHasOrderDao getInstance() {
        if (instance == null) {
            instance = new DeviceHasOrderDaoImpl();
        }
        return instance;
    }

    @Override
    public long insert(DeviceHasOrder entity) throws DaoException {
        return QueryExecutor.createExecutor().executeInsert(
                INSERT,
                entity.getDeviceId(),
                entity.getOrderId(),
                entity.getNumber()
        );
    }

    @Override
    public void update(DeviceHasOrder entity) throws DaoException {
        QueryExecutor.createExecutor().executeUpdateOrDelete(
                UPDATE,
                entity.getDeviceId(),
                entity.getOrderId(),
                entity.getNumber()
        );
    }

    @Override
    public void delete(long id) throws DaoException {
        QueryExecutor.createExecutor().executeUpdateOrDelete(DELETE, id);

    }

    @Override
    public Optional selectById(long id) throws DaoException {
        return QueryExecutor.createExecutor().executeSelect(SELECT_BY_ORDER_ID, this, id);
    }

    @Override
    public List selectById(int offset, int length, String keyword) throws DaoException {
        return QueryExecutor.createExecutor()
                .executeSelectMultiple(SELECT_MULTIPLE_BY_ORDER_ID, this, keyword, offset, length);
    }

    @Override
    public long selectCountById(String keyword) throws DaoException {
        return QueryExecutor.createExecutor().executeSelectCount(SELECT_COUNT_BY_ORDER_ID, keyword);
    }

    @Override
    public List selectAll(int offset, int length) throws DaoException {
        return QueryExecutor.createExecutor().executeSelectMultiple(SELECT_ALL, this, offset, length);
    }

    @Override
    public long selectCountAll() throws DaoException {
        return QueryExecutor.createExecutor().executeSelectCount(SELECT_COUNT_ALL);
    }

    @Override
    public List<DeviceHasOrder> selectByNumber(int offset, int length, String keyword) throws DaoException {
        return QueryExecutor.createTransactionExecutor()
                .executeSelectMultiple(SELECT_MULTIPLE_BY_NUMBER, this, keyword, offset, length);
    }

    @Override
    public long selectCountByNumber(String keyword) throws DaoException {
        return QueryExecutor.createExecutor().executeSelectCount(SELECT_COUNT_BY_NUMBER, keyword);
    }

    @Override
    public List<DeviceHasOrder> selectByDeviceId(int offset, int length, String keyword) throws DaoException {
        return QueryExecutor.createExecutor()
                .executeSelectMultiple(SELECT_MULTIPLE_BY_DEVICE_ID, this, keyword, offset, length);
    }

    @Override
    public long selectCountByDeviceId(String keyword) throws DaoException {
        return QueryExecutor.createExecutor().executeSelectCount(SELECT_COUNT_BY_DEVICE_ID, keyword);
    }

}
