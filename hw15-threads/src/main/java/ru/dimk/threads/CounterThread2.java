package ru.dimk.threads;

public class CounterThread2 extends Thread{

    public CounterThread2() {
        super();
    }

    @Override
    public synchronized void start() {
        for (int i = 1; i <= 10 ; i++) {
            try {
                System.out.println("thread2: " + i);
                notifyAll();
            } catch (Exception e) {
                Thread.currentThread().interrupt();
            }
        }
        super.start();
    }
}
