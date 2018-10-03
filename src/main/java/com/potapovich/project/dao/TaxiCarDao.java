package com.potapovich.project.dao;

import com.potapovich.project.entity.TaxiCar;
import com.potapovich.project.exception.DaoException;

import java.util.List;

public interface TaxiCarDao {

    /**
     * Taxi Car Registration
     * Void
     * @throws DaoException if SQLException
     */
    void carRegistration(TaxiCar car) throws DaoException;

    /**
     * Search for a taxi by his personal number
     * @return TaxiCar
     * @throws DaoException if SQLException
     */
    TaxiCar findCarById(int id) throws DaoException;

    /**
     * Search for a taxi on the personal number of its owner
     * @return List<TaxiCar>
     * @throws DaoException if SQLException
     */
    List<TaxiCar> findCarsByOwnerId(int ownerId) throws DaoException;

    /**
     * Displays an existing list of taxi cars
     * @return List<TaxiCar>
     * @throws DaoException if SQLException
     */
    List<TaxiCar> findAllCars() throws DaoException;

    /**
     * Displays a list of deleted taxi cars
     * @return List<TaxiCar>
     * @throws DaoException if SQLException
     */
    List<TaxiCar> findAllDeletedCars() throws DaoException;

    /**
     * Removing an existing taxi car
     * @return boolean
     * @throws DaoException if SQLException
     */
    boolean deleteCar(int carId) throws DaoException;

    /**
     * Change a personal picture of a taxi car
     * @return boolean
     * @throws DaoException if SQLException
     */
    boolean changeCarAvatar(int carId, int imageId, int ownerId) throws DaoException;

    /**
     * Finds the last recorded personal photo number of a taxi car for the further of its increment
     * @return int
     * @throws DaoException if SQLException
     */
    int findLastCarImageId() throws DaoException;



}
