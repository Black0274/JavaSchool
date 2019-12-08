package com.sbt.javaschool.losev.lesson11;

public class MultDictionary implements Runnable {
    String path;

    MultDictionary(String path){
        this.path = path;
    }

    @Override
    public void run() {
        Dictionary.get(path);
    }
}
