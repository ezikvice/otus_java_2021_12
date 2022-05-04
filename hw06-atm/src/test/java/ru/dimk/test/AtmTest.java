package ru.dimk.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.dimk.atm.AtmImpl;
import ru.dimk.atm.Denomination;
import ru.dimk.atm.Response;

import java.util.HashMap;
import java.util.Map;


public class AtmTest {

    @Test
    @DisplayName("создали АТМ, в котором сто и пятьдесят, проверили баланс = 150")
    void AtmInitTest() {
        Map<Denomination, Long> money = new HashMap<>();
        money.put(Denomination.TEN, 5L);
        money.put(Denomination.HUNDRED, 1L);
        AtmImpl atm = new AtmImpl(money);
        Assertions.assertEquals(150, atm.getBalance());
    }

    @Test
    @DisplayName("На пустой счет положили сто и пятьдесят, проверили баланс = 150")
    void acceptMoneyTest() {
        Map<Denomination, Long> money = new HashMap<>();
        money.put(Denomination.TEN, 5L);
        money.put(Denomination.HUNDRED, 1L);
        AtmImpl atm = new AtmImpl();
        atm.acceptMoney(money);
        Assertions.assertEquals(150, atm.getBalance());
    }

    @Test
    @DisplayName("Со счета 150 снимают 310, выдается ошибка")
    void issueMoneyTest1() {
        Map<Denomination, Long> money = new HashMap<>();
        money.put(Denomination.TEN, 5L);
        money.put(Denomination.HUNDRED, 1L);
        AtmImpl atm = new AtmImpl(money);
        Response resp = atm.issueMoney(310);
        Assertions.assertEquals(Response.STATUS_ERROR, resp.errorCode);
        Assertions.assertEquals(150, atm.getBalance());
    }

    @Test
    @DisplayName("Со счета 150 снимают 123, выдается ошибка")
    void issueMoneyTest2() {
        Map<Denomination, Long> money = new HashMap<>();
        money.put(Denomination.TEN, 5L);
        money.put(Denomination.HUNDRED, 1L);
        AtmImpl atm = new AtmImpl(money);
        Response resp = atm.issueMoney(123);
        Assertions.assertEquals(Response.STATUS_ERROR, resp.errorCode);
        Assertions.assertEquals(150, atm.getBalance());
    }

    @Test
    @DisplayName("Со счета 150 снимают 30, остаток 120")
    void issueMoneyTest3() {
        Map<Denomination, Long> money = new HashMap<>();
        money.put(Denomination.TEN, 5L);
        money.put(Denomination.HUNDRED, 1L);
        AtmImpl atm = new AtmImpl(money);
        Response resp = atm.issueMoney(30);
        Map<Denomination, Long> expectedMap = new HashMap<>();
        expectedMap.put(Denomination.TEN, 3L);
        Assertions.assertEquals(Response.STATUS_OK, resp.errorCode);
        Assertions.assertEquals(expectedMap, resp.responseMap);
        Assertions.assertEquals(120, atm.getBalance());
    }

    @Test
    @DisplayName("Со счета 150 снимают 120, остаток 30")
    void issueMoneyTest4() {
        Map<Denomination, Long> money = new HashMap<>();
        money.put(Denomination.TEN, 5L);
        money.put(Denomination.HUNDRED, 1L);
        AtmImpl atm = new AtmImpl(money);
        Response resp = atm.issueMoney(120);
        Map<Denomination, Long> expectedMap = new HashMap<>();
        expectedMap.put(Denomination.HUNDRED, 1L);
        expectedMap.put(Denomination.TEN, 2L);
        Assertions.assertEquals(Response.STATUS_OK, resp.errorCode);
        Assertions.assertEquals(expectedMap, resp.responseMap);
        Assertions.assertEquals(30, atm.getBalance());
    }

    @Test
    @DisplayName("Со счета 150 снимают 70, выдается ошибка")
    void issueMoneyTest5() {
        Map<Denomination, Long> money = new HashMap<>();
        money.put(Denomination.TEN, 5L);
        money.put(Denomination.HUNDRED, 1L);
        AtmImpl atm = new AtmImpl(money);
        Response resp = atm.issueMoney(70);
        Assertions.assertEquals(Response.STATUS_ERROR, resp.errorCode);
        Assertions.assertEquals(150, atm.getBalance());
    }

    @Test
    @DisplayName("Со счета 500 снимают 500, остаток 0")
    void issueMoneyTest6() {
        Map<Denomination, Long> money = new HashMap<>();
        money.put(Denomination.TEN, 5L);
        money.put(Denomination.FIFTY, 1L);
        money.put(Denomination.HUNDRED, 2L);
        money.put(Denomination.TWO_HUNDRED, 1L);
        AtmImpl atm = new AtmImpl(money);

        Map<Denomination, Long> expectedMap = new HashMap<>();
        expectedMap.put(Denomination.TEN, 5L);
        expectedMap.put(Denomination.FIFTY, 1L);
        expectedMap.put(Denomination.HUNDRED, 2L);
        expectedMap.put(Denomination.TWO_HUNDRED, 1L);
        Response resp = atm.issueMoney(500);
        Assertions.assertEquals(Response.STATUS_OK, resp.errorCode);
        Assertions.assertEquals(expectedMap, resp.responseMap);
        Assertions.assertEquals(0, atm.getBalance());
    }

}
