package com.sbt.javaschool.losev.lesson5.Terminal;

import com.sbt.javaschool.losev.lesson5.Exceptions.*;

import java.io.IOException;
import java.math.BigDecimal;

public class TerminalImpl implements Terminal{

    private final TerminalServer server;
    private final PinValidator pinValidator = new PinValidator();
    private CreditCard currentCard;
    private boolean cardUnlocked = false;
    private boolean cardInserted = false;
    private int wrongPins = 0;
    private long startTimeOut = 0;

    public TerminalImpl(TerminalServer server){
        this.server = server;
    }

    public void insert(CreditCard card) throws CardAlreadyInsertedException {
        if (cardInserted){
            throw new CardAlreadyInsertedException("Card slot was full when you try to insert your card");
        }
        this.currentCard = card;
        cardInserted = true;
    }

    public CreditCard extract() throws CardNotInsertedException {
        if (!cardInserted){
            throw new CardNotInsertedException("Card slot was empty when you try to remove your card");
        }
        CreditCard card = currentCard;
        this.currentCard = null;
        cardInserted = false;
        cardUnlocked = false;
        return card;
    }

    public int typePin(int pin) throws CardNotInsertedException, IOException, CardNotExistsException, AccountIsLockedException, IllegalArgumentException {
        if (!cardInserted){
            throw new CardNotInsertedException("Card slot was empty when you try to type pin");
        }
        if (pin < 0){
            throw new IllegalArgumentException("Negative value");
        }

        if (startTimeOut == 0) {
            server.connect();
            if (server.checkPin(currentCard, pin)) {
                cardUnlocked = true;
                wrongPins = 0;
            } else {
                cardUnlocked = false;
                wrongPins += 1;
            }
        }
        if (wrongPins >= 3){
            if (startTimeOut == 0) {
                startTimeOut = System.currentTimeMillis();
            } else{
                if (System.currentTimeMillis() - startTimeOut > 5000){
                    startTimeOut = 0;
                    server.connect();
                    if (server.checkPin(currentCard, pin)) {
                        cardUnlocked = true;
                        wrongPins = 0;
                    } else{
                        wrongPins = 1;
                    }
                }
                else{
                    throw new AccountIsLockedException(5000 - (System.currentTimeMillis() - startTimeOut));
                }
            }
        }
        return wrongPins;
    }


    @Override
    public BigDecimal checkCapital() throws CardNotExistsException, CardNotInsertedException, AccountIsLockedException, IOException {
        if (!cardInserted){
            throw new CardNotInsertedException("Card slot was empty when you try to check capital");
        }
        if (!cardUnlocked){
            throw new AccountIsLockedException();
        }
        server.connect();
        return server.checkCapital(currentCard);
    }

    @Override
    public void withdraw(BigDecimal value) throws CardNotInsertedException, AccountIsLockedException, CardNotExistsException, NotEnoughMoneyException, IllegalArgumentException, NotMultipleOf100Exception, IOException {
        if (!cardInserted){
            throw new CardNotInsertedException("Card slot was empty when you try to check capital");
        }
        if (!cardUnlocked) {
            throw new AccountIsLockedException();
        }
        if (value.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("Negative value");
        }
        if (!(value.remainder(new BigDecimal(100)).compareTo(BigDecimal.ZERO) == 0)){
            throw new NotMultipleOf100Exception(value);
        }
        server.connect();
        server.withdraw(currentCard, value);
    }

    @Override
    public void put(BigDecimal value) throws CardNotExistsException, NotEnoughMoneyException, AccountIsLockedException, CardNotInsertedException, IllegalArgumentException, NotMultipleOf100Exception, IOException {
        if (!cardInserted){
            throw new CardNotInsertedException("Card slot was empty when you try to check capital");
        }
        if (!cardUnlocked) {
            throw new AccountIsLockedException();
        }
        if (value.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("Negative value");
        }
        if (!(value.remainder(new BigDecimal(100)).compareTo(BigDecimal.ZERO) == 0)){
            throw new NotMultipleOf100Exception(value);
        }
        server.connect();
        server.put(currentCard, value);
    }
}

