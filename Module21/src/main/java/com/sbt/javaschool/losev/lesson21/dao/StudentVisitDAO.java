package com.sbt.javaschool.losev.lesson21.dao;

import com.sbt.javaschool.losev.lesson21.entity.StudentVisit;
import com.sbt.javaschool.losev.lesson21.utils.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentVisitDAO {

    public void add(StudentVisit studentVisit) {
        String query = "INSERT INTO STUDENTS_VISIT VALUES(?, ?)";

        try (Connection connection = Util.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, studentVisit.getStudent_id());
            preparedStatement.setInt(2, studentVisit.getLecture_id());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<StudentVisit> getAll() {
        List<StudentVisit> studentVisitList = new ArrayList<>();
        String query = "SELECT * FROM STUDENTS_VISIT";

        try (Connection connection = Util.getConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                StudentVisit studentVisit = new StudentVisit();
                studentVisit.setStudent_id(resultSet.getInt(1));
                studentVisit.setLecture_id(resultSet.getInt(2));
                studentVisitList.add(studentVisit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentVisitList;
    }

    public boolean visited(int id_Student, int id_Lecture) {
        String query = "SELECT * FROM STUDENTS_VISIT WHERE STUDENT_ID=? AND LECTURE_ID=?";
        try (Connection connection = Util.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id_Student);
            preparedStatement.setInt(2, id_Lecture);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
