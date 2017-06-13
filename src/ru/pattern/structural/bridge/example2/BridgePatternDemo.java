package ru.pattern.structural.bridge.example2;

/**
 * Created by Евгений on 11.01.2017.
 */
public class BridgePatternDemo {
    public static void main(String[] args) {
        Circle circle;


        circle = new Circle(10,10,3,new Red());
        circle.draw();

        circle = new Circle(15,10,5, new Blue());
        circle.draw();
    }

}

interface DrawAPI {
    public void draw(int radius, int x, int y);
}

class Red implements DrawAPI {
    @Override
    public void draw(int radius, int x, int y) {
        System.out.println("Drawing Red Circle, radius: " + radius + ", x: " + x + ", y: " + y);
    }
}

class Blue implements DrawAPI {
    @Override
    public void draw(int radius, int x, int y) {
        System.out.println("Drawing Blue Circle, radius: " + radius + ", x: " + x + ", y: " + y);
    }
}

abstract class Shape {
    protected DrawAPI drawApi;

    public Shape(DrawAPI drawApi) {
        this.drawApi = drawApi;
    }

    abstract void draw();
}


class Circle extends  Shape {

    private int x, y, radius;

    public Circle(int x, int y, int radius, DrawAPI drawApi) {
        super(drawApi);
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    @Override
    void draw() {
        drawApi.draw(radius,x,y);
    }
}