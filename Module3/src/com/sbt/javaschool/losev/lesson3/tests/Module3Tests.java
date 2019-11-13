package com.sbt.javaschool.losev.lesson3.tests;

import com.sbt.javaschool.losev.lesson3.java.Main;
import com.sbt.javaschool.losev.lesson3.java.MyArrayList;
import org.junit.Test;

import java.util.*;

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

    @Test
    public void Task2_10words(){
        Set<String> words = Main.ReadFileToSet(TEST_10_WORDS_PATH);
        List<String> list = new ArrayList<>(Main.SortWords(words));
        assertEquals(list.get(0), "из");
        assertEquals(list.get(1), "для");
        assertEquals(list.get(list.size() - 2), "домашнего");
        assertEquals(list.get(list.size() - 1), "тестирования");
    }

    @Test
    public void Task2_30words(){
        Set<String> words = Main.ReadFileToSet(TEST_30_WORDS_PATH);
        List<String> list = new ArrayList<>(Main.SortWords(words));
        assertEquals(list.get(0), "Выражения");
        assertEquals(list.get(list.size() - 2), "Околодесяти");
        assertEquals(list.get(list.size() - 1), "Сдлинойстроки");
    }

    @Test
    public void Task2_SameWords(){
        Set<String> words = Main.ReadFileToSet(TEST_SAME_WORDS_PATH);
        List<String> list = new ArrayList<>(Main.SortWords(words));
        assertEquals(list.get(0), "абитуриент");
    }

    @Test
    public void Tesk3_10words(){
        Map<String, Integer> map = Main.ReadFileToMap(TEST_10_WORDS_PATH);
        for (Integer value : map.values()) {
            assertEquals(value.intValue(), 1);
        }
    }

    @Test
    public void Tesk3_30words(){
        Map<String, Integer> map = Main.ReadFileToMap(TEST_30_WORDS_PATH);
        for (Integer value : map.values()) {
            assertEquals(value.intValue(), 1);
        }
    }

    @Test
    public void Tesk3_SameWords(){
        Map<String, Integer> map = Main.ReadFileToMap(TEST_SAME_WORDS_PATH);
        assertEquals(map.get("цементовоз").intValue(), 3);
        assertEquals(map.get("барабулька").intValue(), 2);
        assertEquals(map.get("жестокость").intValue(), 2);
        assertEquals(map.get("абитуриент").intValue(), 1);
        assertEquals(map.get("газобаллон").intValue(), 1);
        assertEquals(map.get("зоомагазин").intValue(), 1);
    }

    @Test
    public void Task4_10words(){
        List<String> words = Main.ReverseWordsFromFile(TEST_10_WORDS_PATH);
        assertEquals(words.get(0), "задания");
        assertEquals(words.get(1), "домашнего");
        assertEquals(words.get(words.size() - 2), "файл");
        assertEquals(words.get(words.size() - 1), "Тестовый");
    }

    @Test
    public void Task4_30words(){
        List<String> words = Main.ReverseWordsFromFile(TEST_30_WORDS_PATH);
        assertEquals(words.get(0), "Брандспойт");
        assertEquals(words.get(1), "Сверхновая");
        assertEquals(words.get(words.size() - 2), "Содержатся");
        assertEquals(words.get(words.size() - 1), "Вэтомфайле");
    }

    @Test
    public void Task4_SameWords(){
        List<String> words = Main.ReverseWordsFromFile(TEST_SAME_WORDS_PATH);
        assertEquals(words.get(0), "барабулька");
        assertEquals(words.get(1), "жестокость");
        assertEquals(words.get(words.size() - 2), "цементовоз");
        assertEquals(words.get(words.size() - 1), "абитуриент");
    }

    @Test
    public void Task5_10words(){
        List<String> arrayList = Main.ReadFileToList(TEST_10_WORDS_PATH);
        List<String> myArrayList = new MyArrayList<>();
        myArrayList.addAll(arrayList);
        Collections.reverse(myArrayList);
        Iterator<String> myArrayListIter = myArrayList.iterator();
        Iterator<String> arrayListIter = arrayList.iterator();
        while (myArrayListIter.hasNext() && arrayListIter.hasNext()){
            assertEquals(myArrayListIter.next(), arrayListIter.next());
        }
    }

    @Test
    public void Task5_30words(){
        List<String> arrayList = Main.ReadFileToList(TEST_30_WORDS_PATH);
        List<String> myArrayList = new MyArrayList<>();
        myArrayList.addAll(arrayList);
        Collections.reverse(myArrayList);
        Iterator<String> myArrayListIter = myArrayList.iterator();
        Iterator<String> arrayListIter = arrayList.iterator();
        while (myArrayListIter.hasNext() && arrayListIter.hasNext()){
            assertEquals(myArrayListIter.next(), arrayListIter.next());
        }
    }

    @Test
    public void Task5_SameWords(){
        List<String> arrayList = Main.ReadFileToList(TEST_SAME_WORDS_PATH);
        List<String> myArrayList = new MyArrayList<>();
        myArrayList.addAll(arrayList);
        Collections.reverse(myArrayList);
        Iterator<String> myArrayListIter = myArrayList.iterator();
        Iterator<String> arrayListIter = arrayList.iterator();
        while (myArrayListIter.hasNext() && arrayListIter.hasNext()){
            assertEquals(myArrayListIter.next(), arrayListIter.next());
        }
    }

    @Test
    public void Task6_30words(){
        List<String> words = Main.ReadFileToList(TEST_30_WORDS_PATH);
        assertEquals(words.get(0), "Вэтомфайле");
        assertEquals(words.get(12), "Скворечник");
        assertEquals(words.get(18), "Чебуречная");
        assertEquals(words.get(29), "Брандспойт");
    }
}