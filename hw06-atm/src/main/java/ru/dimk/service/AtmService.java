package ru.dimk.service;

import ru.dimk.model.Atm;
import ru.dimk.model.Denomination;
import ru.dimk.model.Response;

import java.util.Map;

public interface AtmService {
    void acceptMoney(Atm atm, Map<Denomination, Long> money);

    Response issueMoney(Atm atm, long amount);

    long getBalance(Atm atm);
}
