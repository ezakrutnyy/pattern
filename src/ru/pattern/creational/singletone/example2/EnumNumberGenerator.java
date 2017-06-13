package ru.pattern.creational.singletone.example2;

import java.util.concurrent.atomic.AtomicInteger;

public enum EnumNumberGenerator {

	INSTANCE;
	
	private AtomicInteger serial = new AtomicInteger(0);
	
	public int getNextSerial() {
		return serial.incrementAndGet();
	}
}
