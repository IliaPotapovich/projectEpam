package com.potapovich.project.dao;


import com.potapovich.project.entity.Customer;
import com.potapovich.project.exception.DaoException;

import java.util.List;

public interface UserDao {

    /**
     * User registration
     * Void
     * @throws DaoException if SQLException
     */
    void registerUser(Customer customer) throws DaoException;

    /**
     * Determining if a user exists with this login and password
     * @return boolean
     * @throws DaoException if SQLException
     */
    boolean findRealLoginAndPassIfExist(String login, String password) throws DaoException;

    /**
     * Displaying personal information of a user using a personal number
     * @return Customer
     * @throws DaoException if SQLException
     */
    Customer readUser(int id) throws DaoException;

    /**
     * Displaying the user's personal data with a username
     * @return Customer
     * @throws DaoException if SQLException
     */
    Customer readUser(String login) throws DaoException;

    /**
     * Displaying the list of existing users with personal data
     * @return List<Customer>
     * @throws DaoException if SQLException
     */
    List<Customer> findAllUsers() throws DaoException;

    /**
     * Find a personal user number with his login and password
     * @return int (User Id)
     * @throws DaoException if SQLException
     */
    int findCustomerIdByEnteredLoginAndPassword(String login, String password) throws DaoException;

    /**
     * Update user data (discount percentage, number of completed trips)
     * void
     * @throws DaoException if SQLException
     */
    void updateCustomerData(int customerId, int discount, int tripNumber) throws DaoException;

    /**
     * Update user lock status
     * void
     * @throws DaoException if SQLException
     */
    void updateCustomerLockStatus(int customerId, boolean updateStatus) throws DaoException;

    /**
     * Change User Password
     * @return boolean
     * @throws DaoException if SQLException
     */
    boolean changeUserPassword(String login, String oldPass, String newPass) throws DaoException;

    /**
     * Determines if this user name exists
     * @return boolean
     * @throws DaoException if SQLException
     */
    boolean isLoginExist(String login) throws DaoException;
}
