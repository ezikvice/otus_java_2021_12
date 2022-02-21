package ru.dimk;

import org.junit.jupiter.api.Test;
import ru.dimk.model.ATM;
import ru.dimk.model.Denomination;
import ru.dimk.model.SingleDenominationBanknotes;

import java.util.SortedSet;
import java.util.TreeSet;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class ATMTest {

    @Test
    void acceptMoneyTest(){
        SingleDenominationBanknotes fifty = new SingleDenominationBanknotes(Denomination.TEN, 5);
        SingleDenominationBanknotes hundred = new SingleDenominationBanknotes(Denomination.HUNDRED, 1);
        SortedSet money = new TreeSet();
        money.add(fifty);
        money.add(hundred);
        ATM atm = new ATM();
        atm.acceptMoney(money);
        atm.issueMoney(120);
        assertThat(atm.getBalance() == 30);

    }

    @Test
    void issueMoneyTest(){}

}