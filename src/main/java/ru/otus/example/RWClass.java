package ru.otus.example;

public class RWClass {

    private int value;

    public synchronized int read() {
        sleep(1);
        return value;
    }

    public synchronized void write(int value) {
        sleep(50);
        this.value = value;
    }

    public void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
