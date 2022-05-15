package ru.dimk.threads;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.lang.Thread.sleep;

// Основной каркас - класс PingPong с вебинара (с точностью до названий класса и переменных)
public class UpDownCounter {
    private static final Logger logger = LoggerFactory.getLogger(UpDownCounter.class);
    private String currentThread = "thread1";

    private synchronized void action(String message) {
        final boolean UP = true;
        final boolean DOWN = false;

        boolean direction = UP;

        int counter = 0;
        while(!Thread.currentThread().isInterrupted()) {
            try {
                //spurious wakeup https://en.wikipedia.org/wiki/Spurious_wakeup
                //поэтому не if
                while (currentThread.equals(message)) {
                    this.wait();
                }

                if(counter==10) direction = DOWN;
                if(counter==1) direction = UP;
                counter = direction ? counter + 1 : counter-1;
                currentThread = message;
                logger.info(String.format("%1s: %2d", currentThread, counter));

                sleep();
                notifyAll();
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {
        UpDownCounter upDownCounter = new UpDownCounter();
        new Thread(() -> upDownCounter.action("thread0")).start();
        new Thread(() -> upDownCounter.action("thread1")).start();
    }

    private static void sleep() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
}
