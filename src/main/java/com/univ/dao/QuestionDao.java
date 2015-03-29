package com.univ.dao;

import com.univ.model.Answer;
import com.univ.model.Question;

/**
 * Created by oleg on 3/14/15.
 */
public class QuestionDao {

    public Question[] loadQuestionBySubjectId(Long subjectId){
        // todo: asap load real data
        // dummy data
        Question[] questions = new Question[0];
        if(subjectId == 1) {
            questions = new Question[2];
            questions[0] = new Question();
            questions[0].setId(1l);
            questions[0].setSubjectId(2l);
            questions[0].setQuestion("How match is the Fish?");

            Answer[] answers = new Answer[2];
            answers[0] = new Answer();
            answers[0].setSubjectId(2l);
            answers[0].setAnswerId(1l);
            answers[0].setQuestionId(1l);
            answers[0].setAnswer("5$");

            answers[1] = new Answer();
            answers[1].setSubjectId(2l);
            answers[1].setAnswerId(2l);
            answers[1].setQuestionId(1l);
            answers[1].setAnswer("10$");
            questions[0].setAnswer(answers);
            questions[1] = new Question();
            questions[1].setId(2l);
            questions[1].setSubjectId(2l);
            questions[1].setQuestion("How match is the Pig?");

            answers = new Answer[2];
            answers[0] = new Answer();
            answers[0].setSubjectId(2l);
            answers[0].setAnswerId(1l);
            answers[0].setQuestionId(2l);
            answers[0].setAnswer("100$");

            answers[1] = new Answer();
            answers[1].setSubjectId(2l);
            answers[1].setAnswerId(2l);
            answers[1].setQuestionId(1l);
            answers[1].setAnswer("200$");
            questions[1].setAnswer(answers);
        }




        return questions;

    }
}
