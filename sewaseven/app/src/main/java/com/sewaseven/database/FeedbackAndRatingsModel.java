package com.sewaseven.database;

public class FeedbackAndRatingsModel {
    private String posted_user_id;
    private String rating;
    private String comment;



    public FeedbackAndRatingsModel() {
        this.posted_user_id = "";
        this.rating = "";
        this.comment = "";
    }


    public FeedbackAndRatingsModel(String posted_user_id, String rating, String comment) {
        this.posted_user_id = posted_user_id;
        this.rating = rating;
        this.comment = comment;
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
}
