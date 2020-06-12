package com.example.badad_tp8;

import java.io.Serializable;

public class Answer implements Serializable {

    String answerName;
    Boolean isAnswerIsCorrect;
    Boolean active;

    public Answer(String answerName, Boolean isAnswerIsCorrect, Boolean active) {
        this.answerName = answerName;
        this.isAnswerIsCorrect = isAnswerIsCorrect;
        this.active = active;
    }

    public String getAnswerName() {
        return answerName;
    }

    public void setAnswerName(String answerName) {
        this.answerName = answerName;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getAnswerIsCorrect() {
        return isAnswerIsCorrect;
    }

    public void setAnswerIsCorrect(Boolean answerIsCorrect) {
        isAnswerIsCorrect = answerIsCorrect;
    }

    @Override
    public String toString() {
        return this.answerName;
    }
}
