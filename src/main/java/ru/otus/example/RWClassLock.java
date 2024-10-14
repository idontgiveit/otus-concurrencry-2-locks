package ru.otus.example;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class RWClassLock {

    private int value;

    private ReentrantLock reentrantLock = new ReentrantLock();

    public int read() {

        try {
            boolean locked = reentrantLock.tryLock(100, TimeUnit.MILLISECONDS);
            if (!locked) {
                System.out.println("Not waiting");
                return value;
            }
            sleep(1);
            reentrantLock.unlock();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return value;
    }

    public void write(int value) {
        try {
            reentrantLock.lock();
            sleep(50);
        } finally {
            reentrantLock.unlock();
        }
    }

    public void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
