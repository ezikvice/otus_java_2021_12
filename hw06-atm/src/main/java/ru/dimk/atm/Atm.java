package ru.dimk.atm;

import java.util.Map;

public interface Atm {
    void acceptMoney(Map<Denomination, Long> money);

    Response issueMoney(long amount);

    long getBalance();
}
