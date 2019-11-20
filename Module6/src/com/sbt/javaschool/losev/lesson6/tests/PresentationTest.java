package com.sbt.javaschool.losev.lesson6.tests;

import com.sbt.javaschool.losev.lesson6.presentation.*;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;
import java.util.logging.Handler;

import static org.junit.Assert.*;

public class PresentationTest {

    @Test
    public void getAllGetters_Person() throws NoSuchMethodException {
        List<Method> methods = new ArrayList<>(Arrays.asList(Main.getAllGetters(Person.class)));
        assertTrue(methods.contains(Person.class.getMethod("getAge")));
        assertTrue(methods.contains(Person.class.getMethod("getName")));
        assertTrue(methods.contains(Person.class.getMethod("getSpouseName")));
        assertFalse(methods.contains(Person.class.getMethod("getNothing")));
    }

    @Test
    public void getAllGetters_Float() {
        List<Method> methods = new ArrayList<>(Arrays.asList(Main.getAllGetters(Float.class)));
        assertEquals(methods.size(), 0);
    }

    @Test
    public void allStringConstantsEqValues_Person(){
        assertTrue(Main.AllStringConstantsEqValues(Person.class));
    }

    @Test
    public void allStringConstantsEqValues_NewClass(){
        class Countries{
            public static final String EGYPT = "EGYPT";
            private static final String UKRAINE = "UKRAINE";
            protected static final String DONBASS = "UKRAINE";
        }
        assertFalse(Main.AllStringConstantsEqValues(Countries.class));
    }

    @Test
    public void calc_cacheProxy() throws NoSuchFieldException, IllegalAccessException {
        CacheHandler handler = new CacheHandler(new CalculatorImpl());
        Calculator calculator = (Calculator) Proxy.newProxyInstance(Calculator.class.getClassLoader(),
                new Class[] {Calculator.class}, handler);
        Field cacheField = CacheHandler.class.getDeclaredField("cache");
        cacheField.setAccessible(true);
        HashMap cache = (HashMap) cacheField.get(handler);

        assertEquals(cache.size(), 0);
        calculator.calc("2 + 2");
        assertEquals(cache.size(), 1);
        calculator.calc("2 + 3");
        assertEquals(cache.size(), 2);
        calculator.calc("2 + 3");
        assertEquals(cache.size(), 2);
        calculator.calc("3 + 2");
        assertEquals(cache.size(), 3);
        calculator.calc("1 + 3");
        assertEquals(cache.size(), 4);
        calculator.calc("2 + 2");
        assertEquals(cache.size(), 4);
    }

    @Test
    public void calc_NoCacheProxy() throws NoSuchFieldException, IllegalAccessException {
        CacheHandler handler = new CacheHandler(new CalculatorImpl());
        Calculator calculator = (Calculator) Proxy.newProxyInstance(Calculator.class.getClassLoader(),
                new Class[] {Calculator.class}, handler);
        Field cacheField = CacheHandler.class.getDeclaredField("cache");
        cacheField.setAccessible(true);
        HashMap cache = (HashMap) cacheField.get(handler);

        assertEquals(cache.size(), 0);
        calculator.calcNoCache("2 + 2");
        calculator.calcNoCache("2");
        calculator.calcNoCache("5 + 0");
        calculator.calcNoCache("12 + 2 + 9");
        calculator.calcNoCache("10");
        assertEquals(cache.size(), 0);
    }
}