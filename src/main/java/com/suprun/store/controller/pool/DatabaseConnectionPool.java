package com.suprun.store.controller.pool;

import com.suprun.store.exception.DatabaseConnectionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class DatabaseConnectionPool {

    public static final Logger LOGGER = LogManager.getLogger();
    private static final String POOL_PROPERTIES_NAME = "properties/pool.properties";
    private static final String MIN_POOL_SIZE_PROPERTY = "minPoolSize";
    private static final String MAX_POOL_SIZE_PROPERTY = "maxPoolSize";
    public static final String CONNECTION_LIFETIME_PROPERTY = "connectionLifetime";
    public static final String POOL_CHECK_DELAY_PROPERTY = "poolCheckDelay";
    public static final String POOL_CHECK_PERIOD_PROPERTY = "poolCheckPeriod";

    private static final AtomicReference<DatabaseConnectionPool> instance = new AtomicReference<>(null);
    private static final AtomicBoolean isInitialized = new AtomicBoolean(false);

    private final int minPoolSize;
    private final int maxPoolSize;
    private final long connectionLifetime;

    private final Lock poolLock = new ReentrantLock(true);
    private final Queue<Connection> availableConnections;
    private final Queue<Connection> usedConnections;

    public static DatabaseConnectionPool getInstance() {
        while (instance.get() == null) {
            if (isInitialized.compareAndSet(false, true)) {
                instance.set(new DatabaseConnectionPool());
            }
        }
        return instance.get();
    }

    private DatabaseConnectionPool() {
        ClassLoader classLoader = ConnectionFactory.class.getClassLoader();
        int poolCheckDelay;
        int poolCheckPeriod;
        try (InputStream inputStream = classLoader.getResourceAsStream(POOL_PROPERTIES_NAME)) {
            Properties poolProperties = new Properties();
            poolProperties.load(inputStream);

            minPoolSize = Integer.parseInt(poolProperties.getProperty(MIN_POOL_SIZE_PROPERTY));
            maxPoolSize = Integer.parseInt(poolProperties.getProperty(MAX_POOL_SIZE_PROPERTY));

            if (minPoolSize > maxPoolSize) {
                LOGGER.fatal("Min size of pool is greater than max size");
                throw new RuntimeException("Min size of pool is greater than max size");
            }

            connectionLifetime = Long.parseLong(poolProperties.getProperty(CONNECTION_LIFETIME_PROPERTY));
            poolCheckDelay = Integer.parseInt(poolProperties.getProperty(POOL_CHECK_DELAY_PROPERTY));
            poolCheckPeriod = Integer.parseInt(poolProperties.getProperty(POOL_CHECK_PERIOD_PROPERTY));

            availableConnections = new ArrayDeque<>(maxPoolSize);
            usedConnections = new ArrayDeque<>(maxPoolSize);

            for (int i = 0; i < minPoolSize; i++) {
                Connection connection = ConnectionFactory.createConnection();
                availableConnections.add(connection);
            }
        } catch (IOException e) {
            LOGGER.fatal("Couldn't read connection pool properties file", e);
            throw new RuntimeException("Couldn't read connection pool properties file", e);
        } catch (NumberFormatException e) {
            LOGGER.fatal("Couldn't configure vital parameters of connection pool", e);
            throw new RuntimeException("Couldn't configure size of connection pool", e);
        } catch (DatabaseConnectionException e) {
            LOGGER.fatal("Error establishing connection pool", e);
            throw new RuntimeException("Error establishing connection pool", e);
        }

        PoolTimerTask task = new PoolTimerTask();
        Timer timer = new Timer(true);
        timer.schedule(task, poolCheckDelay, poolCheckPeriod);
    }

    public Connection getConnection() throws DatabaseConnectionException {
        try {
            poolLock.lock();
            Connection connection = null;

            if (availableConnections.isEmpty() && usedConnections.size() < maxPoolSize) {
                availableConnections.add(ConnectionFactory.createConnection());
            }

            try {
                connection = availableConnections.remove();
                usedConnections.add(connection);
                ProxyConnection proxyConnection = (ProxyConnection) connection;
                proxyConnection.setLifetimeStart(Instant.now());
            } catch (NoSuchElementException | IllegalStateException e) {
                LOGGER.error("Unexpected exception", e);
            }
            return connection;
        } finally {
            poolLock.unlock();
        }
    }

    public void releaseConnection(Connection connection) {
        try {
            poolLock.lock();

            try {
                usedConnections.remove(connection);
                availableConnections.add(connection);
            } catch (NoSuchElementException | IllegalStateException e) {
                LOGGER.error("Unexpected exception", e);
            }
        } finally {
            poolLock.unlock();
        }
    }

    public void destroy() {
        try {
            poolLock.lock();
            for (int i = 0; i < availableConnections.size(); i++) {
                try {
                    ProxyConnection connection = (ProxyConnection) availableConnections.remove();
                    connection.closeInnerConnection();
                } catch (SQLException e) {
                    LOGGER.error("Failed to close connection", e);
                }
            }

            for (int i = 0; i < usedConnections.size(); i++) {
                try {
                    ProxyConnection connection = (ProxyConnection) usedConnections.remove();
                    connection.closeInnerConnection();
                } catch (SQLException e) {
                    LOGGER.error("Failed to close connection", e);
                }
            }
        } finally {
            poolLock.unlock();
        }

        DriverManager.getDrivers()
                .asIterator()
                .forEachRemaining(driver -> {
                    try {
                        DriverManager.deregisterDriver(driver);
                    } catch (SQLException e) {
                        LOGGER.error("Failed to deregister driver: ", e);
                    }
                });
    }

    Lock getPoolLock() {
        return poolLock;
    }

    Queue<Connection> getAvailableConnections() {
        return availableConnections;
    }

    Queue<Connection> getUsedConnections() {
        return usedConnections;
    }

    int getMinPoolSize() {
        return minPoolSize;
    }

    int getMaxPoolSize() {
        return maxPoolSize;
    }

    long getConnectionLifetime() {
        return connectionLifetime;
    }
}
