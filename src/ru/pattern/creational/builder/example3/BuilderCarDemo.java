package ru.pattern.creational.builder.example3;

/**
 * Created by Евгений on 06.01.2017.
 */
public class BuilderCarDemo {

    public static void main(String[] args) {
        Director director = new Director();
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


    Car getCar() {return car;}
}

class FordMondeoBuilder extends  CarBuilderFull {

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

class SubaruForesterBuilder extends  CarBuilderFull {

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

class Director {
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

