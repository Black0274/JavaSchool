package com.sbt.javaschool.losev.lesson21;

import com.sbt.javaschool.losev.lesson21.dao.StudentsDAO;
import com.sbt.javaschool.losev.lesson21.entity.Students;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        StudentsDAO studentsDAO = new StudentsDAO();

        Students students = new Students();
        students.setId(4);
        students.setFirst_name("Romanov");
        students.setSurname("Nikolas");
        students.setYear(1);

        studentsDAO.add(students);

        List<Students> list = studentsDAO.getAll();
        for (Students s : list){
            System.out.println(s);
        }
    }
}
