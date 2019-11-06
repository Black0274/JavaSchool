package com.sbt.javaschool.losev.lesson2;

public class Main {
    public static void main(String[] args) {
        Person dima = new Person(true, "Dmitry", 21);
        Person anna = new Person(false, "Anna", 20);
        Person olga = new Person(false, "Olga", 19);
        dima.marry(anna);
        anna.increaseAge();
        dima.marry(olga);
        olga.marry(anna);
        System.out.println(olga.getSpouseName());
    }
}
