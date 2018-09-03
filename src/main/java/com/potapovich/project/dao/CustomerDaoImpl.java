package com.potapovich.project.dao;

import com.potapovich.project.constant.Constant;
import com.potapovich.project.entity.Customer;
import com.potapovich.project.exception.DaoException;
import com.potapovich.project.pool.ConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {

    @Override
    public void registerUser(Customer customer) throws DaoException {

        String insertUser = Constant.REGISTER_USER;

        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(insertUser)) {

                preparedStatement.setString(1, customer.getName());
                preparedStatement.setString(2, customer.getPhoneNumber());
                preparedStatement.setBoolean(3, customer.isBannedStatus());
                preparedStatement.setInt(4, customer.getDiscountProcent());
                preparedStatement.setInt(5, customer.getCountOfTrip());
                preparedStatement.setString(6, customer.getLogin());
                preparedStatement.setString(7, customer.getPassword());
                preparedStatement.execute();

        }  catch (SQLException e) {
            throw new DaoException("registerUserError ", e);
        }
    }


    public Customer findRealLoginAndPassIfExist(String login, String password) throws DaoException {
        Customer customer =new Customer();

        int id = findCustomerIdByEnteredLoginAndPassword(login, password);

        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(Constant.FIND_REAL_LOGIN_AND_PASS)) {
          preparedStatement.setInt(1,id);
           ResultSet rs = preparedStatement.executeQuery();
            System.out.println(id);
            if (id>0) {
                customer.setId(id);
                while (rs.next()) {
                    customer.setLogin(rs.getString(1));
                    customer.setPassword(rs.getString(2));
                }
            }
            else {
                customer.setId(id);
            }

        } catch (SQLException e) {
            throw new DaoException("findRealLoginAndPassIfExistError ", e);
        }
        return customer;
    }



    @Override
    public Customer readUser(int id) throws DaoException {
        Customer customer =new Customer();
        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(Constant.READ_USER_BY_ID)) {
            preparedStatement.setInt(1,id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                customer.setId(rs.getInt(1));
                customer.setName(rs.getString(2));
                customer.setPhoneNumber(rs.getString(3));
                customer.setBannedStatus(rs.getBoolean(4));
                customer.setDiscountProcent(rs.getInt(5));
                customer.setCountOfTrip(rs.getInt(6));
                customer.setLogin(rs.getString(7));
                customer.setPassword(rs.getString(8));
            }
        } catch (SQLException e) {
            throw new DaoException("readUserError ", e);
        }
        return customer;
    }



    public Customer readUser(String login) throws DaoException{
        Customer customer = new Customer();
        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(Constant.READ_USER_BY_LOGIN)) {
            preparedStatement.setString(1,login);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                customer.setId(rs.getInt(1));
                customer.setName(rs.getString(2));
                customer.setPhoneNumber(rs.getString(3));
                customer.setBannedStatus(rs.getBoolean(4));
                customer.setDiscountProcent(rs.getInt(5));
                customer.setCountOfTrip(rs.getInt(6));
                customer.setLogin(rs.getString(7));
                customer.setPassword(rs.getString(8));
            }
        } catch (SQLException e) {
            throw new DaoException("readUserError ", e);
        }
        return customer;
    }



    public List<Customer> findAllUsers() throws DaoException {
        List<Customer> listOfUsers = new ArrayList<>();
        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(Constant.FIND_ALL_USERS)) {

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Customer customer =new Customer();
                customer.setId(rs.getInt(1));
                customer.setName(rs.getString(2));
                customer.setPhoneNumber(rs.getString(3));
                customer.setBannedStatus(rs.getBoolean(4));
                customer.setDiscountProcent(rs.getInt(5));
                customer.setCountOfTrip(rs.getInt(6));
                customer.setLogin(rs.getString(7));
                customer.setPassword(rs.getString(8));
                listOfUsers.add(customer);
            }
        } catch (SQLException e) {
            throw new DaoException("findAllUsersError ", e);
        }
        return listOfUsers;
    }






    public int findCustomerIdByEnteredLoginAndPassword(String login, String password) throws DaoException{

        int result = 0;

        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(Constant.FIND_USER_ID)) {
            preparedStatement.setString(1,login);
            ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {

            int customerId = rs.getInt(1);

            String realPassword = rs.getString(2);
            if (realPassword.equals(password)){
                result = customerId;
            }

        }
        } catch (SQLException e) {
            throw new DaoException("findCustomerIdByEnteredLoginAndPasswordError ", e);
        }

        return result;
    }




    public void updateCustomerData(int customerId, int discount, int tripNumber) throws DaoException{

        String inserted = Constant.UPDATE_CUSTOMER_DISC_TRIP;


        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(inserted)) {
            preparedStatement.setInt(1,discount);
            preparedStatement.setInt(2,tripNumber);
            preparedStatement.setInt(3,customerId);
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new DaoException("updateCustomerDataError ", e);
        }
    }

    public void updateCustomerLockStatus(int customerId, boolean updateStatus) throws DaoException{

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constant.UPDATE_CUSTOMER_LOCK_STATUS)) {
            preparedStatement.setBoolean(1,updateStatus);
            preparedStatement.setInt(2,customerId);
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new DaoException("updateCustomerLockStatusError ", e);
        }
    }


    public boolean changeUserPassword(String login, String oldPass, String newPass) throws DaoException {

        boolean result = false;

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constant.CHANGE_USER_PASSWORD)) {
            if (findCustomerIdByEnteredLoginAndPassword(login,oldPass)!=0) {
                preparedStatement.setString(1, newPass);
                preparedStatement.setString(2, login);
                preparedStatement.setString(3, oldPass);
                preparedStatement.execute();
                result = true;
            }

        } catch (SQLException e) {
            throw new DaoException("changeUserPasswordError ", e);
        }
        return result;
    }


    public boolean isLoginExist(String login) throws DaoException{

        boolean result = false;

        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(Constant.IS_LOGIN_EXIST)) {

            preparedStatement.setString(1,login);
            ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            String existLogin = rs.getString(1);
            if (login.equals(existLogin)) {
                result = true;
            }
            else {
                result = false;
            }
        }
        } catch (SQLException e) {
            throw new DaoException("isLoginExistError ", e);
        }
            return result;

    }


    public boolean isNameExist(String name) throws DaoException{

        boolean result = false;

        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(Constant.IS_NAME_EXIST)) {

            preparedStatement.setString(1,name);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String existName = rs.getString(1);
                if (name.equals(existName)) {
                    result = true;
                }
                else {
                    result = false;
                }
            }
        } catch (SQLException e) {
            throw new DaoException("isNameExistError ", e);
        }
        return result;

    }









/*

    public Customer readUser(String customer, String password) {
        Customer customer =new Customer();
        try {
            PreparedStatement preparedStatement =null;
            ResultSet rs = null;

            int id = findCustomer(customer, password);
            System.out.println(id);

            // Connection connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement("select customer_id, name, phone_number, status, discount, trip_number, customer, password from customer where customer_id='" + id + "'");
            rs = preparedStatement.executeQuery();
            while (rs.next()) {

                customer.setId(rs.getInt(1));
                customer.setName(rs.getString(2));
                customer.setPhoneNumber(rs.getString(3));
                customer.setBanned(rs.getBoolean(4));
                customer.setDiscountProcent(rs.getInt(5));
                customer.setCountOfTrip(rs.getInt(6));
                customer.setLogin(rs.getString(7));
                customer.setPassword(rs.getString(8));

            }
            rs.close();
            preparedStatement.close();



        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }
    */







}
