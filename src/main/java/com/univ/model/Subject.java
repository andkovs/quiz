package com.univ.model;

import java.util.ArrayList;

/**
 * Created by oleg on 3/14/15.
 */
public class Subject {

    private String name;
    private Long id;
    private Subject[] childSubject;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Subject[] getChildSubject() {
        return childSubject;
    }

    public void setChildSubject(Subject[] childSubject) {
        this.childSubject = childSubject;
    }
}
