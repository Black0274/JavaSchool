package com.sbt.javaschool.losev.lesson21.entity;

import java.util.Objects;

public class Students {
    private int id;
    private String first_name;
    private String surname;
    private int year;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Students() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Students students = (Students) o;
        return id == students.id &&
                year == students.year &&
                first_name.equals(students.first_name) &&
                surname.equals(students.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, first_name, surname, year);
    }

    @Override
    public String toString() {
        return "Students{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", surname='" + surname + '\'' +
                ", year=" + year +
                '}';
    }
}
