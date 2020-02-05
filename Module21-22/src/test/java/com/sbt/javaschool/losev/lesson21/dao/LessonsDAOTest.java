package com.sbt.javaschool.losev.lesson21.dao;

import com.sbt.javaschool.losev.lesson21.entity.Lessons;
import com.sbt.javaschool.losev.lesson21.utils.Util;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class LessonsDAOTest {

    private Lessons getTestLesson(){
        Lessons lesson = new Lessons();
        lesson.setId(0);
        lesson.setTitle("TEST_LESSON");
        lesson.setLecture_date(new Date(1157822));
        return lesson;
    }

    @Test
    public void add() {
        Lessons lesson = getTestLesson();
        LessonsDAO dao = new LessonsDAO();
        int oldSize = dao.getAll().size();
        dao.add(lesson);
        int newSize = dao.getAll().size();
        Lessons newLesson = dao.getById(lesson.getId());

        assertEquals(oldSize + 1, newSize);
        assertEquals(lesson, newLesson);

        dao.remove(lesson);
    }

    @Test
    public void getAll() {
        String query = "SELECT * FROM LESSONS";
        int count = 0;
        try (Connection connection = Util.getConnection()) {
            ResultSet resultSet = connection.createStatement().executeQuery(query);
            while (resultSet.next())
                count++;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<Lessons> list = new LessonsDAO().getAll();
        assertEquals(count, list.size());
    }

    @Test
    public void getById() {
        Lessons lesson = getTestLesson();
        LessonsDAO dao = new LessonsDAO();
        dao.add(lesson);

        assertEquals(lesson, dao.getById(lesson.getId()));

        dao.remove(lesson);
    }

    @Test
    public void update() {
        Lessons lesson = getTestLesson();
        LessonsDAO dao = new LessonsDAO();
        dao.add(lesson);
        lesson.setTitle("NEW_TITLE");
        dao.update(lesson);
        String newTitle = dao.getById(lesson.getId()).getTitle();

        assertEquals("NEW_TITLE", newTitle);

        dao.remove(lesson);
    }

    @Test
    public void remove() {
        Lessons lesson = getTestLesson();
        LessonsDAO dao = new LessonsDAO();
        int oldSize = dao.getAll().size();
        dao.add(lesson);
        dao.remove(lesson);
        int newSize = dao.getAll().size();

        assertEquals(oldSize, newSize);
    }
}