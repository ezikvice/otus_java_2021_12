package ru.dimk.model;

import java.util.Collection;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class ATM {
    private SortedMap<Denomination, Long> slots;

    {
        slots = new TreeMap<>();
    }

    public void acceptMoney(Map<Denomination, Long> money) {
        for (Denomination denomination : money.keySet()) {
            if (slots.containsKey(denomination)) {
                Long summaryMoneyInSlot = Long.sum(slots.get(denomination), money.get(denomination));
                slots.put(denomination, summaryMoneyInSlot);
            } else {
                slots.put(denomination, money.get(denomination));
            }
        }
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
