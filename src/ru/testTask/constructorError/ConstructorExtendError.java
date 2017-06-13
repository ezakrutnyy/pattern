package ru.testTask.constructorError;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Что будет результатом запуска такого кода?
 */
public class ConstructorExtendError {
    public static void main(String... args) {
        A alpha = new B(0);

    }
}

class A {
    A() {}//TODO без этого работать не будет!
    A(int x) {            // - 1 -
        a(x);            // - 2 -
    }

    void a(int x) {
        System.out.println("A-a: " + x);
    }
}

class B extends A {
    B(int x) {           // - 3 -
        a(x);            // - 4 -
    }

    void a(int x) {
        System.out.println("B-a: " + x);
    }
}
/***
 *1) А-a: 0    B-a: 0
 *2) B-a: 0    A-a: 0
 *3) Ошибка компиляции в строке 1
 *4) Ошибка компиляции в строке 2
 *+5) Ошибка компиляции в строке 3
 *6) Ошибка компиляции в строке 4
 *
 *    Пояснение: В классе A отсутствует конструктор по-умолчанию, поэтому конструктор класса B (потомок)
 *    первым делом должен явно вызвать один из обьявленных конструкторов класса-родителя.
 *
 */
