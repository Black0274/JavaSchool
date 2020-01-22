package com.sbt.javaschool.losev.lesson18.singleton;

import org.junit.Test;
import java.util.Set;
import static org.junit.Assert.*;

public class ElvisTest {
    @Test
    public void ElvisTest(){
     Elvis elvis1 = Elvis.getInstance();
     Elvis elvis2 = Elvis.getInstance();

     assertEquals(elvis1, elvis2);
    }
}
