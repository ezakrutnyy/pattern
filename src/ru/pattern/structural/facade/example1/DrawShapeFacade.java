package ru.pattern.structural.facade.example1;

/**
 * Created by Евгений on 28.09.2016.
 * @description: Шаблон фасад (англ. Facade) — структурный шаблон проектирования,
 * позволяющий скрыть сложность системы путём сведения всех возможных внешних вызовов к одному объекту,
 * делегирующему их соответствующим объектам системы.
 * + Возможность изменить поведение конкретного экземпляра объекта вместо создания нового класса путём наследования.
 * - Этот шаблон обычно затрудняет оптимизацию по скорости в пользу улучшенной чистоты абстракции.
 */
public class DrawShapeFacade {
    public static void main(String[] args) {
        ShapeMaker maker = new ShapeMaker();
//        maker.setShape(new Circle());
//        maker.draw();
//        maker.draw();
//        maker.setShape(new Rectangle());
//        maker.draw();
//        maker.setShape(new Square());
//        maker.draw();
        maker.drawCircle();
        maker.drawRectangle();
        maker.drawSquare();
    }
}

interface Shape {
    void draw();
}

class Rectangle implements  Shape {

    @Override
    public void draw() {
        System.out.println("Drawing Rectangle...");
    }
}

class Square implements Shape {

    @Override
    public void draw() {
        System.out.println("Drawing Square...");
    }
}

class Circle implements  Shape {

    @Override
    public void draw() {
        System.out.println("Drawing Circle...");
    }
}

class ShapeMaker {
    private Rectangle rectangle;
    private Square square;
    private  Circle circle;
//    Shape shape;
//
//    void setShape(Shape shape) {
//        this.shape = shape;
//    }
//
//    void draw() {
//        shape.draw();
//    }
    ShapeMaker() {
        rectangle = new Rectangle();
        square = new Square();
        circle = new Circle();
    }
    void drawRectangle() {
        rectangle.draw();
    }
    void drawSquare() {
        square.draw();
    }
    void drawCircle() {
        circle.draw();
    }
}