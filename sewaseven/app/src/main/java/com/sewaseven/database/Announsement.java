package com.sewaseven.database;



import java.util.Date;

public class Announsement {
    private String description;
    private String imagePath;
    private Date publishedDate;

    public Announsement(String description, String imagePath, Date publishedDate) {
        this.description = description;
        this.imagePath = imagePath;
        this.publishedDate = publishedDate;
    }

    public String getDescription() {
        return description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }
}
