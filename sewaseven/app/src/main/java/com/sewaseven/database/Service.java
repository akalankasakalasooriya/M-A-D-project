package com.sewaseven.database;

import android.location.Location;

import com.google.firebase.firestore.IgnoreExtraProperties;

import java.io.Serializable;

@IgnoreExtraProperties

public class Service implements Serializable {
    private String DocID;
    private String name;
    private String profile_pic_path;
    private String[] followers_id;
    private String location;
    private String email;
    private String tp_number;
    private String description;
    private String owned_user_id;

    public Service() {
        this.name = "";
        this.profile_pic_path = "";
        //this.followers_id = "";
        this.location = "";
        this.email = "";
        this.tp_number = "";
        this.description = "";
        this.owned_user_id = "";
    }

    public Service(String name, String profile_pic_path, String[] followers_id, String location, String email, String tp_number, String description, String owned_user_id) {
        this.name = name;
        this.profile_pic_path = profile_pic_path;
        this.followers_id = followers_id;
        this.location = location;
        this.email = email;
        this.tp_number = tp_number;
        this.description = description;
        this.owned_user_id = owned_user_id;
    }

    public String getName() {
        return name;
    }

    public String getProfile_pic_path() {
        return profile_pic_path;
    }

    public String[] getFollowers_id() {
        return followers_id;
    }

    public String getLocation() {
        return location;
    }

    public String getEmail() {
        return email;
    }

    public String getTp_number() {
        return tp_number;
    }

    public String getDescription() {
        return description;
    }

    public String getOwned_user_id() {
        return owned_user_id;
    }

    public String getDocID() {
        return DocID;
    }

    public void setDocID(String docID) {
        DocID = docID;
    }

    public void setName(String name){this.name = name;} //kanchila

    public void setDescription(String description){this.description = description;} //kanchila

    public void setTp_number(String tp_number){this.tp_number = tp_number;} //kanchila

    public void setLocation(String location){this.location = location;} //kanchila

}
