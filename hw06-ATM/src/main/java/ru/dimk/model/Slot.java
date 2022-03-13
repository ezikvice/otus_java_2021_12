package ru.dimk.model;

public class Slot {
    Denomination denomination;
    long quantity;
    int position;

    public Slot(Denomination denomination, long quantity, int position) {
        this.denomination = denomination;
        this.quantity = quantity;
        this.position = position;
    }

    public Slot() {
    }
}
