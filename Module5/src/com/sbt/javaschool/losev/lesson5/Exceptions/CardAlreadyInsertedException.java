package com.sbt.javaschool.losev.lesson5.Exceptions;

public class CardAlreadyInsertedException extends Exception {

    public CardAlreadyInsertedException(){
        super("Credit card already inserted. Extract current card.");
    }

    public CardAlreadyInsertedException(String message){
        super(message);
    }
}
