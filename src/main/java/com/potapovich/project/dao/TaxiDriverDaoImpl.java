package com.potapovich.project.dao;

import com.potapovich.project.constant.Constant;
import com.potapovich.project.entity.TaxiDriver;
import com.potapovich.project.exception.DaoException;
import com.potapovich.project.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaxiDriverDaoImpl implements TaxiDriverDao {

    @Override
    public void driverRegistration(TaxiDriver driver) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constant.REGISTER_DRIVER)) {
            preparedStatement.setString(1, driver.getDriverName());
            preparedStatement.setInt(2, driver.getExperience());
            preparedStatement.setBoolean(3, driver.isStatus());
            preparedStatement.setString(4, driver.getDriverPassword());
            preparedStatement.setBoolean(5, driver.isDeleteStatus());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DaoException("driverRegistrationError ", e);
        }
    }

    @Override
    public TaxiDriver findDriverById(int id) throws DaoException {
        TaxiDriver driver = new TaxiDriver();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constant.FIND_DRIVER_BY_ID)) {
            preparedStatement.setInt(1, id);
            preparedStatement.setBoolean(2, false);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                driver.setDriverId(rs.getInt(1));
                driver.setDriverName(rs.getString(2));
                driver.setExperience(rs.getInt(3));
                driver.setStatus(rs.getBoolean(4));
            }
        } catch (SQLException e) {
            throw new DaoException("findDriverByIdError ", e);
        }
        return driver;
    }

    @Override
    public TaxiDriver findDriverByName(String name) throws DaoException {
        TaxiDriver driver = new TaxiDriver();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constant.FIND_DRIVER_BY_NAME)) {
            preparedStatement.setString(1, name);
            preparedStatement.setBoolean(2, false);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                driver.setDriverId(rs.getInt(1));
                driver.setDriverName(rs.getString(2));
                driver.setExperience(rs.getInt(3));
                driver.setStatus(rs.getBoolean(4));
            }
        } catch (SQLException e) {
            throw new DaoException("findDriverByNameError ", e);
        }
        return driver;
    }

    @Override
    public TaxiDriver findDriverByLoginAndPassword(String name, String password) throws DaoException {
        TaxiDriver driver = new TaxiDriver();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constant.FIND_DRIVER_BY_LOGIN)) {
            preparedStatement.setString(1, name);
            preparedStatement.setBoolean(2, false);
            preparedStatement.setString(3, password);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                driver.setDriverId(rs.getInt(1));
                driver.setDriverName(rs.getString(2));
                driver.setExperience(rs.getInt(3));
                driver.setStatus(rs.getBoolean(4));
            } else {
                driver.setDriverId(0);
            }
        } catch (SQLException e) {
            throw new DaoException("findDriverByLoginAndPasswordError ", e);
        }
        return driver;
    }

    @Override
    public List<TaxiDriver> findAllDriversWithPrivateData() throws DaoException {
        List<TaxiDriver> listOfDrivers = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constant.FIND_ALL_DRIVERS)) {
            preparedStatement.setBoolean(1, false);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                TaxiDriver driver = new TaxiDriver();
                driver.setDriverId(rs.getInt(1));
                driver.setDriverName(rs.getString(2));
                driver.setExperience(rs.getInt(3));
                driver.setStatus(rs.getBoolean(4));
                driver.setDriverPassword(rs.getString(5));
                if (driver.getDriverId() > 0) {
                    listOfDrivers.add(driver);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("findAllDriversWithPrivateDataError ", e);
        }
        return listOfDrivers;
    }

    @Override
    public List<TaxiDriver> findAllDeletedDrivers() throws DaoException {
        List<TaxiDriver> listOfDrivers = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constant.FIND_ALL_DELETED_DRIVERS)) {
            preparedStatement.setBoolean(1, true);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                TaxiDriver driver = new TaxiDriver();
                driver.setDriverId(rs.getInt(1));
                driver.setDriverName(rs.getString(2));
                driver.setExperience(rs.getInt(3));
                driver.setStatus(rs.getBoolean(4));
                driver.setDriverPassword(rs.getString(5));
                if (driver.getDriverId() > 0) {
                    listOfDrivers.add(driver);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("findAllDeletedDriversError ", e);
        }
        return listOfDrivers;
    }

    @Override
    public void updateDriverLockStatus(int driverId, boolean updateStatus) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constant.UPDATE_DRIVER_LOCK_STATUS)) {
            preparedStatement.setBoolean(1, updateStatus);
            preparedStatement.setInt(2, driverId);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DaoException("updateDriverLockStatusError ", e);
        }
    }

    @Override
    public boolean changeDriverPassword(String login, String oldPass, String newPass, int driverId) throws DaoException {
        boolean result = false;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constant.CHANGE_DRIVER_PASSWORD)) {
            if (findDriverByLoginAndPassword(login, oldPass).getDriverId() != 0) {
                preparedStatement.setString(1, newPass);
                preparedStatement.setString(2, login);
                preparedStatement.setString(3, oldPass);
                preparedStatement.setInt(4, driverId);
                preparedStatement.execute();
                result = true;
            }
        } catch (SQLException e) {
            throw new DaoException("changeDriverPasswordError ", e);
        }
        return result;
    }

    @Override
    public boolean deleteDriver(int driverId) throws DaoException {
        boolean result = false;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constant.DELETE_DRIVER_BY_ID)) {
            TaxiDriver driver = findDriverById(driverId);
            if (driver.getDriverId() != 0 && !driver.isDeleteStatus()) {
                preparedStatement.setBoolean(1, true);
                preparedStatement.setInt(2, driverId);
                preparedStatement.setBoolean(3, false);
                preparedStatement.execute();
                result = true;
            }
        } catch (SQLException e) {
            throw new DaoException("deleteDriverError ", e);
        }
        return result;
    }
}
