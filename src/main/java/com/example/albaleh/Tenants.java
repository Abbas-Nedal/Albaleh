package com.example.albaleh;

public class Tenants {

    private String Name;

    public void setName(String name) {
        Name = name;
    }

    public String getName() {
        return Name;
    }

    private String Password , userName ;
    private boolean status ;

    public Tenants() {
        this.Password = "";
        this.userName = "";
        this.status = false;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
