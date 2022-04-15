package ru.dimk.model;

import java.util.*;

public class Atm {
    SortedSet<Slot> slots;

    public Atm() {
        slots = new TreeSet<>();
    }

    /**
     * В общем случае в параметрах может передаваться не SortedMap денег
     *
     * @param initialSlots начальные значения слотовы
     */
    public Atm(Map<Denomination, Long> initialSlots) {
        this();
        for (Denomination denomination : initialSlots.keySet()) {
            Slot s = new Slot(denomination, initialSlots.get(denomination));
            slots.add(s);
        }
    }

    public SortedSet<Slot> getSlots() {
        return slots;
    }

    public Slot getSlot(int denomination) {
        for (Slot slot : slots) {
            if(denomination == slot.getDenominationInt()) {
                return slot;
            }
        }
        return null;
    }
}
