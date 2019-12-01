package com.sbt.javaschool.losev.lesson9.java;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ServiceImpl implements Service {
    @Override
    public List<String> run(String item, double value, Date date) {
        return null;
    }

    /**
     * returns substrings of item with first letter
     */
    @Override
    public List<String> work(String item) {
        List<String> result = new ArrayList<>();
        for (int i = 1; i <= item.length(); i++){
            result.add(item.substring(0, i));
        }
        return result;
    }
}
