package com.sewaseven.database;

public class User {
    private String firstName;
    private String lastName;
    private String userID;
    private String[] followinId_services;


    public User() {
        this.firstName = "";
        this.lastName = "";
        this.userID = "";
        //this.followinId_services = "";
    }

    public User(String firstName, String lastName, String userID, String[] followinId_services) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userID = userID;
        this.followinId_services = followinId_services;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserID() {
        return userID;
    }

    public String[] getFollowinId_services() {
        return followinId_services;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setFollowinId_services(String[] followinId_services) {
        this.followinId_services = followinId_services;
    }
}
