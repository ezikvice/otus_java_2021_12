package ru.dimk.test;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class AnnotationsTest {

    @BeforeEach
    public void init() {
        System.out.println("before1 ok");
    }

    @BeforeEach
    public void init2() {
        System.out.println("before2 ok");
    }

    @AfterEach
    public void finish1() {
        System.out.println("after1 ok");
    }

    @AfterEach
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
    @Disabled
    public void run3RuntimeExceptionTest() {
        throw new RuntimeException("oops.. RunTimeException raised");
    }


}
