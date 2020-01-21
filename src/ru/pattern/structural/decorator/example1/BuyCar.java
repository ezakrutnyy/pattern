package ru.pattern.structural.decorator.example1;


import java.math.BigDecimal;

//Клиентский
public class BuyCar {
    public static void main(String[] args) {
        Car car = new DecorateClimatControl(new DecorateNavigator(new BodyCar()));
        System.out.println(car.getPrice());
    }
}


interface Car {
    BigDecimal getPrice();
}

class BodyCar implements Car {
    @Override
    public BigDecimal getPrice() {
        return new BigDecimal(450000).setScale(2,BigDecimal.ROUND_HALF_UP);
    }
}

 abstract class Decorate implements Car {
    private Car component;
    public Decorate (Car c){
        component = c;
    }

    @Override
    public BigDecimal getPrice() {
        return component.getPrice();
    }
}

class DecorateNavigator extends Decorate {

    public DecorateNavigator(Car c) {
        super(c);
    }

    public BigDecimal getPrice() {
        return super.getPrice().add(new BigDecimal(5000)).setScale(2,BigDecimal.ROUND_HALF_UP);
    }
}

class DecorateClimatControl extends Decorate {

    public DecorateClimatControl(Car c) {
        super(c);
    }

    public BigDecimal getPrice() {
        return super.getPrice().add(new BigDecimal(12500)).setScale(2,BigDecimal.ROUND_HALF_UP);
    }
}