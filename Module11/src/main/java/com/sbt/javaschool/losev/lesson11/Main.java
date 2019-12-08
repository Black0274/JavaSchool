package com.sbt.javaschool.losev.lesson11;

import java.util.Map;

public class Main {

    private final static String SHORT_TEXT_PATH = "./Module11/src/main/resources/shortText.txt";
    private final static String THE_WHITE_GUARD_PATH = "./Module11/src/main/resources/The White Guard.txt";

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Dictionary.get(THE_WHITE_GUARD_PATH);
        //map.forEach((k, v) -> System.out.println(k + ": " + v));
        long end = System.currentTimeMillis();
        System.out.println("One-thread implementation: " + (end- start));
        start = System.currentTimeMillis();
        Dictionary.get(THE_WHITE_GUARD_PATH);
        end = System.currentTimeMillis();
        System.out.println("One-thread implementation: " + (end- start));

    }
}
