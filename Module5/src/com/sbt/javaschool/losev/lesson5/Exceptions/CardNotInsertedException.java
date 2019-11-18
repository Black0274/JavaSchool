package com.sbt.javaschool.losev.lesson5.Exceptions;

public class CardNotInsertedException extends Exception {

    public CardNotInsertedException(){
        super("Credit card is not inserted.");
    }

    public CardNotInsertedException(String message){
        super(message);
    }
}
