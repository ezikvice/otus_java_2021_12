package ru.dimk.model;

import java.util.Collection;
import java.util.Map;

public class ATM {
    private Map<Denomination, Long> slots;

    public void acceptMoney(Collection<SingleDenominationBanknotes> money) {

    }

    public IssueResult issueMoney(long amount) {
        return null;
    }


}
