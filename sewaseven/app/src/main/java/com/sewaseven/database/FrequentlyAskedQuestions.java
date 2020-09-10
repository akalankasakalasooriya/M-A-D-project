package com.sewaseven.database;

import com.google.type.DateTime;

public class FrequentlyAskedQuestions {
    private String question;
    private String answers;
    private String service_id;
    private DateTime date_time;

    public FrequentlyAskedQuestions(String question, String answers, String service_id, DateTime date_time) {
        this.question = question;
        this.answers = answers;
        this.service_id = service_id;
        this.date_time = date_time;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswers() {
        return answers;
    }

    public String getService_id() {
        return service_id;
    }

    public DateTime getDate_time() {
        return date_time;
    }
}
