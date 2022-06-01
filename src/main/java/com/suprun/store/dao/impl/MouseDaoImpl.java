package com.suprun.store.dao.impl;

import com.suprun.store.dao.MouseDao;
import com.suprun.store.entity.Mouse;
import com.suprun.store.exception.DaoException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class MouseDaoImpl implements MouseDao {


    private static MouseDao instance;

    private static final String INSERT = """
            INSERT INTO mouses (name, price, picture_path, description)
            VALUES(?, ?, ?, ?);
            """;

    private static final String UPDATE = """
            UPDATE mouses
            SET name = ?, price ?, picture_path ?, description ?
            WHERE mouse_id = ?;
            """;

    private static final String DELETE = """
            UPDATE mouses
            SET deleted = 1
            WHERE mouse_id = ?;
            """;

    private static final String SELECT_ALL = """
            SELECT name, price, picture_path, description
            FROM mouses
            WHERE deleted <> 1
            ORDER BY mouse_id
            LIMIT ?, ?;
            """;

    private static final String SELECT_COUNT_ALL = """
            SELECT COUNT (mouse_id)
            FROM mouses
            WHERE deleted <> 1;
            """;

    private static final String SELECT_BY_ID = """
            SELECT name, price, picture_path, description
            FROM mouses
            WHERE mouse_id = ?;
            """;

    private static final String SELECT_MULTIPLE_BY_ID = """
            SELECT name, price, picture_path, description
            FROM mouses
            WHERE mouse_id LIKE CONCAT('%', ?, '%') AND deleted <> 1
            ORDER BY mouse_id
            LIMIT ?, ?;
            """;

    private static final String SELECT_COUNT_BY_ID = """
            SELECT COUNT (hdd_id)
            FROM mouses
            WHERE mouse_id LIKE CONCAT('%', ?, '%') AND deleted <> 1;
            """;

    private static final String SELECT_MULTIPLE_BY_NAME = """
            SELECT name, price, picture_path, description
            FROM mouses
            WHERE name LIKE CONCAT('%', ?, '%') AND deleted <> 1
            ORDER BY mouse_id
            LIMIT ?, ?;
            """;

    private static final String SELECT_COUNT_BY_NAME = """
            SELECT COUNT (mouse_id)
            FROM mouses
            WHERE name LIKE CONCAT('%', ?, '%') AND deleted <> 1;
            """;

    private static final String SELECT_MULTIPLE_BY_PRICE = """
            SELECT name, price, picture_path, description
            FROM mouses
            WHERE price LIKE CONCAT ('%', ?, '%') AND deleted <> 1
            ORDER BY mouse_id
            LIMIT ?, ?;
            """;

    private static final String SELECT_COUNT_BY_PRICE = """
            SELECT COUNT (mouse_id)
            FROM mouses
            WHERE price LIKE CONCAT ('%', ?, '%') AND deleted <> 1;
            """;

    private static final String SELECT_MULTIPLE_BY_PICTURE_PATH = """
            SELECT name, price, picture_path, description
            FROM mouses
            WHERE picture_path LIKE CONCAT ('%', ?, '%') AND deleted <> 1
            ORDER BY mouse_id
            LIMIT ?, ?;
            """;

    private static final String SELECT_COUNT_BY_PICTURE_PATH = """
            SELECT COUNT (mouse_id)
            FROM mouses
            WHERE picture_path LIKE CONCAT ('%', ?, '%') AND deleted <> 1;
            """;

    private static final String SELECT_MULTIPLE_BY_DESCRIPTION = """
            SELECT name, price, picture_path, description
            FROM mouses
            WHERE description LIKE CONCAT ('%', ?, '%') AND deleted <> 1
            ORDER BY mouse_id
            LIMIT ?, ?;
            """;

    private static final String SELECT_COUNT_BY_DESCRIPTION = """
            SELECT COUNT (mouse_id)
            FROM mouses
            WHERE description LIKE CONCAT ('%', ?, '%') AND deleted <> 1;
            """;

    private MouseDaoImpl() {

    }

    public static MouseDao getInstance() {
        if (instance == null) {
            instance = new MouseDaoImpl();
        }
        return instance;
    }

    @Override
    public long insert(Mouse entity) throws DaoException {
        return QueryExecutor.createExecutor().executeInsert(
                INSERT,
                entity.getName(),
                entity.getDescription(),
                entity.getPicturePath(),
                entity.getPrice()
        );
    }

    @Override
    public void update(Mouse entity) throws DaoException {
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
    public Optional<Mouse> selectById(long id) throws DaoException {
        return QueryExecutor.createExecutor().executeSelect(SELECT_BY_ID, this, id);
    }

    @Override
    public List<Mouse> selectById(int offset, int length, String keyword) throws DaoException {
        return QueryExecutor.createExecutor()
                .executeSelectMultiple(SELECT_MULTIPLE_BY_ID, this, keyword, offset, offset, length);
    }

    @Override
    public long selectCountById(String keyword) throws DaoException {
        return QueryExecutor.createExecutor().executeSelectCount(SELECT_COUNT_BY_ID, keyword);
    }

    @Override
    public List<Mouse> selectAll(int offset, int length) throws DaoException {
        return QueryExecutor.createExecutor().executeSelectMultiple(SELECT_ALL, this, offset, length);
    }

    @Override
    public long selectCountAll() throws DaoException {
        return QueryExecutor.createExecutor().executeSelectCount(SELECT_COUNT_ALL);
    }

    @Override
    public List<Mouse> selectByName(int offset, int length, String keyword) throws DaoException {
        return QueryExecutor.createExecutor()
                .executeSelectMultiple(SELECT_MULTIPLE_BY_NAME, this, keyword, offset, length);
    }

    @Override
    public long selectCountByName(String keyword) throws DaoException {
        return QueryExecutor.createExecutor().executeSelectCount(SELECT_COUNT_BY_NAME, keyword);
    }

    @Override
    public List<Mouse> selectByPrice(int offset, int length, String keyword) throws DaoException {
        return QueryExecutor.createExecutor()
                .executeSelectMultiple(SELECT_MULTIPLE_BY_PRICE, this, keyword, offset, length);
    }

    @Override
    public long selectCountByPrice(String keyword) throws DaoException {
        return QueryExecutor.createExecutor().executeSelectCount(SELECT_COUNT_BY_PRICE, keyword);
    }

    @Override
    public List<Mouse> selectByDescription(int offset, int length, String keyword) throws DaoException {
        return QueryExecutor.createExecutor()
                .executeSelectMultiple(SELECT_MULTIPLE_BY_DESCRIPTION, this, keyword, offset, length);
    }

    @Override
    public long selectCountByDescription(String keyword) throws DaoException {
        return QueryExecutor.createExecutor()
                .executeSelectCount(SELECT_COUNT_BY_DESCRIPTION, keyword);
    }

    @Override
    public List<Mouse> selectByPicturePath(int offset, int length, String keyword) throws DaoException {
        return QueryExecutor.createExecutor()
                .executeSelectMultiple(SELECT_MULTIPLE_BY_PICTURE_PATH, this, keyword, offset, length);
    }

    @Override
    public long selectCountByPicturePath(String keyword) throws DaoException {
        return QueryExecutor.createExecutor()
                .executeSelectCount(SELECT_COUNT_BY_PICTURE_PATH);
    }

    @Override
    public Mouse buildEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return MouseDao.super.buildEntityFromResultSet(resultSet);
    }
}
