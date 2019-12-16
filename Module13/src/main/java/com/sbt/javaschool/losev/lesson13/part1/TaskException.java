package com.sbt.javaschool.losev.lesson13.part1;

public class TaskException extends RuntimeException {

    public TaskException(){
        super("Task Exception!");
    }

    public TaskException(Exception e){
        super("Task Exception!", e);
    }
}
