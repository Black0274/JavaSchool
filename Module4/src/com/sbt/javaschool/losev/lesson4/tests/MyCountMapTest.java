package com.sbt.javaschool.losev.lesson4.tests;

import com.sbt.javaschool.losev.lesson4.java.CountMap;
import com.sbt.javaschool.losev.lesson4.java.MyCountMap;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class MyCountMapTest {

    private CountMap<String> testMap;

    private void initTestMap(){
        testMap = new MyCountMap<>();
        testMap.add("Креветка");
        testMap.add("Европа");
        testMap.add("Сатурн");
        testMap.add("Принтер");
        testMap.add("Дуб");
        testMap.add("Писатель");
        testMap.add("Сатурн");
        testMap.add("Крестьянка");
        testMap.add("Креветка");
        testMap.add("Дуб");
        testMap.add("Креветка");
        testMap.add("Полупроводник");
    }

    @Test
    public void getCount() {
        initTestMap();
        assertEquals(testMap.getCount("Креветка"), 3);
        assertEquals(testMap.getCount("Дуб"), 2);
        assertEquals(testMap.getCount("Сатурн"), 2);
        assertEquals(testMap.getCount("Принтер"), 1);
        assertEquals(testMap.getCount("Крестьянка"), 1);
    }

    @Test
    public void remove() {
        initTestMap();
        CountMap<String> map = new MyCountMap<>();
        map.addAll(testMap);
        assertEquals(testMap.getCount("Креветка"), map.remove("Креветка"));
        assertEquals(testMap.getCount("Дуб"), map.remove("Дуб"));
        assertEquals(testMap.getCount("Сатурн"), map.remove("Сатурн"));
        assertEquals(testMap.getCount("Писатель"), map.remove("Писатель"));
        assertEquals(testMap.getCount("Неизвестность"), map.remove("Неизвестность"));
    }

    @Test
    public void size() {
        initTestMap();
        CountMap<String> map = new MyCountMap<>();
        map.add("Стержень");
        map.add("Дуб");
        map.add("Креветка");
        map.add("Метро");
        assertEquals(map.size(), 4);
        map.addAll(testMap);
        assertEquals(map.size(), 10);
    }

    @Test
    public void addAll() {
        initTestMap();
        CountMap<String> map = new MyCountMap<>();
        map.add("Стержень");
        map.add("Дуб");
        map.add("Креветка");
        map.add("Метро");
        map.addAll(testMap);
        assertEquals(map.getCount("Стержень"), 1);
        assertEquals(map.getCount("Дуб"), 3);
        assertEquals(map.getCount("Креветка"), 4);
        assertEquals(map.getCount("Метро"), 1);
        assertEquals(map.getCount("Полупроводник"), 1);
    }

    @Test
    public void toMap() {
        initTestMap();
        Map<String, Integer> map = new HashMap<>();
        testMap.toMap(map);
        assertEquals(map.get("Дуб").intValue(), 2);
        assertEquals(map.get("Креветка").intValue(), 3);
        assertEquals(map.get("Полупроводник").intValue(), 1);
    }
}