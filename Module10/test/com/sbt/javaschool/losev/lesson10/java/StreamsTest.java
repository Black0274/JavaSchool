package com.sbt.javaschool.losev.lesson10.java;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class StreamsTest {

    @Test
    @SuppressWarnings("unchecked")
    public void filter() {
        Streams<Integer> stream = Streams.of(Arrays.asList(0, 1, 2, 3, 4, 5));
        List<Integer> result = stream.filter(x -> x < 3).toList();
        assertEquals(result, Arrays.asList(0, 1, 2));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void transform() {
        Streams<Integer> stream = Streams.of(Arrays.asList(0, 1, 2, 3, 4, 5));
        List<Integer>  resultInt = stream.transform(x -> x + 5).toList();
        assertEquals(resultInt, Arrays.asList(5, 6, 7, 8, 9, 10));

        stream = Streams.of(Arrays.asList(0, 1, 2, 3, 4, 5));
        List<String> resultString = stream.transform(String::valueOf).toList();
        assertEquals(resultString, Arrays.asList("0", "1", "2", "3", "4", "5"));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void toMap() {
        Streams<Integer> stream = Streams.of(Arrays.asList(0, 1, 2, 3, 4, 5));
        Map<String, Integer> result = stream.toMap(x -> String.valueOf(x) + " + 5", x -> x + 5);
        Set<String> keySet = new HashSet<>(Arrays.asList("0 + 5", "1 + 5", "2 + 5", "3 + 5", "4 + 5", "5 + 5"));
        List<Integer> valueListExpected = new ArrayList<>(Arrays.asList(5, 6, 7, 8, 9, 10));
        List<Integer> valueList = new ArrayList<>(result.values());
        Collections.sort(valueList);

        assertEquals(keySet, result.keySet());
        assertEquals(valueListExpected, valueList);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void combination() {
        Streams<String> stream = Streams.of(Arrays.asList("2", "15", "0", "-3", "8", "5.7", "7", "1"));
        Map<Integer, Boolean> result = stream
                .filter(x -> x.length() < 2)
                .transform(Integer::valueOf)
                .filter(x -> x > 0)
                .toMap(x -> x, x -> x % 2 == 0);
        Map<Integer, Boolean> expectedMap = new HashMap<>();
        expectedMap.put(2, true);
        expectedMap.put(8, true);
        expectedMap.put(7, false);
        expectedMap.put(1, false);

        assertEquals(result, expectedMap);
    }
}