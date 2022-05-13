package ru.dimk.threads;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Threads {
    private static final Logger logger = LoggerFactory.getLogger(Threads.class);

    public static void main(String[] args) {
        CounterThread1 t1 = new CounterThread1();
        CounterThread2 t2 = new CounterThread2();
        t1.start();
        t2.start();
    }

}
