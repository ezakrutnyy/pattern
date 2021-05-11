package ru.pattern.behavioral.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Евгений on 26.11.2016.
 */
public class IteratorApp {
    public static void main(String[] args) {
        Tasks task = new Tasks();
        task.add("Вырасти ребенка");
        task.add("Посадить дерево");
        task.add("Купить яхту");
        task.add("Построить дом");
        task.add("Купить машину новую");
        task.add("Купить кофемолку");

        Iterator it = task.iterator();
        while (it.hasNext()) {
            String elem = (String) it.next();
            if (elem.lastIndexOf("Купить") > -1)
                it.remove();
        }

        it = task.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }

    }
}

//Интерфейс какой то колллекции
interface Container {
    Iterator iterator();
}

//Интерфейс какого то итератора
interface Iterator {
    boolean hasNext();

    Object next();

    void remove();
}

class Tasks implements Container {
    private List<String> list = new ArrayList<String>();


    public Iterator iterator() {
        return new Iterator() {

            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < list.size();
            }

            @Override
            public Object next() {
                return list.get(index++);
            }

            @Override
            public void remove() {
                list.remove(index--);
            }
        };
    }

    void add(String name) {
        list.add(name);
    }

}
