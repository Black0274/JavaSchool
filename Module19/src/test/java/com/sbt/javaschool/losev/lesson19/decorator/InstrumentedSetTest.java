package com.sbt.javaschool.losev.lesson19.decorator;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import static org.junit.Assert.*;

public class InstrumentedSetTest {
    @Test
    public void AddTest(){
        InstrumentedSet<Integer> set =
                new InstrumentedSet<Integer>(new HashSet<Integer>());
        set.add(3);
        set.add(2);
        set.add(1);
        set.add(3);
        assertEquals(4, set.getAddCount());
    }

    @Test
    public void AddAllTest(){
        InstrumentedSet<Integer> set =
                new InstrumentedSet<Integer>(new HashSet<Integer>());
        set.addAll(Arrays.asList(0, 1, 3, 2, 3));
        assertEquals(5, set.getAddCount());
    }
}
