package ru.pattern.creational.singletone.example2;

import java.util.concurrent.atomic.AtomicInteger;

public class BrokenLazyMultipleNumberGenerator {

    private static BrokenLazyMultipleNumberGenerator INSTANCE;

    private AtomicInteger serial = new AtomicInteger(0);

    public static BrokenLazyMultipleNumberGenerator getInstance() {

        if (INSTANCE == null) {
            synchronized (BrokenLazyMultipleNumberGenerator.class) {
                if (INSTANCE == null) {
                    INSTANCE = new BrokenLazyMultipleNumberGenerator();
                }
            }
        }
        return INSTANCE;
    }

    public int getNextSerial() {
        return serial.incrementAndGet();
    }
}
