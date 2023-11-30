package com.example.courtreservationwebsite.Model;


import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {

    private int id;
    private String username;
    private String passowrd;
    private String name;
    private String role;
    private String address;
    private String email;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dob;

    private boolean isLogin;

    public User(int id, String username, String passowrd, String name, String role, String address, String email, Date dob) {
        this.id = id;
        this.username = username;
        this.passowrd = passowrd;
        this.name = name;
        this.role = role;
        this.address = address;
        this.email = email;
        this.dob = dob;
    }

    public User() {
        this.isLogin = false;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassowrd() {
        return passowrd;
    }

    public void setPassowrd(String passowrd) {
        this.passowrd = passowrd;
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }
}
