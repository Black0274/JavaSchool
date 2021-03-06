package com.sbt.javaschool.losev.lesson5.Exceptions;

import com.sbt.javaschool.losev.lesson5.Terminal.CreditCard;

public class AccountIsLockedException extends Exception {

    private double waitTime = 0;

    public double getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(double waitTime) {
        this.waitTime = waitTime;
    }

    public AccountIsLockedException() { super("Card is locked. Type correct pin code."); }

    public AccountIsLockedException(CreditCard card){
        super("Card " + card.getNumber() + " is locked. Wait some time.");
    }

    public AccountIsLockedException(long time){
        super("Card is locked. Wait " + time / (double) 1000 + " seconds.");
        waitTime = time / (double) 1000;
    }
}
