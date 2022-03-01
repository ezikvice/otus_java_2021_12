package ru.dimk.service;

import ru.dimk.model.Atm;
import ru.dimk.model.Denomination;
import ru.dimk.model.IssueResult;

import java.util.Map;

public interface AtmService {
    void acceptMoney(Atm atm, Map<Denomination, Long> money);

    IssueResult issueMoney(Atm atm, long amount);

    long getBalance(Atm atm);
}
