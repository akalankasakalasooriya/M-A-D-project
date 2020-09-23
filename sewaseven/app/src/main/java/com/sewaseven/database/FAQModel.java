package com.sewaseven.database;

import com.google.firebase.firestore.IgnoreExtraProperties;

import java.io.Serializable;

@IgnoreExtraProperties
public class FAQModel implements Serializable {
    private String docID;
    private String answer;
    private String question;

    public FAQModel() {
        this.answer = "";
        this.question = "";
    }

    public FAQModel(String answer, String question) {
        this.answer = answer;
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getDocID() {
        return docID;
    }

    public void setDocID(String docID) {
        this.docID = docID;
    }
}
