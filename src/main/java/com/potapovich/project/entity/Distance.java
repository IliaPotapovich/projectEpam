package com.potapovich.project.entity;

import java.util.Objects;

public class Distance {

    private int id;
    private double distance;

    public Distance() {
    }

    public Distance(int id, double distance) {
        this.id = id;
        this.distance = distance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Distance distance1 = (Distance) o;
        return getId() == distance1.getId() &&
                Double.compare(distance1.getDistance(), getDistance()) == 0;
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getDistance());
    }

    @Override
    public String toString() {
        return "Distance{" +
                "id=" + id +
                ", distance=" + distance +
                '}';
    }
}



