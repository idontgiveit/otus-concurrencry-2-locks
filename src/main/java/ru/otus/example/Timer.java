package ru.otus.example;

public class Timer {
    private static long time;

    public static void stamp() {
        time = System.nanoTime();
    }

    public static void print() {
        time = System.nanoTime() - time;
        System.out.println(time / 1000 + "mcs " + time / 1000000 + "ms");
    }


}
