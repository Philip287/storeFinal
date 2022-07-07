package com.suprun.store.dao.impl;

import com.suprun.store.dao.DeviceDao;
import com.suprun.store.entity.Device;
import com.suprun.store.exception.DaoException;

import java.util.List;
import java.util.Optional;

public class DeviceDaoImpl implements DeviceDao {

    private static DeviceDao instance;

    private static final String INSERT = """
            INSERT INTO devices(name, price, picture_path, description, type)
            VALUES (?, ?, ?, ?, ?);
            """;

    private static final String UPDATE = """
            UPDATE devices
            SET name = ?, price = ?, picture_path = ?, description = ?, type = ?
            WHERE device_id = ?;
            """;

    private static final String DELETE = """
            UPDATE devices
            SET deleted = 1
            WHERE device_id = ?;
            """;

    private static final String SELECT_BY_ID = """
            SELECT device_id, name, price, picture_path, description, type
            FROM devices
            WHERE device_id = ?;
            """;

    private static final String SELECT_MULTIPLE_BY_ID = """
            SELECT device_id, name, price, picture_path, description, type
            FROM devices
            WHERE device_id LIKE CONCAT('%', ?, '%') AND deleted <>1
            ORDER BY device_id
            LIMIT ?, ?;
            """;

    private static final String SELECT_COUNT_BY_ID = """
            SELECT COUNT(device_id)
            FROM devices
            WHERE device_id LIKE CONCAT('%', ?, '%') AND deleted <> 1;
            """;

    private static final String SELECT_ALL = """
            SELECT device_id, name, price, picture_path, description, type
            FROM devices
            WHERE deleted <> 1
            ORDER BY device_id
            LIMIT ?, ?;
            """;

    private static final String SELECT_COUNT_ALL = """
            SELECT COUNT(device_id)
            FROM devices
            WHERE deleted <> 1;
            """;

    private static final String SELECT_MULTIPLE_BY_NAME = """
            SELECT device_id, name, price, picture_path, description, type
            FROM devices
            WHERE name LIKE CONCAT('%', ?, '%') AND deleted <> 1
            ORDER BY device_id
            LIMIT ?, ?;
            """;

    private static final String SELECT_COUNT_BY_NAME = """
            SELECT COUNT(device_id)
            FROM devices
            WHERE name LIKE CONCAT('%', ?, '?') AND deleted <> 1;
            """;

    private static final String SELECT_MULTIPLE_BY_PRICE = """
            SELECT device_id, name, price, picture_path, description, type
            FROM devices
            WHERE price LIKE CONCAT('%', ?, '%') AND DELETED <> 1
            ORDER BY device_id
            LIMIT ?, ?;
            """;

    private static final String SELECT_COUNT_BY_PRICE = """
            SELECT COUNT(device_id)
            FROM devices
            WHERE price LIKE CONCAT('%', ?, '%') AND deleted <> 1;
            """;

    private static final String SELECT_MULTIPLE_BY_PICTURE_PATH = """
            SELECT device_id, name, price, picture_path, description, type
            FROM devices
            WHERE picture_path LIKE CONCAT('%', ?, '%') AND deleted <> 1
            ORDER BY device_id
            LIMIT ?, ?;
            """;

    private static final String SELECT_COUNT_BY_PICTURE_PATH = """
            SELECT COUNT(device_id)
            FROM devices
            WHERE picture_path LIKE CONCAT('%', ?, '%') AND deleted <> 1;
            """;

    private static final String SELECT_MULTIPLE_BY_DESCRIPTION = """
            SELECT device_id, name, price, picture_path, description, type
            FROM devices
            WHERE description LIKE CONCAT('%', ?, '%') AND deleted <> 1
            ORDER BY device_id
            LIMIT ?, ?;
            """;

    private static final String SELECT_COUNT_BY_DESCRIPTION = """
            SELECT COUNT(device_id)
            FROM devices
            WHERE description LIKE CONCAT('%', ?, '%') AND deleted <> 1;
            """;

    private static final String SELECT_MULTIPLE_BY_TYPE = """
            SELECT device_id, name, price, picture_path, description, type
            FROM devices
            WHERE type LIKE  ? AND deleted <> 1
            ORDER BY device_id
            LIMIT ?, ?;
            """;

