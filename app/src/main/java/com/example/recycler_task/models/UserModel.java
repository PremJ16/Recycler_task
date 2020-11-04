package com.example.recycler_task.models;

public class UserModel {

    //variables
    private String name;
    private String gender;

    //constructor
    public UserModel() {
        //for firebase
    }

    public UserModel(String name, String gender) {
        this.name = name;
        this.gender = gender;
    }

    //adding getters
    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    //adding setters
    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}