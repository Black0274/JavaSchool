package com.sbt.javaschool.losev.lesson4.java;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Task 3:");
        CountMap<Integer> map = new MyCountMap<>();

        map.add(10);
        map.add(10);
        map.add(5);
        map.add(6);
        map.add(5);
        map.add(10);
        System.out.println("5: " + map.getCount(5));
        System.out.println("6: " + map.getCount(6));
        System.out.println("10: " + map.getCount(10));
        System.out.println("123: " + map.getCount(123));

        System.out.println();
        System.out.println("Remove 5: " + map.remove(5));
        map.print();

        CountMap<Integer> anotherMap = new MyCountMap<>();
        anotherMap.add(17);
        anotherMap.add(55);
        anotherMap.add(17);
        anotherMap.addAll(map);
        System.out.println();
        System.out.println("anotherMap:");
        anotherMap.print();

        System.out.println();
        System.out.println("to HashMap:");
        Map<Integer, Integer> mp = new HashMap<>();
        mp.put(1, 3);
        anotherMap.toMap(mp);
        mp.forEach((k, v) -> System.out.println(k + ": " + v));
    }
}
