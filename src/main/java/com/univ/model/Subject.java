package com.univ.model;

import java.util.ArrayList;

/**
 * Created by oleg on 3/14/15.
 */
public class Subject {

    private String name;
    private Long id;
    private ChildSubject[] childSubject;

    public ChildSubject[] getChildSubject() {
        return childSubject;
    }

    public void setChildSubject(ChildSubject[] childSubject) {
        this.childSubject = childSubject;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }




}
