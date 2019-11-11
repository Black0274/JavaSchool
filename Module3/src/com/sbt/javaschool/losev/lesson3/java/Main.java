package com.sbt.javaschool.losev.lesson3.java;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystemNotFoundException;
import java.util.*;

public class Main {

    private final static String TEST_WORDS_PATH = "./Module3/res/words.txt";

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

    public static Set<String> SortWords(Set<String> words){
        Set<String> sortedWords = new TreeSet<>(Comparator.comparingInt(String::length).thenComparing(String::compareTo));
        sortedWords.addAll(words);
        return sortedWords;
    }

    public static void main(String[] args) {

        Set<String> words = ReadFileToSet(TEST_WORDS_PATH);
        System.out.println("Task 1:\nКоличество различных слов: " + words.size() + '\n');

        System.out.println("Task 2:");
        Set<String> sortedWords = SortWords(words);
        sortedWords.forEach(word -> System.out.print(word + ' '));

    }
}
