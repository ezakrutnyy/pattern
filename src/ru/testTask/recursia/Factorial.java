package ru.testTask.recursia;

/**
 * Created by Евгений on 21.11.2016.
 */
public class Factorial {
    public static void main(String[] args) {
        System.out.println(fact(5));
    }

    static int fact(int n) {
        int result;
        if (n==1) {
            return 1;
        }
        result = fact(n-1)*n;
        return result;
    }
}
