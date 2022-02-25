package ru.dimk.test;

import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.dimk.model.ATM;
import ru.dimk.model.Denomination;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class ATMTest {

    @Test
    void acceptMoneyTest(){
        Map<Denomination, Long> money = new HashMap<>();
        money.put(Denomination.TEN, 5L);
        money.put(Denomination.HUNDRED, 1L);
        ATM atm = new ATM();
        atm.acceptMoney(money);
        Assertions.assertEquals(150, atm.getBalance());

    }

    @Test
    void issueMoneyTest(){}

}
