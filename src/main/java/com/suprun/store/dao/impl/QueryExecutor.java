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

/**
 * {@code QueryExecutor} class provides logic for executing database queries.
 * It also allows to execute sequence of queries as a transaction.
 *
 * @author Philip Suprun
 */
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

    /**
     * Creates executor instance to execute queries one by one.
     *
     * @return executor instance.
     * @throws DaoException if an error occurred while creating executor.
     */
    static QueryExecutor createExecutor() throws DaoException {
        return createExecutor(false);
    }


    /**
     * Creates executor instance to execute queries as a transaction.
     *
     * @return executor instance.
     * @throws DaoException if an error occurred while creating executor.
     */
    static QueryExecutor createTransactionExecutor() throws DaoException {
        return createExecutor(true);
    }

    private static QueryExecutor createExecutor(boolean transcription) throws DaoException {
        try {
            return new QueryExecutor(transcription);
        } catch (DatabaseConnectionException | SQLException e) {
            throw new DaoException("Can not created query executor", e);
        }
    }

    /**
     * Execute SELECT query for single result.
     *
     * @param sql    is an SQL query string which will be executed as prepared statement.
     * @param dao    interface instance used to specify the way to build entity from {@link ResultSet}.
     * @param params are parameters that will be set to prepared statement.
     * @param <T>    entity to select.
     * @return {@code T} object wrapped with {@link Optional}.
     * @throws DaoException if an error occurred while processing the query.
     */
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

    /**
     * Execute SELECT query for multiple results.
     *
     * @param sql    is an SQL query string which will be executed as prepared statement.
     * @param dao    interface instance used to specify the way to build entity from {@link ResultSet}.
     * @param params are parameters that will be set to prepared statement.
     * @param <T>    entity to select.
     * @return {@link List} of {@code T} objects.
     * @throws DaoException if an error occurred while processing the query.
     */
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


    /**
     * Execute SELECT query for finding COUNT of matches to specified condition.
     *
     * @param sql    is an SQL query string which will be executed as prepared statement.
     * @param params are parameters that will be set to prepared statement.
     * @return long number of matches.
     * @throws DaoException if an error occurred while processing the query.
     */
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


    /**
     * Execute UPDATE or DELETE query.
     *
     * @param sql    is an SQL query string which will be executed as prepared statement.
     * @param params are parameters that will be set to prepared statement.
     * @throws DaoException if an error occurred while processing the query.
     */
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

    /**
     * Execute INSERT query.
     *
     * @param sql    is an SQL query string which will be executed as prepared statement.
     * @param params are parameters that will be set to prepared statement.
     * @return {@code long} generated primary key.
     * @throws DaoException if an error occurred while processing the query.
     */
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


    /**
     * Commit transaction.
     *
     * @throws DaoException if a database error occurred.
     */
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
