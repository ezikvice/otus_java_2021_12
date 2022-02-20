package ru.dimk.model;

public class SingleDenominationBanknotes {
    Denomination denomination;
    long amount;

    public SingleDenominationBanknotes(Denomination denomination, long amount) {
        this.denomination = denomination;
        this.amount = amount;
    }

}
