package com.sewaseven.database;

import com.google.firebase.firestore.IgnoreExtraProperties;
import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

@IgnoreExtraProperties
public class FeedbackAndRatingsModel {
    private String posted_user_name;
    private String posted_user_id;
    private String rating;
    private String comment;
    private @ServerTimestamp
    Date serverTimeStamp;


    public FeedbackAndRatingsModel() {
        this.posted_user_id = "";
        this.rating = "";
        this.comment = "";
        serverTimeStamp = null;
        this.posted_user_name = "";
    }


    public FeedbackAndRatingsModel(String posted_user_id, String rating, String comment, String posted_user_name) {
        this.posted_user_id = posted_user_id;
        this.rating = rating;
        this.comment = comment;
        this.posted_user_name = posted_user_name;
    }

    public Date getServerTimeStamp() {
        return serverTimeStamp;
    }

    public void setServerTimeStamp(Date serverTimeStamp) {
        this.serverTimeStamp = serverTimeStamp;
    }

    public String getPosted_user_id() {
        return posted_user_id;
    }

    public String getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public void setPosted_user_id(String posted_user_id) {
        this.posted_user_id = posted_user_id;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getPosted_user_name() {
        return posted_user_name;
    }

    public void setPosted_user_name(String posted_user_name) {
        this.posted_user_name = posted_user_name;
    }
}
