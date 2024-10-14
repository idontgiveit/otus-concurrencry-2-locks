package ru.otus.example;

public class Waiting {
    public static void main(String[] args) {
        int[] ctr = new int[1];
        ctr[0] = 0;
        int threadNum = 100;
        Object monitor = new Object();

        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized (monitor) {
                        ctr[0] ++;
                        try {
                            Thread.sleep(10);
                            System.out.println("Started " + ctr[0]);
                            if (ctr[0] < threadNum) {
                                monitor.wait();
                            } else {
                                monitor.notifyAll();
                            }

                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }

                    }

                }
            }).start();
        }

        synchronized (monitor) {
            try {
                monitor.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("Threads finished");


    }
}
