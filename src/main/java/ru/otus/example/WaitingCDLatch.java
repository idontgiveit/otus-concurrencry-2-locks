package ru.otus.example;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class WaitingCDLatch {
    public static void main(String[] args) throws Exception  {

        int[] ctr = new int[1];
        ctr[0] = 0;
        int threadNum = 100;
        Object monitor = new Object();

        CountDownLatch latch = new CountDownLatch(threadNum);

        for (int i = 0; i < threadNum; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized (monitor) {
                        ctr[0] ++;
                        try {
                            Thread.sleep(10);
                            System.out.println("Started " + ctr[0]);
                            latch.countDown();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }).start();
        }

        boolean result = latch.await(5, TimeUnit.SECONDS);
        System.out.println("DONE " + result);

    }
}
