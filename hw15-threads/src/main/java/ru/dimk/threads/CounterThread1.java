package ru.dimk.threads;

public class CounterThread1 extends Thread{

    public CounterThread1() {
        super();
    }

    @Override
    public synchronized void start() {
        for (int i = 1; i <= 10 ; i++) {
            System.out.println("thread1: " + i);
            notifyAll();
            try {
            } catch (Exception e) {
                Thread.currentThread().interrupt();
            }
        }
        super.start();
    }
}
