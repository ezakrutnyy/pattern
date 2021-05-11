package ru.pattern.behavioral.visitor;

public class ShapeVisitorRunner {

    public static void main(String[] args) {
        Shape circle = new Circle(3, 4, 3);
        Shape square = new Square(7, 10, 4);
        Shape rectangle = new Rectangle(10, 16, 6, 2);

        Visitor areaVisitor = new AreaVisitor();
        Visitor perimetrVisitor = new PerimetrVisitor();

        System.out.println("**********Circle**************");
        System.out.println(circle);
        System.out.println("Area circle: " + circle.accept(areaVisitor));
        System.out.println("Perimetr circle: " + circle.accept(perimetrVisitor));

        System.out.println("**********Square**************");
        System.out.println(square);
        System.out.println("Area square: " + square.accept(areaVisitor));
        System.out.println("Perimetr square: " + square.accept(perimetrVisitor));

        System.out.println("**********Rectangle**************");
        System.out.println(rectangle);
        System.out.println("Area rectangle: " + rectangle.accept(areaVisitor));
        System.out.println("Perimetr rectangle: " + rectangle.accept(perimetrVisitor));
    }
}


interface Visitor {

    int visitCircle(Circle circle);

    int visitSquare(Square square);

    int visitRectangle(Rectangle rectangle);

}


class AreaVisitor implements Visitor {

    @Override
    public int visitCircle(Circle circle) {
        return (int) (Math.PI * (circle.getRadius() * circle.getRadius()));
    }

    @Override
    public int visitSquare(Square square) {
        return square.getWidth() * square.getWidth();
    }

    @Override
    public int visitRectangle(Rectangle rectangle) {
        return rectangle.getWidth() * rectangle.getHeight();
    }
}

class PerimetrVisitor implements Visitor {

    @Override
    public int visitCircle(Circle circle) {
        return (int) (2 * Math.PI * circle.getRadius());
    }

    @Override
    public int visitSquare(Square square) {
        return square.getWidth() * 4;
    }

    @Override
    public int visitRectangle(Rectangle rectangle) {
        return (rectangle.getWidth() + rectangle.getHeight()) * 2;
    }
}

abstract class Shape {

    private final int x;

    private final int y;


    public Shape(int x, int y) {
        this.x = x;
        this.y = y;
    }


    protected int getX() {
        return x;
    }

    protected int getY() {
        return y;
    }

    public abstract int accept(Visitor visitor);

}

class Circle extends Shape {

    private final int radius;

    public Circle(int x, int y, int radius) {
        super(x, y);
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public int accept(Visitor visitor) {
        return visitor.visitCircle(this);
    }

    @Override
    public String toString() {
        return String.format("Circle: Point{%d, %d}, Radius{%d}", this.getX(), this.getY(), this.radius);
    }
}

class Square extends Shape {

    private final int width;

    public Square(int x, int y, int width) {
        super(x, y);
        this.width = width;
    }

    public int getWidth() {
        return width;
    }

    public int accept(Visitor visitor) {
        return visitor.visitSquare(this);
    }

    @Override
    public String toString() {
        return String.format("Square: Point{%d, %d}, Width, Height {%d}", this.getX(), this.getY(), this.width);
    }
}

class Rectangle extends Square {

    private final int height;

    public Rectangle(int x, int y, int width, int height) {
        super(x, y, width);
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public int accept(Visitor visitor) {
        return visitor.visitRectangle(this);
    }

    @Override
    public String toString() {
        return String.format("Rectangle: Point{%d, %d}, Width{%d}, Height {%d}", this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }
}