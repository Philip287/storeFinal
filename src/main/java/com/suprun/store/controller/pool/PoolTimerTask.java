package com.suprun.store.controller.pool;

import com.suprun.store.exception.DatabaseConnectionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;
import java.util.Queue;
import java.util.TimerTask;
import java.util.concurrent.locks.Lock;

public class PoolTimerTask extends TimerTask {
    public static final Logger logger = LogManager.getLogger();

    @Override
    public void run() {
        DatabaseConnectionPool pool = DatabaseConnectionPool.getInstance();
        Lock poolLock = pool.getPoolLock();
        Queue<Connection> availableConnections = pool.getAvailableConnections();
        Queue<Connection> usedConnections = pool.getUsedConnections();
        long connectionLifetime = pool.getConnectionLifetime();
        int minPoolSize = pool.getMinPoolSize();
        int maxPoolSize = pool.getMaxPoolSize();

        try {
            poolLock.lock();
            int availableConnectionsSize = availableConnections.size();

            if (availableConnectionsSize > minPoolSize) {
                for (Connection connection : availableConnections) {
                    ProxyConnection proxyConnection = (ProxyConnection) connection;
                    Instant lifetimeStart = proxyConnection.getLifetimeStart();
                    Instant currentTimestamp = Instant.now();
                    long lifetimeDuration = Duration.between(lifetimeStart, currentTimestamp).toMillis();

                    if (lifetimeDuration >= connectionLifetime) {
                        availableConnections.remove(connection);
                        proxyConnection.closeInnerConnection();
                    }

                    if (availableConnections.size() == minPoolSize) {
                        break;
                    }
                }
            } else if (availableConnectionsSize < minPoolSize
                    && availableConnectionsSize + usedConnections.size() < maxPoolSize) {
                for (int i = minPoolSize - availableConnectionsSize; i > 0; i--) {
                    availableConnections.add(ConnectionFactory.createConnection());
                }
            }
        } catch (SQLException e) {
            logger.error("Failed to close connection", e);
        } catch (DatabaseConnectionException e) {
            logger.error("Failed to establish connection", e);
        } finally {
            poolLock.unlock();
        }
    }
}
