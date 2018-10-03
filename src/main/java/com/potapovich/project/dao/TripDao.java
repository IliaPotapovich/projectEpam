package com.potapovich.project.dao;

import com.potapovich.project.entity.Trip;
import com.potapovich.project.exception.DaoException;

import java.util.List;

public interface TripDao {

    /**
     * Create a new trip
     * @return int (Trip Id)
     * @throws DaoException if SQLException
     */
    int startTrip(int customerId, int taxiId, String customerPhone, String customerName, double cost) throws DaoException;

    /**
     * Changes in the parameters of the current trip
     * Void
     * @throws DaoException if SQLException
     */
    void changeCurrentTrip(int tripId, int taxiId, String customerPhone, String customerName, double cost) throws DaoException;

    /**
     * Changes in the status of the current trip to "not on the road"
     * Void
     * @throws DaoException if SQLException
     */
    void changeCurrentInWayTripStatus(int taxiId) throws DaoException;

    /**
     * Find the personal number of the trip, which is currently performed by the personal taxi number
     * @return int (Trip Id)
     * @throws DaoException if SQLException
     */
    int findActiveTripIdByTaxiId(int taxi_id) throws DaoException;

    /**
     * Find the personal number of the trip, which is currently performed by the personal taxi number
     * @return boolean
     * @throws DaoException if SQLException
     */
    boolean isActiveTripExist(int taxiId) throws DaoException;

    /**
     * End your current trip
     * Void
     * @throws DaoException if SQLException
     */
    void finishTrip(int taxiId, int taxiMark) throws DaoException;

    /**
     * Show a list of trips confirmed by the user
     * @return List<Trip>
     * @throws DaoException if SQLException
     */
    List<Trip> findTripsByUserIdForUser(int userId) throws DaoException;

    /**
     * Show a list of trips confirmed by a taxi driver
     * @return List<Trip>
     * @throws DaoException if SQLException
     */
    List<Trip> findTripsByDriverIdForDriver(int driverId) throws DaoException;

    /**
     * Display a full list of trips
     * @return List<Trip>
     * @throws DaoException if SQLException
     */
    List<Trip> findListOfTrips() throws DaoException;

    /**
     * Display a list of current trips
     * @return List<Trip>
     * @throws DaoException if SQLException
     */
    List<Trip> findActiveListOfTrips() throws DaoException;

    /**
     * Display a list of completed trips
     * @return List<Trip>
     * @throws DaoException if SQLException
     */
    List<Trip> findFinishedListOfTrips() throws DaoException;

    /**
     * Find a trip by her personal number
     * @return Trip
     * @throws DaoException if SQLException
     */
    Trip findTripById(int tripId) throws DaoException;
}
