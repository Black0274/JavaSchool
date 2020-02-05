package com.sbt.javaschool.losev.lesson21;

import com.sbt.javaschool.losev.lesson21.dao.LessonsDAO;
import com.sbt.javaschool.losev.lesson21.entity.Lessons;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        LessonsDAO lessonsDAO = new LessonsDAO();
        Lessons lesson = new Lessons();
        lesson.setId(0);
        List<Lessons> list = lessonsDAO.getAll();
        for (Lessons s : list){
            System.out.println(s);
        }
    }
}
