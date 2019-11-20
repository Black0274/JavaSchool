package com.sbt.javaschool.losev.lesson6.tests;

import com.sbt.javaschool.losev.lesson6.beanutils.BeanUtils;
import com.sbt.javaschool.losev.lesson6.beanutils.From;
import com.sbt.javaschool.losev.lesson6.beanutils.To;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.*;

public class BeanUtilsTest {

    @Test
    public void assignIntVar() throws InvocationTargetException, IllegalAccessException {
        From from = new From();
        To to = new To();
        BeanUtils.assign(to, from);
        assertEquals(to.getIntVar(), from.getIntVar());
    }

    @Test
    public void assignStringVar() throws InvocationTargetException, IllegalAccessException {
        From from = new From();
        To to = new To();
        BeanUtils.assign(to, from);
        assertEquals(to.getStringVar(), from.getStringVar());
    }

    @Test
    public void assignNumberVar() throws InvocationTargetException, IllegalAccessException {
        From from = new From();
        To to = new To();
        BeanUtils.assign(to, from);
        assertEquals(to.getNumberVar(), from.getNumberVar());
    }

    @Test
    public void assignDifferentVar() throws InvocationTargetException, IllegalAccessException {
        From from = new From();
        To to = new To();
        BeanUtils.assign(to, from);
        assertEquals(to.getDifferentVar(), 2);
    }

    @Test
    public void assignClosedStringVar() throws InvocationTargetException, IllegalAccessException {
        From from = new From();
        To to = new To();
        BeanUtils.assign(to, from);
        assertEquals(to.getClosedStringVar(), "closedTo");
    }

    @Test
    public void assignOnlyInToVar() throws InvocationTargetException, IllegalAccessException {
        From from = new From();
        To to = new To();
        BeanUtils.assign(to, from);
        assertEquals(to.getOnlyInToVar(), 44);
    }
}