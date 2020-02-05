package com.sbt.javaschool.losev.lesson21.dao;

import com.sbt.javaschool.losev.lesson21.entity.StudentVisit;
import com.sbt.javaschool.losev.lesson21.utils.Util;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class StudentVisitDAOTest {

    private StudentVisit getTestStudentVisit(){
        StudentVisit studentVisit = new StudentVisit();
        studentVisit.setStudent_id(2);
        studentVisit.setLecture_id(2);
        return studentVisit;
    }

    @Test
    public void add() {
        StudentVisit studentVisited = getTestStudentVisit();
        StudentVisitDAO dao = new StudentVisitDAO();
        int oldSize = dao.getAll().size();
        dao.add(studentVisited);
        int newSize = dao.getAll().size();
        boolean visited = dao.visited(studentVisited.getStudent_id(),
                studentVisited.getLecture_id());

        assertEquals(oldSize + 1, newSize);
        assertTrue(visited);
    }

    @Test
    public void getAll() {
        String query = "SELECT * FROM STUDENTS_VISIT";
        int count = 0;
        try (Connection connection = Util.getConnection()) {
            ResultSet resultSet = connection.createStatement().executeQuery(query);
            while (resultSet.next())
                count++;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<StudentVisit> list = new StudentVisitDAO().getAll();
        assertEquals(count, list.size());
    }
}