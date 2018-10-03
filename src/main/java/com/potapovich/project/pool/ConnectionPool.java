package com.potapovich.project.pool;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {

    private final static int POOL_SIZE = 10;
    private static final Logger LOGGER = LogManager.getLogger();
    private static ConnectionPool instance;
    private static AtomicBoolean instanceCreated = new AtomicBoolean();
    private static ReentrantLock lock = new ReentrantLock();
    private BlockingQueue<ProxyConnection> queue;

    /**
     * Reflection protection.
     * You cannot create an object from the outside directly.
     */
    private ConnectionPool() {
        if (instance != null) {
            throw new RuntimeException("Reflection defender");
        }
        createPool();
    }

    /**
     * The method by which we can create a single object. Triple lock is used
     */
    public static ConnectionPool getInstance() {
        if (!instanceCreated.get()) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new ConnectionPool();
                    instanceCreated.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    /**
     * Protection against cloning
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    /**
     * Creating a restricted pool of connections using the Proxy template
     */
    private void createPool() {
        ProxyConnection proxyConnection;
        queue = new ArrayBlockingQueue(POOL_SIZE);
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            for (int i = 0; i < POOL_SIZE; i++) {
                Connection connection = DriverManager.getConnection(DataBaseConfig.url, DataBaseConfig.user, DataBaseConfig.password);
                proxyConnection = new ProxyConnection(connection);
                queue.offer(proxyConnection);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "SQLException ", e.getMessage());
        }
    }

    /**
     * Get an instance of connection for further connection to the database.
     */
    public ProxyConnection getConnection() {
        ProxyConnection proxyConnection = null;
        try {
            proxyConnection = queue.take();
        } catch (InterruptedException e) {
            LOGGER.log(Level.ERROR, "InterruptedException ", e.getMessage());
        }
        return proxyConnection;
    }

    /**
     * Return a connection to the connection pool.
     */
    public void returnConnection(ProxyConnection proxyConnection) {
        try {
            queue.put(proxyConnection);
        } catch (InterruptedException e) {
            LOGGER.log(Level.ERROR, "InterruptedException ", e.getMessage());
        }
    }

    /**
     * Closing a connection pool and unregistering drivers.
     */
    public void releasePool() {
        ProxyConnection proxyConnection;
        try {
            for (int i = 0; i < POOL_SIZE; i++) {
                proxyConnection = queue.take();
                proxyConnection.closeConnection();
            }
        } catch (InterruptedException e) {
            LOGGER.log(Level.ERROR, "InterruptedException ", e.getMessage());
            Thread.currentThread().interrupt();
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "SQLException ", e.getMessage());
        } finally {
            try {
                Enumeration<java.sql.Driver> driverEnumeration = DriverManager.getDrivers();
                while (driverEnumeration.hasMoreElements()) {
                    Driver driver = driverEnumeration.nextElement();
                    DriverManager.deregisterDriver(driver);
                }
            } catch (SQLException e) {
                LOGGER.log(Level.ERROR, "SQLException ", e.getMessage());
            }
        }
    }
}
