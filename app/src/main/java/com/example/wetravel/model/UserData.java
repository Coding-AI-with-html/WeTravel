package com.example.wetravel.model;

public class UserData {

    private String Name;
    private String Surname;
    private String email;
    private String password;


    public UserData(String name, String surname, String email, String password) {
        Name = name;
        Surname = surname;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
