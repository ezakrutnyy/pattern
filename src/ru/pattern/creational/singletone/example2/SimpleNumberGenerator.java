package ru.pattern.creational.singletone.example2;

public class SimpleNumberGenerator {

	private static final SimpleNumberGenerator INSTANCE = new SimpleNumberGenerator();

	private int serial;

	private SimpleNumberGenerator() {
		serial = 0;
	}

	public int getNextSerial() {
		return ++serial;
	}

	public static SimpleNumberGenerator getInstance() {
		return INSTANCE;
	}
}