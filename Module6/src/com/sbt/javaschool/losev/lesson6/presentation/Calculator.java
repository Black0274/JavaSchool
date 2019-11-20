package com.sbt.javaschool.losev.lesson6.presentation;

public interface Calculator {

    // works only for addition
    @Cache
    int calc(String input);

    int calcNoCache(String input);
}
