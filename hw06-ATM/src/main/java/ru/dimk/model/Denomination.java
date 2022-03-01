package ru.dimk.model;

import java.util.Comparator;

public enum Denomination {

    TEN(10),
    FIFTY(50),
    HUNDRED(100),
    TWO_HUNDRED(200),
    FIVE_HUNDRED(500),
    THOUSAND(1000),
    TWO_THOUSAND(2000),
    FIVE_THOUSAND(5000);

    public final int numberRepresentation;

    Denomination(int numberRepresentation){
        this.numberRepresentation = numberRepresentation;
    }

    public class DenominationComparator implements Comparator<Denomination>
    {
        public int compare(Denomination o1, Denomination o2)
        {
            return o1.numberRepresentation - o2.numberRepresentation;
        }
    }
}
