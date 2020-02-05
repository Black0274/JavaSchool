package com.sbt.javaschool.losev.lesson21.dao;

import com.sbt.javaschool.losev.lesson21.entity.Lessons;
import com.sbt.javaschool.losev.lesson21.utils.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LessonsDAO implements DAO<Lessons> {
    @Override
    public void add(Lessons lessons) {
        String query = "INSERT INTO LESSONS VALUES(?, ?, ?)";
        try (Connection connection = Util.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, lessons.getId());
            preparedStatement.setString(2, lessons.getTitle());
            preparedStatement.setDate(3, lessons.getLecture_date());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Lessons> getAll() {
        List<Lessons> lessonsList = new ArrayList<>();
        String query = "SELECT * FROM LESSONS";

        try (Connection connection = Util.getConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                Lessons lessons = new Lessons();
                lessons.setId(resultSet.getInt(1));
                lessons.setTitle(resultSet.getString(2));
                lessons.setLecture_date(resultSet.getDate(3));
                lessonsList.add(lessons);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lessonsList;
    }

    @Override
    public Lessons getById(int id) {
        String query = "SELECT ID, TITLE, LECTURE_DATE FROM LESSONS WHERE ID=?";
        Lessons lessons = new Lessons();
        try (Connection connection = Util.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                lessons.setId(resultSet.getInt("ID"));
                lessons.setTitle(resultSet.getString("TITLE"));
                lessons.setLecture_date(resultSet.getDate("LECTURE_DATE"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lessons;
    }

    @Override
    public void update(Lessons lessons) {
        String query = "UPDATE LESSONS SET TITLE=?, LECTURE_DATE=? WHERE ID=?";
        try (Connection connection = Util.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, lessons.getTitle());
            preparedStatement.setDate(2, lessons.getLecture_date());
            preparedStatement.setInt(3, lessons.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Lessons lessons) {
        String query = "DELETE FROM LESSONS WHERE ID=?";
        try (Connection connection = Util.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, lessons.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
