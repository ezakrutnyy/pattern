package ru.pattern.creational.singletone;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CivilizationGodRunner {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 1000; i++) {
            executor.execute(CivilizationGod::getInstance);
        }
        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);
        System.out.println(CivilizationGod.getCount());
    }
}

class CivilizationGod {

    private static int count = 0;

    private static final Object lock = new Object();

    private static volatile CivilizationGod god;

    private CivilizationGod() {
        count++;
    }

    public static CivilizationGod getInstance() {
        if (god == null) {
            synchronized (lock) {
                if (god == null) {
                    god = new CivilizationGod();
                }
            }
        }
        return god;
    }

    public static int getCount() {
        return count;
    }
}
