package ru.dimk.model;

public class Slot implements Comparable {
    private Denomination denomination;
    private long quantity;

    /**
     *
     * @param denomination номинал купюр
     * @param quantity количество купюр в слоте банкомата
     */
    public Slot(Denomination denomination, long quantity) {
        this.denomination = denomination;
        this.quantity = quantity;
    }

    public int getDenominationInt() {
        return denomination.numericalRepresentation;
    }

    public Denomination getDenomination() {
        return denomination;
    }

    public void setDenomination(Denomination denomination) {
        this.denomination = denomination;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public long getSum() {
        return this.quantity*this.denomination.numericalRepresentation;
    }

    /**
     * хотим обеспечить порядок от большего номинала к меньшему
     *
     * @param o the object to be compared.
     */
    @Override
    public int compareTo(Object o) {
        if(o instanceof Slot s){
            return s.denomination.numericalRepresentation - denomination.numericalRepresentation;
        }
        return -1;
    }
}