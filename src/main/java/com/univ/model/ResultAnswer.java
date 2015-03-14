package com.univ.model;

/**
 * Created by oleg on 3/14/15.
 */
public class ResultAnswer {
    private Long questionId;
    private Long answerId;

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    public Long getQuestionId() {

        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }
}
