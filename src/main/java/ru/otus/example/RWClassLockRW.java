package ru.otus.example;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class RWClassLockRW {

    private int value;

    private ReentrantReadWriteLock reentrantLock = new ReentrantReadWriteLock();

    public int read() {

            boolean locked = reentrantLock.readLock().tryLock();
            sleep(1);
            reentrantLock.readLock().unlock();

        return value;
    }

    public void write(int value) {
        try {
            reentrantLock.writeLock().lock();
            sleep(50);
        } finally {
            reentrantLock.writeLock().unlock();
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
