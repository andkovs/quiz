package com.univ.model;

import java.util.ArrayList;

/**
 * Created by oleg on 3/14/15.
 */
public class Question {
    private Long id;
    private Long subjectId;
    private String question;
    private Answer[] answers;


    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Answer[] getAnswer() {
        return answers;
    }

    public void setAnswer(Answer[] answers) {
        this.answers = answers;
    }
}
