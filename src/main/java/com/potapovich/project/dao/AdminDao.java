package com.potapovich.project.dao;

import com.potapovich.project.entity.Admin;
import com.potapovich.project.exception.DaoException;

import java.util.List;

public interface AdminDao {


    void createStandartAdmin() throws DaoException;

    /**
     * Create an administrator
     * Void
     * @throws DaoException if SQLException
     */
    void createAdmin(String name, String password) throws DaoException;

    /**
     * When registering, checks whether the given login and password exist in the database
     * @return boolean
     * @throws DaoException if SQLException
     */
    boolean isAdminLoginPassExist(String login, String password) throws DaoException;

    /**
     * When registering, checks whether the given login and password exist in the database
     * @return boolean
     * @throws DaoException if SQLException
     */
    boolean isAdminLoginExist(String login) throws DaoException;

    /**
     * Displays the list of Administrator names
     * @return List<Admin>
     * @throws DaoException if SQLException
     */
    List<Admin> findAllAdminNames() throws DaoException;

    /**
     * When the login and old password match, it changes to a new one
     * @return boolean
     * @throws DaoException if SQLException
     */
    boolean changeAdminPassword(String login, String oldPass, String newPass) throws DaoException;

}
