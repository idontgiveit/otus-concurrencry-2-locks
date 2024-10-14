package ru.otus.example;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

public class RWRunner {
    public static void main(String[] args) throws Exception {

        RWClass rwClass = new RWClass();
//        RWClassLock rwClass = new RWClassLock();
//        RWClassLockRW rwClass = new RWClassLockRW();

        int threadNumRead = 300;
        int threadNumWrite = 10;

        Thread[] readThreads = new Thread[threadNumRead];
        Thread[] writerThreads = new Thread[threadNumWrite];

        CountDownLatch latch = new CountDownLatch(threadNumWrite + threadNumRead);

        for (int i = 0; i < threadNumRead; i++) {
            readThreads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    rwClass.read();
                    System.out.println(Thread.currentThread().getName() + " Read ");
                    latch.countDown();
                }
            });
        }

        for (int i = 0; i < threadNumWrite; i++) {
            writerThreads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    rwClass.write(10);
                    System.out.println(Thread.currentThread().getName() + " write ");
                    latch.countDown();
                }
            });
        }

        Timer.stamp();

        Arrays.stream(readThreads).forEach(Thread::start);
        Arrays.stream(writerThreads).forEach(Thread::start);

        latch.await();

        Timer.print();

    }
}
