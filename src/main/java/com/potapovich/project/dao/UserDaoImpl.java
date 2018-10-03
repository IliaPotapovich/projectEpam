package com.potapovich.project.dao;

import com.potapovich.project.constant.Constant;
import com.potapovich.project.entity.Customer;
import com.potapovich.project.exception.DaoException;
import com.potapovich.project.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    @Override
    public void registerUser(Customer customer) throws DaoException {
        String insertUser = Constant.REGISTER_USER;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertUser)) {
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getPhoneNumber());
            preparedStatement.setBoolean(3, customer.isBannedStatus());
            preparedStatement.setInt(4, customer.getDiscountProcent());
            preparedStatement.setInt(5, customer.getCountOfTrip());
            preparedStatement.setString(6, customer.getLogin());
            preparedStatement.setString(7, customer.getPassword());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DaoException("registerUserError ", e);
        }
    }

    @Override
    public boolean findRealLoginAndPassIfExist(String login, String password) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constant.FIND_REAL_LOGIN_AND_PASS)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new DaoException("findRealLoginAndPassIfExistError ", e);
        }
    }


    @Override
    public Customer readUser(int id) throws DaoException {
        Customer customer = new Customer();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constant.READ_USER_BY_ID)) {
            preparedStatement.setInt(1, id);
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

    @Override
    public Customer readUser(String login) throws DaoException {
        Customer customer = new Customer();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constant.READ_USER_BY_LOGIN)) {
            preparedStatement.setString(1, login);
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

    @Override
    public List<Customer> findAllUsers() throws DaoException {
        List<Customer> listOfUsers = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constant.FIND_ALL_USERS)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getInt(1));
                customer.setName(rs.getString(2));
                customer.setPhoneNumber(rs.getString(3));
                customer.setBannedStatus(rs.getBoolean(4));
                customer.setDiscountProcent(rs.getInt(5));
                customer.setCountOfTrip(rs.getInt(6));
                customer.setLogin(rs.getString(7));
                listOfUsers.add(customer);
            }
        } catch (SQLException e) {
            throw new DaoException("findAllUsersError ", e);
        }
        return listOfUsers;
    }

    @Override
    public int findCustomerIdByEnteredLoginAndPassword(String login, String password) throws DaoException {
        int customerId = 0;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constant.FIND_USER_ID)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                customerId = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new DaoException("findCustomerIdByEnteredLoginAndPasswordError ", e);
        }
        return customerId;
    }

    @Override
    public void updateCustomerData(int customerId, int discount, int tripNumber) throws DaoException {
        String inserted = Constant.UPDATE_CUSTOMER_DISC_TRIP;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(inserted)) {
            preparedStatement.setInt(1, discount);
            preparedStatement.setInt(2, tripNumber);
            preparedStatement.setInt(3, customerId);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DaoException("updateCustomerDataError ", e);
        }
    }

    @Override
    public void updateCustomerLockStatus(int customerId, boolean updateStatus) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constant.UPDATE_CUSTOMER_LOCK_STATUS)) {
            preparedStatement.setBoolean(1, updateStatus);
            preparedStatement.setInt(2, customerId);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DaoException("updateCustomerLockStatusError ", e);
        }
    }

    @Override
    public boolean changeUserPassword(String login, String oldPass, String newPass) throws DaoException {
        boolean result = false;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constant.CHANGE_USER_PASSWORD)) {
            if (findCustomerIdByEnteredLoginAndPassword(login, oldPass) != 0) {
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

    @Override
    public boolean isLoginExist(String login) throws DaoException {
        boolean result = false;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constant.IS_LOGIN_EXIST)) {
            preparedStatement.setString(1, login);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                String existLogin = rs.getString(1);
                result = login.equals(existLogin);
            }
        } catch (SQLException e) {
            throw new DaoException("isLoginExistError ", e);
        }
        return result;
    }
}
