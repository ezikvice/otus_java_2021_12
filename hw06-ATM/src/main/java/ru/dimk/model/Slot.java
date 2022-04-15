package ru.dimk.model;

public class Slot implements Comparable {
    private int denomination;
    private long quantity;

    /**
     *
     * @param denomination
     * @param quantity
     */
    public Slot(int denomination, long quantity) {
        this.denomination = denomination;
        this.quantity = quantity;
    }

    public int getDenomination() {
        return denomination;
    }

    public void setDenomination(int denomination) {
        this.denomination = denomination;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public long getSum() {
        return this.quantity*this.denomination;
    }

    /**
     * хотим обеспечить порядок от большего номинала к меньшему
     *
     * @param o the object to be compared.
     * @return
     */
    @Override
    public int compareTo(Object o) {
        if(o instanceof Slot){
            Slot s = (Slot) o;
            return s.denomination - denomination;
        }
        return -1;
    }
}
