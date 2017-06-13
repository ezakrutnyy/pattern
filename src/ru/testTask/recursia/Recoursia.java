package ru.testTask.recursia;

//Что выведет следующий код???

public class Recoursia {

    public static void main(String[] args) {
        recur(99);
    }
    public static void recur(int a) {
        if (a <= 100) {
            System.out.println("a=" + a);
            recur(++a);
            System.out.println("a=" + a);
        }
    }
}
/***
 *1)a=99
 * a=100
 * a=100
 * a=101
 *
 *2) a=99
 * a=100
 * a=100
 * a=99
 *
 *3) a=99
 * a=100
 * a=101
 * a=100
 *
 *4) a=99
 * a=100
 * a=100
 * a=100
 */