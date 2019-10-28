package com.sbt.javaschool.losev.lesson2;

public class Main {
    public static void main(String[] args) {
        Person Dima = new Person(true, "Dmitry", 21);
        Person Anna = new Person(false, "Anna", 20);
        Person Olga = new Person(false, "Olga", 19);
        Dima.marry(Anna);
        Anna.increaseAge();
        Dima.marry(Olga);
        Olga.marry(Anna);
        System.out.println(Olga.getSpouseName());
    }
}
