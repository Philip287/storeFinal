package com.suprun.store.dao;

import com.suprun.store.entity.User;
import com.suprun.store.exception.DaoException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static com.suprun.store.dao.TableColum.*;
import static com.suprun.store.dao.TableColum.USER_EMAIL;
import static com.suprun.store.dao.TableColum.USER_ID;
import static com.suprun.store.dao.TableColum.USER_LOGIN;
import static com.suprun.store.dao.TableColum.USER_ROLE;

public interface UserDao extends BaseDao<User> {

    Optional<User> selectByEmail(String email) throws DaoException;


    List<User> selectByEmail(int offset, int length, String keyword) throws DaoException;

    long selectCountByEmail(String keyword) throws DaoException;

    Optional<User> selectByLogin(String login) throws DaoException;

    List<User> selectByLogin(int offset, int length, String keyword) throws DaoException;

    long selectCountByLogin(String keyword) throws DaoException;

    List<User> selectByRole(int offset, int length, String keyword) throws DaoException;

    long selectCountByRole(String keyword) throws DaoException;

    List<User> selectByStatus(int offset, int length, String keyword) throws DaoException;

    long selectCountByStatus(String keyword) throws DaoException;

    @Override
    default User buildEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return (User) User.builder()
                .setEmail(resultSet.getString(USER_EMAIL))
                .setLogin(resultSet.getString(USER_LOGIN))
                .setPasswordHash(resultSet.getBytes(USER_PASSWORD_HASH))
                .setSalt(resultSet.getBytes(USER_SALT))
                .setRole(User.UserRole.valueOf(resultSet.getString(USER_ROLE)))
                .setStatus(User.UserStatus.valueOf(resultSet.getString(USER_STATUS)))
                .setEntityId(resultSet.getLong(USER_ID))
                .build();
    }
}
