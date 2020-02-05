package com.sbt.javaschool.losev.lesson21.entity;

import java.util.Objects;

public class StudentVisit {
    private int student_id;
    private int lecture_id;

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getLecture_id() {
        return lecture_id;
    }

    public void setLecture_id(int lecture_id) {
        this.lecture_id = lecture_id;
    }

    public StudentVisit() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentVisit that = (StudentVisit) o;
        return student_id == that.student_id &&
                lecture_id == that.lecture_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(student_id, lecture_id);
    }

    @Override
    public String toString() {
        return "StudentVisit{" +
                "student_id=" + student_id +
                ", lecture_id=" + lecture_id +
                '}';
    }
}
