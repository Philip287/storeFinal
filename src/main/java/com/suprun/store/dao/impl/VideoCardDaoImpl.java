package com.suprun.store.dao.impl;

import com.suprun.store.dao.VideoCardDao;
import com.suprun.store.entity.VideoCard;
import com.suprun.store.exception.DaoException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class VideoCardDaoImpl implements VideoCardDao {

    private static VideoCardDao instance;

    private static final String INSERT = """
            INSERT INTO videocards(name, price, picture_path, description)
            VALUES(?, ?, ?, ?);
            """;

    private static final String UPDATE = """
            UPDATE videocards
            SET name = ?, price ?, picture_path ?, description ?
            WHERE video_card_id = ?;
            """;

    private static final String DELETE = """
            UPDATE videocards
            SET deleted = 1
            WHERE video_card_id = ?;
            """;

    private static final String SELECT_ALL = """
            SELECT name, price, picture_path, description
            FROM videocards
            WHERE deleted <> 1
            ORDER BY video_card_id
            LIMIT ?, ?;
            """;

    private static final String SELECT_COUNT_ALL = """
            SELECT COUNT (video_card_id)
            FROM videocards
            WHERE deleted <> 1;
            """;

    private static final String SELECT_BY_ID = """
            SELECT name, price, picture_path, description
            FROM videocards
            WHERE video_card_id = ?;
            """;

    private static final String SELECT_MULTIPLE_BY_ID = """
            SELECT name, price, picture_path, description
            FROM videocards
            WHERE video_card_id LIKE CONCAT('%', ?, '%') AND deleted <> 1
            ORDER BY video_card_id
            LIMIT ?, ?;
            """;

    private static final String SELECT_COUNT_BY_ID = """
            SELECT COUNT (video_card_id)
            FROM videocards
            WHERE video_card_id LIKE CONCAT('%', ?, '%') AND deleted <> 1;
            """;

    private static final String SELECT_MULTIPLE_BY_NAME = """
            SELECT name, price, picture_path, description
            FROM videocards
            WHERE name LIKE CONCAT('%', ?, '%') AND deleted <> 1
            ORDER BY video_card_id
            LIMIT ?, ?;
            """;

    private static final String SELECT_COUNT_BY_NAME = """
            SELECT COUNT (video_card_id)
            FROM videocards
            WHERE name LIKE CONCAT('%', ?, '%') AND deleted <> 1;
            """;

    private static final String SELECT_MULTIPLE_BY_PRICE = """
            SELECT name, price, picture_path, description
            FROM videocards
            WHERE price LIKE CONCAT ('%', ?, '%') AND deleted <> 1
            ORDER BY video_card_id
            LIMIT ?, ?;
            """;

    private static final String SELECT_COUNT_BY_PRICE = """
            SELECT COUNT (video_card_id)
            FROM videocards
            WHERE price LIKE CONCAT ('%', ?, '%') AND deleted <> 1;
            """;

    private static final String SELECT_MULTIPLE_BY_PICTURE_PATH = """
            SELECT name, price, picture_path, description
            FROM videocards
            WHERE picture_path LIKE CONCAT ('%', ?, '%') AND deleted <> 1
            ORDER BY video_card_id
            LIMIT ?, ?;
            """;

    private static final String SELECT_COUNT_BY_PICTURE_PATH = """
            SELECT COUNT (video_card_id)
            FROM videocards
            WHERE picture_path LIKE CONCAT ('%', ?, '%') AND deleted <> 1;
            """;

    private static final String SELECT_MULTIPLE_BY_DESCRIPTION = """
            SELECT name, price, picture_path, description
            FROM videocards
            WHERE description LIKE CONCAT ('%', ?, '%') AND deleted <> 1
            ORDER BY video_card_id
            LIMIT ?, ?;
            """;

    private static final String SELECT_COUNT_BY_DESCRIPTION = """
            SELECT COUNT (video_card_id)
            FROM videocards
            WHERE description LIKE CONCAT ('%', ?, '%') AND deleted <> 1;
            """;

    private VideoCardDaoImpl() {

    }

    public static VideoCardDao getInstance() {
        if (instance == null) {
            instance = new VideoCardDaoImpl();
        }
        return instance;
    }

    @Override
    public long insert(VideoCard entity) throws DaoException {
        return QueryExecutor.createExecutor().executeInsert(
                INSERT,
                entity.getName(),
                entity.getDescription(),
                entity.getPicturePath(),
                entity.getPrice()
        );
    }

    @Override
    public void update(VideoCard entity) throws DaoException {
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
    public Optional<VideoCard> selectById(long id) throws DaoException {
        return QueryExecutor.createExecutor().executeSelect(SELECT_BY_ID, this, id);
    }

    @Override
    public List<VideoCard> selectById(int offset, int length, String keyword) throws DaoException {
        return QueryExecutor.createExecutor()
                .executeSelectMultiple(SELECT_MULTIPLE_BY_ID, this, keyword, offset, offset, length);
    }

    @Override
    public long selectCountById(String keyword) throws DaoException {
        return QueryExecutor.createExecutor().executeSelectCount(SELECT_COUNT_BY_ID, keyword);
    }

    @Override
    public List<VideoCard> selectAll(int offset, int length) throws DaoException {
        return QueryExecutor.createExecutor().executeSelectMultiple(SELECT_ALL, this, offset, length);
    }

    @Override
    public long selectCountAll() throws DaoException {
        return QueryExecutor.createExecutor().executeSelectCount(SELECT_COUNT_ALL);
    }

    @Override
    public List<VideoCard> selectByName(int offset, int length, String keyword) throws DaoException {
        return QueryExecutor.createExecutor()
                .executeSelectMultiple(SELECT_MULTIPLE_BY_NAME, this, keyword, offset, length);
    }

    @Override
    public long selectCountByName(String keyword) throws DaoException {
        return QueryExecutor.createExecutor().executeSelectCount(SELECT_COUNT_BY_NAME, keyword);
    }

    @Override
    public List<VideoCard> selectByPrice(int offset, int length, String keyword) throws DaoException {
        return QueryExecutor.createExecutor()
                .executeSelectMultiple(SELECT_MULTIPLE_BY_PRICE, this, keyword, offset, length);
    }

    @Override
    public long selectCountByPrice(String keyword) throws DaoException {
        return QueryExecutor.createExecutor().executeSelectCount(SELECT_COUNT_BY_PRICE, keyword);
    }

    @Override
    public List<VideoCard> selectByDescription(int offset, int length, String keyword) throws DaoException {
        return QueryExecutor.createExecutor()
                .executeSelectMultiple(SELECT_MULTIPLE_BY_DESCRIPTION, this, keyword, offset, length);
    }

    @Override
    public long selectCountByDescription(String keyword) throws DaoException {
        return QueryExecutor.createExecutor()
                .executeSelectCount(SELECT_COUNT_BY_DESCRIPTION, keyword);
    }

    @Override
    public List<VideoCard> selectByPicturePath(int offset, int length, String keyword) throws DaoException {
        return QueryExecutor.createExecutor()
                .executeSelectMultiple(SELECT_MULTIPLE_BY_PICTURE_PATH, this, keyword, offset, length);
    }

    @Override
    public long selectCountByPicturePath(String keyword) throws DaoException {
        return QueryExecutor.createExecutor()
                .executeSelectCount(SELECT_COUNT_BY_PICTURE_PATH);
    }

    @Override
    public VideoCard buildEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return VideoCardDao.super.buildEntityFromResultSet(resultSet);
    }

}
