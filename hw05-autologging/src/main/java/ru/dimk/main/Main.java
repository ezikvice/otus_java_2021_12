package ru.dimk.main;

public class Main {
    public static void main(String[] args) {
        TestLoggingImpl tst = new TestLoggingImpl();
        tst.calculation(1);
        tst.calculation(1, "2");

    }
}
