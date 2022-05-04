package ru.dimk.atm;

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

    /**
     * хотим обеспечить порядок от большего номинала к меньшему, поэтому сравниваем в обратном порядке
     *
     * @param o the object to be compared.
     */
    @Override
    public int compareTo(Object o) {
        if(o instanceof Slot s){
            Integer my = denomination.numericalRepresentation;
            Integer other = s.denomination.numericalRepresentation;
            return other.compareTo(my) ;
        }
        return -1;
    }
}
