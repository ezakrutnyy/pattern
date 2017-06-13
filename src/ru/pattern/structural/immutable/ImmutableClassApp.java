package ru.pattern.structural.immutable;

/**
 * Created by Евгений on 12.11.2016.
 */
public class ImmutableClassApp {
    public static void main(String[] args) {
        ImmutableClass coord = new ImmutableClass(3,4);

    }
}


interface Immutable {

    int getX();
    int getY();
}

class ImmutableClass implements  Immutable {
    private int x;
    private int y;
    ImmutableClass(int x, int y) {
        this.x= x;
        this.y=y;
    }
    public  int getX() {
        return x;
    }
    public  int getY() {
        return y;
    }
}