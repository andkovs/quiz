package com.univ.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.univ.model.Subject;

import java.io.*;

/**
 * Created by oleg on 3/14/15.
 */
public class SubjectDao {

    public Subject[] loadSubjects(){

        // todo: asap load real data
        // dummy data
//        Subject[] subjects = new Subject[3];
//        subjects[0] = new Subject();
//        subjects[0].setId(1);
//        subjects[0].setName("Chemical");
//
//        subjects[1] = new Subject();
//        subjects[1].setId(2);
//        subjects[1].setName("Physics");
//
//        subjects[2] = new Subject();
//        subjects[2].setId(2);
//        subjects[2].setName("Culture");
//
//        Subject[] subjectsChild = new Subject[2];
//        subjectsChild[0] = new Subject();
//        subjectsChild[0].setId(1);
//        subjectsChild[0].setName("History");
//
//
//        subjectsChild[1] = new Subject();
//        subjectsChild[1].setId(2);
//        subjectsChild[1].setName("English");
//        subjects[2].setChildSubject(subjectsChild);
//
//
//
//        return subjects;


            Gson gson = new GsonBuilder().create();
            Subject[] subjects = gson.fromJson(readJson("subjects.js").toString(), Subject[].class);


        return subjects;

    }

    private StringBuffer readJson(String dir) {
        BufferedReader in;
        String s;
        StringBuffer s2=null;
        try {
            // read from Json
            InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("/database/"+dir);
            in = new BufferedReader(new InputStreamReader(resourceAsStream, "UTF8"));
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