    private static final String SELECT_COUNT_BY_TYPE = """
            SELECT COUNT(device_id)
            FROM devices
            WHERE type LIKE ? AND deleted <> 1;
            """;


    public static DeviceDao getInstance() {
        if (instance == null) {
            instance = new DeviceDaoImpl();
        }
        return instance;
    }

    @Override
    public long insert(Device entity) throws DaoException {
        return QueryExecutor.createExecutor().executeInsert(
                INSERT,
                entity.getName(),
                entity.getPrice(),
                entity.getPicturePath(),
                entity.getDescription(),
                entity.getType().toString()
        );
    }

    @Override
    public void update(Device entity) throws DaoException {
        QueryExecutor.createExecutor().executeUpdateOrDelete(
                UPDATE,
                entity.getName(),
                entity.getPrice(),
                entity.getPicturePath(),
                entity.getDescription(),
                entity.getType().toString(),
                entity.getEntityId()
        );
    }

    @Override
    public void delete(long id) throws DaoException {
        QueryExecutor.createExecutor().executeUpdateOrDelete(DELETE, id);
    }

    @Override
    public Optional<Device> selectById(long id) throws DaoException {
        return QueryExecutor.createExecutor().executeSelect(SELECT_BY_ID, this, id);
    }

    @Override
    public List<Device> selectById(int offset, int length, String keyword) throws DaoException {
        return QueryExecutor.createExecutor()
                .executeSelectMultiple(SELECT_MULTIPLE_BY_ID, this, keyword, offset, length);
    }

    @Override
    public long selectCountById(String keyword) throws DaoException {
        return QueryExecutor.createExecutor().executeSelectCount(SELECT_COUNT_BY_ID, keyword);
    }

    @Override
    public List<Device> selectAll(int offset, int length) throws DaoException {
        return QueryExecutor.createExecutor()
                .executeSelectMultiple(SELECT_ALL, this, offset, length);
    }

    @Override
    public long selectCountAll() throws DaoException {
        return QueryExecutor.createExecutor().executeSelectCount(SELECT_COUNT_ALL);
    }

    @Override
    public List<Device> selectByName(int offset, int length, String keyword) throws DaoException {
        return QueryExecutor.createExecutor()
                .executeSelectMultiple(SELECT_MULTIPLE_BY_NAME, this, keyword, offset, length);
    }

    @Override
    public long selectCountByName(String keyword) throws DaoException {
        return QueryExecutor.createExecutor().executeSelectCount(SELECT_COUNT_BY_NAME, keyword);
    }

    @Override
    public List<Device> selectByPrice(int offset, int length, String keyword) throws DaoException {
        return QueryExecutor.createExecutor()
                .executeSelectMultiple(SELECT_MULTIPLE_BY_PRICE, this, keyword, offset, length);
    }

    @Override
    public long selectCountByPrice(String keyword) throws DaoException {
        return QueryExecutor.createExecutor().executeSelectCount(SELECT_COUNT_BY_PRICE, keyword);
    }

    @Override
    public List<Device> selectByPicturePath(int offset, int length, String keyword) throws DaoException {
        return QueryExecutor.createExecutor()
                .executeSelectMultiple(SELECT_MULTIPLE_BY_PICTURE_PATH, this, keyword, offset, length);
    }

    @Override
    public long selectCountByPicturePath(String keyword) throws DaoException {
        return QueryExecutor.createExecutor().executeSelectCount(SELECT_COUNT_BY_PICTURE_PATH, keyword);
    }

    @Override
    public List<Device> selectByDescription(int offset, int length, String keyword) throws DaoException {
        return QueryExecutor.createExecutor()
                .executeSelectMultiple(SELECT_MULTIPLE_BY_DESCRIPTION, this, keyword, offset, length);
    }

    @Override
    public long selectCountByDescription(String keyword) throws DaoException {
        return QueryExecutor.createExecutor()
                .executeSelectCount(SELECT_COUNT_BY_DESCRIPTION, keyword);
    }

    @Override
    public List<Device> selectByType(int offset, int length, String keyword) throws DaoException {
        return QueryExecutor.createExecutor()
                .executeSelectMultiple(SELECT_MULTIPLE_BY_TYPE, this, keyword, offset, length);
    }

    @Override
    public long selectCountByType(String keyword) throws DaoException {
        return QueryExecutor.createExecutor().executeSelectCount(SELECT_COUNT_BY_TYPE, keyword);
    }
}
