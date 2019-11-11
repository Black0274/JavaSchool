package com.sbt.javaschool.losev.lesson3.java;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystemNotFoundException;
import java.util.*;

public class Main {

    private final static String TEST_10_WORDS_PATH = "./Module3/res/words.txt";

    public static Set<String> ReadFileToSet(String path){
        Set<String> words = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null){
                words.addAll(Arrays.asList(line.split(" ")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return words;
    }

    public static void main(String[] args) {

        Set<String> words = ReadFileToSet(TEST_10_WORDS_PATH);

        System.out.println("Task 1:\nКоличество различных слов: " + words.size());

    }
}
