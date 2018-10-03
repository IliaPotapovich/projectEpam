package com.potapovich.project.entity;

import java.util.Objects;

public class TaxiDriver {
    private int driverId;
    private String driverName;
    private String driverPassword;
    private int experience;
    private boolean status = false;
    private boolean deleteStatus = false;

    public TaxiDriver() {
    }

    public TaxiDriver(String driverName, String driverPassword, int experience) {
        this.driverName = driverName;
        this.driverPassword = driverPassword;
        this.experience = experience;
    }


    public TaxiDriver(int driverId, String driverName, String driverPassword, int experience, boolean status) {
        this.driverId = driverId;
        this.driverName = driverName;
        this.driverPassword = driverPassword;
        this.experience = experience;
        this.status = status;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverPassword() {
        return driverPassword;
    }

    public void setDriverPassword(String driverPassword) {
        this.driverPassword = driverPassword;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(boolean deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaxiDriver driver = (TaxiDriver) o;
        return getDriverId() == driver.getDriverId() &&
                getExperience() == driver.getExperience() &&
                isStatus() == driver.isStatus() &&
                isDeleteStatus() == driver.isDeleteStatus() &&
                Objects.equals(getDriverName(), driver.getDriverName()) &&
                Objects.equals(getDriverPassword(), driver.getDriverPassword());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getDriverId(), getDriverName(), getDriverPassword(), getExperience(), isStatus(), isDeleteStatus());
    }


    @Override
    public String toString() {
        return "TaxiDriver{" +
                "driverId=" + driverId +
                ", driverName='" + driverName + '\'' +
                ", driverPassword='" + driverPassword + '\'' +
                ", experience=" + experience +
                ", status=" + status +
                ", deleteStatus=" + deleteStatus +
                '}';
    }
}
