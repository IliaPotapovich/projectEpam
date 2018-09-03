package com.potapovich.project.entity;

import java.util.List;
import java.util.Objects;

public class Taxi {

    private int id;
    private TaxiDriver taxiDriver;
    private TaxiCar taxiCar;
    private boolean active;
    private boolean free = true;
    private Point taxiLocation;
    private List<Customer> listOfClients;

    private String startWorkDate;
    private String finishWorkDate;


    public Taxi() {
    }

    public Taxi(TaxiDriver taxiDriver, TaxiCar taxiCar) {
        this.taxiDriver = taxiDriver;
        this.taxiCar = taxiCar;
    }

    public Taxi(TaxiDriver taxiDriver, TaxiCar taxiCar, Point taxiLocation) {
        this.taxiDriver = taxiDriver;
        this.taxiCar = taxiCar;
        this.taxiLocation = taxiLocation;
    }

    public Taxi(int id, TaxiDriver taxiDriver, TaxiCar taxiCar, boolean active) {
        this.id = id;
        this.taxiDriver = taxiDriver;
        this.taxiCar = taxiCar;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TaxiDriver getTaxiDriver() {
        return taxiDriver;
    }

    public void setTaxiDriver(TaxiDriver taxiDriver) {
        this.taxiDriver = taxiDriver;
    }

    public TaxiCar getTaxiCar() {
        return taxiCar;
    }

    public void setTaxiCar(TaxiCar taxiCar) {
        this.taxiCar = taxiCar;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public Point getTaxiLocation() {
        return taxiLocation;
    }

    public void setTaxiLocation(Point taxiLocation) {
        this.taxiLocation = taxiLocation;
    }

    public List<Customer> getListOfClients() {
        return listOfClients;
    }

    public void setListOfClients(List<Customer> listOfClients) {
        this.listOfClients = listOfClients;
    }

    public String getStartWorkDate() {
        return startWorkDate;
    }

    public void setStartWorkDate(String startWorkDate) {
        this.startWorkDate = startWorkDate;
    }

    public String getFinishWorkDate() {
        return finishWorkDate;
    }

    public void setFinishWorkDate(String finishWorkDate) {
        this.finishWorkDate = finishWorkDate;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Taxi taxi = (Taxi) o;
        return getId() == taxi.getId() &&
                isActive() == taxi.isActive() &&
                isFree() == taxi.isFree() &&
                Objects.equals(getTaxiDriver(), taxi.getTaxiDriver()) &&
                Objects.equals(getTaxiCar(), taxi.getTaxiCar()) &&
                Objects.equals(getTaxiLocation(), taxi.getTaxiLocation()) &&
                Objects.equals(getListOfClients(), taxi.getListOfClients()) &&
                Objects.equals(getStartWorkDate(), taxi.getStartWorkDate()) &&
                Objects.equals(getFinishWorkDate(), taxi.getFinishWorkDate());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getTaxiDriver(), getTaxiCar(), isActive(), isFree(), getTaxiLocation(), getListOfClients(), getStartWorkDate(), getFinishWorkDate());
    }

    @Override
    public String toString() {
        return "Taxi{" +
                "id=" + id +
                ", taxiDriver=" + taxiDriver +
                ", taxiCar=" + taxiCar +
                ", active=" + active +
                ", free=" + free +
                ", taxiLocation=" + taxiLocation +
                ", listOfClients=" + listOfClients +
                ", startWorkDate='" + startWorkDate + '\'' +
                ", finishWorkDate='" + finishWorkDate + '\'' +
                '}';
    }
}