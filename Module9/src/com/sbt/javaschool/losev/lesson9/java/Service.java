package com.sbt.javaschool.losev.lesson9.java;

import java.util.Date;
import java.util.List;

interface Service{
    @Cache(cacheType = CacheType.FILE, fileNamePrefix = "data", zip = true, identityBy = {String.class, double.class})
    List<String> run(String item, double value, Date date);

    @Cache(cacheType = CacheType.MEMORY, listMaxLength = 20)
    List<String> work(String item);
}

