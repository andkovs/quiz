package com.univ.model;

/**
 * Created by Andrey on 05.04.2015.
 */
public class TestResponse {

    private String name;
    private String surname;
    private int correctAnswers;
    private IncorrectAnswers[] questionsWithUncorrectAnswers;

    public IncorrectAnswers[] getQuestionsWithUncorrectAnswers() {
        return questionsWithUncorrectAnswers;
    }

    public void setQuestionsWithUncorrectAnswers(IncorrectAnswers[] questionsWithUncorrectAnswers) {
        this.questionsWithUncorrectAnswers = questionsWithUncorrectAnswers;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setCorrectAnswers(int correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }



}
