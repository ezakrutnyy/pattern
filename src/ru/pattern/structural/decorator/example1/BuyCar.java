package ru.pattern.structural.decorator.example1;


import java.math.BigDecimal;

//Клиентский
public class BuyCar {
    public static void main(String[] args) {
        //CarI car = new BodyCar();
        //CarI car = new DecorateClimatControl(new BodyCar());
        //CarI car = new DecorateNavigator(new BodyCar());
        CarI car = new DecorateNavigator(new DecorateClimatControl(new BodyCar()));
        System.out.println(car.getPrice());
    }
}


interface CarI {
    BigDecimal getPrice();
}

class BodyCar implements CarI{
    @Override
    public BigDecimal getPrice() {
        return new BigDecimal(450000).setScale(2,BigDecimal.ROUND_HALF_UP);
    }
}

 abstract class Decorate implements  CarI {
    private CarI component;
    public Decorate (CarI c){
        component = c;
    }

    @Override
    public BigDecimal getPrice() {
        return component.getPrice();
    }
}

class DecorateNavigator extends Decorate {

    public DecorateNavigator(CarI c) {
        super(c);
    }

    public BigDecimal getPrice() {
        return super.getPrice().add(new BigDecimal(5000)).setScale(2,BigDecimal.ROUND_HALF_UP);
    }
}

class DecorateClimatControl extends Decorate {

    public DecorateClimatControl(CarI c) {
        super(c);
    }

    public BigDecimal getPrice() {
        return super.getPrice().add(new BigDecimal(12500)).setScale(2,BigDecimal.ROUND_HALF_UP);
    }
}