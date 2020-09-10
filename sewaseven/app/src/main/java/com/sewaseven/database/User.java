package com.sewaseven.database;

public class User {
    private String firstName;
    private String lastName;
    private String userID;
    private String[] followinId_services;


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
}
