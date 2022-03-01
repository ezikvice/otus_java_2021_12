package ru.dimk.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.dimk.model.ATM;
import ru.dimk.service.ATMService;
import ru.dimk.model.Denomination;
import ru.dimk.service.ATMServiceImpl;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class ATMServiceTest {

    @Test
    void acceptMoneyTest(){
        Map<Denomination, Long> money = new HashMap<>();
        money.put(Denomination.TEN, 5L);
        money.put(Denomination.HUNDRED, 1L);
        ATM atm = new ATM(money);
        ATMService atmService = new ATMServiceImpl();
        atmService.acceptMoney(atm, money);
        Assertions.assertEquals(150, atmService.getBalance(atm));
    }



    @Test
    void issueMoneyTest(){}

}
