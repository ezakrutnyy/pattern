package ru.pattern.creational.builder.example1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Евгений on 05.01.2017.
 */
public class BuilderPatternDemo {
    public static void main(String[] args) {
        OrderBuilder builder = new OrderBuilder();

        System.out.println("Veg Meal");
        Order order1 = builder.prepareVegatable();
        order1.showOrder();
        System.out.println("Total cost = "+order1.getTotalOrderCost());

        System.out.println("Mean Meal");
        Order order2 = builder.prepareNonVegatable();
        order2.showOrder();
        System.out.println("Total cost = "+order2.getTotalOrderCost());
    }
}

//Create an interface Item representing food item and packing.

interface Packing {
    public String pack();
}

class Wrapper implements Packing {

    @Override
    public String pack() {
        return "Запакуем в пакет!";
    }
}

class Bottle implements Packing {

    @Override
    public String pack() {
        return "Нальем в бутылку!";
    }
}

interface Item {
    public String name();
    public Packing packing();
    public double price();
}

 abstract class Burger implements Item {

     @Override
     public Packing packing() {
         return new Wrapper();
     }

     @Override
     abstract public double price();
 }

 class VegMak extends Burger {

     @Override
     public String name() {
         return "Вегетарианский бургер";
     }

     @Override
     public double price() {
         return 98;
     }
 }

 class ChickenBurger extends Burger {

     @Override
     public String name() {
         return "ЧикенБургер";
     }

     @Override
     public double price() {
         return 83;
     }
 }

abstract class ColdDrink implements Item {

     @Override
     public Packing packing() {
         return new Bottle();
     }

     @Override
     abstract public double price();
 }

 class Coke extends ColdDrink {

     @Override
     public String name() {
         return "Кока-кола";
     }

     @Override
     public double price() {
         return 45;
     }
 }

class Pepsi extends ColdDrink {

    @Override
    public String name() {
        return "Пепси";
    }

    @Override
    public double price() {
        return 38;
    }
}


class Order {
    private List<Item> orders = new ArrayList<Item>();

    public void addItemOrder(Item item) {
        orders.add(item);
    }

    public double getTotalOrderCost(){
        double cost = 0;
        for (Item order : orders) {
            cost += order.price();
        }
        return cost;
    }

    public void showOrder() {
        for (Item order : orders) {
            System.out.print("Item : " + order.name());
            System.out.print(", Packing : " + order.packing().pack());
            System.out.println(", Price : " + order.price());
        }
    }
}

class OrderBuilder {
    Order prepareVegatable() {
        Order order = new Order();
        order.addItemOrder(new VegMak());
        order.addItemOrder(new Coke());
        return order;
    }

    Order prepareNonVegatable() {
        Order order = new Order();
        order.addItemOrder(new ChickenBurger());
        order.addItemOrder(new Pepsi());
        return order;
    }
}