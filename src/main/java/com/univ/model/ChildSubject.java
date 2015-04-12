package com.univ.model;

/**
 * Created by Andrey on 12.04.2015.
 */
public class ChildSubject {

    Long childSubjectId;
    String childSubjectName;
    QuestionsFrom questionsFrom[];

    public Long getChildSubjectId() {
        return childSubjectId;
    }

    public void setChildSubjectId(Long childSubjectId) {
        this.childSubjectId = childSubjectId;
    }

    public String getChildSubjectName() {
        return childSubjectName;
    }

    public void setChildSubjectName(String childSubjectName) {
        this.childSubjectName = childSubjectName;
    }

    public QuestionsFrom[] getQuestionsFrom() {
        return questionsFrom;
    }

    public void setQuestionsFrom(QuestionsFrom[] questionsFrom) {
        this.questionsFrom = questionsFrom;
    }
}