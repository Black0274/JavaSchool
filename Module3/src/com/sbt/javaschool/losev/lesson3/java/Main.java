package com.sbt.javaschool.losev.lesson3.java;

import jdk.jshell.spi.ExecutionControl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystemNotFoundException;
import java.util.*;

public class Main {

    private final static String WORDS_PATH = "./Module3/res/words.txt";

    /**
     * Reads from a file at path
     * @param path of text file
     * @return set of unique words from file
     */
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

    /**
     * Reads from a file at path
     * @param path of text file
     * @return set of unique words from file
     */
    public static Map<String, Integer> ReadFileToMap(String path){
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Sorts words by order of length
     * @param words — set of words
     * @return sorted set of words
     */
    public static Set<String> SortWords(Set<String> words){
        Set<String> sortedWords = new TreeSet<>(Comparator.comparingInt(String::length).thenComparing(String::compareTo));
        sortedWords.addAll(words);
        return sortedWords;
    }

    public static void main(String[] args) {

        Set<String> words = ReadFileToSet(WORDS_PATH);
        System.out.println("Task 1:\nКоличество различных слов: " + words.size() + '\n');

        System.out.println("Task 2:");
        Set<String> sortedWords = SortWords(words);
        sortedWords.forEach(word -> System.out.print(word + ' '));

    }
}
