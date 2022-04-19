package ru.dimk.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.dimk.model.Atm;
import ru.dimk.model.Denomination;
import ru.dimk.model.Response;
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
    @DisplayName("Со счета 150 снимают 310, выдается ошибка")
    void issueMoneyTest1() {
        Map<Denomination, Long> money = new HashMap<>();
        money.put(Denomination.TEN, 5L);
        money.put(Denomination.HUNDRED, 1L);
        Atm atm = new Atm(money);
        AtmService atmService = new AtmServiceImpl();
        Response resp = atmService.issueMoney(atm, 310);
        Assertions.assertEquals(Response.STATUS_ERROR, resp.errorCode);
        Assertions.assertEquals(150, atmService.getBalance(atm));
    }

    @Test
    @DisplayName("Со счета 150 снимают 123, выдается ошибка")
    void issueMoneyTest2() {
        Map<Denomination, Long> money = new HashMap<>();
        money.put(Denomination.TEN, 5L);
        money.put(Denomination.HUNDRED, 1L);
        Atm atm = new Atm(money);
        AtmService atmService = new AtmServiceImpl();
        Response resp = atmService.issueMoney(atm, 123);
        Assertions.assertEquals(Response.STATUS_ERROR, resp.errorCode);
        Assertions.assertEquals(150, atmService.getBalance(atm));
    }

    @Test
    @DisplayName("Со счета 150 снимают 30, остаток 120")
    void issueMoneyTest3() {
        Map<Denomination, Long> money = new HashMap<>();
        money.put(Denomination.TEN, 5L);
        money.put(Denomination.HUNDRED, 1L);
        Atm atm = new Atm(money);
        AtmService atmService = new AtmServiceImpl();
        Response resp = atmService.issueMoney(atm, 30);
        Map<Denomination, Long> expectedMap = new HashMap<>();
        expectedMap.put(Denomination.TEN, 3L);
        Assertions.assertEquals(Response.STATUS_OK, resp.errorCode);
        Assertions.assertEquals(expectedMap, resp.responseMap);
        Assertions.assertEquals(120, atmService.getBalance(atm));
    }

    @Test
    @DisplayName("Со счета 150 снимают 120, остаток 30")
    void issueMoneyTest4() {
        Map<Denomination, Long> money = new HashMap<>();
        money.put(Denomination.TEN, 5L);
        money.put(Denomination.HUNDRED, 1L);
        Atm atm = new Atm(money);
        AtmService atmService = new AtmServiceImpl();
        Response resp = atmService.issueMoney(atm,  120);
        Map<Denomination, Long> expectedMap = new HashMap<>();
        expectedMap.put(Denomination.HUNDRED, 1L);
        expectedMap.put(Denomination.TEN, 2L);
        Assertions.assertEquals(Response.STATUS_OK, resp.errorCode);
        Assertions.assertEquals(expectedMap, resp.responseMap);
        Assertions.assertEquals(30, atmService.getBalance(atm));
    }

    @Test
    @DisplayName("Со счета 150 снимают 70, результат ошибка")
    void issueMoneyTest5() {
        Map<Denomination, Long> money = new HashMap<>();
        money.put(Denomination.TEN, 5L);
        money.put(Denomination.HUNDRED, 1L);
        Atm atm = new Atm(money);
        AtmService atmService = new AtmServiceImpl();
        Response resp = atmService.issueMoney(atm,  70);
        Assertions.assertEquals(Response.STATUS_ERROR, resp.errorCode);
        Assertions.assertEquals(150, atmService.getBalance(atm));
    }

    @Test
    @DisplayName("Со счета 500 снимают 500, результат 0")
    void issueMoneyTest6() {
        Map<Denomination, Long> money = new HashMap<>();
        money.put(Denomination.TEN, 5L);
        money.put(Denomination.FIFTY, 1L);
        money.put(Denomination.HUNDRED, 2L);
        money.put(Denomination.TWO_HUNDRED, 1L);
        Atm atm = new Atm(money);
        AtmService atmService = new AtmServiceImpl();

        Map<Denomination, Long> expectedMap = new HashMap<>();
        expectedMap.put(Denomination.TEN, 5L);
        expectedMap.put(Denomination.FIFTY, 1L);
        expectedMap.put(Denomination.HUNDRED, 2L);
        expectedMap.put(Denomination.TWO_HUNDRED, 1L);
        Response resp = atmService.issueMoney(atm,  500);
        Assertions.assertEquals(Response.STATUS_OK, resp.errorCode);
        Assertions.assertEquals(expectedMap, resp.responseMap);
        Assertions.assertEquals(0, atmService.getBalance(atm));
    }

}
