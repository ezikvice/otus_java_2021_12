package ru.dimk.service;

import ru.dimk.model.*;

import java.util.*;

public class AtmServiceImpl implements AtmService {


    @Override
    public void acceptMoney(Atm atm, Map<Denomination, Long> money) {
        SortedSet<Slot> slots = atm.getSlots();
        for (Denomination denomination : money.keySet()) {
            Slot slot = atm.getSlot(denomination.numberRepresentation);
            if (slot != null) {
                Long summaryMoneyInSlot = Long.sum(slot.getDenomination(), money.get(denomination));
                slot.setQuantity(summaryMoneyInSlot);
            } else {
                slots.add(new Slot(denomination.numberRepresentation, money.get(denomination)));
            }
        }
    }

    @Override
    public Response issueMoney(Atm atm, long moneyToIssue) {
        Response issueResponse = new Response();
        AtmService atmService = new AtmServiceImpl();
        if (!isMoneyEnough(atm, moneyToIssue)){
                issueResponse.errorCode = 1;
                issueResponse.errorMsg = "Не хватает денег в банкомате.";
        }
        SortedSet<Slot> slots = atm.getSlots();

        long restMoneyToIssue = moneyToIssue;
        for (Slot slot : slots) {
            System.out.printf("купюры номиналом %d, количество: %d", slot.getDenomination(), slot.getQuantity());
            System.out.println("");
            if (restMoneyToIssue < slot.getDenomination()) {

            }
        }
        return issueResponse;

    }



    @Override
    public long getBalance(Atm atm) {
        Set<Slot> slots = atm.getSlots();
        var summaryBalance = 0;
        for (Slot slot : slots) {
            long countOfMoneyInSlot = slot.getDenomination() * slot.getQuantity();
            summaryBalance += countOfMoneyInSlot;
        }
        return summaryBalance;
    }

    private boolean isMoneyEnough(Atm atm, long amount) {
        AtmService atmService = new AtmServiceImpl();
        return atmService.getBalance(atm) >= amount;
    }

}
