package ru.dimk.model;

import java.util.*;

public class Atm {
    SortedSet<Slot> slots;

    public Atm(){
        slots = new TreeSet<Slot>();
    }

    /**
     * В общем случае в параметрах может передаваться не SortedMap денег
      * @param initialSlots
     */
    public Atm(Map<Denomination, Long> initialSlots){
        this();
        for (Denomination denomination : initialSlots.keySet()) {
            Slot s = new Slot(denomination.numberRepresentation, 0);
            slots.add(s);
        }
    }

    public SortedSet<Slot> getSlots(){
        return slots;
    }
}
