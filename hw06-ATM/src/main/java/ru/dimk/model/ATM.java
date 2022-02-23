package ru.dimk.model;

import java.util.Collection;
import java.util.Map;

public class ATM {
    private Map<Denomination, Long> slots;

    public void acceptMoney(Map<Denomination, Long> money) {

    }

    public IssueResult issueMoney(long amount) {
        return null;
    }


    public long getBalance(){
        var balance = 0;
        for (Denomination denomination : slots.keySet()) {
            long l = denomination.numberRepresentation * slots.get(denomination);
            balance+=l;
        }
        return balance;
    }
}
