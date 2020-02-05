package com.sbt.javaschool.losev.lesson21;

import com.sbt.javaschool.losev.lesson21.dao.StudentVisitDAO;
import com.sbt.javaschool.losev.lesson21.entity.StudentVisit;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        StudentVisitDAO studentVisitDAO = new StudentVisitDAO();
        StudentVisit studentVisit = new StudentVisit();
        studentVisit.setStudent_id(0);
        studentVisit.setLecture_id(0);
        //studentVisitDAO.add(studentVisit);
        List<StudentVisit> list = studentVisitDAO.getAll();
        for (StudentVisit s : list){
            System.out.println(s);
        }
    }
}
