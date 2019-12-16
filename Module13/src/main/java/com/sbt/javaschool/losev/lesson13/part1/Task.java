package com.sbt.javaschool.losev.lesson13.part1;

import java.util.concurrent.Callable;

public class Task<T> {

    private final Callable<? extends T> callable;
    private boolean exceptionFlag = false;
    private boolean alreadyCalled = false;
    private final Object lock = new Object();
    private T value;

    public Task(Callable<? extends T> callable) {
        this.callable = callable;
    }

    public T get() {
        if (exceptionFlag){
            throw new TaskException();
        }

        if (!alreadyCalled) {
            synchronized (lock) {
                try{
                    alreadyCalled = true;
                    value = callable.call();
                } catch (Exception e){
                    exceptionFlag = true;
                    throw new TaskException(e);
                }
            }
        }

        return value;
    }
}

