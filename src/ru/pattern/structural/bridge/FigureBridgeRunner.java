package ru.pattern.structural.bridge;

/**
 * Created by Евгений on 11.01.2017.
 */
public class FigureBridgeRunner {
    public static void main(String[] args) {
        Shape shape;

        shape = new Circle(10, 10, 3, new Red());
        shape.draw();

        shape = new Square(5, 5, new Blue());
        shape.draw();
    }

}

interface Fill {
    void fill();

}

class Red implements Fill {

    @Override
    public void fill() {
        System.out.println("    ***FILL RED COLOR***    ");
    }
}

class Blue implements Fill {

    @Override
    public void fill() {
        System.out.println("    ***FILL BLUE COLOR***    ");
    }
}

abstract class Shape {

    private final int x;

    private final int y;

    protected Fill fill;

    public Shape(int x, int y, Fill fill) {
        this.x = x;
        this.y = y;
        this.fill = fill;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    abstract void draw();
}


class Circle extends Shape {

    private int radius;

    public Circle(int x, int y, int radius, Fill fill) {
        super(x, y, fill);
        this.radius = radius;
    }

    @Override
    void draw() {
        System.out.print(String.format("Draw circle... point[%s, %s], radius = %s", super.getX(), super.getY(), radius));
        fill.fill();
    }
}

class Square extends Shape {

    public Square(int x, int y, Fill fill) {
        super(x, y, fill);
    }

    @Override
    void draw() {
        System.out.print(String.format("Draw square... x = %s, y = %s" , super.getX(), super.getY()));
        fill.fill();
    }
}