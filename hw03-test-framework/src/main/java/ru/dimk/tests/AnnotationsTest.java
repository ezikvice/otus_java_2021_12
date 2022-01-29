package ru.dimk.tests;

import ru.dimk.annotations.After;
import ru.dimk.annotations.Before;
import ru.dimk.annotations.Test;

public class AnnotationsTest {

    @Before
    public void init() {
        System.out.println("before test");
    }

    @Test
    public void runOk() {
        System.out.println("ok!");
    }

    @Test
    public void exceptionFail() {
        throw new RuntimeException("oops..");
    }

    @After
    public void finish() {
        System.out.println("after test");
    }


}
