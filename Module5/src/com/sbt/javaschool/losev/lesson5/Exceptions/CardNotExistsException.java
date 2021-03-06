package com.sbt.javaschool.losev.lesson5.Exceptions;

import com.sbt.javaschool.losev.lesson5.Terminal.CreditCard;

public class CardNotExistsException extends Exception{

    public CardNotExistsException(){
        super("Card doesn't exist. Add card on Terminal Server.");
    }

    public CardNotExistsException(String message){
        super(message);
    }

    public CardNotExistsException(CreditCard card){
        super("Card " + card.getNumber() + " doesn't exist. Add card to Terminal Server.");
    }
}
