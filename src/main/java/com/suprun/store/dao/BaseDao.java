package com.suprun.store.dao;

import com.suprun.store.entity.AbstractEntity;
import com.suprun.store.exception.DaoException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface BaseDao<T extends AbstractEntity> {
    long insert(T entity) throws DaoException;

    void update(T entity) throws DaoException;

    void delete(long id) throws DaoException;

    Optional<T> selectById(long id) throws DaoException;

    List<T> selectById(int offset, int length, String keyword) throws DaoException;

    long selectCountById(String keyword) throws DaoException;

    List<T> selectAll(int offset, int length) throws DaoException;

    long selectCountAll() throws DaoException;

    T buildEntityFromResultSet(ResultSet resultSet) throws SQLException;


}
