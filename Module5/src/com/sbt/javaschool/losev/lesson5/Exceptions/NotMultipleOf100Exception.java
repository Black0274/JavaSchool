package com.sbt.javaschool.losev.lesson5.Exceptions;

import java.math.BigDecimal;

public class NotMultipleOf100Exception extends Exception {

    BigDecimal value = BigDecimal.ZERO;

    public NotMultipleOf100Exception(){
        super("Value is not multiple of 100");
    }

    public NotMultipleOf100Exception(BigDecimal value){
        super("Value " + value + " is not multiple of 100");
        this.value = value;
    }
}
