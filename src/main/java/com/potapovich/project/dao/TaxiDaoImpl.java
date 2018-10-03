package com.potapovich.project.dao;

import com.potapovich.project.constant.Constant;
import com.potapovich.project.entity.Point;
import com.potapovich.project.entity.Taxi;
import com.potapovich.project.entity.TaxiCar;
import com.potapovich.project.entity.TaxiDriver;
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

public class TaxiDaoImpl implements TaxiDao {

    @Override
    public void registertTaxi(Taxi taxi) throws DaoException {
        int coordX = (int) (Math.random() * 100);
        int coordY = (int) (Math.random() * 100);
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constant.REGISTER_TAXI)) {
            preparedStatement.setInt(1, taxi.getTaxiCar().getCarId());
            preparedStatement.setInt(2, taxi.getTaxiDriver().getDriverId());
            preparedStatement.setBoolean(3, taxi.isActive());
            preparedStatement.setInt(4, coordX);
            preparedStatement.setInt(5, coordY);
            preparedStatement.setBoolean(6, true);
            preparedStatement.setString(7, DateConvert.dateToString(LocalDateTime.now()));
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DaoException("registertTaxiError ", e);
        }
    }

    @Override
    public void startTaxiWork(Taxi taxi) throws DaoException {
        String inserted = Constant.START_TAXI_WORK_STATUS;
        int coordX = (int) (Math.random() * 100);
        int coordY = (int) (Math.random() * 100);
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(inserted)) {
            preparedStatement.setBoolean(1, true);
            preparedStatement.setInt(2, coordX);
            preparedStatement.setInt(3, coordY);
            preparedStatement.setString(4, DateConvert.dateToString(LocalDateTime.now()));
            preparedStatement.setInt(5, taxi.getTaxiDriver().getDriverId());
            preparedStatement.setInt(6, taxi.getTaxiCar().getCarId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DaoException("startTaxiWorkError ", e);
        }
    }

    @Override
    public void finishTaxiWork(Taxi taxi) throws DaoException {
        String inserted = Constant.FINISH_TAXI_WORK_STATUS;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(inserted)) {
            preparedStatement.setBoolean(1, false);
            preparedStatement.setString(2, DateConvert.dateToString(LocalDateTime.now()));
            preparedStatement.setInt(3, taxi.getTaxiDriver().getDriverId());
            preparedStatement.setInt(4, taxi.getTaxiCar().getCarId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DaoException("finishTaxiWorkError ", e);
        }
    }

    @Override
    public Taxi findActiveTaxiByDriverIdWithChangingFreeStatus(int driverId) throws DaoException {
        Taxi taxi = new Taxi();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatementSelect = connection.prepareStatement(Constant.FIND_ACTIVE_TAXI_BY_DRIVER_ID);
             PreparedStatement preparedStatementUpdate = connection.prepareStatement(Constant.CHANGE_TAXI_IS_FREE_STATUS)) {
            preparedStatementSelect.setInt(1, driverId);
            preparedStatementSelect.setBoolean(2, true);
            ResultSet rs = preparedStatementSelect.executeQuery();
            if (driverId > 0) {
                while (rs.next()) {
                    preparedStatementUpdate.setBoolean(1, false);
                    preparedStatementUpdate.setInt(2, driverId);
                    preparedStatementUpdate.setBoolean(3, true);
                    preparedStatementUpdate.execute();
                    TaxiCar car = new TaxiCar();
                    TaxiDriver driver = new TaxiDriver();
                    taxi.setId(rs.getInt(1));
                    car.setCarId(rs.getInt(2));
                    taxi.setTaxiCar(car);
                    driver.setDriverId(rs.getInt(3));
                    driver.setDriverName(rs.getString(4));
                    taxi.setTaxiDriver(driver);
                    taxi.setActive(rs.getBoolean(5));
                    taxi.setFree(false);
                    taxi.setTaxiLocation(new Point(rs.getInt(7), rs.getInt(8)));
                    taxi.setStartWorkDate(rs.getString(9));
                }
            } else {
                taxi.setId(driverId);
            }
        } catch (SQLException e) {
            throw new DaoException("findActiveTaxiByDriverIdWithChangingFreeStatusError ", e);
        }
        return taxi;
    }

    @Override
    public Taxi findActiveTaxiByDriverId(int driverId) throws DaoException {
        Taxi taxi = new Taxi();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constant.FIND_TAXI_BY_DRIVER_ID)) {
            preparedStatement.setInt(1, driverId);
            preparedStatement.setBoolean(2, true);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                TaxiCar car = new TaxiCar();
                TaxiDriver driver = new TaxiDriver();
                taxi.setId(rs.getInt(1));
                car.setCarId(rs.getInt(2));
                taxi.setTaxiCar(car);
                driver.setDriverId(rs.getInt(3));
                taxi.setTaxiDriver(driver);
                taxi.setActive(rs.getBoolean(4));
                taxi.setTaxiLocation(new Point(rs.getInt(5), rs.getInt(6)));
                taxi.setFree(rs.getBoolean(7));
            }
        } catch (SQLException e) {
            throw new DaoException("findActiveTaxiByDriverIdError ", e);
        }
        return taxi;
    }

    @Override
    public Taxi findTaxiById(int taxiId) throws DaoException {
        Taxi taxi = new Taxi();
        TaxiCar taxiCar = new TaxiCar();
        TaxiDriver taxiDriver = new TaxiDriver();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constant.FIND_TAXI_BY_OWN_ID)) {
            preparedStatement.setInt(1, taxiId);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                taxi.setId(rs.getInt(1));
                taxiCar.setCarId(rs.getInt(2));
                taxi.setTaxiCar(taxiCar);
                taxiDriver.setDriverId(rs.getInt(3));
                taxi.setTaxiDriver(taxiDriver);
                taxi.setActive(rs.getBoolean(4));
                taxi.setFree(rs.getBoolean(5));
                taxi.setStartWorkDate(rs.getString(6));
                taxi.setFinishWorkDate(rs.getString(7));
            }
        } catch (SQLException e) {
            throw new DaoException("findTaxiByIdError ", e);
        }
        return taxi;
    }

    @Override
    public boolean isTaxiExist(int driverId, int carId) throws DaoException {
        boolean result;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constant.IS_TAXI_EXIST)) {
            preparedStatement.setInt(1, driverId);
            preparedStatement.setInt(2, carId);
            ResultSet rs = preparedStatement.executeQuery();
            result = rs.next();
        } catch (SQLException e) {
            throw new DaoException("isTaxiExistError ", e);
        }
        return result;
    }

    @Override
    public boolean isTaxiActive(int driverId) throws DaoException {
        boolean workStatus = false;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement
                     (Constant.IS_TAXI_ACTIVE)) {
            preparedStatement.setInt(1, driverId);
            preparedStatement.setBoolean(2, true);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                workStatus = rs.getBoolean(1);
            }
        } catch (SQLException e) {
            throw new DaoException("isTaxiActiveError ", e);
        }
        return workStatus;
    }

    @Override
    public List<Taxi> findListOfWorkingTaxi() throws DaoException {
        List<Taxi> listOfTaxi = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement
                     (Constant.FIND_LIST_OF_WORKING_TAXI)) {
            preparedStatement.setBoolean(1, true);
            preparedStatement.setBoolean(2, true);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Taxi taxi = new Taxi();
                taxi.setId(rs.getInt(1));
                if (taxi.getId() > 0) {
                    TaxiCar car = new TaxiCar();
                    TaxiDriver driver = new TaxiDriver();
                    driver.setDriverId(rs.getInt(2));
                    driver.setDriverName(rs.getString(3));
                    driver.setExperience(rs.getInt(4));
                    driver.setStatus(rs.getBoolean(5));
                    driver.setDeleteStatus(rs.getBoolean(6));
                    taxi.setTaxiDriver(driver);
                    car.setCarId(rs.getInt(7));
                    car.setModel(rs.getString(8));
                    car.setDeleteStatus(rs.getBoolean(9));
                    car.setImageCarId(rs.getInt(10));
                    car.setOwnerId(rs.getInt(11));
                    car.setYearOFManufacture(rs.getInt(12));
                    taxi.setTaxiCar(car);
                    taxi.setActive(rs.getBoolean(13));
                    taxi.setTaxiLocation(new Point(rs.getInt(14), rs.getInt(15)));
                    taxi.setStartWorkDate(rs.getString(16));
                    listOfTaxi.add(taxi);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("findListOfWorkingTaxiError ", e);
        }
        return listOfTaxi;
    }

    @Override
    public void changeTaxiFreeTrue(int taxiId) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constant.CHANGE_TAXI_FREE_STATUS_TRUE)) {
            preparedStatement.setBoolean(1, true);
            preparedStatement.setInt(2, taxiId);
            preparedStatement.setBoolean(3, true);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DaoException("changeTaxiFreeTrueError ", e);
        }
    }
}