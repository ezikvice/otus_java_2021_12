package ru.dimk.service;

import ru.dimk.model.ATM;
import ru.dimk.model.Denomination;
import ru.dimk.model.IssueResult;

import java.util.Map;

public interface ATMService {
    void acceptMoney(ATM atm, Map<Denomination, Long> money);

    IssueResult issueMoney(ATM atm, long amount);

    long getBalance(ATM atm);
}
