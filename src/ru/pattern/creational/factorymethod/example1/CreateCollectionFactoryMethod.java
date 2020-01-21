package ru.pattern.creational.factorymethod.example1;

import java.util.*;

/**
 * Created by Евгений on 25.12.2016.
 */
public class CreateCollectionFactoryMethod {
    public static void main(String[] args) {
        CollectionMaker maker = new HashSetMaker();
        maker.createCollection();
        maker.printCollection();
    }
}

abstract class CollectionMaker {
    private Collection<String> collection;

    abstract Collection<String> getTypeCollection();

    void createCollection() {
        collection = getTypeCollection();
        Scanner console = new Scanner(System.in);
        while(console.hasNext()) {
            String s = console.nextLine();
            if (s.equals("stop"))
                break;
            collection.add(s);
        }
    }

    void printCollection() {
        System.out.println(collection);
    }

}

class ArrayListMaker extends CollectionMaker {

    @Override
    Collection<String> getTypeCollection() {
       return  new ArrayList<>();
    }
}

class HashSetMaker extends CollectionMaker {

    @Override
    Collection<String> getTypeCollection() {
        return  new HashSet<>();
    }
}

class TreeSetMaker extends CollectionMaker {

    @Override
    Collection<String> getTypeCollection() {
        return  new TreeSet<>();
    }
}