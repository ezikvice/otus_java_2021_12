package ru.dimk.tests;

import ru.dimk.annotations.After;
import ru.dimk.annotations.Before;
import ru.dimk.annotations.Test;

public class AnnotationsTest {

    @Before
    public void init() {
        System.out.println("before1 ok");
    }

    @Before
    public void init2() {
        System.out.println("before2 ok");
    }

    @After
    public void finish1() {
        System.out.println("after1 ok");
    }

    @After
    public void finish2() {
        System.out.println("after2 ok");
    }

    @Test
    public void run1GoodTest() {
        System.out.println("good1 test ok");
    }

    @Test
    public void run2GoodTest() {
        System.out.println("good2 test ok");
    }

    @Test
    public void run3RuntimeExceptionTest() {
        throw new RuntimeException("oops.. RunTimeException raised");
    }


}
