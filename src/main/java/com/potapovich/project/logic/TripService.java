package com.potapovich.project.logic;

import com.potapovich.project.dao.TripDao;
import com.potapovich.project.dao.TripDaoImpl;
import com.potapovich.project.entity.Trip;
import com.potapovich.project.exception.DaoException;
import com.potapovich.project.exception.LogicException;
import java.util.List;

public class TripService {


    public int startTrip(int customerId, int taxiId, String customerPhone, String customerName, double cost) throws LogicException {
        TripDao tripDao = new TripDaoImpl();
        int tripId;
        try {
            tripId = tripDao.startTrip(customerId, taxiId, customerPhone, customerName, cost);
        } catch (DaoException e) {
            throw new LogicException("startTripError ", e);
        }
        return tripId;
    }


    public void changeTrip(int tripId, int taxiId, String customerPhone, String customerName, double cost) throws LogicException {
        TripDao tripDao = new TripDaoImpl();
        try {
            tripDao.changeCurrentTrip(tripId, taxiId, customerPhone, customerName, cost);
        } catch (DaoException e) {
            throw new LogicException("changeTripError ", e);
        }
    }


    public void changeInWayTripStatus(int taxiId) throws LogicException {
        TripDao tripDao = new TripDaoImpl();
        try {
            tripDao.changeCurrentInWayTripStatus(taxiId);
        } catch (DaoException e) {
            throw new LogicException("changeInWayTripStatusError ", e);
        }
    }

    public void changeInWayTripStatusOnFalse(int taxiId) throws LogicException {
        TripDao tripDao = new TripDaoImpl();
        try {
            tripDao.changeCurrentInWayTripStatus(taxiId);
        } catch (DaoException e) {
            throw new LogicException("changeInWayTripStatusOnFalseError ", e);
        }
    }


    public void finishTrip(int taxiId, int taxiMark) throws LogicException {
        TripDao tripDao = new TripDaoImpl();
        try {
            tripDao.finishTrip(taxiId, taxiMark);
        } catch (DaoException e) {
            throw new LogicException("finishTripError ", e);
        }

    }


    public List<Trip> findTripsByUserId(int userId) throws LogicException {
        TripDao tripDao = new TripDaoImpl();
        List<Trip> listOfTrips;
        try {
            listOfTrips = tripDao.findTripsByUserIdForUser(userId);
        } catch (DaoException e) {
            throw new LogicException("findTripsByUserIdError ", e);
        }
        return listOfTrips;
    }


    public List<Trip> findTripsByDriverId(int driverId) throws LogicException {
        TripDao tripDao = new TripDaoImpl();
        List<Trip> listOfTrips;
        try {
            listOfTrips = tripDao.findTripsByDriverIdForDriver(driverId);
        } catch (DaoException e) {
            throw new LogicException("findTripsByDriverIdError ", e);
        }
        return listOfTrips;
    }


    public List<Trip> findListOfTrips() throws LogicException {
        TripDao tripDao = new TripDaoImpl();
        List<Trip> listOfTrips;
        try {
            listOfTrips = tripDao.findListOfTrips();
        } catch (DaoException e) {
            throw new LogicException("findListOfTripsError ", e);
        }
        return listOfTrips;
    }

    public List<Trip> findListOfActiveTrips() throws LogicException {
        TripDao tripDao = new TripDaoImpl();
        List<Trip> listOfTrips;
        try {
            listOfTrips = tripDao.findActiveListOfTrips();
        } catch (DaoException e) {
            throw new LogicException("findActiveListOfTripsError ", e);
        }
        return listOfTrips;
    }


    public List<Trip> findListOfFinishedTrips() throws LogicException {
        TripDao tripDao = new TripDaoImpl();
        List<Trip> listOfTrips;
        try {
            listOfTrips = tripDao.findFinishedListOfTrips();
        } catch (DaoException e) {
            throw new LogicException("findListOfFinishedTripsError ", e);
        }
        return listOfTrips;
    }


    public Trip findTripById(int tripId) throws LogicException {
        TripDao tripDao = new TripDaoImpl();
        Trip trip;
        try {
            trip = tripDao.findTripById(tripId);
        } catch (DaoException e) {
            throw new LogicException("findTripByIdError ", e);
        }
        return trip;
    }

    public boolean isExistActiveTripByTaxiId(int taxiId) throws LogicException {
        TripDao tripDao = new TripDaoImpl();
        try {
            return tripDao.isActiveTripExist(taxiId);
        } catch (DaoException e) {
            throw new LogicException("isExistActiveTripByDriverIdError ", e);
        }
    }
}
