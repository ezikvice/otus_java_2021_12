package ru.dimk.service;

import ru.dimk.model.Atm;
import ru.dimk.model.Denomination;
import ru.dimk.model.Response;

import java.util.Map;

public interface AtmService {
    void acceptMoney(Map<Denomination, Long> money);

    Response issueMoney(long amount);

    long getBalance();
}
