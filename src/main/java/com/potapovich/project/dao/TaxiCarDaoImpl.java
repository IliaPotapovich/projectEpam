package com.potapovich.project.dao;

import com.potapovich.project.constant.Constant;
import com.potapovich.project.entity.TaxiCar;
import com.potapovich.project.exception.DaoException;
import com.potapovich.project.pool.ConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaxiCarDaoImpl {

    public void carRegistration(TaxiCar car) throws DaoException{

        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(Constant.REGISTER_CAR)) {

            preparedStatement.setString(1, car.getModel());
            preparedStatement.setInt(2, car.getOwnerId());
            preparedStatement.setInt(3, car.getYearOFManufacture());
            preparedStatement.setBoolean(4,car.isDeleteStatus());
            preparedStatement.execute();
        }  catch (SQLException e) {
            throw new DaoException("carRegistrationError ", e);
        }

    }



    public TaxiCar findCarById(int id) throws DaoException{

        TaxiCar car = new TaxiCar();
        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(Constant.FIND_CAR_BY_ID)) {

            preparedStatement.setInt(1,id);
            preparedStatement.setBoolean(2,false);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                car.setCarId(rs.getInt(1));
                car.setModel(rs.getString(2));
                car.setOwnerId(rs.getInt(3));
                car.setYearOFManufacture(rs.getInt(4));
                car.setImageCarId(rs.getInt(5));
            }

        } catch (SQLException e) {
            throw new DaoException("findCarByIdError ", e);
        }
        return car;
    }




    public List<TaxiCar> findCarsByOwnerId(int ownerId) throws DaoException{
        List<TaxiCar> listOfCars = new ArrayList<>();
        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(Constant.FIND_CAR_BY_OWNER_ID)) {

            preparedStatement.setInt(1,ownerId);
            preparedStatement.setBoolean(2,false);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                TaxiCar car = new TaxiCar();
                car.setCarId(rs.getInt(1));
                car.setModel(rs.getString(2));
                car.setOwnerId(rs.getInt(3));
                car.setYearOFManufacture(rs.getInt(4));
                car.setImageCarId(rs.getInt(5));
                listOfCars.add(car);
            }
        } catch (SQLException e) {
            throw new DaoException("findCarsByOwnerIdError ", e);
        }
        return listOfCars;
    }



    public List<TaxiCar> findAllCars() throws DaoException{

        List<TaxiCar> listOfCars = new ArrayList<>();
        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(Constant.FIND_ALL_CARS)) {

            preparedStatement.setBoolean(1,false);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                TaxiCar car = new TaxiCar();
                car.setCarId(rs.getInt(1));
                car.setModel(rs.getString(2));
                car.setOwnerId(rs.getInt(3));
                car.setYearOFManufacture(rs.getInt(4));
                car.setImageCarId(rs.getInt(5));
                listOfCars.add(car);
            }
        } catch (SQLException e) {
            throw new DaoException("findAllCarsError ", e);
        }
        return listOfCars;
    }


    public List<TaxiCar> findAllDeletedCars() throws DaoException{

        List<TaxiCar> listOfCars = new ArrayList<>();
        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(Constant.FIND_ALL_CARS)) {

            preparedStatement.setBoolean(1,true);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                TaxiCar car = new TaxiCar();
                car.setCarId(rs.getInt(1));
                car.setModel(rs.getString(2));
                car.setOwnerId(rs.getInt(3));
                car.setYearOFManufacture(rs.getInt(4));
                car.setImageCarId(rs.getInt(5));
                listOfCars.add(car);
            }
        } catch (SQLException e) {
            throw new DaoException("findDeletedCarsError ", e);
        }
        return listOfCars;
    }


    public boolean deleteCar(int carId) throws DaoException {

        boolean result = false;

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constant.DELETE_CAR_BY_ID)) {
            TaxiCar car = findCarById(carId);
            if (car.getCarId()!=0 && !car.isDeleteStatus()) {
                preparedStatement.setBoolean(1, true);
                preparedStatement.setInt(2, carId);
                preparedStatement.setBoolean(3,false);
                preparedStatement.execute();
                result = true;
            }

        } catch (SQLException e) {
            throw new DaoException("deleteCarError ", e);
        }
        return result;
    }


    public boolean changeCarAvatar(int carId, int imageId, int ownerId) throws DaoException {

        boolean result = false;

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constant.CHANGE_CAR_AVATAR_ID)) {
            TaxiCar car = findCarById(carId);
            if (car.getCarId()!=0 && !car.isDeleteStatus()) {
                preparedStatement.setInt(1, imageId);
                preparedStatement.setInt(2, carId);
                preparedStatement.setInt(3,ownerId);
                preparedStatement.setBoolean(4,false);
                preparedStatement.execute();
                result = true;
            }

        } catch (SQLException e) {
            throw new DaoException("changeCarAvatarError ", e);
        }
        return result;
    }


    public int findLastCarImageId() throws DaoException {

        int carImageId = 0;

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constant.FIND_LAST_CAR_IMAGE_ID)) {

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                carImageId = rs.getInt(1);
            }

        } catch (SQLException e) {
            throw new DaoException("findLastCarImageIdError ", e);
        }
        return carImageId;
    }


}
