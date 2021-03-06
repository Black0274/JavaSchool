package com.sbt.javaschool.losev.lesson21.dao;

import com.sbt.javaschool.losev.lesson21.utils.Util;
import com.sbt.javaschool.losev.lesson21.entity.Students;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentsDAO implements DAO<Students> {

    @Override
    public void add(Students students) {
        String query = "INSERT INTO STUDENTS VALUES(?, ?, ?, ?)";

        try (Connection connection = Util.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, students.getId());
            preparedStatement.setString(2, students.getFirst_name());
            preparedStatement.setString(3, students.getSurname());
            preparedStatement.setInt(4, students.getYear());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Students> getAll() {
        List<Students> studentsList = new ArrayList<>();
        String query = "SELECT * FROM STUDENTS";

        try (Connection connection = Util.getConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                Students students = new Students();
                students.setId(resultSet.getInt(1));
                students.setFirst_name(resultSet.getString(2));
                students.setSurname(resultSet.getString(3));
                students.setYear(resultSet.getInt(4));
                studentsList.add(students);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentsList;
    }

    @Override
    public Students getById(int id) {
        String query = "SELECT ID, FIRST_NAME, SURNAME, YEAR FROM STUDENTS WHERE ID=?";
        Students students = new Students();
        try (Connection connection = Util.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                students.setId(resultSet.getInt("ID"));
                students.setFirst_name(resultSet.getString("FIRST_NAME"));
                students.setSurname(resultSet.getString("SURNAME"));
                students.setYear(resultSet.getInt("YEAR"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    @Override
    public void update(Students students) {
        String query = "UPDATE STUDENTS SET FIRST_NAME=?, SURNAME=?, YEAR=? WHERE ID=?";
        try (Connection connection = Util.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, students.getFirst_name());
            preparedStatement.setString(2, students.getSurname());
            preparedStatement.setInt(3, students.getYear());
            preparedStatement.setInt(4, students.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Students students) {
        String query = "DELETE FROM STUDENTS WHERE ID=?";
        try (Connection connection = Util.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, students.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
