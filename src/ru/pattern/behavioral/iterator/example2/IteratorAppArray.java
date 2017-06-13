package ru.pattern.behavioral.iterator.example2;

/**
 * Created by Евгений on 26.11.2016.
 */
public class IteratorAppArray {
    public static void main(String[] args) {
        Object[] ar = new Object[5];
        ar[0] = 12;
        ar[1] = "Строка";
        ar[2] = new Object();
        ar[3] = 1000L;
        ar[4] = true;
        ArrayDifferentObject array = new ArrayDifferentObject(ar);

        IteratorArray iter = array.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
    }
}

//Интерфейс какой то колллекции
interface ContainerArray {
    IteratorArray iterator();
}
//Интерфейс какого то итератора
interface IteratorArray {
    boolean hasNext();
    Object next();
}

class ArrayDifferentObject implements  ContainerArray {
    private Object[] collection;
    ArrayDifferentObject(Object[] a) {
        collection = a;
    }
    public IteratorArray iterator() {
        return new IteratorArray()  {
            private int index = 0;
            public boolean hasNext() {
                return index< collection.length;
            }
            public Object next() {
                return collection[index++];
            }
        };
    }
}