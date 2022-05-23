package ru.dimk.main;

public class Main {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        TestLogging tst = Ioc.createProxedClass(TestLoggingImpl.class);
        tst.calculation(1);
        System.out.println("-----------------------------");
        tst.calculation(1, "2");
        System.out.println("-----------------------------");
        tst.calculation(1, 2);
        System.out.println("-----------------------------");
        tst.calculation(1, 2, "3");
        System.out.println("-----------------------------");
        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;
        System.out.println("full time: " + timeElapsed);
    }
}
