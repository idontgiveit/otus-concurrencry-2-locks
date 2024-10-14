package ru.otus.example;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class SemaphoreExample {
    public static void main(String[] args) throws Exception{
        int threadNum = 100;

        AtomicInteger ctr = new AtomicInteger(0);

        Semaphore semaphore = new Semaphore(1);

        for (int i = 0; i < threadNum; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                        int threads = ctr.incrementAndGet();
                        System.out.println(threads);
                        doWork();
                        ctr.decrementAndGet();
                        semaphore.release();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }).start();

        }

        Thread.sleep(10000);
    }

    public static void doWork() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
