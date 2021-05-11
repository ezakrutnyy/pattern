package ru.pattern.creational.factorymethod;

import java.util.*;

/**
 * Created by Евгений on 25.12.2016.
 */
public class CollectionsFactoryRunner {
    public static void main(String[] args) {
        CollectionMaker maker = CollectionMaker.getMaker();
        maker.createCollection();
        maker.printCollection();
    }
}

abstract class CollectionMaker {

    private Collection<String> collection;

    abstract Collection<String> getTypeCollection();

    void createCollection() {
        System.out.println("Введите элементы коллекции:");
        collection = getTypeCollection();
        Scanner console = new Scanner(System.in);
        while (console.hasNext()) {
            String s = console.nextLine();
            if (s.equals("stop"))
                break;
            collection.add(s);
        }
    }

    void printCollection() {
        System.out.println(collection);
    }

    public static CollectionMaker getMaker() {
        System.out.println("Выберите тип коллекции: List, Set, Three");
        Scanner console = new Scanner(System.in);
        String s = console.nextLine();
        if (s.equals("List")) return new ArrayListMaker();
        if (s.equals("Set")) return new HashSetMaker();
        if (s.equals("Three")) return new TreeSetMaker();
        return new ArrayListMaker();
    }


}

class ArrayListMaker extends CollectionMaker {

    @Override
    Collection<String> getTypeCollection() {
        return new ArrayList<>();
    }
}

class HashSetMaker extends CollectionMaker {

    @Override
    Collection<String> getTypeCollection() {
        return new HashSet<>();
    }
}

class TreeSetMaker extends CollectionMaker {

    @Override
    Collection<String> getTypeCollection() {
        return new TreeSet<>();
    }
}