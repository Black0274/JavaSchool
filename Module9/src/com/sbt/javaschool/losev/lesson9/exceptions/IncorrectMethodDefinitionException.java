package com.sbt.javaschool.losev.lesson9.exceptions;

public class IncorrectMethodDefinitionException extends ClassCastException {

    public IncorrectMethodDefinitionException(){
        super("The method is not defined correctly. The method must be defined as List<T> methodName(T item).");
    }
}
