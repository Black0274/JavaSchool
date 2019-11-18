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
        if (capital.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("Negative value");
        }
        validator.addCard(card, pin);
        database.addCard(card, capital);
    }

    void connect() throws IOException {
        if (!enabled){
            throw new IOException("Connection error");
        }
    }

    boolean checkPin(CreditCard card, int pin) throws CardNotExistsException {
        return validator.correctPin(card, pin);
    }

    BigDecimal checkCapital(CreditCard card) throws CardNotExistsException {
        return database.checkCapital(card);
    }

    void withdraw(CreditCard card, BigDecimal value) throws CardNotExistsException, NotEnoughMoneyException {
        database.transaction(card, value.multiply(BigDecimal.valueOf(-1)));
    }

    void put(CreditCard card, BigDecimal value) throws CardNotExistsException, NotEnoughMoneyException {
        database.transaction(card, value);
    }
}
