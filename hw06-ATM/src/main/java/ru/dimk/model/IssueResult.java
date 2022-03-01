package ru.dimk.model;

import java.util.HashMap;
import java.util.Map;

public class IssueResult {

    private Map<Denomination, Long> resultMap;

    public IssueResult() {
        resultMap = new HashMap<>();
    }

    public void addMoneyToSlot(Map<Denomination, Long> money) {
        for (Denomination denomination : money.keySet()) {
            resultMap.put(denomination, money.get(denomination));
        }
    }

    public String toString() {
        System.out.println("Money:");
        long summary = 0;
        for (Denomination denomination : resultMap.keySet()) {
            System.out.println(denomination + ": " + resultMap.get(denomination));
            summary += denomination.numberRepresentation*resultMap.get(denomination);
        }
        return "Summary: " + summary;
    }

}
