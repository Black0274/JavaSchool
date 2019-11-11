package com.sbt.javaschool.losev.lesson3.tests;

import com.sbt.javaschool.losev.lesson3.java.Main;
import org.junit.Test;

import java.io.File;
import java.util.Set;

import static org.junit.Assert.*;

public class Module3Tests {

    private final static String TEST_10_WORDS_PATH = "./testres/test10words.txt";
    private final static String TEST_30_WORDS_PATH = "./testres/test30words.txt";
    private final static String TEST_SAME_WORDS_PATH = "./testres/testSameWords.txt";

    @Test
    public void Task1_10words(){
        Set<String> words = Main.ReadFileToSet(TEST_10_WORDS_PATH);
        assertEquals(words.size(), 10);
    }

    @Test
    public void Task1_30words(){
        Set<String> words = Main.ReadFileToSet(TEST_30_WORDS_PATH);
        assertEquals(words.size(), 30);
    }

    @Test
    public void Task1_SameWords(){
        Set<String> words = Main.ReadFileToSet(TEST_SAME_WORDS_PATH);
        assertEquals(words.size(), 6);
    }
}