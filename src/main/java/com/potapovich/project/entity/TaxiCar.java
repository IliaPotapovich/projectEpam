package com.potapovich.project.entity;

import java.util.Objects;

public class TaxiCar {

    private int carId;
    private String model;
    private int ownerId;
    private int yearOFManufacture;
    private boolean deleteStatus = false;
    private int imageCarId;

    public TaxiCar() {
    }

    public TaxiCar(String model, int ownerId, int yearOFManufacture) {
        this.model = model;
        this.ownerId = ownerId;
        this.yearOFManufacture = yearOFManufacture;
    }

    public TaxiCar(int carId, String model, int ownerId, int yearOFManufacture) {
        this.carId = carId;
        this.model = model;
        this.ownerId = ownerId;
        this.yearOFManufacture = yearOFManufacture;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public int getYearOFManufacture() {
        return yearOFManufacture;
    }

    public void setYearOFManufacture(int yearOFManufacture) {
        this.yearOFManufacture = yearOFManufacture;
    }

    public boolean isDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(boolean deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public int getImageCarId() {
        return imageCarId;
    }

    public void setImageCarId(int imageCarId) {
        this.imageCarId = imageCarId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaxiCar taxiCar = (TaxiCar) o;
        return getCarId() == taxiCar.getCarId() &&
                getOwnerId() == taxiCar.getOwnerId() &&
                getYearOFManufacture() == taxiCar.getYearOFManufacture() &&
                isDeleteStatus() == taxiCar.isDeleteStatus() &&
                getImageCarId() == taxiCar.getImageCarId() &&
                Objects.equals(getModel(), taxiCar.getModel());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getCarId(), getModel(), getOwnerId(), getYearOFManufacture(), isDeleteStatus(), getImageCarId());
    }


    @Override
    public String toString() {
        return "TaxiCar{" +
                "carId=" + carId +
                ", model='" + model + '\'' +
                ", ownerId=" + ownerId +
                ", yearOFManufacture=" + yearOFManufacture +
                ", deleteStatus=" + deleteStatus +
                ", imageCarId=" + imageCarId +
                '}';
    }
}
