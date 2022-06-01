package com.suprun.store.dao.impl;

import com.suprun.store.dao.SpeakerDao;
import com.suprun.store.entity.Speaker;
import com.suprun.store.exception.DaoException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class SpeakerDaoImpl implements SpeakerDao {

    private static SpeakerDao instance;

    private static final String INSERT = """
            INSERT INTO speakers(name, price, picture_path, description)
            VALUES(?, ?, ?, ?);
            """;

    private static final String UPDATE = """
            UPDATE speakers
            SET name = ?, price ?, picture_path ?, description ?
            WHERE speaker_id = ?;
            """;

    private static final String DELETE = """
            UPDATE speakers
            SET deleted = 1
            WHERE speaker_id = ?;
            """;

    private static final String SELECT_ALL = """
            SELECT name, price, picture_path, description
            FROM speakers
            WHERE deleted <> 1
            ORDER BY speaker_id
            LIMIT ?, ?;
            """;

    private static final String SELECT_COUNT_ALL = """
            SELECT COUNT (speaker_id)
            FROM speakers
            WHERE deleted <> 1;
            """;

    private static final String SELECT_BY_ID = """
            SELECT name, price, picture_path, description
            FROM speakers
            WHERE speaker_id = ?;
            """;

    private static final String SELECT_MULTIPLE_BY_ID = """
            SELECT name, price, picture_path, description
            FROM speakers
            WHERE speaker_id LIKE CONCAT('%', ?, '%') AND deleted <> 1
            ORDER BY speaker_id
            LIMIT ?, ?;
            """;

    private static final String SELECT_COUNT_BY_ID = """
            SELECT COUNT (speaker_id)
            FROM speakers
            WHERE speaker_id LIKE CONCAT('%', ?, '%') AND deleted <> 1;
            """;

    private static final String SELECT_MULTIPLE_BY_NAME = """
            SELECT name, price, picture_path, description
            FROM speakers
            WHERE name LIKE CONCAT('%', ?, '%') AND deleted <> 1
            ORDER BY speaker_id
            LIMIT ?, ?;
            """;

    private static final String SELECT_COUNT_BY_NAME = """
            SELECT COUNT (speaker_id)
            FROM speakers
            WHERE name LIKE CONCAT('%', ?, '%') AND deleted <> 1;
            """;

    private static final String SELECT_MULTIPLE_BY_PRICE = """
            SELECT name, price, picture_path, description
            FROM speakers
            WHERE price LIKE CONCAT ('%', ?, '%') AND deleted <> 1
            ORDER BY speaker_id
            LIMIT ?, ?;
            """;

    private static final String SELECT_COUNT_BY_PRICE = """
            SELECT COUNT (speaker_id)
            FROM speakers
            WHERE price LIKE CONCAT ('%', ?, '%') AND deleted <> 1;
            """;

    private static final String SELECT_MULTIPLE_BY_PICTURE_PATH = """
            SELECT name, price, picture_path, description
            FROM speakers
            WHERE picture_path LIKE CONCAT ('%', ?, '%') AND deleted <> 1
            ORDER BY speaker_id
            LIMIT ?, ?;
            """;

    private static final String SELECT_COUNT_BY_PICTURE_PATH = """
            SELECT COUNT (speaker_id)
            FROM speakers
            WHERE picture_path LIKE CONCAT ('%', ?, '%') AND deleted <> 1;
            """;

    private static final String SELECT_MULTIPLE_BY_DESCRIPTION = """
            SELECT name, price, picture_path, description
            FROM speakers
            WHERE description LIKE CONCAT ('%', ?, '%') AND deleted <> 1
            ORDER BY speaker_id
            LIMIT ?, ?;
            """;

    private static final String SELECT_COUNT_BY_DESCRIPTION = """
            SELECT COUNT (speaker_id)
            FROM speakers
            WHERE description LIKE CONCAT ('%', ?, '%') AND deleted <> 1;
            """;

    private SpeakerDaoImpl() {

    }

    public static SpeakerDao getInstance() {
        if (instance == null) {
            instance = new SpeakerDaoImpl();
        }
        return instance;
    }

    @Override
    public long insert(Speaker entity) throws DaoException {
        return QueryExecutor.createExecutor().executeInsert(
                INSERT,
                entity.getName(),
                entity.getDescription(),
                entity.getPicturePath(),
                entity.getPrice()
        );
    }

    @Override
    public void update(Speaker entity) throws DaoException {
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
    public Optional<Speaker> selectById(long id) throws DaoException {
        return QueryExecutor.createExecutor().executeSelect(SELECT_BY_ID, this, id);
    }

    @Override
    public List<Speaker> selectById(int offset, int length, String keyword) throws DaoException {
        return QueryExecutor.createExecutor()
                .executeSelectMultiple(SELECT_MULTIPLE_BY_ID, this, keyword, offset, offset, length);
    }

    @Override
    public long selectCountById(String keyword) throws DaoException {
        return QueryExecutor.createExecutor().executeSelectCount(SELECT_COUNT_BY_ID, keyword);
    }

    @Override
    public List<Speaker> selectAll(int offset, int length) throws DaoException {
        return QueryExecutor.createExecutor().executeSelectMultiple(SELECT_ALL, this, offset, length);
    }

    @Override
    public long selectCountAll() throws DaoException {
        return QueryExecutor.createExecutor().executeSelectCount(SELECT_COUNT_ALL);
    }

    @Override
    public List<Speaker> selectByName(int offset, int length, String keyword) throws DaoException {
        return QueryExecutor.createExecutor()
                .executeSelectMultiple(SELECT_MULTIPLE_BY_NAME, this, keyword, offset, length);
    }

    @Override
    public long selectCountByName(String keyword) throws DaoException {
        return QueryExecutor.createExecutor().executeSelectCount(SELECT_COUNT_BY_NAME, keyword);
    }

    @Override
    public List<Speaker> selectByPrice(int offset, int length, String keyword) throws DaoException {
        return QueryExecutor.createExecutor()
                .executeSelectMultiple(SELECT_MULTIPLE_BY_PRICE, this, keyword, offset, length);
    }

    @Override
    public long selectCountByPrice(String keyword) throws DaoException {
        return QueryExecutor.createExecutor().executeSelectCount(SELECT_COUNT_BY_PRICE, keyword);
    }

    @Override
    public List<Speaker> selectByDescription(int offset, int length, String keyword) throws DaoException {
        return QueryExecutor.createExecutor()
                .executeSelectMultiple(SELECT_MULTIPLE_BY_DESCRIPTION, this, keyword, offset, length);
    }

    @Override
    public long selectCountByDescription(String keyword) throws DaoException {
        return QueryExecutor.createExecutor()
                .executeSelectCount(SELECT_COUNT_BY_DESCRIPTION, keyword);
    }

    @Override
    public List<Speaker> selectByPicturePath(int offset, int length, String keyword) throws DaoException {
        return QueryExecutor.createExecutor()
                .executeSelectMultiple(SELECT_MULTIPLE_BY_PICTURE_PATH, this, keyword, offset, length);
    }

    @Override
    public long selectCountByPicturePath(String keyword) throws DaoException {
        return QueryExecutor.createExecutor()
                .executeSelectCount(SELECT_COUNT_BY_PICTURE_PATH);
    }

    @Override
    public Speaker buildEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return SpeakerDao.super.buildEntityFromResultSet(resultSet);
    }

}
