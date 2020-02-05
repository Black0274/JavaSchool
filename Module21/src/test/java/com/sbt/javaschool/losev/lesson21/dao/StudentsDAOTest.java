package com.sbt.javaschool.losev.lesson21.dao;

import com.sbt.javaschool.losev.lesson21.entity.Students;
import com.sbt.javaschool.losev.lesson21.utils.Util;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class StudentsDAOTest {

    private Students getTestStudent(){
        Students student = new Students();
        student.setId(0);
        student.setFirst_name("JOHN");
        student.setSurname("DOE");
        student.setYear(0);
        return student;
    }

    @Test
    public void add() {
        Students student = getTestStudent();
        StudentsDAO dao = new StudentsDAO();
        int oldSize = dao.getAll().size();
        dao.add(student);
        int newSize = dao.getAll().size();
        Students newStudent = dao.getById(student.getId());

        assertEquals(oldSize + 1, newSize);
        assertEquals(student, newStudent);

        dao.remove(student);
    }

    @Test
    public void getAll() {
        String query = "SELECT * FROM STUDENTS";
        int count = 0;
        try (Connection connection = Util.getConnection()) {
            ResultSet resultSet = connection.createStatement().executeQuery(query);
            while (resultSet.next())
                count++;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<Students> list = new StudentsDAO().getAll();
        assertEquals(count, list.size());
    }

    @Test
    public void getById() {
        Students student = getTestStudent();
        StudentsDAO dao = new StudentsDAO();
        dao.add(student);

        assertEquals(student, dao.getById(student.getId()));

        dao.remove(student);
    }

    @Test
    public void update() {
        Students student = getTestStudent();
        StudentsDAO dao = new StudentsDAO();
        dao.add(student);
        student.setYear(5);
        dao.update(student);
        int newYear = dao.getById(student.getId()).getYear();

        assertEquals(newYear, 5);

        dao.remove(student);
    }

    @Test
    public void remove() {
        Students student = getTestStudent();
        StudentsDAO dao = new StudentsDAO();
        int oldSize = dao.getAll().size();
        dao.add(student);
        dao.remove(student);
        int newSize = dao.getAll().size();

        assertEquals(oldSize, newSize);
    }
}