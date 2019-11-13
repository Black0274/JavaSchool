package com.sbt.javaschool.losev.lesson4.java;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.*;

public class CollectionUtilsTest {

    @Test
    public void addAll() {
        List<Number> list1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        List<Integer> list2 = new ArrayList<>(Arrays.asList(4, 5, 6, 7));
        CollectionUtils.addAll(list2, list1);
        assertEquals(list1, new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 4, 5, 6, 7)));
    }

    @Test
    public void indexOf() {
        List<Number> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        assertEquals(CollectionUtils.indexOf(list, 3), 2);
        Integer i = 5;
        assertEquals(CollectionUtils.indexOf(list, i), 4);
        Number n = 3.14;
        assertEquals(CollectionUtils.indexOf(list, n), -1);
    }

    @Test
    public void limit() {
        List<Integer> listInt = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        List<Double> listDouble = new ArrayList<>(Arrays.asList(1.009, 1.5, 2.4, 3.14));
        List<Number> listNum = CollectionUtils.limit(listInt, 2);
        assertEquals(listNum, new ArrayList<>(Arrays.asList(1, 2)));
        listNum = CollectionUtils.limit(listDouble, 3);
        assertEquals(listNum, new ArrayList<>(Arrays.asList(1.009, 1.5, 2.4)));
    }

    @Test
    public void add() {
        List<Number> listNum = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        CollectionUtils.add(listNum, 3.14);
        CollectionUtils.add(listNum, 15);
        assertEquals(listNum, new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 3.14, 15)));
    }

    @Test
    public void removeAll() {
        List<Number> listNum = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        List<Integer> listInt = new ArrayList<>(Arrays.asList(1, 2, 4));
        CollectionUtils.removeAll(listNum, listInt);
        assertEquals(listNum, new ArrayList<>(Arrays.asList(3, 5)));
    }

    @Test
    public void containsAll() {
        List<Number> listNum = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        List<Integer> listInt1 = new ArrayList<>(Arrays.asList(1, 2, 4));
        List<Integer> listInt2 = new ArrayList<>(Arrays.asList(2, 2, 4));
        List<Integer> listInt3 = new ArrayList<>(Arrays.asList(7, 2, 4));
        assertTrue(CollectionUtils.containsAll(listNum, listInt1));
        assertTrue(CollectionUtils.containsAll(listNum, listInt2));
        assertFalse(CollectionUtils.containsAll(listNum, listInt3));
    }

    @Test
    public void containsAny() {
        List<Number> listNum = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        List<Integer> listInt1 = new ArrayList<>(Arrays.asList(1, 2, 4));
        List<Integer> listInt2 = new ArrayList<>(Arrays.asList(7, 2, 13));
        List<Integer> listInt3 = new ArrayList<>(Arrays.asList(7, 8, 10));
        assertTrue(CollectionUtils.containsAny(listNum, listInt1));
        assertTrue(CollectionUtils.containsAny(listNum, listInt2));
        assertFalse(CollectionUtils.containsAny(listNum, listInt3));
    }

    @Test
    public void range() {
        List<Integer> listInt = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        List<Integer> rangeResult = CollectionUtils.range(listInt, 2, 4);
        assertEquals(rangeResult, new ArrayList<>(Arrays.asList(2, 3, 4)));
    }

    @Test
    public void range1() {
        List<String> listString = new ArrayList<>(Arrays.asList("Aaaaa", "bd", "BCKL", "1", "se"));
        List<String> rangeResult = CollectionUtils.range(listString, "bb", "zzzz",
                Comparator.comparing(String::length));
        assertEquals(rangeResult, new ArrayList<>(Arrays.asList("bd", "BCKL", "se")));
    }
}