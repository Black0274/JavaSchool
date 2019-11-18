package com.sbt.javaschool.losev.lesson5.Exceptions;

import com.sbt.javaschool.losev.lesson5.Terminal.CreditCard;

public class AccountIsLockedException extends Exception {

    public double waitTime = 0;

    public AccountIsLockedException() { super("Card is locked."); }

    public AccountIsLockedException(CreditCard card){
        super("Card " + card.getNumber() + " is locked. Wait some time.");
    }

    public AccountIsLockedException(long time){
        super("Card is locked. Wait " + time / (double) 1000 + " seconds.");
        waitTime = time / (double) 1000;
    }
}
