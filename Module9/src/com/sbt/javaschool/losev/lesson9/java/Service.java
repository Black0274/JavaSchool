package com.sbt.javaschool.losev.lesson9.java;

import java.util.Date;
import java.util.List;

public interface Service{
    /**
     * returns substrings of item with first letter
     */
    @Cache(cacheType = CacheType.MEMORY, listMaxLength = 20)
    List<String> substrings(String item);

    /**
     * returns all divisors of item
     */
    @Cache(cacheType = CacheType.MEMORY, listMaxLength = 10)
    List<Integer> divisors(int item);

    /**
     * incorrectly defined method
     */
    @Cache(cacheType = CacheType.MEMORY, listMaxLength = 10)
    int length(String item);

    /**
     * length method without annotation
     */
    int lengthNoCache(String item);
}

