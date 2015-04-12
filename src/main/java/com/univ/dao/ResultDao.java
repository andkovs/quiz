package com.univ.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.univ.model.CorrectAnswerBySubject;

import com.univ.model.Result;
import com.univ.model.TestResponse;

import java.io.*;

/**
 * Created by Andrey on 05.04.2015.
 */
public class ResultDao {

    public TestResponse resultHandler (Result result){

        BufferedReader inCorrectAnswer;
        String s;
        StringBuffer s2;
        CorrectAnswerBySubject[] allCorrectAnswers = new CorrectAnswerBySubject[0];

        try {
            // read from Json
            InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("/database/correctAnswers.js");
            inCorrectAnswer = new BufferedReader(new InputStreamReader(resourceAsStream, "UTF8"));
            s2 = new StringBuffer();
            while ((s = inCorrectAnswer.readLine()) != null) {
                s2.append(s + "\n") ;
            }
            System.out.println(s2);
            inCorrectAnswer.close();
            //write from Json into object

            Gson gson = new GsonBuilder().create();
            allCorrectAnswers = gson.fromJson(s2.toString(), CorrectAnswerBySubject[].class);


        } catch(FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex){
            System.out.println(ex);
        }

        //create array with user correct answers
        CorrectAnswerBySubject userCorrectAnswers = new CorrectAnswerBySubject();

        for(CorrectAnswerBySubject val: allCorrectAnswers){
            if(val.getSubjectId() == result.getSubjectId()){
                userCorrectAnswers = val;
            }
        }

        //hadling answers
        int countCorrectAnswers = 0;
        for (int i = 0; i < result.getAnswers().length; i++) {
            int j = 0;
            while(result.getAnswers()[i].getQuestionId() !=  userCorrectAnswers.getCorrectAnswers()[j].getQuestionId()){
                j++;
            }
            if (result.getAnswers()[i].getAnswerId() == userCorrectAnswers.getCorrectAnswers()[j].getCorrectAnswerId()){
                countCorrectAnswers++;
            }
        }

        TestResponse response = new TestResponse();
        response.setName(result.getName());
        response.setSurname(result.getSurname());
        response.setCorrectAnswers(countCorrectAnswers);
        response.setAllAnswers(result.getAnswers().length);

        return response;
    }
}


