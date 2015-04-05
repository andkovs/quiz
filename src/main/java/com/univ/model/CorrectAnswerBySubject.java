package com.univ.model;

/**
 * Created by Andrey on 05.04.2015.
 */
public class CorrectAnswerBySubject {

    private Long subjectId;
    private CorrectAnswer[] correctAnswers;

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public void setCorrectAnswers(CorrectAnswer[] correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public CorrectAnswer[] getCorrectAnswers() {
        return correctAnswers;
    }
}




