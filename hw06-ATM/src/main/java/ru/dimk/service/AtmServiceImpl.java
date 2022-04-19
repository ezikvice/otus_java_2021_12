package ru.dimk.service;

import ru.dimk.model.Atm;
import ru.dimk.model.Denomination;
import ru.dimk.model.Response;
import ru.dimk.model.Slot;

import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

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
        if (!isMoneyEnough(atm, moneyToIssue)) {
            return new Response(Response.STATUS_ERROR, "Not enough money in the ATM");
        }
        if (!isDenominationValid(atm, moneyToIssue)) {
            return new Response(Response.STATUS_ERROR, "The requested amount must be divisible by"
                    + atm.getSlots().last().getDenominationInt());
        }

        SortedSet<Slot> slots = atm.getSlots();
        long restMoneyToIssue = moneyToIssue; // сколько денег осталось передать в снятие
        Response issueResponse = new Response();

        for (Slot slot : slots) {
            if (slot.getDenominationInt() < restMoneyToIssue) {
                long billsToWithdraw = restMoneyToIssue / slot.getDenominationInt(); // сколько купюр данного номинала надо снять
                if (restMoneyToIssue - slot.getQuantity() * slot.getDenominationInt() >= 0) {
                    restMoneyToIssue -= slot.getQuantity() * slot.getDenominationInt();
                    issueResponse.responseMap.put(slot.getDenomination(), slot.getQuantity());
                } else {
                    restMoneyToIssue -= billsToWithdraw * slot.getDenominationInt();
                    issueResponse.responseMap.put(slot.getDenomination(), billsToWithdraw);
                }
            }
        }
        if (restMoneyToIssue > 0) {
            issueResponse.errorCode = Response.STATUS_ERROR;
            issueResponse.errorMsg = "The amount is not divided by the banknotes available in the ATM";
        }
        Map<Denomination, Long> responseMap = issueResponse.responseMap;
        if (restMoneyToIssue == 0) {
            issueFromAtm(atm, responseMap);
        }
        return issueResponse;
    }

    /**
     * get ATM balance
     *
     * @param atm
     * @return balance of the atm
     */
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
     * @param atm    банкомат
     * @param amount запрашиваемая сумма
     * @return false, если в банкомате суммарный баланс меньше чем запрашиваемая сумма
     */
    private boolean isMoneyEnough(Atm atm, long amount) {
        return getBalance(atm) >= amount;
    }

    /**
     * быстрая проверка на минимальный номинал в банкомате
     *
     * @param atm    банкомат
     * @param amount запрашиваемая сумма
     * @return false если минимальный номинал в банкомате больше требуемого в запрашиваемой сумме
     */
    private boolean isDenominationValid(Atm atm, long amount) {
        final SortedSet<Slot> slots = atm.getSlots();
        Slot minimalSlot = slots.last();
        return amount % minimalSlot.getDenominationInt() == 0;
    }

    private void issueFromAtm(Atm atm, Map<Denomination, Long> responseMap) {
        for (Denomination denomination : responseMap.keySet()) {
            Slot slot = atm.getSlot(denomination.numericalRepresentation);
            slot.setQuantity(slot.getQuantity() - responseMap.get(denomination));
        }
    }
}
