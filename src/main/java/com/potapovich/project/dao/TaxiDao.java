package com.potapovich.project.dao;

import com.potapovich.project.entity.Taxi;
import com.potapovich.project.exception.DaoException;

import java.util.List;

public interface TaxiDao {

    /**
     * Taxi registration (consists of driver and taxi car)
     * Void
     * @throws DaoException if SQLException
     */
    void registertTaxi(Taxi taxi) throws DaoException;

    /**
     * The beginning of the taxi work. After it, the taxi begins to be available to the user
     * Void
     * @throws DaoException if SQLException
     */
    void startTaxiWork(Taxi taxi) throws DaoException;

    /**
     * The end of the taxi. After it, the taxi stops being available to the customer
     * Void
     * @throws DaoException if SQLException
     */
    void finishTaxiWork(Taxi taxi) throws DaoException;

    /**
     * Finding a free taxi and further changing free status to busy
     * @return Taxi
     * @throws DaoException if SQLException
     */
    Taxi findActiveTaxiByDriverIdWithChangingFreeStatus(int driverId) throws DaoException;

    /**
     * Finding a free taxi with his personal number
     * @return Taxi
     * @throws DaoException if SQLException
     */
    Taxi findActiveTaxiByDriverId(int driverId) throws DaoException;

    /**
     * Finding a working taxi with his personal number
     * @return Taxi
     * @throws DaoException if SQLException
     */
    Taxi findTaxiById(int taxiId) throws DaoException;

    /**
     * Checks if a taxi exists with the help of a personal driver number and personal number of a taxi car
     * @return boolean
     * @throws DaoException if SQLException
     */
    boolean isTaxiExist(int driverId, int carId) throws DaoException;

    /**
     * Checks if this taxi exists with the help of a personal driver number and whether it is in working condition now
     * @return boolean
     * @throws DaoException if SQLException
     */
    boolean isTaxiActive(int driverId) throws DaoException;

    /**
     * Find a list of taxis in working order (on the marsh)
     * @return List<Taxi>
     * @throws DaoException if SQLException
     */
    List<Taxi> findListOfWorkingTaxi() throws DaoException;

    /**
     * Change of working status to "free"
     * Void
     * @throws DaoException if SQLException
     */
    void changeTaxiFreeTrue(int taxiId) throws DaoException;
}
