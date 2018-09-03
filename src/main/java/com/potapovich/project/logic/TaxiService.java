package com.potapovich.project.logic;

import com.potapovich.project.dao.TaxiCarDaoImpl;
import com.potapovich.project.dao.TaxiDaoImpl;
import com.potapovich.project.dao.TaxiDriverDaoImpl;
import com.potapovich.project.entity.*;
import com.potapovich.project.exception.DaoException;
import com.potapovich.project.exception.LogicException;
import com.potapovich.project.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class TaxiService {

    public void taxiDriverRegistration(TaxiDriver driver) throws LogicException{

        TaxiDriverDaoImpl taxiDriverDao = new TaxiDriverDaoImpl();
        try {
            taxiDriverDao.driverRegistration(driver);
        } catch (DaoException e){
            throw new LogicException("taxiDriverRegistrationError ", e);
        }

    }



    public void taxiCarRegistration(TaxiCar car) throws LogicException{

        TaxiCarDaoImpl taxiCarDao = new TaxiCarDaoImpl();
        try {
            taxiCarDao.carRegistration(car);
        } catch (DaoException e){
            throw new LogicException("taxiCarRegistrationError ", e);
        }

    }




    public TaxiDriver findDriverById(int id) throws LogicException{

        TaxiDriver driver = new TaxiDriver();
        TaxiDriverDaoImpl driverDao = new TaxiDriverDaoImpl();
        try {
            driver = driverDao.findDriverById(id);
        } catch (DaoException e){
            throw new LogicException("findDriverByIdError ", e);
        }

        return driver;
    }


    public TaxiDriver findDriverByName(String name) throws LogicException{
        TaxiDriver driver = new TaxiDriver();
        TaxiDriverDaoImpl driverDao = new TaxiDriverDaoImpl();
        try {
            driver = driverDao.findDriverByName(name);
        } catch (DaoException e){
            throw new LogicException("findDriverByNameError ", e);
        }
        return driver;
    }

    public TaxiDriver findDriverByNameAndPassword(String name, String password) throws LogicException{
        TaxiDriver driver = new TaxiDriver();
        TaxiDriverDaoImpl driverDao = new TaxiDriverDaoImpl();

        try {
            driver = driverDao.findDriverByLoginAndPassword(name,password);
        } catch (DaoException e){
            throw new LogicException("findDriverByNameAndPasswordError ", e);
        }
        return driver;
    }



    public TaxiCar findCarById(int id) throws LogicException{
        TaxiCar car = new TaxiCar();
        TaxiCarDaoImpl taxiCarDao = new TaxiCarDaoImpl();

        try {
            car = taxiCarDao.findCarById(id);
        } catch (DaoException e){
            throw new LogicException("findCarByIdError ", e);
        }


        return car;
    }


    public List<TaxiCar> findCarsByOwnerId(int ownerId) throws LogicException{
        TaxiCar car = new TaxiCar();
        List<TaxiCar> listOfCars = new ArrayList<>();
        TaxiCarDaoImpl taxiCarDao = new TaxiCarDaoImpl();

        try {
            listOfCars = taxiCarDao.findCarsByOwnerId(ownerId);
        } catch (DaoException e){
            throw new LogicException("findCarsByOwnerIdError ", e);
        }
        return listOfCars;
    }


    public boolean isExistTaxiDriverNamePass(String name, String password) throws LogicException{

        TaxiDriver driver = new TaxiDriver();
        TaxiDriverDaoImpl driverDao = new TaxiDriverDaoImpl();

        try {
            driver = driverDao.findDriverByLoginAndPassword(name,password);
        } catch (DaoException e){
            throw new LogicException("isExistTaxiDriverNamePassError ", e);
        }

        return driver.getDriverName()!=null && name.equals(driver.getDriverName()) && !driver.getDriverName().isEmpty() && password.equals(driver.getDriverPassword())
               && driver.getDriverPassword()!=null && !driver.getDriverPassword().isEmpty();

    }

    public boolean updateLockStatus(int driverId) throws LogicException{

        TaxiDriverDaoImpl taxiDriverDao = new TaxiDriverDaoImpl();

        TaxiDriver driver = findDriverById(driverId);
        boolean updateStatus;
        updateStatus = !driver.isStatus();
        try {
            taxiDriverDao.updateDriverLockStatus(driverId, updateStatus);
        } catch (DaoException e){
            throw new LogicException("updateLockStatusError ", e);
        }
        return updateStatus;
    }




    public List<TaxiDriver> findListOfDriver() throws LogicException{
        List<TaxiDriver> listOfDrivers = new ArrayList<>();
        TaxiDriverDaoImpl driverDao = new TaxiDriverDaoImpl();

        try {
            listOfDrivers = driverDao.findAllDriversWithPrivateData();
        } catch (DaoException e){
            throw new LogicException("findListOfDriverError ", e);
        }

        return listOfDrivers;
    }


    public List<TaxiDriver> findDeletedDriver() throws LogicException{
        List<TaxiDriver> listOfDrivers = new ArrayList<>();
        TaxiDriverDaoImpl driverDao = new TaxiDriverDaoImpl();

        try {
            listOfDrivers = driverDao.findAllDeletedDrivers();
        } catch (DaoException e){
            throw new LogicException("findDeletedDriverError ", e);
        }

        return listOfDrivers;
    }


    public List<TaxiCar> findDeletedCars() throws LogicException{
        List<TaxiCar> listOfCars = new ArrayList<>();
        TaxiCarDaoImpl taxiCarDao = new TaxiCarDaoImpl();
        try {
            listOfCars = taxiCarDao.findAllDeletedCars();
        } catch (DaoException e){
            throw new LogicException("findAllDeletedCarsError ", e);
        }

        return listOfCars;
    }


    public List<TaxiCar> findListOfCars() throws LogicException{
        List<TaxiCar> listOfCars = new ArrayList<>();
        TaxiCarDaoImpl taxiCarDao = new TaxiCarDaoImpl();
        try {
            listOfCars = taxiCarDao.findAllCars();
        } catch (DaoException e){
            throw new LogicException("findListOfCarsError ", e);
        }

        return listOfCars;
    }

    public boolean startTaxiWork(Taxi taxi) throws LogicException{

        TaxiDaoImpl taxiDao = new TaxiDaoImpl();
        try {
            if (!taxiDao.isTaxiExist(taxi.getTaxiDriver().getDriverId(),taxi.getTaxiCar().getCarId()) && !taxiDao.isTaxiActive(taxi.getTaxiDriver().getDriverId()) && !taxi.getTaxiDriver().isStatus()){
                taxi.setActive(true);
                taxiDao.registertTaxi(taxi);
                return true;
            }
           else if (taxiDao.isTaxiExist(taxi.getTaxiDriver().getDriverId(),taxi.getTaxiCar().getCarId()) && !taxiDao.isTaxiActive(taxi.getTaxiDriver().getDriverId()) && !taxi.getTaxiDriver().isStatus()){
                taxi.setActive(true);
                taxiDao.startTaxiWork(taxi);
                return true;
            }

            else {
                return false;
            }
        } catch (DaoException e){
            throw new LogicException("startTaxiWorkError ", e);
        }
    }


    public boolean finishTaxiWork(String driverLogin, String driverPassword) throws LogicException{

        TaxiDaoImpl taxiDao = new TaxiDaoImpl();
        Taxi taxi = new Taxi();
        try {
            taxi = taxiDao.findActiveTaxiByDriverLoginAndPass(driverLogin,driverPassword);
            System.out.println("TAAAAAAAAAAXXXXXXXXIIIIIII " + taxi);
            if (taxi.getId()!=0 && taxiDao.isTaxiExist(taxi.getTaxiDriver().getDriverId(),taxi.getTaxiCar().getCarId())){
                System.out.println("1");
                taxi.setActive(false);
                taxiDao.finishTaxiWork(taxi);
                return true;
            }
            else {
                System.out.println("2");
                return false;
            }
        } catch (DaoException e){
            throw new LogicException("finishTaxiWorkError ", e);
        }

    }



    public List<Taxi> findListOfWorkingTaxi() throws LogicException{
        List<Taxi> listOfTaxi = new ArrayList<>();
        TaxiDaoImpl taxiDao = new TaxiDaoImpl();
        try {
            listOfTaxi = taxiDao.findListOfWorkingTaxi();
        } catch (DaoException e){
            throw new LogicException("findListOfWorkingTaxiError ", e);
        }
        return listOfTaxi;
    }


    public Taxi findWorkingTaxiById(int driverId) throws LogicException{

        Taxi taxi = new Taxi();
        TaxiDaoImpl taxiDao = new TaxiDaoImpl();

        try {
            taxi = taxiDao.findActiveTaxiByDriverIdWithChangingFreeStatus(driverId);
        } catch (DaoException e){
            throw new LogicException("findWorkingTaxiByIdError ", e);
        }

        return taxi;
    }


    public Taxi findTaxiById(int taxiId) throws LogicException{

        Taxi taxi = new Taxi();
        TaxiDaoImpl taxiDao = new TaxiDaoImpl();

        try {
            taxi = taxiDao.findTaxiById(taxiId);
        } catch (DaoException e){
            throw new LogicException("findTaxiByIdError ", e);
        }

        return taxi;
    }


    public int findLastImageCarId() throws LogicException{

        int lastImageCarId;
        TaxiCarDaoImpl taxiCarDao = new TaxiCarDaoImpl();
        try {
            lastImageCarId = taxiCarDao.findLastCarImageId();
        } catch (DaoException e){
            throw new LogicException("findLastImageCarIdError ", e);
        }

        return lastImageCarId;
    }


    public void changeTaxiFreeStatusTrue(int taxiId) throws LogicException{

        TaxiDaoImpl taxiDao = new TaxiDaoImpl();
        try {
            taxiDao.changeTaxiFreeTrue(taxiId);
        } catch (DaoException e){
            throw new LogicException("changeTaxiFreeStatusTrueError ", e);
        }
    }


    public boolean changeDriverPassword(String login, String oldPass, String newPass, int driverId) throws LogicException{

        TaxiDriverDaoImpl taxiDriverDao = new TaxiDriverDaoImpl();
        boolean result;
        try {
           result = taxiDriverDao.changeDriverPassword(login, oldPass, newPass, driverId);
        } catch (DaoException e){
            throw new LogicException("changeDriverPasswordError ", e);
        }
        return result;
    }


    public boolean changeCarAvatar(int imageId, String driverName, String driverPassword, int carId) throws LogicException{

        TaxiDriverDaoImpl taxiDriverDao = new TaxiDriverDaoImpl();
        TaxiCarDaoImpl taxiCarDao = new TaxiCarDaoImpl();
        boolean result;
        try {
            int driverId = taxiDriverDao.findDriverIdByEnteredLoginAndPassword(driverName, driverPassword);
            result = taxiCarDao.changeCarAvatar(carId, imageId, driverId);
        } catch (DaoException e){
            throw new LogicException("changeCarAvatarError ", e);
        }
        return result;
    }



    public boolean deleteDriver(int driverId) throws LogicException{

        TaxiDriverDaoImpl taxiDriverDao = new TaxiDriverDaoImpl();
        boolean result;
        try {
            result = taxiDriverDao.deleteDriver(driverId);
        } catch (DaoException e){
            throw new LogicException("deleteDriverError ", e);
        }
        return result;
    }


    public boolean deleteCar(int carId) throws LogicException{

        TaxiCarDaoImpl taxiCarDao = new TaxiCarDaoImpl();
        boolean result;
        try {
            result = taxiCarDao.deleteCar(carId);
        } catch (DaoException e){
            throw new LogicException("deleteCarError ", e);
        }
        return result;
    }



    public List<Distance> findDistanceBetweenTaxiAndClient(List<Taxi> availableTaxi, Customer customer){
        List <Distance> listOfDistance = new ArrayList<>();
        for (int i = 0; i < availableTaxi.size(); i++) {
            Distance distance = new Distance();
            double length = Math.hypot(availableTaxi.get(i).getTaxiLocation().getX() - customer.getRoute().getLocation().getX(),
                    availableTaxi.get(i).getTaxiLocation().getY() - customer.getRoute().getLocation().getY());

            distance.setId(availableTaxi.get(i).getId());
            distance.setDistance(length);
            listOfDistance.add(distance);
        }
        return listOfDistance;
    }

    public Distance findDistance(Route route){

            Distance distance = new Distance();
        double length = Math.hypot(route.getLocation().getX() - route.getDestination().getX(),
                route.getLocation().getY() - route.getDestination().getY());
            distance.setDistance(length);
        return distance;
    }

    public int calculateTimeByDistance(Distance distance){
        int distanceInMinutes = 7;
       int time;
        time = (int) (distance.getDistance()/distanceInMinutes);

        return time;
    }

    public int calculateCostByDistance(Distance distance){

        int costPerDistance = 12;
        int cost;
        cost = (int) (distance.getDistance()/costPerDistance);


        return cost;
    }










}
