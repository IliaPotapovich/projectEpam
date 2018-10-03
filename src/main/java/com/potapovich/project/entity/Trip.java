package com.potapovich.project.entity;

import java.util.Objects;

public class Trip {

    private int tripId;
    private int customerId;
    private String customerName;
    private String customerPhone;
    private int markOfTrip;
    private double price;
    private Taxi taxi;
    private boolean inWay = false;
    private String startTripDate;
    private String finishTripDate;


    public Trip() {
    }

    public Trip(int tripId, int customerId, String customerName, String customerPhone, int markOfTrip, double price, Taxi taxi, boolean inWay) {
        this.tripId = tripId;
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.markOfTrip = markOfTrip;
        this.price = price;
        this.taxi = taxi;
        this.inWay = inWay;
    }

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public int getMarkOfTrip() {
        return markOfTrip;
    }

    public void setMarkOfTrip(int markOfTrip) {
        this.markOfTrip = markOfTrip;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Taxi getTaxi() {
        return taxi;
    }

    public void setTaxi(Taxi taxi) {
        this.taxi = taxi;
    }

    public boolean isInWay() {
        return inWay;
    }

    public void setInWay(boolean inWay) {
        this.inWay = inWay;
    }

    public String getStartTripDate() {
        return startTripDate;
    }

    public void setStartTripDate(String startTripDate) {
        this.startTripDate = startTripDate;
    }

    public String getFinishTripDate() {
        return finishTripDate;
    }

    public void setFinishTripDate(String finishTripDate) {
        this.finishTripDate = finishTripDate;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trip trip = (Trip) o;
        return getTripId() == trip.getTripId() &&
                getCustomerId() == trip.getCustomerId() &&
                getMarkOfTrip() == trip.getMarkOfTrip() &&
                Double.compare(trip.getPrice(), getPrice()) == 0 &&
                isInWay() == trip.isInWay() &&
                Objects.equals(getCustomerName(), trip.getCustomerName()) &&
                Objects.equals(getCustomerPhone(), trip.getCustomerPhone()) &&
                Objects.equals(getTaxi(), trip.getTaxi()) &&
                Objects.equals(getStartTripDate(), trip.getStartTripDate()) &&
                Objects.equals(getFinishTripDate(), trip.getFinishTripDate());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getTripId(), getCustomerId(), getCustomerName(), getCustomerPhone(), getMarkOfTrip(), getPrice(), getTaxi(), isInWay(), getStartTripDate(), getFinishTripDate());
    }


    @Override
    public String toString() {
        return "Trip{" +
                "tripId=" + tripId +
                ", customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", customerPhone='" + customerPhone + '\'' +
                ", markOfTrip=" + markOfTrip +
                ", price=" + price +
                ", taxi=" + taxi +
                ", inWay=" + inWay +
                ", startTripDate='" + startTripDate + '\'' +
                ", finishTripDate='" + finishTripDate + '\'' +
                '}';
    }
}
