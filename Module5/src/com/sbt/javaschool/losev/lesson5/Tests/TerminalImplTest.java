package com.sbt.javaschool.losev.lesson5.Tests;

import com.sbt.javaschool.losev.lesson5.Exceptions.*;
import com.sbt.javaschool.losev.lesson5.Terminal.CreditCard;
import com.sbt.javaschool.losev.lesson5.Terminal.TerminalImpl;
import com.sbt.javaschool.losev.lesson5.Terminal.TerminalServer;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;

public class TerminalImplTest {

    @Test
    public void insertCorrect() {
        TerminalServer terminalServer = new TerminalServer();
        CreditCard card1 = new CreditCard(1111);
        TerminalImpl impl = new TerminalImpl(terminalServer);

        try {
            impl.insert(card1);
        } catch (CardAlreadyInsertedException e) {
            Assert.fail("Unexpected CardAlreadyInsertedException");
        }
    }

    @Test
    public void insertIncorrect() {
        TerminalServer terminalServer = new TerminalServer();
        CreditCard card1 = new CreditCard(1111);
        CreditCard card2 = new CreditCard(2222);
        TerminalImpl impl = new TerminalImpl(terminalServer);

        try {
            impl.insert(card1);
            impl.insert(card2);
            Assert.fail("Expected CardAlreadyInsertedException");
        } catch (CardAlreadyInsertedException ignored) {}
    }

    @Test
    public void extractCorrect() {
        TerminalServer terminalServer = new TerminalServer();
        CreditCard card1 = new CreditCard(1111);
        TerminalImpl impl = new TerminalImpl(terminalServer);

        try {
            impl.insert(card1);
            CreditCard extractedCard = impl.extract();
            if (extractedCard.getNumber() != card1.getNumber()){
                Assert.fail("Not the same cards");
            }
        } catch (Exception e) {
            Assert.fail("Unexpected " + e.getClass().getName());
        }
    }

    @Test
    public void extractIncorrect() {
        TerminalServer terminalServer = new TerminalServer();
        TerminalImpl impl = new TerminalImpl(terminalServer);

        try {
            impl.extract();
            Assert.fail("Expected CardNotInsertedException");
        } catch (CardNotInsertedException ignored) {}
    }

    @Test
    public void typePinCorrect() {
        TerminalServer terminalServer = new TerminalServer();
        CreditCard card1 = new CreditCard(1111);
        TerminalImpl impl = new TerminalImpl(terminalServer);
        terminalServer.addNewCard(card1, 3223);

        try {
            impl.insert(card1);
            int mistakes = impl.typePin(1234);
            if (mistakes != 1){
                Assert.fail("Incorrect mistakes number");
            }
            mistakes = impl.typePin(9875);
            if (mistakes != 2){
                Assert.fail("Incorrect mistakes number");
            }
            mistakes = impl.typePin(1111);
            if (mistakes != 3){
                Assert.fail("Incorrect mistakes number");
            }
            Thread.sleep(5100);
            mistakes = impl.typePin(1234);
            if (mistakes != 1){
                Assert.fail("Incorrect mistakes number");
            }
            mistakes = impl.typePin(3223);
            if (mistakes != 0){
                Assert.fail("Incorrect mistakes number");
            }
        } catch (Exception e) {
            Assert.fail("Unexpected " + e.getClass().getName());
        }
    }

    @Test
    public void typePinIncorrectInsert() {
        TerminalServer terminalServer = new TerminalServer();
        CreditCard card1 = new CreditCard(1111);
        TerminalImpl impl = new TerminalImpl(terminalServer);
        terminalServer.addNewCard(card1, 3223);

        try {
            impl.typePin(1111);
            Assert.fail("Expected CardNotInsertedException");
        } catch (CardNotInsertedException ignored) {
        } catch (Exception e) {
            Assert.fail("Unexpected " + e.getClass().getName());
        }
    }

    @Test
    public void typePinIncorrectNotExists() {
        TerminalServer terminalServer = new TerminalServer();
        CreditCard card1 = new CreditCard(1111);
        TerminalImpl impl = new TerminalImpl(terminalServer);

        try {
            impl.insert(card1);
            impl.typePin(1111);
            Assert.fail("Expected CardNotExistsException");
        } catch (CardNotExistsException ignored) {
        } catch (Exception e) {
            Assert.fail("Unexpected " + e.getClass().getName());
        }
    }

    @Test
    public void typePinIncorrectDisabledServer() {
        TerminalServer terminalServer = new TerminalServer();
        CreditCard card1 = new CreditCard(1111);
        TerminalImpl impl = new TerminalImpl(terminalServer);
        terminalServer.addNewCard(card1, 3223);
        terminalServer.setEnabled(false);

        try {
            impl.insert(card1);
            impl.typePin(1111);
            Assert.fail("Expected IOException");
        } catch (IOException ignored) {
        } catch (Exception e) {
            Assert.fail("Unexpected " + e.getClass().getName());
        }
    }

    @Test
    public void typePinIncorrectNegativeValue() {
        TerminalServer terminalServer = new TerminalServer();
        CreditCard card1 = new CreditCard(1111);
        TerminalImpl impl = new TerminalImpl(terminalServer);
        terminalServer.addNewCard(card1, 3223);

        try {
            impl.insert(card1);
            impl.typePin(-1111);
            Assert.fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException ignored) {
        } catch (Exception e) {
            Assert.fail("Unexpected " + e.getClass().getName());
        }
    }

