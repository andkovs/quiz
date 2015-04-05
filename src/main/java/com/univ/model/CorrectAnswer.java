package com.univ.model;

/**
 * Created by Andrey on 05.04.2015.
 */
public class CorrectAnswer {

    private Long questionId;
    private Long correctAnswerId;

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public void setCorrectAnswerId(Long correctAnswerId) {
        this.correctAnswerId = correctAnswerId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public Long getCorrectAnswerId() {
        return correctAnswerId;
    }



}
