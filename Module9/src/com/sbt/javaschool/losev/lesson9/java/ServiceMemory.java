package com.sbt.javaschool.losev.lesson9.java;

import java.util.*;

public class ServiceMemory implements Service {
    /**
     * returns substrings of item with first letter
     */
    @Override
    public List<String> substrings(String item) {
        List<String> result = new ArrayList<>();
        for (int i = 1; i <= item.length(); i++){
            result.add(item.substring(0, i));
        }
        return result;
    }

    /**
     * returns all divisors of item
     */
    @Override
    public List<Integer> divisors(int item) {
        List<Integer> result = new ArrayList<>();
        double sqrt = Math.sqrt(item);
        for (int i = 1; i <= sqrt; i++) {
            if (item % i==0) {
                if (item / i == i) {
                    result.add(i);
                }
                else {
                    result.add(i);
                    result.add(item / i);
                }
            }
        }
        Collections.sort(result);
        return result;
    }

    /**
     * incorrectly defined method
     */
    @Override
    public int length(String item){
        return item.length();
    }

    @Override
    public int lengthNoCache(String s){
        return s.length();
    }
}
