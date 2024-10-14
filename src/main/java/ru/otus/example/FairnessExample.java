package ru.otus.example;

import java.util.concurrent.locks.ReentrantLock;

public class FairnessExample {
    public static void main(String[] args) throws Exception{
        int threadsNum = 100;

        ReentrantLock lock = new ReentrantLock(true);

        for(int i = 0; i < threadsNum; i ++) {
            int ctr = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("try " + ctr);
                    lock.lock();
                    System.out.println("acquire " + ctr);
                    lock.unlock();

                }
            }).start();
        }



        Thread.sleep(10000);


    }
}

