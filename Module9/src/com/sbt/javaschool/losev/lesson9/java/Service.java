package com.sbt.javaschool.losev.lesson9.java;

import java.util.Date;
import java.util.List;

public interface Service{
    @Cache(cacheType = CacheType.MEMORY, listMaxLength = 20)
    List<String> substrings(String item);

    @Cache(cacheType = CacheType.MEMORY, listMaxLength = 10)
    List<Integer> divisors(int item);

    @Cache(cacheType = CacheType.MEMORY, listMaxLength = 10)
    int length(String s);

    int lengthNoCache(String s);
}

