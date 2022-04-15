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
                return issueResponse;
        }
        if (! isDenominationValid(atm, moneyToIssue)) {
            issueResponse.errorCode = 1;
            issueResponse.errorMsg = "запрошенная сумма должна делиться на " + atm.getSlots().last().getDenomination();
            return issueResponse;
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

    /**
     * быстрая проверка
     * возвращает false, если в банкомате суммарный баланс меньше чем запрашиваемая сумма
     * @param atm
     * @param amount
     * @return
     */
    private boolean isMoneyEnough(Atm atm, long amount) {
        return getBalance(atm) >= amount;
    }

    /**
     *  быстрая проверка
     *  возвращает false если минимальный номинал в банкомате больше требуемого в запрашиваемой сумме
     * @param atm
     * @param amount
     * @return
     */
    private boolean isDenominationValid(Atm atm, long amount) {
        final SortedSet<Slot> slots = atm.getSlots();
        Slot minimalSlot = slots.last();
        return amount % minimalSlot.getDenomination() == 0;
    }
}
