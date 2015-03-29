package com.univ.model;

import java.util.ArrayList;

/**
 * Created by oleg on 3/14/15.
 */
public class Result {
    private Long subjectId;
    private String name;
    private String surname;
    private ResultAnswer[] answers;

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public ResultAnswer[] getAnswers() {
        return answers;
    }

    public void setAnswers(ResultAnswer[] answers) {
        this.answers = answers;
    }
}
