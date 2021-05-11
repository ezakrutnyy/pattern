package ru.pattern.behavioral.strategy;

import java.util.Arrays;

public class SortedStrategyRunner {

    public static void main(String[] args) {
        int[] ints = new int[]{-3, 12, 3, 4, 0, -2, 9, 5, 1, 7};
        Context context = new Context(ints);

        context.setStrategy(new BubbleSort());
        context.sort();
    }
}

class Context {

    private final int[] arrays;

    private Sort strategy;

    public Context(int[] arrays) {
        this.arrays = arrays;
    }

    public void setStrategy(Sort strategy) {
        this.strategy = strategy;
    }

    void sort() {
        strategy.sort(arrays);
    }

}

interface Sort {
    void sort(int[] arr);
}

//Пузырьковая сортировка
class BubbleSort implements Sort {
    @Override
    public void sort(int[] arr) {
        System.out.println("Start Bubble sort");
        System.out.println("Before sorting...");
        System.out.println(Arrays.toString(arr));
        for (int i = arr.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
        System.out.println("After sorting...");
        System.out.println(Arrays.toString(arr));
    }
}

//Сортировка выбором
class SelectionSort implements Sort {
    @Override
    public void sort(int[] arr) {
        System.out.println("Start Selection sort");
        System.out.println("Before sorting...");
        System.out.println(Arrays.toString(arr));
        for (int i = 0; i < arr.length; i++) {
        /*Предполагаем, что первый элемент (в каждом
           подмножестве элементов) является минимальным */
            int min = arr[i];
            int min_i = i;
        /*В оставшейся части подмножества ищем элемент,
           который меньше предположенного минимума*/
            for (int j = i + 1; j < arr.length; j++) {
                //Если находим, запоминаем его индекс
                if (arr[j] < min) {
                    min = arr[j];
                    min_i = j;
                }
            }
        /*Если нашелся элемент, меньший, чем на текущей позиции,
          меняем их местами*/
            if (i != min_i) {
                int tmp = arr[i];
                arr[i] = arr[min_i];
                arr[min_i] = tmp;
            }
        }
        System.out.println("After sorting...");
        System.out.println(Arrays.toString(arr));
    }
}

//Сортировка вставками
class InsertionSort implements Sort {
    @Override
    public void sort(int[] arr) {
        System.out.println("Start Insertion sort");
        System.out.println("Before sorting...");
        System.out.println(Arrays.toString(arr));
        for (int i = 1; i < arr.length; i++) {
            int currElem = arr[i];
            int prevKey = i - 1;
            while (prevKey >= 0 && arr[prevKey] > currElem) {
                arr[prevKey + 1] = arr[prevKey];
                prevKey--;
            }
            arr[prevKey + 1] = currElem;
        }
        System.out.println("After sorting...");
        System.out.println(Arrays.toString(arr));
    }
}