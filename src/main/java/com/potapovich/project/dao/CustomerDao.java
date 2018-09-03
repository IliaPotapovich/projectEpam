package com.potapovich.project.dao;


import com.potapovich.project.entity.Customer;
import com.potapovich.project.exception.DaoException;

public interface CustomerDao {

    void registerUser(Customer customer) throws DaoException;
    Customer readUser(int id) throws DaoException;

}
