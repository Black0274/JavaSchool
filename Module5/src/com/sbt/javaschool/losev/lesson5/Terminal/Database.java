package com.sbt.javaschool.losev.lesson5.Terminal;

import com.sbt.javaschool.losev.lesson5.Exceptions.CardNotExistsException;
import com.sbt.javaschool.losev.lesson5.Exceptions.NotEnoughMoneyException;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

class Database {

    private final Map<Long, BigDecimal> map = new HashMap<>();

    void addCard(CreditCard card, BigDecimal capital){
        map.put(card.getNumber(), capital);
    }

    BigDecimal checkCapital(CreditCard card) throws CardNotExistsException {
        if (!map.containsKey(card.getNumber())){
            throw new CardNotExistsException(card);
        }
        return map.get(card.getNumber());
    }

    void transaction(CreditCard card, BigDecimal value) throws CardNotExistsException, NotEnoughMoneyException {
        if (!map.containsKey(card.getNumber())){
            throw new CardNotExistsException(card);
        }
        BigDecimal newValue = map.get(card.getNumber()).add(value);
        if (newValue.compareTo(BigDecimal.ZERO) < 0){
            throw new NotEnoughMoneyException(card);
        }
        map.put(card.getNumber(), newValue);
    }


}
