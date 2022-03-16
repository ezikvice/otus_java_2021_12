package ru.dimk.model;

public class Slot implements Comparable {
    int denomination;
    long quantity;

    public Slot(int denomination, long quantity) {
        this.denomination = denomination;
        this.quantity = quantity;
    }

    public Slot() {
    }

    @Override
    public int compareTo(Object o) {
        if(o instanceof Slot){
            Slot s = (Slot) o;
            return denomination - s.denomination;
        }
        return -1;
    }
}
