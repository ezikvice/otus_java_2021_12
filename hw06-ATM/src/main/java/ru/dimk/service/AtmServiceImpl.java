package ru.dimk.service;

import ru.dimk.model.Atm;
import ru.dimk.model.Denomination;
import ru.dimk.model.IssueResult;
import ru.dimk.model.Slot;

import java.util.Map;
import java.util.SortedSet;
import java.util.TreeMap;

public class AtmServiceImpl implements AtmService {


    @Override
    public void acceptMoney(Atm atm, Map<Denomination, Long> money) {
        SortedSet<Slot> slots = atm.getSlots();
        for (Denomination denomination : money.keySet()) {
            if (slots.contains(denomination)) {
                Long summaryMoneyInSlot = Long.sum(slots.get(denomination), money.get(denomination));
                slots.put(denomination, summaryMoneyInSlot);
            } else {
                slots.put(denomination, money.get(denomination));
            }
        }
    }

    @Override
    public IssueResult issueMoney(Atm atm, long moneyToIssue) {
        IssueResult result = new IssueResult();
        AtmService atmService = new AtmServiceImpl();
        if (!isMoneyEnough(atm, moneyToIssue)){
                throw new ArithmeticException("Не хватает денег в банкомате");
        }
        TreeMap slots = atm.getSlots();
        return result;

    }


    @Override
    public long getBalance(Atm atm) {
        Map<Denomination, Long> slots = atm.getSlots();
        var summaryBalance = 0;
        for (Denomination denomination : slots.keySet()) {
            long countOfMoneyInSlot = denomination.numberRepresentation * slots.get(denomination);
            summaryBalance += countOfMoneyInSlot;
        }
        return summaryBalance;
    }

    private boolean isMoneyEnough(Atm atm, long amount) {
        AtmService atmService = new AtmServiceImpl();
        return atmService.getBalance(atm) >= amount;
    }


}
