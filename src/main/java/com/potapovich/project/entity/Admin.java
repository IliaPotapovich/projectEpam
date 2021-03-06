package com.potapovich.project.entity;


import java.util.Objects;

public class Admin {

    private String name;
    private String password;


    public Admin() {
    }

    public Admin(String name, String password) {
        this.name = name;
        this.password = password;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        if (!(o instanceof Admin)) return false;
        Admin admin = (Admin) o;
        return Objects.equals(getName(), admin.getName()) &&
                Objects.equals(getPassword(), admin.getPassword());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getName(), getPassword());
    }

    @Override
    public String toString() {
        return "Admin{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
