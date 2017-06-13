package ru.pattern.creational.abstractfactory.example1;

/**
 * Created by Евгений on 03.01.2017.
 */
public class AbstractFactoryDemo {
    public static void main(String[] args) {
        AbstractFactory makerShape = FactoryMaker.getMaker("shape");
        AbstractFactory makerColor = FactoryMaker.getMaker("color");

        Shape shape;
        Color color;

        shape = makerShape.createShape("CIRCLE");
        shape.draw();
        color = makerColor.createColor("red");
        color.fill();

        shape = makerShape.createShape("square");
        shape.draw();
        color = makerColor.createColor("blue");
        color.fill();

        shape = makerShape.createShape("TriaNgle");
        shape.draw();
        color = makerColor.createColor("green");
        color.fill();


    }
}

interface Shape {
    void draw();
}

class Circle implements Shape {

    @Override
    public void draw() {
        System.out.println("Рисуем круг");
    }
}

class Square implements Shape {

    @Override
    public void draw() {
        System.out.println("Рисуем квадрат");
    }
}

class Triangle implements Shape {

    @Override
    public void draw() {
        System.out.println("Рисуем треугольник");
    }
}

interface  Color {
    void fill();
}

class Red implements Color {

    @Override
    public void fill() {
        System.out.println("Заполняем красным цветом");
    }
}

class Green implements Color {

    @Override
    public void fill() {
        System.out.println("Заполняем зеленым цветом");
    }
}

class Blue implements Color {

    @Override
    public void fill() {
        System.out.println("Заполняем синим цветом");
    }
}


abstract class AbstractFactory {
    abstract Color createColor(String color);
    abstract Shape createShape(String shape);
}

class ShapeFactory extends AbstractFactory {

    @Override
    Color createColor(String color) {
        return null;
    }

    @Override
    Shape createShape(String shape) {
        if (shape == null) {
            return null;
        }
        if (shape.equalsIgnoreCase("circle")) {
            return new Circle();
        } else if (shape.equalsIgnoreCase("square")) {
            return new Square();
        } else if (shape.equalsIgnoreCase("triangle")) {
            return new Triangle();
        }
        return null;
    }
}

class ColorFactory extends AbstractFactory {

    @Override
    Color createColor(String color) {
        if (color == null) {
            return null;
        }
        if (color.equalsIgnoreCase("red")) {
            return new Red();
        } else if (color.equalsIgnoreCase("green")) {
            return new Green();
        } else if (color.equalsIgnoreCase("blue")) {
            return new Blue();
        }
        return null;
    }

    @Override
    Shape createShape(String shape) {
        return null;
    }
}

class FactoryMaker {
    public static AbstractFactory getMaker(String factory) {
        if (factory.equalsIgnoreCase("shape")) {
            return new ShapeFactory();
        } else if (factory.equalsIgnoreCase("color")) {
            return new ColorFactory();
        }
        return null;
    }
}