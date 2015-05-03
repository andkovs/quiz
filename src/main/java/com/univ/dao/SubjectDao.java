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