    @Test
    public void typePinIncorrectAccountIsLocked() {
        TerminalServer terminalServer = new TerminalServer();
        CreditCard card1 = new CreditCard(1111);
        TerminalImpl impl = new TerminalImpl(terminalServer);
        terminalServer.addNewCard(card1, 3223);

        try {
            impl.insert(card1);
            impl.typePin(1111);
            impl.typePin(1111);
            impl.typePin(1111);
            impl.typePin(1111);
            Assert.fail("Expected AccountIsLockedException");
        } catch (AccountIsLockedException ignored) {
        } catch (Exception e) {
            Assert.fail("Unexpected " + e.getClass().getName());
        }
    }

    @Test
    public void checkCorrect() {
        TerminalServer terminalServer = new TerminalServer();
        CreditCard card1 = new CreditCard(1111);
        CreditCard card2 = new CreditCard(2222);
        TerminalImpl impl = new TerminalImpl(terminalServer);
        terminalServer.addNewCard(card1, 3223);
        terminalServer.addNewCard(card2, 2332, BigDecimal.valueOf(1000));

        try {
            impl.insert(card1);
            impl.typePin(3223);
            if (!(impl.checkCapital().compareTo(BigDecimal.ZERO) == 0)){
                Assert.fail("Incorrect capital");
            }
            impl.extract();
            impl.insert(card2);
            impl.typePin(2332);
            if (!(impl.checkCapital().compareTo(BigDecimal.valueOf(1000)) == 0)){
                Assert.fail("Incorrect capital");
            }
        } catch (Exception e) {
            Assert.fail("Unexpected " + e.getClass().getName());
        }
    }

    @Test
    public void withdrawCorrect() {
        TerminalServer terminalServer = new TerminalServer();
        CreditCard card1 = new CreditCard(1111);
        TerminalImpl impl = new TerminalImpl(terminalServer);
        terminalServer.addNewCard(card1, 3223, BigDecimal.valueOf(500));

        try {
            impl.insert(card1);
            impl.typePin(3223);
            impl.withdraw(BigDecimal.valueOf(200));
            if (!(impl.checkCapital().compareTo(BigDecimal.valueOf(300)) == 0)){
                Assert.fail("Incorrect withdraw transaction");
            }
        } catch (Exception e) {
            Assert.fail("Unexpected " + e.getClass().getName());
        }
    }

    @Test
    public void withdrawIncorrectNotMultipleOf100() {
        TerminalServer terminalServer = new TerminalServer();
        CreditCard card1 = new CreditCard(1111);
        TerminalImpl impl = new TerminalImpl(terminalServer);
        terminalServer.addNewCard(card1, 3223, BigDecimal.valueOf(500));

        try {
            impl.insert(card1);
            impl.typePin(3223);
            impl.withdraw(BigDecimal.valueOf(330));
            Assert.fail("Expected NotMultipleOf100Exception");
        } catch (NotMultipleOf100Exception ignored) {
        } catch (Exception e) {
            Assert.fail("Unexpected " + e.getClass().getName());
        }
    }

    @Test
    public void withdrawIncorrectNotEnoughMoney() {
        TerminalServer terminalServer = new TerminalServer();
        CreditCard card1 = new CreditCard(1111);
        TerminalImpl impl = new TerminalImpl(terminalServer);
        terminalServer.addNewCard(card1, 3223, BigDecimal.valueOf(500));

        try {
            impl.insert(card1);
            impl.typePin(3223);
            impl.withdraw(BigDecimal.valueOf(700));
            Assert.fail("Expected NotEnoughMoneyException");
        } catch (NotEnoughMoneyException ignored) {
        } catch (Exception e) {
            Assert.fail("Unexpected " + e.getClass().getName());
        }
    }

    @Test
    public void putCorrect() {
        TerminalServer terminalServer = new TerminalServer();
        CreditCard card1 = new CreditCard(1111);
        TerminalImpl impl = new TerminalImpl(terminalServer);
        terminalServer.addNewCard(card1, 3223, BigDecimal.valueOf(500));

        try {
            impl.insert(card1);
            impl.typePin(3223);
            impl.put(BigDecimal.valueOf(200));
            if (!(impl.checkCapital().compareTo(BigDecimal.valueOf(700)) == 0)){
                Assert.fail("Incorrect withdraw transaction");
            }
        } catch (Exception e) {
            Assert.fail("Unexpected " + e.getClass().getName());
        }
    }

    @Test
    public void putIncorrectNotMultipleOf100() {
        TerminalServer terminalServer = new TerminalServer();
        CreditCard card1 = new CreditCard(1111);
        TerminalImpl impl = new TerminalImpl(terminalServer);
        terminalServer.addNewCard(card1, 3223, BigDecimal.valueOf(500));

        try {
            impl.insert(card1);
            impl.typePin(3223);
            impl.put(BigDecimal.valueOf(330));
            Assert.fail("Expected NotMultipleOf100Exception");
        } catch (NotMultipleOf100Exception ignored) {
        } catch (Exception e) {
            Assert.fail("Unexpected " + e.getClass().getName());
        }
    }
}