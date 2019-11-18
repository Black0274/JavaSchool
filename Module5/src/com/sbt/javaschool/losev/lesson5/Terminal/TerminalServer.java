package com.sbt.javaschool.losev.lesson5.Terminal;

import com.sbt.javaschool.losev.lesson5.Exceptions.CardNotExistsException;
import com.sbt.javaschool.losev.lesson5.Exceptions.NotEnoughMoneyException;

import java.io.IOException;
import java.math.BigDecimal;

public class TerminalServer {

    private boolean enabled = true;
    private final PinValidator validator = new PinValidator();
    private final Database database = new Database();

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void addNewCard(CreditCard card, int pin){
        validator.addCard(card, pin);
        database.addCard(card, BigDecimal.ZERO);
    }

    public void addNewCard(CreditCard card, int pin, BigDecimal capital){
        validator.addCard(card, pin);
        database.addCard(card, capital);
    }

    public void connect() throws IOException {
        if (!enabled){
            throw new IOException("Connection error");
        }
    }

    public boolean checkPin(CreditCard card, int pin) throws CardNotExistsException {
        return validator.correctPin(card, pin);
    }

    public BigDecimal checkCapital(CreditCard card) throws CardNotExistsException {
        return database.checkCapital(card);
    }

    public void withdraw(CreditCard card, BigDecimal value) throws CardNotExistsException, NotEnoughMoneyException {
        database.transaction(card, value.multiply(BigDecimal.valueOf(-1)));
    }

    public void put(CreditCard card, BigDecimal value) throws CardNotExistsException, NotEnoughMoneyException {
        database.transaction(card, value);
    }
}
