package ru.dimk.main;

public class Main {
    public static void main(String[] args) {
        TestLogging tst = Ioc.createProxedClass(TestLoggingImpl.class);
        tst.calculation(1);
        tst.calculation(1, "2");
        tst.calculation(1, 2);
        tst.calculation(1, 2, "3");
    }
}
