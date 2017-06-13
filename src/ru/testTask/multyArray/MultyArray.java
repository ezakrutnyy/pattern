package ru.testTask.multyArray;

import java.util.Calendar;

/**
 * Различия копирования методов одномерных и многомерных
 */
public class MultyArray {
    public static void main(String[] args) {

        int[][] x  = new int[2][5];
        int[][] y = new int[2][5];
        System.out.println("Многомерный массив 1 = "+x[1][3]);
        System.arraycopy(x, 0, y, 0, x.length);
        x[1][3] = 55;
        System.out.println("Многомерный массив 2 = "+y[1][3]);
        /**
         * Какой результат выполнения данной программы ?
         * 1) null null
         * + 2) 0 55
         * 3) 0 0
         * 4) null 55
         * Пояснение: Любой N-мерный массив это одномерный массив, элементами которого являются >>ссылки<< на массивы размерности N-1.
         * System.arraycopy копирует как раз эти ссылки.
         * */
        int[] a  = new int[5];
        int[] b = new int[5];
        System.out.println("Одномерный массив 1 = "+a[3]);
        System.arraycopy(a, 0, b, 0, a.length);
        a[3] = 55;
        System.out.println("Одномерный массив 2 = "+b[3]);
        /**
         * Здесь уже копируем только значения, поэтому изменения в одном массиве, не затрагивают значения в другом.
         * */
    }
}