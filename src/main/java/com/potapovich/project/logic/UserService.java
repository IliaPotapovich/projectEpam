package com.potapovich.project.logic;



import com.potapovich.project.logic.comparator.CompareDistance;
import com.potapovich.project.logic.comparator.SortData;
import com.potapovich.project.entity.Distance;
import com.potapovich.project.exception.DaoException;
import com.potapovich.project.exception.LogicException;
import com.potapovich.project.logic.specification.FindApropriateTaxi;
import com.potapovich.project.logic.specification.TaxiQuery;
import com.potapovich.project.dao.CustomerDaoImpl;
import com.potapovich.project.entity.Customer;
import com.potapovich.project.entity.Taxi;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    private int customerId;

    public boolean userRegistration(Customer customer) throws LogicException{

        CustomerDaoImpl customerDao = new CustomerDaoImpl();

        try {
            if (!customerDao.isLoginExist(customer.getLogin())) {
                customerDao.registerUser(customer);
                return true;
            }
            else {
               return false;
            }
        } catch (DaoException e){
            throw new LogicException("userRegistrationError ", e);
        }
    }






    public boolean checkLogin(String enteredLogin, String enteredPassword) throws LogicException{


        CustomerDaoImpl customerDao = new CustomerDaoImpl();

        Customer customer = null;
        try {
            customer = customerDao.findRealLoginAndPassIfExist(enteredLogin,enteredPassword);
            customerId = customer.getId();
        } catch (DaoException e){
            throw new LogicException("checkLoginError ", e);
        }
        return customer.getLogin()!=null  && enteredLogin.equals(customer.getLogin()) &&
                !customer.getLogin().isEmpty() && customer.getPassword()!=null && !customer.getPassword().isEmpty() &&
                enteredPassword.equals(customer.getPassword());
    }


    public Customer loadUser() throws LogicException{


        CustomerDaoImpl customerDao = new CustomerDaoImpl();
        Customer customer = null;
        try {
            customer = customerDao.readUser(customerId);
        } catch (DaoException e){
            throw new LogicException("loadUserError ", e);
        }
        return customer;
    }

    public Customer loadUser(int userId) throws LogicException{


        CustomerDaoImpl customerDao = new CustomerDaoImpl();
        Customer customer;
        try {
            customer = customerDao.readUser(userId);
        } catch (DaoException e){
            throw new LogicException("loadUserByIdError ", e);
        }
        return customer;
    }

    public Customer loadUser(String userLogin) throws LogicException{

        Customer customer;
        try {
            CustomerDaoImpl customerDao = new CustomerDaoImpl();
            customer =  customerDao.readUser(userLogin);
        }
        catch (DaoException e){
            throw new LogicException("loadUserByLoginError ", e);
        }
        return customer;
    }


    public void updateUserData(int userId) throws LogicException{

        CustomerDaoImpl customerDao = new CustomerDaoImpl();


        try {
            Customer customer = loadUser(userId);

            int countOfTrip = customer.getCountOfTrip();
            int discount;
            countOfTrip += 1;
            if (countOfTrip<20) {
                discount = countOfTrip;
            }
            else{
                discount = 20;
            }
            customerDao.updateCustomerData(userId,discount,countOfTrip);

        } catch (DaoException e){
            throw new LogicException("updateUserDataError ", e);
        }
    }

    public boolean updateUserLockStatus(int userId) throws LogicException{

        boolean updateStatus;
        try {
            CustomerDaoImpl customerDao = new CustomerDaoImpl();
            Customer customer = loadUser(userId);

            updateStatus = !customer.isBannedStatus();
            customerDao.updateCustomerLockStatus(userId, updateStatus);
        } catch (DaoException e){
            throw new LogicException("updateUserLockStatusError ", e);
        }
        return updateStatus;
    }


    public boolean changeUserPassword(String login, String oldPass, String newPass) throws LogicException{

        CustomerDaoImpl customerDao = new CustomerDaoImpl();
        boolean result;
        try {
            result = customerDao.changeUserPassword(login, oldPass, newPass);
        } catch (DaoException e){
            throw new LogicException("changeUserPasswordError ", e);
        }
        return result;
    }


    public List<Customer> findAllUsers() throws LogicException{

        List<Customer> listOfUsers = new ArrayList<>();
        CustomerDaoImpl customerDao = new CustomerDaoImpl();
        try {
            listOfUsers = customerDao.findAllUsers();
        } catch (DaoException e){
            throw new LogicException("findAllUsersError ", e);
        }
        return listOfUsers;
    }


    public List<Taxi> callTaxi(Customer customer, List<Taxi> listOfTaxi) {//RUNTIME EXCEPTION

        int rangeArea = 5;
        List<Taxi> availableTaxi = new ArrayList();
        TaxiQuery taxiQuery = new TaxiQuery();
        int increaseArea = 0;
        while (true) {
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

        for (Taxi taxi : availableTaxi) {
            List<Customer> listOfClients = new ArrayList<>();

            if (taxi.getListOfClients() != null) {
                listOfClients = taxi.getListOfClients();
                listOfClients.add(customer);
                taxi.setListOfClients(listOfClients);

            } else {
                listOfClients.add(customer);
                taxi.setListOfClients(listOfClients);

            }
        }
        return availableTaxi;
    }


    public Distance findAppropriateDistanceToTaxi(Customer customer, List<Taxi> listOfTaxi) {//RUNTIME EXCEPTION

        TaxiService taxiService = new TaxiService();
        int rangeArea = 5;
        List<Taxi> availableTaxi = new ArrayList();
        TaxiQuery taxiQuery = new TaxiQuery();
        int increaseArea = 0;
        Distance distance = new Distance();
        while (true) {
            availableTaxi = taxiQuery.query(new FindApropriateTaxi(customer.getRoute().getLocation().getX() - rangeArea - increaseArea,
                    customer.getRoute().getLocation().getX() + rangeArea + increaseArea,
                    customer.getRoute().getLocation().getY() - rangeArea - increaseArea,
                    customer.getRoute().getLocation().getY() + rangeArea + increaseArea), listOfTaxi);
            if (availableTaxi == null || availableTaxi.size() < 2) {
                increaseArea++;
            } else {
                break;
            }
        }
        List<Distance> listOfDistance = taxiService.findDistanceBetweenTaxiAndClient(availableTaxi, customer);
        distance = new SortData(listOfDistance).sortDistance(new CompareDistance()).get(0);
        return distance;
    }












}
