package com.sbt.javaschool.losev.lesson13.part1;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<String> callable = () -> "lesson13";

        FutureTask<String> futureTask = new FutureTask<>(callable);
        new Thread(futureTask).start();
        new Thread(futureTask).start();
        System.out.println(futureTask.get());

        Task<String> task = new Task<>(callable);
        System.out.println(task.get());
    }
}
