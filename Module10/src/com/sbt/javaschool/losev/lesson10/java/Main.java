package com.sbt.javaschool.losev.lesson10.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {

        System.out.println("Basic List:");
        List<String> list = new ArrayList<>(Arrays.asList("2", "15", "0", "-3", "8", "5.7", "7", "1"));
        System.out.println(list);

        Streams<String> stream = Streams.of(Arrays.asList("2", "15", "0", "-3", "8", "5.7", "7", "1"));
        Map<Integer, Boolean> result = stream
                .filter(x -> x.length() < 2)
                .transform(Integer::valueOf)
                .filter(x -> x > 0)
                .toMap(x -> x, x -> x % 2 == 0);

        System.out.println("Result map:");
        System.out.println(result);
    }
}
