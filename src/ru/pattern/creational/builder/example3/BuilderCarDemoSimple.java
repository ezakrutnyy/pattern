package ru.pattern.creational.builder.example3;

/**
 * Created by Евгений on 06.01.2017.
 */

public class BuilderCarDemoSimple {
    public static void main(String[] args) {
        Car car = new CarBuilder().buildName("Ford Mondeo").buildTransmission(Transmission.AUTO).buildMaxSpeed(360).build();
        System.out.println(car);
    }
}

enum Transmission {
    MANUAL,
    AUTO;
}

class Car {
    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", transmission=" + transmission +
                ", maxSpeed=" + maxSpeed +
                '}';
    }

    private String name;
    private Transmission transmission;
    private int maxSpeed;


    public void setName(String name) {
        this.name = name;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

}

class CarBuilder {

    private String n = "Skoda Octavia";
    private Transmission t = Transmission.MANUAL;
    private int m = 120;

    public CarBuilder buildName(String n) {
        this.n = n;
        return this;
    }

    public CarBuilder buildTransmission(Transmission t) {
        this.t = t;
        return this;
    }

    public CarBuilder buildMaxSpeed(int m) {
        this.m = m;
        return this;
    }

    Car build() {
        Car car = new Car();
        car.setName(n);
        car.setTransmission(t);
        car.setMaxSpeed(m);
        return car;
    }
}
