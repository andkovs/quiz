package com.univ.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.univ.model.Answer;
import com.univ.model.Question;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by oleg on 3/14/15.
 */
public class QuestionDao {

    public Question[] loadQuestionBySubjectId(Long subjectId){
        // todo: asap load real data
        // dummy data
//        Question[] questions = new Question[0];
//        if(subjectId == 1) {
//            questions = new Question[2];
//            questions[0] = new Question();
//            questions[0].setId(1l);
//            questions[0].setSubjectId(2l);
//            questions[0].setQuestion("How match is the Fish?");
//
//            Answer[] answers = new Answer[2];
//            answers[0] = new Answer();
//            answers[0].setSubjectId(2l);
//            answers[0].setAnswerId(1l);
//            answers[0].setQuestionId(1l);
//            answers[0].setAnswer("5$");
//
//            answers[1] = new Answer();
//            answers[1].setSubjectId(2l);
//            answers[1].setAnswerId(2l);
//            answers[1].setQuestionId(1l);
//            answers[1].setAnswer("10$");
//            questions[0].setAnswer(answers);
//            questions[1] = new Question();
//            questions[1].setId(2l);
//            questions[1].setSubjectId(2l);
//            questions[1].setQuestion("How match is the Pig?");
//
//            answers = new Answer[2];
//            answers[0] = new Answer();
//            answers[0].setSubjectId(2l);
//            answers[0].setAnswerId(1l);
//            answers[0].setQuestionId(2l);
//            answers[0].setAnswer("100$");
//
//            answers[1] = new Answer();
//            answers[1].setSubjectId(2l);
//            answers[1].setAnswerId(2l);
//            answers[1].setQuestionId(1l);
//            answers[1].setAnswer("200$");
//            questions[1].setAnswer(answers);
//        }
//
//
//
//
//        return questions;

        BufferedReader inQestion;
        String s;
        StringBuffer s2;
        //Question[] questions = new Question[0];
        Question[] allQuestions = new Question[0];

        try {
            // read from Json
            InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("/database/questions.js");
            inQestion = new BufferedReader(new InputStreamReader(resourceAsStream, "UTF8"));
            s2 = new StringBuffer();
            while ((s = inQestion.readLine()) != null) {
                s2.append(s + "\n") ;
            }
            inQestion.close();
            //write from Json into object (subject)

            Gson gson = new GsonBuilder().create();
            allQuestions = gson.fromJson(s2.toString(), Question[].class);


        } catch(FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex){
            System.out.println(ex);
        }

        ArrayList<Question> tmpSubjectList = new ArrayList<Question>();
        for (int i = 0; i < allQuestions.length; i++) {
            if(subjectId == allQuestions[i].getSubjectId()){
                tmpSubjectList.add(allQuestions[i]);
            }
        }

        ArrayList<Question> finalQuestionList = new ArrayList<Question>();
        Random rand = new Random();
        int r;
        for (int i = 0; i < 3; i++) {
            r = rand.nextInt(tmpSubjectList.size());
            finalQuestionList.add(tmpSubjectList.get(r));
            tmpSubjectList.remove(r);
        }

        Question[] questions = new Question[finalQuestionList.size()];
        questions = finalQuestionList.toArray(questions);




        return questions;

    }
}
