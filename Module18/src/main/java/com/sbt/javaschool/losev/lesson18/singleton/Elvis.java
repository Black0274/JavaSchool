package com.sbt.javaschool.losev.lesson18.singleton;

// Pattern singleton + static factory example from "Effective Java" (Joshua Bloch)
public class Elvis {
    private static final Elvis INSTANCE = new Elvis();

    private Elvis() {
        // do something
    }

    public static Elvis getInstance(){ return INSTANCE; }
}
