package com.sbt.javaschool.losev.lesson11;

import java.util.Map;

public class Main {

    private final static String SHORT_TEXT_PATH = "./Module11/src/main/resources/shortText.txt";
    private final static String ANNA_KARENINA_PATH = "./Module11/src/main/resources/Anna Karenina.txt";
    private final static String DANTE_PATH = "./Module11/src/main/resources/Dante.txt";
    private final static String DEATH_ZONE_PATH = "./Module11/src/main/resources/Death Zone.txt";
    private final static String EVGENY_ONEGIN_PATH = "./Module11/src/main/resources/Evgeny Onegin.txt";
    private final static String THE_HISTORY_OF_A_TOWN_PATH = "./Module11/src/main/resources/The History of a Town.txt";
    private final static String THE_WHITE_GUARD_PATH = "./Module11/src/main/resources/The White Guard.txt";

    public static void main(String[] args) {
        String[] books = {
                SHORT_TEXT_PATH,
                ANNA_KARENINA_PATH,
                DANTE_PATH,
                DEATH_ZONE_PATH,
                EVGENY_ONEGIN_PATH,
                THE_HISTORY_OF_A_TOWN_PATH,
                THE_WHITE_GUARD_PATH
        };

        long start = System.currentTimeMillis();
        for (String book: books) {
            Dictionary.get(book);
        }
        long end = System.currentTimeMillis();
        System.out.println("One-thread implementation: " + (end- start));

        start = System.currentTimeMillis();
        for (String book: books) {
            new Thread(new MultDictionary(book)).start();
        }
        end = System.currentTimeMillis();
        System.out.println("MultiThread implementation: " + (end- start));

    }
}
