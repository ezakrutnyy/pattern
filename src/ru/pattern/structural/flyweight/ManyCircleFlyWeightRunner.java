package ru.pattern.structural.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Евгений on 17.01.2017.
 */
public class ManyCircleFlyWeightRunner {

    private static final String[] colors = {"Red", "Green", "Blue", "White", "Black"};

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            Circle circle = (Circle) ShapeFactory.getCircle(getRandomColor());
            circle.setX(getRandomX());
            circle.setY(getRandomY());
            circle.setRadius(100);
            circle.draw();
        }
    }

    private static String getRandomColor() {
        return colors[(int) (Math.random() * colors.length)];
    }

    private static int getRandomX() {
        return (int) (Math.random() * 100);
    }

    private static int getRandomY() {
        return (int) (Math.random() * 100);
    }
}

// 1. Create an interface.
interface Shape {
    void draw();
}

// 2. Create concrete class implementing the same interface.
class Circle implements Shape {

    private final String color;

    private int x;

    private int y;

    private int radius;

    public Circle(String color) {
        this.color = color;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public void draw() {
        System.out.println(String.format("Circle hash[%d]: Draw() [color:%s, x:%d, y:%d, radius:%d",
                this.hashCode(), color, x, y, radius));
    }
}

// 3. Create a factory to generate object of concrete class based on given information.
class ShapeFactory {

    private static final Map<String, Shape> circleMap = new HashMap<>();

    public static Shape getCircle(String color) {
        Circle circle = (Circle) circleMap.get(color);
        if (circle == null) {
            circle = new Circle(color);
            circleMap.put(color, circle);
            System.out.println("Creating circle of color : " + color);
        }
        return circle;
    }
}