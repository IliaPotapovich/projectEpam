package com.potapovich.project.entity;

import java.util.Objects;

public class Customer {

    private int id;
    private String name;
    private String phoneNumber;
    private boolean bannedStatus = false;
    private int discountProcent;
    private int countOfTrip;
    private String login;
    private String password;

    private Route route;
    private boolean isTripComplete = false;

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public boolean isTripComplete() {
        return isTripComplete;
    }

    public void setTripComplete(boolean tripComplete) {
        isTripComplete = tripComplete;
    }

    public Customer() {
    }



    public Customer(String name, String phoneNumber, Route route) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.route = route;
    }

    public Customer(String login, String password, String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.login = login;
        this.password = password;
    }

    public Customer(int id, String name, String phoneNumber, boolean bannedStatus, int discountProcent, int countOfTrip) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.bannedStatus = bannedStatus;
        this.discountProcent = discountProcent;
        this.countOfTrip = countOfTrip;
    }

    public Customer(int id, String name, String phoneNumber, boolean bannedStatus, int discountProcent, int countOfTrip, String login, String password) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.bannedStatus = bannedStatus;
        this.discountProcent = discountProcent;
        this.countOfTrip = countOfTrip;
        this.login = login;
        this.password = password;
    }

    public Customer(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public Customer(int id, String name, String phoneNumber, boolean bannedStatus, int discountProcent, int countOfTrip, String login, String password, Route route, boolean isTripComplete) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.bannedStatus = bannedStatus;
        this.discountProcent = discountProcent;
        this.countOfTrip = countOfTrip;
        this.login = login;
        this.password = password;
        this.route = route;
        this.isTripComplete = isTripComplete;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isBannedStatus() {
        return bannedStatus;
    }

    public void setBannedStatus(boolean bannedStatus) {
        this.bannedStatus = bannedStatus;
    }

    public int getDiscountProcent() {
        return discountProcent;
    }

    public void setDiscountProcent(int discountProcent) {
        this.discountProcent = discountProcent;
    }

    public int getCountOfTrip() {
        return countOfTrip;
    }

    public void setCountOfTrip(int countOfTrip) {
        this.countOfTrip = countOfTrip;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return getId() == customer.getId() &&
                isBannedStatus() == customer.isBannedStatus() &&
                getDiscountProcent() == customer.getDiscountProcent() &&
                getCountOfTrip() == customer.getCountOfTrip() &&
                isTripComplete() == customer.isTripComplete() &&
                Objects.equals(getName(), customer.getName()) &&
                Objects.equals(getPhoneNumber(), customer.getPhoneNumber()) &&
                Objects.equals(getLogin(), customer.getLogin()) &&
                Objects.equals(getPassword(), customer.getPassword()) &&
                Objects.equals(getRoute(), customer.getRoute());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getName(), getPhoneNumber(), isBannedStatus(), getDiscountProcent(), getCountOfTrip(), getLogin(), getPassword(), getRoute(), isTripComplete());
    }


    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", bannedStatus=" + bannedStatus +
                ", discountProcent=" + discountProcent +
                ", countOfTrip=" + countOfTrip +
                ", customer='" + login + '\'' +
                ", password='" + password + '\'' +
                ", route=" + route +
                ", isTripComplete=" + isTripComplete +
                '}';
    }
}
