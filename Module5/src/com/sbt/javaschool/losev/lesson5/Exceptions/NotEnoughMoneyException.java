package com.sbt.javaschool.losev.lesson5.Exceptions;

import com.sbt.javaschool.losev.lesson5.Terminal.CreditCard;

public class NotEnoughMoneyException extends Exception{

    public NotEnoughMoneyException(){
        super("Not enough money on the card. Try to withdraw another value.");
    }

    public NotEnoughMoneyException(String message){
        super(message);
    }

    public NotEnoughMoneyException(CreditCard card){
        super("Not enough money on the card " + card.getNumber() + " Try to withdraw another value.");
    }
}
