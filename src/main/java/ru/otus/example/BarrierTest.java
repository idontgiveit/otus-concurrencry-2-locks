package ru.otus.example;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class BarrierTest {
    public static void main(String[] args) throws Exception {

        int threadNum = 10;

        CyclicBarrier barrier = new CyclicBarrier(threadNum, new Runnable() {
            @Override
            public void run() {
                System.out.println("Barrier done");
            }
        });

        for (int i = 0; i < threadNum; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {

                        System.out.println("Собрали белье");
                        barrier.await();
                        System.out.println("Постирал белье");
                        Thread.sleep(10);
                        barrier.await();
                        System.out.println("Высушил белье");
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    } catch (BrokenBarrierException e1) {

                    }
                }
            }).start();
        }

        Thread.sleep(10000);

    }

    static void doWork() {

    }
}
