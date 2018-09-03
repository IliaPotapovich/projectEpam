package com.potapovich.project.dao;

import com.potapovich.project.constant.Constant;
import com.potapovich.project.entity.Admin;
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

public class AdminDaoImpl {
    private static final Logger LOGGER = LogManager.getLogger();

    public void createStandartAdmin() throws DaoException{
        String inserted = Constant.CREATE_ADMIN;

        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(inserted)) {

            preparedStatement.setString(1, Constant.DEFAULT_ADMIN_NAME);
            preparedStatement.setString(2, Constant.DEFAULT_ADMIN_PASSWORD);

            preparedStatement.execute();


        }   catch (SQLException e) {
            throw new DaoException("createStandartAdminError ", e);
        }
    }


    public void createAdmin(String name, String password) throws DaoException{
        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(Constant.CREATE_ADMIN)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, password);

            preparedStatement.execute();


        }   catch (SQLException e) {
            throw new DaoException("createAdminError ", e);
        }
    }



    public boolean isAdminLoginPassExist(String login, String password) throws DaoException{

            boolean result = false;

            try(Connection connection = ConnectionPool.getInstance().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(Constant.IS_ADMIN_LOGIN_PASS_EXIST)) {
                preparedStatement.setString(1,login);

                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    String existLogin = rs.getString(1);
                    String existPassword = rs.getString(2);
                    result = login.equals(existLogin) && password.equals(existPassword);
                }
            }  catch (SQLException e) {
                throw new DaoException("isAdminLoginPassExistError ", e);
            }
            return result;

        }

    public boolean isAdminLoginExist(String login) throws DaoException{

        boolean result = false;

        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(Constant.IS_ADMIN_LOGIN_EXIST)) {
            preparedStatement.setString(1,login);

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                String existLogin = rs.getString(1);
                result = login.equals(existLogin);
            }
        }  catch (SQLException e) {
            throw new DaoException("isAdminLoginExistError ", e);
        }
        return result;

    }

    public List<Admin> findAllAdminNames() throws DaoException{

        List<Admin> listOfAdmins = new ArrayList<>();
        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(Constant.FIND_ALL_ADMINS);
            ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                Admin admin = new Admin();
                admin.setName(rs.getString(1));
                admin.setPassword(rs.getString(2));
                listOfAdmins.add(admin);
            }
        }  catch (SQLException e) {
            throw new DaoException("findAllAdminNamesError ", e);
        }
        return listOfAdmins;
    }




    public boolean changeAdminPassword(String login, String oldPass, String newPass) throws DaoException {

        boolean result = false;

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constant.CHANGE_ADMIN_PASSWORD)) {
            if (isAdminLoginPassExist(login,oldPass)) {
                preparedStatement.setString(1, newPass);
                preparedStatement.setString(2, login);
                preparedStatement.setString(3, oldPass);
                preparedStatement.execute();
                result = true;
            }
            System.out.println("DAO RESULT  " + result);

        } catch (SQLException e) {
            throw new DaoException("changeAdminPasswordError ", e);
        }
        return result;
    }









}









