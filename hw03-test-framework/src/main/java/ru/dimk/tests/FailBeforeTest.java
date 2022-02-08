package ru.dimk.tests;

import ru.dimk.annotations.After;
import ru.dimk.annotations.Before;
import ru.dimk.annotations.Test;

public class FailBeforeTest {

    @Before
    public void badInit() {
        throw new RuntimeException();
    }

    @Before
    public void goodInit() {
        System.out.println("before: goodInit runnung");
    }

    @After
    public void finish1() {
        System.out.println("after1 test runnung");
    }

    @After
    public void finish2() {
        System.out.println("after2 test runnung");
    }

    @Test
    public void run1GoodTest() {
        System.out.println("good1 test running");
    }

    @Test
    public void run2GoodTest() {
        System.out.println("good2 test running");
    }

    @Test
    public void run3RuntimeExceptionTest() {
        throw new RuntimeException("oops.. RunTimeException raised");
    }


}
