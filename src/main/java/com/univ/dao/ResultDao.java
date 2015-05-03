package com.univ.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.univ.model.*;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Andrey on 05.04.2015.
 */
public class ResultDao {

    public TestResponse resultHandler (Result result){

        CorrectAnswerBySubject[] allCorrectAnswers;
        Subject[] allSubjects;
        Question[] allQuestions;
        Gson gson = new GsonBuilder().create();
        allCorrectAnswers = gson.fromJson(readJson("correctAnswers.js").toString(), CorrectAnswerBySubject[].class);
        allSubjects = gson.fromJson(readJson("subjects.js").toString(), Subject[].class);
        allQuestions = gson.fromJson(readJson("questions.js").toString(), Question[].class);

        //create list with needful "questionListId"
        ArrayList<Long> listOfquestionListId = new ArrayList<Long>();

        for(Subject sub: allSubjects){
            for(ChildSubject chSub: sub.getChildSubject()){
                if(chSub.getChildSubjectId().equals(result.getSubjectId())){
                    for(QuestionsFrom questFr: chSub.getQuestionsFrom()){
                        listOfquestionListId.add(questFr.getQuestionListId());
                    }
                }
            }
        }

        //create array with user correct answers
        CorrectAnswerBySubject[] userCorrectAnswers = new CorrectAnswerBySubject[listOfquestionListId.size()];

        for (int i = 0; i < listOfquestionListId.size(); i++) {
            for (int j = 0; j < allCorrectAnswers.length; j++) {
                if(listOfquestionListId.get(i).equals(allCorrectAnswers[j].getSubjectId())){
                    userCorrectAnswers[i] =  allCorrectAnswers[j];
                }
            }
        }

        //hadling answers
        int countCorrectAnswers = 0;
        ArrayList<IncorrectAnswers> questionsWithUncorrectAnswersList = new ArrayList<IncorrectAnswers>();

        // compare user answers with correct answers
        for (int i = 0; i < result.getAnswers().length; i++) {
            int j = 0;
            Long currentResultQuestionListId = result.getAnswers()[i].getSubjectId();
            Long currentCorrectAnswersQuestionListId = userCorrectAnswers[j].getSubjectId();

            while (!(currentResultQuestionListId.equals(currentCorrectAnswersQuestionListId))){
                j++;
                currentCorrectAnswersQuestionListId =  userCorrectAnswers[j].getSubjectId();
            }

            int k = 0;
            Long resultQuestId = result.getAnswers()[i].getQuestionId();
            Long correctQuestId = userCorrectAnswers[j].getCorrectAnswers()[k].getQuestionId();

            while (!(resultQuestId.equals(correctQuestId))){
                k++;
                correctQuestId = userCorrectAnswers[j].getCorrectAnswers()[k].getQuestionId();
            }

            Long resultAnswId = result.getAnswers()[i].getAnswerId();
            Long correctAnswId = userCorrectAnswers[j].getCorrectAnswers()[k].getCorrectAnswerId();

            if (resultAnswId.equals(correctAnswId)) {
                countCorrectAnswers++;
            } else {
                questionsWithUncorrectAnswersList.add(getQuestionsWithIncorrectAnswersList(allQuestions, result.getAnswers()[i].getSubjectId(), result.getAnswers()[i].getQuestionId(), result.getAnswers()[i].getAnswerId()));
                System.out.println("");
            }
        }

        IncorrectAnswers[] questionsWithUncorrectAnswers = questionsWithUncorrectAnswersList.toArray(new IncorrectAnswers[questionsWithUncorrectAnswersList.size()]);

        TestResponse response = new TestResponse();
        response.setName(result.getName());
        response.setSurname(result.getSurname());
        response.setCorrectAnswers(countCorrectAnswers);
        response.setQuestionsWithUncorrectAnswers(questionsWithUncorrectAnswers);

        return response;
    }

    private StringBuffer readJson(String dir) {
        BufferedReader in;
        String s;
        StringBuffer s2=null;
        try {
            // read from Json
            in = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\TestDataBase\\"+dir), "UTF8"));
            //InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("/database/"+dir);
            //in = new BufferedReader(new InputStreamReader(resourceAsStream, "UTF8"));
            s2 = new StringBuffer();
            while ((s = in.readLine()) != null) {
                s2.append(s + "\n");
            }
            in.close();
            return s2;
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return s2;
    }

    private IncorrectAnswers getQuestionsWithIncorrectAnswersList(Question[] allQuestions, Long questListId, Long questId, Long answerId){
        Question needQuestion = null;
        for (int i = 0; i < allQuestions.length; i++) {
            if (questListId.equals(allQuestions[i].getSubjectId()) && questId.equals(allQuestions[i].getId())){
                needQuestion = allQuestions[i];
                System.out.println("");
            }
        }
        String answer = null;
        if(answerId==0){
            answer = "Нет ответа";
        }
        else {
            for (int i = 0; i < needQuestion.getAnswer().length; i++) {
                if (needQuestion.getAnswer()[i].getAnswerId().equals(answerId)) {
                    answer = needQuestion.getAnswer()[i].getAnswer();
                }
            }
        }
//        String s = needQuestion.getQuestion()+"\n"+answer;
        IncorrectAnswers incorrectAnswers = new IncorrectAnswers();
        incorrectAnswers.setAnswer(answer);
        incorrectAnswers.setNeedQuestion(needQuestion.getQuestion());
        return incorrectAnswers;
    }

}


