package com.suprun.store.dao.impl;

import com.suprun.store.dao.UserDao;
import com.suprun.store.entity.User;
import com.suprun.store.exception.DaoException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {

    private static UserDao instance;

    private static final String SELECT_ALL = """
            SELECT user_id, email, login, password_nash, salt, role, status
            FROM users
            WHERE status <> 'DELETED'
            ORDER BY user_id
            LIMIT ?, ?;
            """;

    private static final String INSERT = """
            INSERT INTO users(email, login, password_hash, salt, role, status)
            VALUES(?, ?, ?, ?, ?, ?)
            """;

    private static final String UPDATE = """
            UPDATE user
            SET email = ?, login = ?, password_hash = ?; salt = ?; role = ?, status = ?
            WHERE user_id = ?;
            """;


    public static UserDao getInstance() {
        if (instance == null) {
            instance = new UserDaoImpl();
        }
        return instance;
    }

    @Override
    public long insert(User entity) throws DaoException {
        return QueryExecutor.createExecutor().executeInsert(
                INSERT,
                entity.getEmail(),
                entity.getLogin(),
                entity.getPasswordHash(),
                entity.getSalt(),
                entity.getRole().toString(),
                entity.getStatus().toString()
        );
    }

    @Override
    public void update(User entity) throws DaoException {
        QueryExecutor.createExecutor().executeUpdateOrDelete(
                UPDATE,
                entity.getEmail(),
                entity.getLogin(),
                entity.getPasswordHash(),
                entity.getSalt(),
                entity.getRole().toString(),
                entity.getStatus().toString(),
                entity.getEntityId()
        );
    }

    @Override
    public void delete(long id) throws DaoException {

    }

    @Override
    public Optional<User> selectById(long id) throws DaoException {
        return Optional.empty();
    }

    @Override
    public List<User> selectById(int offset, int length, String keyword) throws DaoException {
        return null;
    }

    @Override
    public long selectCountById(String keyword) throws DaoException {
        return 0;
    }

    @Override
    public List<User> selectAll(int offset, int length) throws DaoException {
        return QueryExecutor.createExecutor().executeSelectMultiple(SELECT_ALL, this, offset, length);
    }

    @Override
    public long selectCountAll() throws DaoException {
        return 0;
    }

    @Override
    public Optional<User> selectByEmail(String email) throws DaoException {
        return Optional.empty();
    }

    @Override
    public List<User> selectByEmail(int offset, int length, String keyword) throws DaoException {
        return null;
    }

    @Override
    public long selectCountByEmail(String keyword) throws DaoException {
        return 0;
    }

    @Override
    public Optional<User> selectByLogin(String login) throws DaoException {
        return Optional.empty();
    }

    @Override
    public List<User> selectByLogin(int offset, int length, String keyword) throws DaoException {
        return null;
    }

    @Override
    public long selectCountByLogin(String keyword) throws DaoException {
        return 0;
    }

    @Override
    public List<User> selectByRole(int offset, int length, String keyword) throws DaoException {
        return null;
    }

    @Override
    public long selectCountByRole(String keyword) throws DaoException {
        return 0;
    }

    @Override
    public List<User> selectByStatus(int offset, int length, String keyword) throws DaoException {
        return null;
    }

    @Override
    public long selectCountByStatus(String keyword) throws DaoException {
        return 0;
    }

    @Override
    public User buildEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return null;
    }
}
