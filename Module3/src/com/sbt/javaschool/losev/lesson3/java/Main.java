package com.sbt.javaschool.losev.lesson3.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
     * Sorts words by order of length
     * @param words — set of words
     * @return sorted set of words
     */
    public static Set<String> SortWords(Set<String> words){
        Set<String> sortedWords = new TreeSet<>(Comparator.comparingInt(String::length).thenComparing(String::compareTo));
        sortedWords.addAll(words);
        return sortedWords;
    }

    /**
     * Reads from a file at path
     * @param path of text file
     * @return set of unique words from file
     */
    public static Map<String, Integer> ReadFileToMap(String path){
        Map<String, Integer> map = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null){
                for (String word : line.split(" ")){
                    if (!map.containsKey(word)){
                        map.put(word, 0);
                    }
                    map.put(word, map.get(word) + 1);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * Reads from a file at path
     * @param path of text file
     * @return list of unique words from file
     */
    public static List<String> ReadFileToList(String path){
        List<String> words = new ArrayList<>();
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

    public static List<String> ReverseWordsFromFile(String path){
        List<String> words = ReadFileToList(path);
        Collections.reverse(words);
        return words;
    }

    public static void main(String[] args) {
        Set<String> wordsSet = ReadFileToSet(WORDS_PATH);
        System.out.println("Task 1:\nКоличество различных слов: " + wordsSet.size() + '\n');

        System.out.println("Task 2:");
        Set<String> sortedWords = SortWords(wordsSet);
        sortedWords.forEach(word -> System.out.print(word + ' '));

        System.out.println('\n');
        System.out.println("Task 3:");
        Map<String, Integer> map = ReadFileToMap(WORDS_PATH);
        map.forEach((k, v) -> System.out.println(k + ": " + v));

        System.out.println();
        System.out.println("Task 4:");
        List<String> reversedWordList = ReverseWordsFromFile(WORDS_PATH);
        reversedWordList.forEach(word -> System.out.print(word + ' '));

        System.out.println('\n');
        System.out.println("Task 5:");
        List<String> myArrayList = new MyArrayList<>();
        myArrayList.addAll(reversedWordList);
        System.out.println("ArrayList:");
        for (String word : reversedWordList) {
            System.out.print(word + ' ');
        }
        System.out.println("\nMyArrayList:");
        for (String word : myArrayList) {
            System.out.print(word + ' ');
        }

        System.out.println('\n');
        System.out.println("Task 6:");
        List<String> wordList = ReadFileToList(WORDS_PATH);
        while (true) {
            System.out.print("\nType number of word: ");
            Scanner sc = new Scanner(System.in);
            String choose = sc.next();
            if (choose.equals("c") || choose.equals("C")) {
                break;
            }
            System.out.println(wordList.get(Integer.valueOf(choose) - 1));
        }
    }
}
