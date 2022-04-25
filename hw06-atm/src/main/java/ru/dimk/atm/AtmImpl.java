package ru.dimk.atm;

import java.util.*;

public class AtmImpl implements Atm {
    private final SortedSet<Slot> slots;

    public AtmImpl() {
        slots = new TreeSet<>();
    }

    /**
     * В общем случае в параметрах может передаваться не SortedMap денег
     *
     * @param initialSlots начальные значения слотов
     */
    public AtmImpl(Map<Denomination, Long> initialSlots) {
        this();
        for (Denomination denomination : initialSlots.keySet()) {
            Slot s = new Slot(denomination, initialSlots.get(denomination));
            slots.add(s);
        }
    }

    @Override
    public void acceptMoney(Map<Denomination, Long> money) {
        for (Denomination denomination : money.keySet()) {
            Slot slot = getSlot(denomination.numericalRepresentation);
            if (slot != null) {
                long summaryMoneyInSlot = Long.sum(slot.getDenominationInt(), money.get(denomination));
                slot.setQuantity(summaryMoneyInSlot);
            } else {
                // "добавлять новую кассету для несуществующего номинала кажется комнительной практикой."
                // TODO: по хорошему, надо отдавать невалидные деньги обратно. подумать как это делать
                slots.add(new Slot(denomination, money.get(denomination)));
            }
        }
    }

    @Override
    public Response issueMoney(long moneyToIssue) {
        if (!isMoneyEnough(moneyToIssue)) {
            return new Response(Response.STATUS_ERROR, "Not enough money in the ATM");
        }
        if (!isDenominationValid(moneyToIssue)) {
            return new Response(Response.STATUS_ERROR, "The requested amount must be divisible by"
                    + slots.last().getDenominationInt());
        }

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
            issueFromAtm(responseMap);
        }
        return issueResponse;
    }

    /**
     * get ATM balance
     *
     * @return balance of the atm
     */
    @Override
    public long getBalance() {
        var summaryBalance = 0;
        for (Slot slot : slots) {
            long countOfMoneyInSlot = slot.getDenominationInt() * slot.getQuantity();
            summaryBalance += countOfMoneyInSlot;
        }
        return summaryBalance;
    }

    public Slot getSlot(int denomination) {
        for (Slot slot : slots) {
            if(denomination == slot.getDenominationInt()) {
                return slot;
            }
        }
        return null;
    }

    /**
     * быстрая проверка на достаточность денег
     *
     * @param amount запрашиваемая сумма
     * @return false, если в банкомате суммарный баланс меньше чем запрашиваемая сумма
     */
    private boolean isMoneyEnough(long amount) {
        return getBalance() >= amount;
    }

    /**
     * быстрая проверка на минимальный номинал в банкомате
     *
     * @param amount запрашиваемая сумма
     * @return false если минимальный номинал в банкомате больше требуемого в запрашиваемой сумме
     */
    private boolean isDenominationValid(long amount) {
        Slot minimalSlot = slots.last();
        return amount % minimalSlot.getDenominationInt() == 0;
    }

    private void issueFromAtm(Map<Denomination, Long> responseMap) {
        for (Denomination denomination : responseMap.keySet()) {
            Slot slot = getSlot(denomination.numericalRepresentation);
            slot.setQuantity(slot.getQuantity() - responseMap.get(denomination));
        }
    }
}
