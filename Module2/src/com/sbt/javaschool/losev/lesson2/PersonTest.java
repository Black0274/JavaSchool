package com.sbt.javaschool.losev.lesson2;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class PersonTest {

    @Test
    public void isMan() {
        Person man = new Person(true, "Joona", 21);
        Person woman = new Person(false, "Elizabeth", 75);

        assertTrue(man.isMan());
        assertFalse(woman.isMan());
    }

    @Test
    public void getSpouseName() {
        Person husband = new Person(true, "Roman", 22);
        Person wife = new Person(false, "Eva", 18);
        Person single = new Person(true, "Dmitry", 21);
        husband.marry(wife);

        assertEquals(husband.getSpouseName(), wife.getName());
        assertEquals(wife.getSpouseName(), husband.getName());
        assertEquals(single.getSpouseName(), "—");
    }

    @Test
    public void increaseAge() {
        Person dima = new Person(true, "Dmitry", 21);
        dima.increaseAge();

        assertEquals(dima.getAge(), 22);
    }

    @Test
    public void marry() {
        Person girl = new Person(false, "Alice", 14);
        Person boy = new Person(true, "Bob", 16);
        Person dima = new Person(true, "Dmitry", 21);
        Person anna = new Person(false, "Anna", 20);
        Person gena = new Person(true, "Gennady", 53);
        Person vera = new Person(false, "Vera", 37);

        assertFalse(girl.marry(boy));
        assertFalse(anna.marry(girl));
        assertFalse(anna.marry(vera));
        assertFalse(dima.marry(boy));
        assertFalse(dima.marry(gena));
        assertTrue(dima.marry(anna));
        assertFalse(anna.marry(dima));
        assertFalse(dima.marry(anna));
        assertTrue(anna.marry(gena));
        assertTrue(dima.marry(vera));
    }

    @Test
    public void divorce() {
        Person husband = new Person(true, "Roman", 22);
        Person wife = new Person(false, "Eva", 18);
        Person single = new Person(true, "Dmitry", 21);
        husband.marry(wife);

        assertFalse(single.divorce());
        assertTrue(wife.divorce());
        assertTrue(husband.divorce());
        assertFalse(wife.divorce());
        assertFalse(husband.divorce());
    }
}