package com.sewaseven.database;

import com.google.type.DateTime;

public class Announsement {
    private String description;
    private String imagePath;
    private DateTime publishedDate;

    public Announsement(String description, String imagePath, DateTime publishedDate) {
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

    public DateTime getPublishedDate() {
        return publishedDate;
    }
}
