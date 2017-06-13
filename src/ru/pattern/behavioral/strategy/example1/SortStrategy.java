package ru.pattern.behavioral.strategy.example1;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Евгений on 19.01.2017.
 */
public class SortStrategy {
    public static void main(String[] args) {
        int[] arr1 = new int[]{1,-3,-10,100,-44,9,0,3,-1,-3};
        int[] arr2 = new int[]{6,13,4,-3,0,3};
        int[] arr3 = new int[]{-21,3,4,5,0,0,22,-33,3};
        Context context = new Context();
        context.setStrategy(new BubleSort());
        context.sort(arr1);
        context.setStrategy(new InsertionSort());
        context.sort(arr2);
        context.setStrategy(new SelectionSort());
        context.sort(arr3);

    }
}

class Context {

    Sort strategy;

    public void setStrategy(Sort strategy) {
        this.strategy = strategy;
    }

    void sort(int[] arr) {
        strategy.sort(arr);
    }



}

interface Sort {
    void sort(int[] arr);
}

//Пузырьковая сортировка
class BubleSort implements Sort{
    @Override
    public void sort(int[] arr) {
        System.out.println("Start Buble sort");
        System.out.println("Before sorting...");
        System.out.println(Arrays.toString(arr));
        for(int i = arr.length-1; i>0; i--) {
            for(int j = 0; j<i; j++) {
                if (arr[j]>arr[j+1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = tmp;
                }
            }
        }
        System.out.println("After sorting...");
        System.out.println(Arrays.toString(arr));
    }
}

//Сортировка выбором
class SelectionSort  implements Sort{
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
            for (int j = i+1; j < arr.length; j++) {
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
class InsertionSort implements Sort{
    @Override
    public void sort(int[] arr) {
        System.out.println("Start Insertion sort");
        System.out.println("Before sorting...");
        System.out.println(Arrays.toString(arr));
        for(int i = 1; i < arr.length; i++){
            int currElem = arr[i];
            int prevKey = i - 1;
            while(prevKey >= 0 && arr[prevKey] > currElem){
                arr[prevKey+1] = arr[prevKey];
                prevKey--;
            }
            arr[prevKey+1] = currElem;
        }
        System.out.println("After sorting...");
        System.out.println(Arrays.toString(arr));
    }
}