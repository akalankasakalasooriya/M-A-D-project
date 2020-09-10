package com.sewaseven.database;

public class FeedbackAndRating {
    private String posted_user_id;
    private float rating;
    private String comment;

    public FeedbackAndRating(String posted_user_id, float rating, String comment) {
        this.posted_user_id = posted_user_id;
        this.rating = rating;
        this.comment = comment;
    }

    public String getPosted_user_id() {
        return posted_user_id;
    }

    public float getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }
}
