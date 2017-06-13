package ru.pattern.creational.singletone.example2;

import java.util.concurrent.atomic.AtomicInteger;

public class LazyMultipleThreadNumberGenerator {

	private AtomicInteger serial = new AtomicInteger(0);

	private static final class Holder {
		private static final LazyMultipleThreadNumberGenerator INSTANCE = new LazyMultipleThreadNumberGenerator();
	}

	/**
	 * Returns the single instance of this class. This method is thread-safe
	 * without any synchronization needed.
	 */
	public static LazyMultipleThreadNumberGenerator getInstance() {
		return LazyMultipleThreadNumberGenerator.Holder.INSTANCE;
	}

	public int getNextSerial() {
		return serial.getAndIncrement();
	}
}
