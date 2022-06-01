package com.suprun.store.dao.impl;

import com.suprun.store.dao.MonitorDao;
import com.suprun.store.entity.Monitor;
import com.suprun.store.exception.DaoException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class MonitorDaoImpl implements MonitorDao {

    private static MonitorDao instance;

    private static final String INSERT = """
            INSERT INTO monitors(name, price, picture_path, description)
            VALUES(?, ?, ?, ?);
            """;

    private static final String UPDATE = """
            UPDATE monitors
            SET name = ?, price ?, picture_path ?, description ?
            WHERE monitor_id = ?;
            """;

    private static final String DELETE = """
            UPDATE monitors
            SET deleted = 1
            WHERE monitor_id = ?;
            """;

    private static final String SELECT_ALL = """
            SELECT name, price, picture_path, description
            FROM monitors
            WHERE deleted <> 1
            ORDER BY monitor_id
            LIMIT ?, ?;
            """;

    private static final String SELECT_COUNT_ALL = """
            SELECT COUNT (monitor_id)
            FROM monitors
            WHERE deleted <> 1;
            """;

    private static final String SELECT_BY_ID = """
            SELECT name, price, picture_path, description
            FROM monitors
            WHERE monitor_id = ?;
            """;

    private static final String SELECT_MULTIPLE_BY_ID = """
            SELECT name, price, picture_path, description
            FROM monitors
            WHERE monitor_id LIKE CONCAT('%', ?, '%') AND deleted <> 1
            ORDER BY monitor_id
            LIMIT ?, ?;
            """;

    private static final String SELECT_COUNT_BY_ID = """
            SELECT COUNT (monitor_id)
            FROM monitors
            WHERE monitor_id LIKE CONCAT('%', ?, '%') AND deleted <> 1;
            """;

    private static final String SELECT_MULTIPLE_BY_NAME = """
            SELECT name, price, picture_path, description
            FROM monitors
            WHERE name LIKE CONCAT('%', ?, '%') AND deleted <> 1
            ORDER BY monitor_id
            LIMIT ?, ?;
            """;

    private static final String SELECT_COUNT_BY_NAME = """
            SELECT COUNT (monitor_id)
            FROM monitors
            WHERE name LIKE CONCAT('%', ?, '%') AND deleted <> 1;
            """;

    private static final String SELECT_MULTIPLE_BY_PRICE = """
            SELECT name, price, picture_path, description
            FROM monitors
            WHERE price LIKE CONCAT ('%', ?, '%') AND deleted <> 1
            ORDER BY monitor_id
            LIMIT ?, ?;
            """;

    private static final String SELECT_COUNT_BY_PRICE = """
            SELECT COUNT (monitor_id)
            FROM monitors
            WHERE price LIKE CONCAT ('%', ?, '%') AND deleted <> 1;
            """;

    private static final String SELECT_MULTIPLE_BY_PICTURE_PATH = """
            SELECT name, price, picture_path, description
            FROM monitors
            WHERE picture_path LIKE CONCAT ('%', ?, '%') AND deleted <> 1
            ORDER BY monitor_id
            LIMIT ?, ?;
            """;

    private static final String SELECT_COUNT_BY_PICTURE_PATH = """
            SELECT COUNT (monitor_id)
            FROM monitors
            WHERE picture_path LIKE CONCAT ('%', ?, '%') AND deleted <> 1;
            """;

    private static final String SELECT_MULTIPLE_BY_DESCRIPTION = """
            SELECT name, price, picture_path, description
            FROM monitors
            WHERE description LIKE CONCAT ('%', ?, '%') AND deleted <> 1
            ORDER BY monitor_id
            LIMIT ?, ?;
            """;

    private static final String SELECT_COUNT_BY_DESCRIPTION = """
            SELECT COUNT (monitor_id)
            FROM monitors
            WHERE description LIKE CONCAT ('%', ?, '%') AND deleted <> 1;
            """;

    private MonitorDaoImpl() {

    }

    private MonitorDao getInstance() {
        if (instance == null) {
            instance = new MonitorDaoImpl();
        }
        return instance;
    }

    @Override
    public long insert(Monitor entity) throws DaoException {
        return QueryExecutor.createExecutor().executeInsert(
                INSERT,
                entity.getName(),
                entity.getDescription(),
                entity.getPicturePath(),
                entity.getPrice()
        );
    }

    @Override
    public void update(Monitor entity) throws DaoException {
        QueryExecutor.createExecutor().executeUpdateOrDelete(
                UPDATE,
                entity.getName(),
                entity.getDescription(),
                entity.getPicturePath(),
                entity.getPrice()
        );
    }

    @Override
    public void delete(long id) throws DaoException {
        QueryExecutor.createExecutor().executeUpdateOrDelete(DELETE, id);
    }

    @Override
    public Optional<Monitor> selectById(long id) throws DaoException {
        return QueryExecutor.createExecutor().executeSelect(SELECT_BY_ID, this, id);
    }

    @Override
    public List<Monitor> selectById(int offset, int length, String keyword) throws DaoException {
        return QueryExecutor.createExecutor()
                .executeSelectMultiple(SELECT_MULTIPLE_BY_ID, this, keyword, offset, offset, length);
    }

    @Override
    public long selectCountById(String keyword) throws DaoException {
        return QueryExecutor.createExecutor().executeSelectCount(SELECT_COUNT_BY_ID, keyword);
    }

    @Override
    public List<Monitor> selectAll(int offset, int length) throws DaoException {
        return QueryExecutor.createExecutor().executeSelectMultiple(SELECT_ALL, this, offset, length);
    }

    @Override
    public long selectCountAll() throws DaoException {
        return QueryExecutor.createExecutor().executeSelectCount(SELECT_COUNT_ALL);
    }

    @Override
    public List<Monitor> selectByName(int offset, int length, String keyword) throws DaoException {
        return QueryExecutor.createExecutor()
                .executeSelectMultiple(SELECT_MULTIPLE_BY_NAME, this, keyword, offset, length);
    }

    @Override
    public long selectCountByName(String keyword) throws DaoException {
        return QueryExecutor.createExecutor().executeSelectCount(SELECT_COUNT_BY_NAME, keyword);
    }

    @Override
    public List<Monitor> selectByPrice(int offset, int length, String keyword) throws DaoException {
        return QueryExecutor.createExecutor()
                .executeSelectMultiple(SELECT_MULTIPLE_BY_PRICE, this, keyword, offset, length);
    }

    @Override
    public long selectCountByPrice(String keyword) throws DaoException {
        return QueryExecutor.createExecutor().executeSelectCount(SELECT_COUNT_BY_PRICE, keyword);
    }

    @Override
    public List<Monitor> selectByDescription(int offset, int length, String keyword) throws DaoException {
        return QueryExecutor.createExecutor()
                .executeSelectMultiple(SELECT_MULTIPLE_BY_DESCRIPTION, this, keyword, offset, length);
    }

    @Override
    public long selectCountByDescription(String keyword) throws DaoException {
        return QueryExecutor.createExecutor()
                .executeSelectCount(SELECT_COUNT_BY_DESCRIPTION, keyword);
    }

    @Override
    public List<Monitor> selectByPicturePath(int offset, int length, String keyword) throws DaoException {
        return QueryExecutor.createExecutor()
                .executeSelectMultiple(SELECT_MULTIPLE_BY_PICTURE_PATH, this, keyword, offset, length);
    }

    @Override
    public long selectCountByPicturePath(String keyword) throws DaoException {
        return QueryExecutor.createExecutor()
                .executeSelectCount(SELECT_COUNT_BY_PICTURE_PATH);
    }

    @Override
    public Monitor buildEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return MonitorDao.super.buildEntityFromResultSet(resultSet);
    }
}
