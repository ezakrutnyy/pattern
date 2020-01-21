package ru.pattern.creational.factorymethod.example0;

/**
 * Created by Евгений on 04.03.2017.
 */
public class TranspartDemo {

    public static void main(String[] args) {
        Delivery maker = getMaker(2);
        Transport tr = maker.createDelivery();
        tr.descriptionDelivery();
    }
    
    //// TODO: 04.03.2017  getMaker
    static Delivery getMaker(int type) {
        if (type==1) {
            return new CarDelivery();
        } else if (type==2) {
            return new AviaDelivery();
        } else if (type==3) {
            return new ShipDelivery();
        }
        return new CarDelivery();
    }
    
}

abstract class Delivery {
    abstract Transport createDelivery();
}

class CarDelivery extends  Delivery {
    Transport createDelivery() {
        return new Car();
    }
}

class AviaDelivery extends  Delivery {
    Transport createDelivery() {
        return new Plane();
    }
}

class ShipDelivery extends  Delivery {
    Transport createDelivery() {
        return new Ship();
    }
}


interface Transport {
    void descriptionDelivery();
}

class Car implements Transport {

    @Override
    public void descriptionDelivery() {
        System.out.println("Доставка по земле....");
    }
}

class Plane implements Transport {

    @Override
    public void descriptionDelivery() {
        System.out.println("Доставка по воздуху....");
    }
}

class Ship implements Transport {

    @Override
    public void descriptionDelivery() {
        System.out.println("Доставка по морю....");
    }
}