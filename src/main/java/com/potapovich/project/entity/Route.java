package com.potapovich.project.entity;

import java.util.Objects;

public class Route {

    private int id;
    private Point location;
    private Point destination;

    public Route() {
    }

    public Route(int id, Point location) {
        this.id = id;
        this.location = location;
    }

    public Route(Point location) {
        this.location = location;
    }

    public Route(Point location, Point destination) {
        this.location = location;
        this.destination = destination;
    }

    public Route(int id, Point location, Point destination) {
        this.id = id;
        this.location = location;
        this.destination = destination;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public Point getDestination() {
        return destination;
    }

    public void setDestination(Point destination) {
        this.destination = destination;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return getId() == route.getId() &&
                Objects.equals(getLocation(), route.getLocation()) &&
                Objects.equals(getDestination(), route.getDestination());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLocation(), getDestination());
    }

    @Override
    public String toString() {
        return "Route{" +
                "ClientId=" + id +
                ", location=" + location +
                ", destination=" + destination +
                '}';
    }
}
