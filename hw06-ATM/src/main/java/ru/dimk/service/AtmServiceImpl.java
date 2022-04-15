package ru.dimk.service;

import ru.dimk.model.*;

import java.util.*;

public class AtmServiceImpl implements AtmService {


    @Override
    public void acceptMoney(Atm atm, Map<Denomination, Long> money) {
        SortedSet<Slot> slots = atm.getSlots();
        for (Denomination denomination : money.keySet()) {
            Slot slot = atm.getSlot(denomination.numericalRepresentation);
            if (slot != null) {
                long summaryMoneyInSlot = Long.sum(slot.getDenominationInt(), money.get(denomination));
                slot.setQuantity(summaryMoneyInSlot);
            } else {
                slots.add(new Slot(denomination, money.get(denomination)));
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
                return issueResponse;
        }
        if (! isDenominationValid(atm, moneyToIssue)) {
            issueResponse.errorCode = 1;
            issueResponse.errorMsg = "запрошенная сумма должна делиться на " + atm.getSlots().last().getDenominationInt();
            return issueResponse;
        }
        SortedSet<Slot> slots = atm.getSlots();

        long restMoneyToIssue = moneyToIssue;
        for (Slot slot : slots) {
            System.out.printf("купюры номиналом %d, количество: %d \n", slot.getDenominationInt(), slot.getQuantity());

            if (slot.getDenominationInt() < restMoneyToIssue) {
                long countOfCache = restMoneyToIssue/slot.getDenominationInt();
                if (restMoneyToIssue - countOfCache*slot.getDenominationInt() == 0) {
                    issueResponse.responseMap.put(slot.getDenomination(), countOfCache);
                    return issueResponse;
                }
//                TODO: если есть остаток
            }
        }
        return issueResponse;

    }

    @Override
    public long getBalance(Atm atm) {
        Set<Slot> slots = atm.getSlots();
        var summaryBalance = 0;
        for (Slot slot : slots) {
            long countOfMoneyInSlot = slot.getDenominationInt() * slot.getQuantity();
            summaryBalance += countOfMoneyInSlot;
        }
        return summaryBalance;
    }

    /**
     * быстрая проверка на достаточность денег
     *
     * @param atm банкомат
     * @param amount запрашиваемая сумма
     * @return false, если в банкомате суммарный баланс меньше чем запрашиваемая сумма
     */
    private boolean isMoneyEnough(Atm atm, long amount) {
        return getBalance(atm) >= amount;
    }

    /**
     *  быстрая проверка на минимальный номинал в банкомате
     *
     * @param atm банкомат
     * @param amount запрашиваемая сумма
     * @return false если минимальный номинал в банкомате больше требуемого в запрашиваемой сумме
     */
    private boolean isDenominationValid(Atm atm, long amount) {
        final SortedSet<Slot> slots = atm.getSlots();
        Slot minimalSlot = slots.last();
        return amount % minimalSlot.getDenominationInt() == 0;
    }
}
