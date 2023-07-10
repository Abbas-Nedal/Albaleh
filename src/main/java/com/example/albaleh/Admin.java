package com.example.albaleh;

public class Admin {


   private String Password , userName ;

    public String getPassword() {
        return Password;
    }

    public String getUserName() {
        return userName;
    }

    public Admin() {
        this.Password = "admin";
        this.userName = "admin";
        this.status = false;
    }

    private boolean status ;


    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }
}
