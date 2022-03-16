package ru.dimk.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.dimk.model.Atm;
import ru.dimk.model.Denomination;
import ru.dimk.service.AtmService;
import ru.dimk.service.AtmServiceImpl;

import java.util.HashMap;
import java.util.Map;


public class AtmServiceTest {

    @Test
    @DisplayName("создали АТМ, в котором сто и пятьдесят, проверили баланс = 150")
    void AtmInitTest() {
        Map<Denomination, Long> money = new HashMap<>();
        money.put(Denomination.TEN, 5L);
        money.put(Denomination.HUNDRED, 1L);
        Atm atm = new Atm(money);
        AtmService atmService = new AtmServiceImpl();
        Assertions.assertEquals(150, atmService.getBalance(atm));
    }

    @Test
    @DisplayName("На пустой счет положили сто и пятьдесят, проверили баланс = 150")
    void acceptMoneyTest() {
        Map<Denomination, Long> money = new HashMap<>();
        money.put(Denomination.TEN, 5L);
        money.put(Denomination.HUNDRED, 1L);
        Atm atm = new Atm();
        AtmService atmService = new AtmServiceImpl();
        atmService.acceptMoney(atm, money);
        Assertions.assertEquals(150, atmService.getBalance(atm));
    }

    @Test
    @DisplayName("Со счета 150 сняли 30, проверили баланс = 120")
    void issueMoneyTest1() {
        Map<Denomination, Long> money = new HashMap<>();
        money.put(Denomination.TEN, 5L);
        money.put(Denomination.HUNDRED, 1L);
        Atm atm = new Atm(money);
        AtmService atmService = new AtmServiceImpl();
        atmService.issueMoney(atm, 30);
        Assertions.assertEquals(120, atmService.getBalance(atm));
    }

}
