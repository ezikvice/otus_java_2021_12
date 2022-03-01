package ru.dimk.service;

import ru.dimk.model.ATM;
import ru.dimk.model.Denomination;
import ru.dimk.model.IssueResult;

import java.util.Map;

public class ATMServiceImpl implements ATMService {



    @Override
    public void acceptMoney(ATM atm, Map<Denomination, Long> money) {
        Map<Denomination, Long> slots = atm.getSlots();
        for (Denomination denomination : money.keySet()) {
            if (slots.containsKey(denomination)) {
                Long summaryMoneyInSlot = Long.sum(slots.get(denomination), money.get(denomination));
                slots.put(denomination, summaryMoneyInSlot);
            } else {
                slots.put(denomination, money.get(denomination));
            }
        }
    }

    @Override
    public IssueResult issueMoney(ATM atm, long amount) {
        return null;
    }


    @Override
    public long getBalance(ATM atm) {
        Map<Denomination, Long> slots = atm.getSlots();
        var summaryBalance = 0;
        for (Denomination denomination : slots.keySet()) {
            long countOfMoneyInSlot = denomination.numberRepresentation * slots.get(denomination);
            summaryBalance += countOfMoneyInSlot;
        }
        return summaryBalance;
    }
}
