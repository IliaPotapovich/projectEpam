package com.potapovich.project.dao;

import com.potapovich.project.entity.TaxiDriver;
import com.potapovich.project.exception.DaoException;

import java.util.List;

public interface TaxiDriverDao {

    /**
     * Taxi driver registration
     * Void
     * @throws DaoException if SQLException
     */
    void driverRegistration(TaxiDriver driver) throws DaoException;

    /**
     * Search taxi driver by personal number
     * @return TaxiDriver
     * @throws DaoException if SQLException
     */
    TaxiDriver findDriverById(int id) throws DaoException;

    /**
     * Searching for a taxi driver by name
     * @return TaxiDriver
     * @throws DaoException if SQLException
     */
    TaxiDriver findDriverByName(String name) throws DaoException;

    /**
     * Search taxi driver by name and password. Applies to the entrance to the technical room
     * @return TaxiDriver
     * @throws DaoException if SQLException
     */
    TaxiDriver findDriverByLoginAndPassword(String name, String password) throws DaoException;

    /**
     * Displays all taxi drivers along with their personal data
     * @return List<TaxiDriver>
     * @throws DaoException if SQLException
     */
    List<TaxiDriver> findAllDriversWithPrivateData() throws DaoException;

    /**
     * Displays all deleted taxi drivers along with their personal data
     * @return List<TaxiDriver>
     * @throws DaoException if SQLException
     */
    List<TaxiDriver> findAllDeletedDrivers() throws DaoException;

    /**
     * Change the lock status of a taxi driver
     * void
     * @throws DaoException if SQLException
     */
    void updateDriverLockStatus(int driverId, boolean updateStatus) throws DaoException;

    /**
     * Changing the taxi driver password if he exists
     * @return boolean
     * @throws DaoException if SQLException
     */
    boolean changeDriverPassword(String login, String oldPass, String newPass, int driverId) throws DaoException;

    /**
     * Removal of the taxi driver if exists
     * @return boolean
     * @throws DaoException if SQLException
     */
    boolean deleteDriver(int driverId) throws DaoException;
}
