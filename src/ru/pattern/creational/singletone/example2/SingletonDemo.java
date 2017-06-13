package ru.pattern.creational.singletone.example2;

import ru.pattern.creational.singletone.example2.LazyMultipleThreadNumberGenerator;

public class SingletonDemo {

	public static void main(String[] args) {

    //SimpleNumberGenerator generator = SimpleNumberGenerator.getInstance();//INSTANCE;//.getNextSerial();
	//LazySingleThreadNumberGenerator generator = LazySingleThreadNumberGenerator.getInstance();//INSTANCE;//.getNextSerial();

	//EnumNumberGenerator generator = EnumNumberGenerator.INSTANCE;//.getNextSerial();
	
	LazyMultipleThreadNumberGenerator generator = LazyMultipleThreadNumberGenerator.getInstance();//INSTANCE;//.getNextSerial();

		for (int i = 0; i < 10; i++) {

			System.out.println("Obtained next serial number: "
					+ generator.getNextSerial());
		}
	}
}