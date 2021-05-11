package ru.pattern.creational.builder;

/**
 * Created by Евгений on 06.01.2017.
 */
public class CarFactoryBuilderRunner {

    public static void main(String[] args) {
        DirectorCar director = new DirectorCar();
        director.setBuilder(new SubaruForesterBuilder());
        Car a = director.build();
        System.out.println(a);
        director.setBuilder(new FordMondeoBuilder());
        Car b = director.build();
        System.out.println(b);
    }
}


abstract class CarBuilderFull {
    Car car;

    void createCar() {
        car = new Car();
    }

    abstract void buildName();

    abstract void buildTransmission();

    abstract void buildMaxSpeed();


    Car getCar() {
        return car;
    }
}

class FordMondeoBuilder extends CarBuilderFull {

    @Override
    void buildName() {
        car.setName("Ford Mondeo");
    }

    @Override
    void buildTransmission() {
        car.setTransmission(Transmission.AUTO);
    }

    @Override
    void buildMaxSpeed() {
        car.setMaxSpeed(260);
    }
}

class SubaruForesterBuilder extends CarBuilderFull {

    @Override
    void buildName() {
        car.setName("Subaru Forester");
    }

    @Override
    void buildTransmission() {
        car.setTransmission(Transmission.MANUAL);
    }

    @Override
    void buildMaxSpeed() {
        car.setMaxSpeed(320);
    }
}

class DirectorCar {
    private CarBuilderFull builder;

    void setBuilder(CarBuilderFull builder) {
        this.builder = builder;
    }

    Car build() {
        builder.createCar();
        builder.buildName();
        builder.buildTransmission();
        builder.buildMaxSpeed();
        return builder.getCar();
    }
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

enum Transmission {
    MANUAL,
    AUTO
}