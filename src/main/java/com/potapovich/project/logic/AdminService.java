package com.potapovich.project.logic;

import com.potapovich.project.dao.AdminDao;
import com.potapovich.project.dao.AdminDaoImpl;
import com.potapovich.project.entity.Admin;
import com.potapovich.project.exception.DaoException;
import com.potapovich.project.exception.LogicException;

import java.util.List;

public class AdminService {

    public boolean isAdminLoginPasswordExist(String login, String password) throws LogicException {
        AdminDao adminDao = new AdminDaoImpl();
        boolean result;
        try {
            result = adminDao.isAdminLoginPassExist(login, password);
        } catch (DaoException e) {
            throw new LogicException("isAdminLoginPasswordExistError ", e);
        }
        return result;
    }


    public boolean changeAdminPassword(String login, String oldPass, String newPass) throws LogicException {
        AdminDao adminDao = new AdminDaoImpl();
        boolean result;
        try {
            result = adminDao.changeAdminPassword(login, oldPass, newPass);
        } catch (DaoException e) {
            throw new LogicException("changeAdminPasswordError ", e);
        }
        return result;
    }


    public boolean adminRegistration(Admin admin) throws LogicException {
        AdminDao adminDao = new AdminDaoImpl();
        try {
            if (!adminDao.isAdminLoginExist(admin.getName())) {
                adminDao.createAdmin(admin.getName(), admin.getPassword());
                return true;
            } else {
                return false;
            }
        } catch (DaoException e) {
            throw new LogicException("adminRegistrationError ", e);
        }
    }


    public List<Admin> findListOfAdminName() throws LogicException {
        AdminDao adminDao = new AdminDaoImpl();
        List<Admin> listOfAdmin;
        try {
            listOfAdmin = adminDao.findAllAdminNames();
        } catch (DaoException e) {
            throw new LogicException("findListOfAdminNameError ", e);
        }
        return listOfAdmin;
    }
}
