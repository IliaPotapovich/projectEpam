package com.potapovich.project.dao;

import com.potapovich.project.constant.Constant;
import com.potapovich.project.entity.Taxi;
import com.potapovich.project.entity.TaxiCar;
import com.potapovich.project.entity.TaxiDriver;
import com.potapovich.project.entity.Trip;
import com.potapovich.project.exception.DaoException;
import com.potapovich.project.logic.DateConvert;
import com.potapovich.project.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TripDaoImpl implements TripDao {

    @Override
    public int startTrip(int customerId, int taxiId, String customerPhone, String customerName, double cost) throws DaoException {
        int tripId;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constant.START_TRIP)) {
            preparedStatement.setString(1, customerName);
            preparedStatement.setString(2, customerPhone);
            preparedStatement.setBoolean(3, true);
            preparedStatement.setDouble(4, cost);
            preparedStatement.setInt(5, taxiId);
            preparedStatement.setInt(6, customerId);
            preparedStatement.setString(7, DateConvert.dateToString(LocalDateTime.now()));
            preparedStatement.execute();
            tripId = findActiveTripIdByTaxiId(taxiId);
        } catch (SQLException e) {
            throw new DaoException("startTripError ", e);
        }
        return tripId;
    }

    @Override
    public void changeCurrentTrip(int tripId, int taxiId, String customerPhone, String customerName, double cost) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constant.CHANGE_CURRENT_TRIP)) {
            preparedStatement.setInt(1, taxiId);
            preparedStatement.setDouble(2, cost);
            preparedStatement.setString(3, customerName);
            preparedStatement.setString(4, customerPhone);
            preparedStatement.setString(5, DateConvert.dateToString(LocalDateTime.now()));
            preparedStatement.setInt(6, tripId);
            preparedStatement.setBoolean(7, true);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DaoException("changeCurrentTripError ", e);
        }
    }

    @Override
    public void changeCurrentInWayTripStatus(int taxiId) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constant.CHANGE_IN_WAY_CURRENT_TRIP)) {
            preparedStatement.setBoolean(1, false);
            preparedStatement.setString(2, DateConvert.dateToString(LocalDateTime.now()));
            preparedStatement.setInt(3, taxiId);
            preparedStatement.setBoolean(4, true);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DaoException("changeCurrentInWayTripStatusError ", e);
        }
    }

    @Override
    public int findActiveTripIdByTaxiId(int taxi_id) throws DaoException {
        int tripId = 0;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constant.FIND_ACTIVE_TRIP_ID_BY_TAXI_ID)) {
            preparedStatement.setInt(1, taxi_id);
            preparedStatement.setBoolean(2, true);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                tripId = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new DaoException("findActiveTripIdByTaxiIdError ", e);
        }
        return tripId;
    }

    @Override
    public boolean isActiveTripExist(int taxiId) throws DaoException {
        boolean result;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement
                     (Constant.IS_ACTIVE_TRIP_EXIST)) {
            preparedStatement.setInt(1, taxiId);
            preparedStatement.setBoolean(2, true);
            ResultSet rs = preparedStatement.executeQuery();
            result = rs.next();
        } catch (SQLException e) {
            throw new DaoException("isActiveTripExistError ", e);
        }
        return result;

    }

    @Override
    public void finishTrip(int taxiId, int taxiMark) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constant.FINISH_TRIP);
             PreparedStatement taxiPreparedStatement = connection.prepareStatement(Constant.FINISH_TAXI_TRIP)) {
            taxiPreparedStatement.setBoolean(1, true);
            taxiPreparedStatement.setInt(2, taxiId);
            taxiPreparedStatement.setBoolean(3, true);
            taxiPreparedStatement.execute();
            preparedStatement.setInt(1, taxiMark);
            preparedStatement.setBoolean(2, false);
            preparedStatement.setString(3, DateConvert.dateToString(LocalDateTime.now()));
            preparedStatement.setInt(4, taxiId);
            preparedStatement.setBoolean(5, true);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DaoException("finishTripError ", e);
        }
    }

    @Override
    public List<Trip> findTripsByUserIdForUser(int userId) throws DaoException {
        List<Trip> listOfTrips = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement
                     (Constant.FIND_TRIPS_BY_USER_ID)) {
            preparedStatement.setInt(1, userId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Trip trip = new Trip();
                Taxi taxi = new Taxi();
                TaxiDriver taxiDriver = new TaxiDriver();
                TaxiCar taxiCar = new TaxiCar();
                trip.setTripId(rs.getInt(1));
                trip.setCustomerName(rs.getString(2));
                trip.setPrice(rs.getInt(3));
                trip.setMarkOfTrip(rs.getInt(4));
                taxiDriver.setDriverName(rs.getString(5));
                taxiCar.setModel(rs.getString(6));
                taxi.setTaxiDriver(taxiDriver);
                taxi.setTaxiCar(taxiCar);
                trip.setTaxi(taxi);
                trip.setStartTripDate(rs.getString(7));
                trip.setFinishTripDate(rs.getString(8));
                listOfTrips.add(trip);
            }
        } catch (SQLException e) {
            throw new DaoException("findTripsByUserIdError ", e);
        }
        return listOfTrips;
    }

    @Override
    public List<Trip> findTripsByDriverIdForDriver(int driverId) throws DaoException {
        List<Trip> listOfTrips = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement
                     (Constant.FIND_TRIPS_BY_DRIVER_ID)) {
            preparedStatement.setInt(1, driverId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Trip trip = new Trip();
                Taxi taxi = new Taxi();
                TaxiDriver taxiDriver = new TaxiDriver();
                TaxiCar taxiCar = new TaxiCar();
                trip.setTripId(rs.getInt(1));
                trip.setCustomerName(rs.getString(2));
                trip.setPrice(rs.getInt(3));
                trip.setMarkOfTrip(rs.getInt(4));
                taxiDriver.setDriverName(rs.getString(5));
                taxiCar.setCarId(rs.getInt(6));
                taxiCar.setModel(rs.getString(7));
                taxi.setTaxiDriver(taxiDriver);
                taxi.setTaxiCar(taxiCar);
                trip.setTaxi(taxi);
                trip.setStartTripDate(rs.getString(8));
                trip.setFinishTripDate(rs.getString(9));
                listOfTrips.add(trip);
            }
        } catch (SQLException e) {
            throw new DaoException("findTripsByDriverIdForDriverError ", e);
        }
        return listOfTrips;
    }

    @Override
    public List<Trip> findListOfTrips() throws DaoException {
        List<Trip> listOfTrips = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constant.FIND_ALL_TRIPS)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Trip trip = new Trip();
                Taxi taxi = new Taxi();
                trip.setTripId(rs.getInt(1));
                trip.setCustomerId(rs.getInt(2));
                trip.setCustomerName(rs.getString(3));
                trip.setCustomerPhone(rs.getString(4));
                trip.setInWay(rs.getBoolean(5));
                trip.setMarkOfTrip(rs.getInt(6));
                trip.setPrice(rs.getInt(7));
                taxi.setId(rs.getInt(8));
                trip.setTaxi(taxi);
                trip.setStartTripDate(rs.getString(9));
                trip.setFinishTripDate(rs.getString(10));
                listOfTrips.add(trip);
            }
        } catch (SQLException e) {
            throw new DaoException("findListOfTripsError ", e);
        }
        return listOfTrips;
    }

    @Override
    public List<Trip> findActiveListOfTrips() throws DaoException {
        List<Trip> listOfTrips = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constant.FIND_ACTIVE_TRIPS)) {
            preparedStatement.setBoolean(1, true);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Trip trip = new Trip();
                Taxi taxi = new Taxi();
                trip.setTripId(rs.getInt(1));
                trip.setCustomerId(rs.getInt(2));
                trip.setCustomerName(rs.getString(3));
                trip.setCustomerPhone(rs.getString(4));
                trip.setInWay(rs.getBoolean(5));
                trip.setMarkOfTrip(rs.getInt(6));
                trip.setPrice(rs.getInt(7));
                taxi.setId(rs.getInt(8));
                trip.setTaxi(taxi);
                trip.setStartTripDate(rs.getString(9));
                listOfTrips.add(trip);
            }
        } catch (SQLException e) {
            throw new DaoException("findActiveListOfTripsError ", e);
        }
        return listOfTrips;
    }

    @Override
    public List<Trip> findFinishedListOfTrips() throws DaoException {
        List<Trip> listOfTrips = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constant.FIND_FINISHED_TRIPS)) {
            preparedStatement.setBoolean(1, false);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Trip trip = new Trip();
                Taxi taxi = new Taxi();
                trip.setTripId(rs.getInt(1));
                trip.setCustomerId(rs.getInt(2));
                trip.setCustomerName(rs.getString(3));
                trip.setCustomerPhone(rs.getString(4));
                trip.setInWay(rs.getBoolean(5));
                trip.setMarkOfTrip(rs.getInt(6));
                trip.setPrice(rs.getInt(7));
                taxi.setId(rs.getInt(8));
                trip.setTaxi(taxi);
                trip.setStartTripDate(rs.getString(9));
                trip.setFinishTripDate(rs.getString(10));
                listOfTrips.add(trip);
            }
        } catch (SQLException e) {
            throw new DaoException("findFinishedListOfTripsError ", e);
        }
        return listOfTrips;
    }

    @Override
    public Trip findTripById(int tripId) throws DaoException {
        Trip trip = new Trip();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constant.FIND_TRIP_BY_ID)) {
            preparedStatement.setInt(1, tripId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Taxi taxi = new Taxi();
                trip.setTripId(rs.getInt(1));
                trip.setCustomerId(rs.getInt(2));
                trip.setCustomerName(rs.getString(3));
                trip.setCustomerPhone(rs.getString(4));
                trip.setInWay(rs.getBoolean(5));
                trip.setMarkOfTrip(rs.getInt(6));
                trip.setPrice(rs.getInt(7));
                taxi.setId(rs.getInt(8));
                trip.setStartTripDate(rs.getString(9));
                trip.setFinishTripDate(rs.getString(10));
                trip.setTaxi(taxi);
            }
        } catch (SQLException e) {
            throw new DaoException("findListOfTripsError ", e);
        }
        return trip;
    }
}
