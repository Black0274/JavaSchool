package com.sbt.javaschool.losev.lesson6;

public class CalculatorImpl implements Calculator {

    // works only for addition
    public int calc(String input){
        String[] stringNumbers = input.replaceAll("\\s+","").split("\\+");
        int sum = 0;
        for (String stringNumber : stringNumbers) {
            sum += Integer.valueOf(stringNumber);
        }
        return sum;
    }

    public int calcNoCache(String input){
        return calc(input);
    }
}
