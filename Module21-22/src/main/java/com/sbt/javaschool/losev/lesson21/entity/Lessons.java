package com.sbt.javaschool.losev.lesson21.entity;

import java.sql.Date;
import java.util.Objects;

public class Lessons {
    private int id;
    private String title;
    private Date lecture_date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getLecture_date() {
        return lecture_date;
    }

    public void setLecture_date(Date lecture_date) {
        this.lecture_date = lecture_date;
    }

    public Lessons() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lessons lessons = (Lessons) o;
        return id == lessons.id &&
                title.equals(lessons.title) &&
                Objects.equals(lecture_date.toString(), lessons.lecture_date.toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, lecture_date.toString());
    }

    @Override
    public String toString() {
        return "Lessons{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", lecture_date=" + lecture_date +
                '}';
    }
}
