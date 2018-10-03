package com.potapovich.project.logic;


import com.potapovich.project.dao.UserDao;
import com.potapovich.project.dao.UserDaoImpl;
import com.potapovich.project.entity.Customer;
import com.potapovich.project.entity.Distance;
import com.potapovich.project.entity.Taxi;
import com.potapovich.project.exception.DaoException;
import com.potapovich.project.exception.LogicException;
import com.potapovich.project.logic.comparator.CompareDistance;
import com.potapovich.project.logic.comparator.SortData;
import com.potapovich.project.logic.specification.FindApropriateTaxi;
import com.potapovich.project.logic.specification.TaxiQuery;

import java.util.List;

public class UserService {

    public boolean userRegistration(Customer customer) throws LogicException {
        UserDao userDao = new UserDaoImpl();
        try {
            if (!userDao.isLoginExist(customer.getLogin())) {
                userDao.registerUser(customer);
                return true;
            } else {
                return false;
            }
        } catch (DaoException e) {
            throw new LogicException("userRegistrationError ", e);
        }
    }


    public boolean checkLogin(String enteredLogin, String enteredPassword) throws LogicException {
        UserDao userDao = new UserDaoImpl();
        try {
            return userDao.findRealLoginAndPassIfExist(enteredLogin, enteredPassword);
        } catch (DaoException e) {
            throw new LogicException("checkLoginError ", e);
        }
    }


    public Customer loadUser(int userId) throws LogicException {
        UserDao userDao = new UserDaoImpl();
        Customer customer;
        try {
            customer = userDao.readUser(userId);
        } catch (DaoException e) {
            throw new LogicException("loadUserByIdError ", e);
        }
        return customer;
    }


    public Customer loadUser(String userLogin) throws LogicException {
        Customer customer;
        try {
            UserDao userDao = new UserDaoImpl();
            customer = userDao.readUser(userLogin);
        } catch (DaoException e) {
            throw new LogicException("loadUserByLoginError ", e);
        }
        return customer;
    }


    public void updateUserData(int userId) throws LogicException {
        UserDao userDao = new UserDaoImpl();
        try {
            Customer customer = loadUser(userId);
            int countOfTrip = customer.getCountOfTrip();
            int discount;
            countOfTrip += 1;
            if (countOfTrip < 20) {
                discount = countOfTrip;
            } else {
                discount = 20;
            }
            userDao.updateCustomerData(userId, discount, countOfTrip);
        } catch (DaoException e) {
            throw new LogicException("updateUserDataError ", e);
        }
    }


    public boolean updateUserLockStatus(int userId) throws LogicException {
        boolean updateStatus;
        try {
            UserDao userDao = new UserDaoImpl();
            Customer customer = loadUser(userId);
            updateStatus = !customer.isBannedStatus();
            userDao.updateCustomerLockStatus(userId, updateStatus);
        } catch (DaoException e) {
            throw new LogicException("updateUserLockStatusError ", e);
        }
        return updateStatus;
    }


    public boolean changeUserPassword(String login, String oldPass, String newPass) throws LogicException {
        UserDao userDao = new UserDaoImpl();
        boolean result;
        try {
            result = userDao.changeUserPassword(login, oldPass, newPass);
        } catch (DaoException e) {
            throw new LogicException("changeUserPasswordError ", e);
        }
        return result;
    }


    public List<Customer> findAllUsers() throws LogicException {
        List<Customer> listOfUsers;
        UserDao userDao = new UserDaoImpl();
        try {
            listOfUsers = userDao.findAllUsers();
        } catch (DaoException e) {
            throw new LogicException("findAllUsersError ", e);
        }
        return listOfUsers;
    }


    public List<Taxi> callTaxi(Customer customer, List<Taxi> listOfTaxi) {
        int rangeArea = 5;
        List<Taxi> availableTaxi;
        TaxiQuery taxiQuery = new TaxiQuery();
        int increaseArea = 0;
        while (true) {
            if (listOfTaxi.size() == 1) {
                availableTaxi = listOfTaxi;
                break;
            } else {
                availableTaxi = taxiQuery.query(new FindApropriateTaxi(customer.getRoute().getLocation().getX() - rangeArea - increaseArea,
                        customer.getRoute().getLocation().getX() + rangeArea + increaseArea,
                        customer.getRoute().getLocation().getY() - rangeArea - increaseArea,
                        customer.getRoute().getLocation().getY() + rangeArea + increaseArea), listOfTaxi);
                if (availableTaxi.isEmpty() || availableTaxi.size() < 2) {
                    increaseArea++;
                } else {
                    break;
                }
            }
        }
        return availableTaxi;
    }


    public Distance findAppropriateDistanceToTaxi(Customer customer, List<Taxi> listOfTaxi) {
        TaxiService taxiService = new TaxiService();
        int rangeArea = 5;
        List<Taxi> availableTaxi;
        TaxiQuery taxiQuery = new TaxiQuery();
        int increaseArea = 0;
        Distance distance;
        while (true) {
            if (listOfTaxi.size() == 1) {
                availableTaxi = listOfTaxi;
                break;
            } else {
                availableTaxi = taxiQuery.query(new FindApropriateTaxi(customer.getRoute().getLocation().getX() - rangeArea - increaseArea,
                        customer.getRoute().getLocation().getX() + rangeArea + increaseArea,
                        customer.getRoute().getLocation().getY() - rangeArea - increaseArea,
                        customer.getRoute().getLocation().getY() + rangeArea + increaseArea), listOfTaxi);
                if ((availableTaxi == null || availableTaxi.size() < 2)) {
                    increaseArea++;
                } else {
                    break;
                }
            }
        }
        List<Distance> listOfDistance = taxiService.findDistanceBetweenTaxiAndClient(availableTaxi, customer);
        distance = new SortData(listOfDistance).sortDistance(new CompareDistance()).get(0);
        return distance;
    }
}
