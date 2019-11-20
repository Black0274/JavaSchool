package com.sbt.javaschool.losev.lesson6.tests;

import com.sbt.javaschool.losev.lesson6.beanutils.BeanUtils;
import com.sbt.javaschool.losev.lesson6.beanutils.From;
import com.sbt.javaschool.losev.lesson6.beanutils.To;
import org.junit.Test;

import static org.junit.Assert.*;

public class BeanUtilsTest {

    @Test
    public void assignIntVar() {
        From from = new From();
        To to = new To();
        BeanUtils.assign(to, from);
        assertEquals(to.getIntVar(), from.getIntVar());
    }

    @Test
    public void assignStringVar() {
        From from = new From();
        To to = new To();
        BeanUtils.assign(to, from);
        assertEquals(to.getStringVar(), from.getStringVar());
    }

    @Test
    public void assignNumberVar() {
        From from = new From();
        To to = new To();
        BeanUtils.assign(to, from);
        assertEquals(to.getNumberVar(), from.getNumberVar());
    }

    @Test
    public void assignClosedStringVar() {
        From from = new From();
        To to = new To();
        BeanUtils.assign(to, from);
        assertEquals(to.getClosedStringVar(), "closedTo");
    }

    @Test
    public void assignOnlyInToVar() {
        From from = new From();
        To to = new To();
        BeanUtils.assign(to, from);
        assertEquals(to.getOnlyInToVar(), 44);
    }
}