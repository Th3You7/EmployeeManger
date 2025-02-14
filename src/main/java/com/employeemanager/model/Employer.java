package com.employeemanager.model;
import java.util.UUID;

public class Employer {
    private int _id;
    private String name;
    private String gender;
    private String address;
    private String phone;
    private String email;

    public Employer(int id, String name, String gender,String address, String phone, String email) {
        this._id = id;
        this.name = name;
        this.gender = gender;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    public Employer( String name, String gender,String address, String phone, String email) {
        this.name = name;
        this.gender = gender;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    public int get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return this._id + " " + this.name + " " + this.address + " " + this.phone + " " + this.email;
    }
}
