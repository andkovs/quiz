package com.univ.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.univ.model.Answer;
import com.univ.model.ChildSubject;
import com.univ.model.Question;
import com.univ.model.Subject;
import com.univ.model.QuestionsFrom;


import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Created by oleg on 3/14/15.
 */
public class QuestionDao {

    public static final int MAX_QUESTIONS_SIZE = 10;

    public Question[] loadQuestionBySubjectId(Long subjectId) {

        Gson gson = new GsonBuilder().create();
        Question[] allQuestions = gson.fromJson(readJson("questions.js").toString(), Question[].class);
        Subject[] allSubjects = gson.fromJson(readJson("subjects.js").toString(), Subject[].class);

        HashMap<Long, ArrayList<Question>>  questionsBySubIdMap = createQuestionMapBySubjectId(allQuestions);
        ChildSubject currentChildSubject = getMainTest(subjectId, allSubjects);

        ArrayList[] questionsListArray = new ArrayList[currentChildSubject.getQuestionsFrom().length];

        ArrayList<Question> resultQuestions = new ArrayList<Question>();
        Random random = new Random();
        for (int i = 0; i < questionsListArray.length; i++){
            QuestionsFrom questionsFrom = currentChildSubject.getQuestionsFrom()[i];
            Integer questionSize = questionsFrom.getAmount();
            Long currentSubjectId = questionsFrom.getQuestionListId();
            List<Question> currentQuestionList = questionsBySubIdMap.get(currentSubjectId);
            if(currentQuestionList != null){
                int currentQuestionsListSize = currentQuestionList.size();
                if(questionSize <= currentQuestionsListSize){
                    for (int j = 0; j < questionSize; j++) {
                        int question = random.nextInt(currentQuestionsListSize - 1);
                        resultQuestions.add(currentQuestionList.get(question));
                        currentQuestionList.remove(question);
                    }
                } else {
                    //todo: handle this
                }
            }
        }

        return resultQuestions.toArray(new Question[resultQuestions.size()]);

    }

    private HashMap<Long, ArrayList<Question>> createQuestionMapBySubjectId(Question[] allQuestions) {
        HashMap<Long, ArrayList<Question>> hashMap = new HashMap<Long, ArrayList<Question>>();
        for (Question question : allQuestions) {
            if(!hashMap.containsKey(question.getSubjectId())){
                hashMap.put(question.getSubjectId(), new ArrayList<Question>());
            }
            hashMap.get(question.getSubjectId()).add(question);
        }
        return hashMap;
    }

    private ChildSubject getMainTest(Long subjectId, Subject[] allSubjects) {
        ChildSubject currentChildSubject = null;
        for(Subject sub : allSubjects){
            for(ChildSubject chSub : sub.getChildSubject()) {
                if (chSub.getChildSubjectId().equals(subjectId)){
                    currentChildSubject = chSub;
                }
            }
        }
        return currentChildSubject;
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


}
