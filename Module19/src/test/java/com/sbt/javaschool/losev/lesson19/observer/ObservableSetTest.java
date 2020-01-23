package com.sbt.javaschool.losev.lesson19.observer;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class ObservableSetTest {
    @Test
    public void AddTest(){
        List<Integer> range = IntStream.range(0, 100).boxed().collect(Collectors.toList());
        List<Integer> list = new ArrayList<>();

        ObservableSet<Integer> set = new ObservableSet<>(new HashSet<>());
        set.addObserver((s, e) -> list.add(e));
        for (int i = 0; i < 100; i++)
            set.add(i);

        assertEquals(range, list);
    }
}
