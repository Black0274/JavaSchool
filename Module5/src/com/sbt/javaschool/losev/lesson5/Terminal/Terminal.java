package com.sbt.javaschool.losev.lesson5.Terminal;

import com.sbt.javaschool.losev.lesson5.Exceptions.*;

import java.io.IOException;
import java.math.BigDecimal;

public interface Terminal {

    BigDecimal checkCapital() throws CardNotExistsException, CardNotInsertedException, AccountIsLockedException, IOException;

    void withdraw(BigDecimal value) throws CardNotInsertedException, AccountIsLockedException, CardNotExistsException, NotEnoughMoneyException, IllegalArgumentException, NotMultipleOf100Exception, IOException;

    void put(BigDecimal value) throws CardNotExistsException, NotEnoughMoneyException, AccountIsLockedException, CardNotInsertedException, IllegalArgumentException, NotMultipleOf100Exception, IOException;
}
