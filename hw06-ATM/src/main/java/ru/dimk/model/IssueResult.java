package ru.dimk.model;

import java.util.Map;

public class IssueResult {

    private Map<Denomination, Long> resultMap;

    public void addAmountToSlot(SingleDenominationBanknotes bill) {
        resultMap.put(bill.denomination, bill.amount);
    }

    public String toString() {
        return "";
    }

}
