package ru.pattern.creational.singletone.example2;

public class LazySingleThreadNumberGenerator {

	private static LazySingleThreadNumberGenerator INSTANCE;

	private int serial = 0;

	public static LazySingleThreadNumberGenerator getInstance() {
		if (INSTANCE == null) {
			
			INSTANCE = new LazySingleThreadNumberGenerator();
		}
		return INSTANCE;
	}

	public int getNextSerial() {
		return ++serial;
	}
}