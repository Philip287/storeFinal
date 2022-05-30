package com.suprun.store.dao.impl;

import com.suprun.store.entity.controller.pool.DatabaseConnectionPool;
import com.suprun.store.dao.BaseDao;
import com.suprun.store.entity.AbstractEntity;
import com.suprun.store.exception.DaoException;
import com.suprun.store.exception.DatabaseConnectionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class QueryExecutor {
    private static final Logger LOGGER = LogManager.getLogger();

    private final Connection connection;
    private final boolean transaction;
    private boolean destroyed;

    private QueryExecutor(boolean transcription) throws DatabaseConnectionException, SQLException {
        this.transaction = transcription;
        connection = DatabaseConnectionPool.getInstance().getConnection();
        if (transcription) {
            connection.setAutoCommit(false);
        }
    }

    static QueryExecutor createExecutor() throws DaoException {
        return createExecutor(false);
    }

    static QueryExecutor createdTransactionExecutor() throws DaoException {
        return createExecutor(true);
    }

    private static QueryExecutor createExecutor(boolean transcription) throws DaoException {
        try {
            return new QueryExecutor(transcription);
        } catch (DatabaseConnectionException | SQLException e) {
            throw new DaoException("Can not created query executor", e);
        }
    }


    <T extends AbstractEntity> Optional<T> executeSelect(String sql, BaseDao<T> dao, Object... params) throws DaoException {
        T entity = null;
        try (PreparedStatement statement = prepareStatement(sql, Statement.NO_GENERATED_KEYS, params)) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                entity = dao.buildEntityFromResultSet(resultSet);
            }
            return Optional.ofNullable(entity);
        } catch (SQLException e) {
            destroyExecutor();
            throw new DaoException(" ", e);
        } finally {
            if (!transaction && !destroyed) {
                destroyExecutor();
            }
        }
    }

    <T extends AbstractEntity> List<T> executeSelectMultiple(String sql, BaseDao<T> dao, Object... params)
            throws DaoException {
        try (PreparedStatement statement = prepareStatement(sql, Statement.NO_GENERATED_KEYS, params)) {
            ResultSet resultSet = statement.executeQuery();
            List<T> entityList = new ArrayList<>();
            while (resultSet.next()) {
                T entity = dao.buildEntityFromResultSet(resultSet);
                entityList.add(entity);
            }
            return entityList;
        } catch (SQLException e) {
            destroyExecutor();
            throw new DaoException(e);
        } finally {
            if (!transaction && !destroyed) {
                destroyExecutor();
            }
        }
    }

    long executeSelectCount(String sql, Object... params) throws DaoException {
        try (PreparedStatement statement = prepareStatement(sql, Statement.NO_GENERATED_KEYS, params)) {
            ResultSet resultSet = statement.executeQuery();
            long count = 0;
            if (resultSet.next()) {
                count = resultSet.getLong(1);
            }
            return count;
        } catch (SQLException e) {
            destroyExecutor();
            throw new DaoException(e);
        } finally {
            if (!transaction && !destroyed) {
                destroyExecutor();
            }
        }
    }

    void executeUpdateOrDelete(String sql, Object... params) throws DaoException {
        if (destroyed) {
            throw new DaoException("Context is already terminated");
        }

        try (PreparedStatement statement = prepareStatement(sql, Statement.NO_GENERATED_KEYS, params)) {
            statement.execute();
        } catch (SQLException e) {
            if (transaction) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    throw new DaoException(ex);
                }
            }
            destroyExecutor();
            throw new DaoException(e);
        } finally {
            if (!transaction && !destroyed) {
                destroyExecutor();
            }
        }
    }

    long executeInsert(String sql, Object... params) throws DaoException {
        try (PreparedStatement statement = prepareStatement(sql, Statement.NO_GENERATED_KEYS, params)) {
            statement.execute();
            ResultSet generatedKey = statement.getGeneratedKeys();
            return generatedKey.next() ? generatedKey.getLong(1) : 0;
        } catch (SQLException e) {
            if (transaction) {
                try {
                    connection.rollback();
                } catch (SQLException exception) {
                    throw new DaoException(exception);
                }
            }
            destroyExecutor();
            throw new DaoException(e);
        } finally {
            if (!transaction && !destroyed) {
                destroyExecutor();
            }
        }
    }

    void commit() throws DaoException {
        if (destroyed) {
            throw new DaoException("Context is already terminated");
        }

        if (transaction) {
            try {
                connection.commit();
            } catch (SQLException e) {
                throw new DaoException("Can not commit transaction", e);
            }
        }
    }

    private PreparedStatement prepareStatement(String sql, int generatedKeys, Object... params) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sql, generatedKeys);
        int i = 1;
        for (Object param : params) {
            preparedStatement.setObject(i++, param);
        }
        return preparedStatement;
    }

    private void destroyExecutor() {
        destroyed = true;
        try {
            if (transaction) {
                connection.setAutoCommit(true);
            }
            connection.close();
        } catch (SQLException e) {
            LOGGER.error("Can not close connection: ", e);
        }
    }
}
