package com.sbt.javaschool.losev.lesson21.dao;

import com.sbt.javaschool.losev.lesson21.entity.Students;

import java.util.List;

public interface DAO<T> {
    //create
    void add(T t);

    //read
    List<T> getAll();

    T getById(int id);

    //update
    void update(T t);

    //remove
    void remove(T t);
}

