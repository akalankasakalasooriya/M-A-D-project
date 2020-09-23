package com.sewaseven.database;



import com.google.firebase.firestore.IgnoreExtraProperties;
import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;
@IgnoreExtraProperties
public class Announsement {
    private String name;
    private String description;
    private String imagePath;
    private String proPicPath;
    private @ServerTimestamp Date serverTimeStamp;
    private  String publishedby;

    public Announsement(String name, String description, String imagePath, String proPicPath, Date serverTimeStamp, String publishedby) {
        this.name = name;
        this.description = description;
        this.imagePath = imagePath;
        this.proPicPath = proPicPath;
        this.serverTimeStamp = serverTimeStamp;
        this.publishedby = publishedby;
    }

    public Announsement() {
        this.description = "";
        this.imagePath = "";
        this.serverTimeStamp = null;
        this.publishedby = "";
        this.name = "";
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Date getServerTimeStamp() {
        return serverTimeStamp;
    }

    public void setServerTimeStamp(Date serverTimeStamp) {
        this.serverTimeStamp = serverTimeStamp;
    }

    public String getPublishedby() {
        return publishedby;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPublishedby(String publishedby) {
        this.publishedby = publishedby;
    }

    public String getProPicPath() {
        return proPicPath;
    }

    public void setProPicPath(String proPicPath) {
        this.proPicPath = proPicPath;
    }
}
