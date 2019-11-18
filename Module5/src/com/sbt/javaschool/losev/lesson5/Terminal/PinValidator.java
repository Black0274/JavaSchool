package com.sbt.javaschool.losev.lesson5.Terminal;

import com.sbt.javaschool.losev.lesson5.Exceptions.CardNotExistsException;

import java.util.HashMap;
import java.util.Map;

class PinValidator {

    private final Map<Long, Integer> map = new HashMap<>();

    void addCard(CreditCard card, int pin){
        map.put(card.getNumber(), pin);     // If the card already exists then change password
    }

    boolean correctPin(CreditCard card, int pin) throws CardNotExistsException {
        if (!map.containsKey(card.getNumber())){
            throw new CardNotExistsException(card);
        }
        return map.get(card.getNumber()) == pin;
    }
}
