package ru.dimk.model;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class ATM {
    SortedMap <Denomination, Long> slots;

    public ATM(){
        slots = new TreeMap<>();
    }

    /**
     * В общем случае в параметрах может передаваться не SortedMap денег
      * @param initialSlots
     */
    public ATM(Map<Denomination, Long> initialSlots){
        this();
        for (Denomination denomination : initialSlots.keySet()) {
            slots.put(denomination, initialSlots.get(denomination));
        }
    }

    public Map<Denomination, Long> getSlots(){
        return slots;
    }
}
