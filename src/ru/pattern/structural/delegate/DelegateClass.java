package ru.pattern.structural.delegate;

/**
 * Created by Евгений on 28.09.2016.
 */
public class DelegateClass {
    public static void main(String[] args) {
        System.out.println("-----------------------Новый заказ----------------------");
        Waiter waiter = new Waiter();
        waiter.salad();
        System.out.println("-----------------------Новый заказ----------------------");
        waiter.coffee();
    }
}

class Waiter {
    Cook cook = new Cook();
    void salad() {
        sayCooking();
        cook.salad();
        go();
    }
    void soup() {
        sayCooking();
        cook.soup();
        go();
    }
    void coffee() {
        sayCooking();
        cook.coffee();
        go();
    }
    void sayCooking() {
        System.out.println("Назначил повара.......");
    }
    void go() {
        System.out.println("Несу блюдо........");
    }
 }

 final class Cook {
    void salad() {
        System.out.println("Готовлю салат.....");
    }

    void soup() {
        System.out.println("Готовлю суп.....");
    }

    void coffee() {
        System.out.println("Готовлю кофе.....");
    }
}