package ru.pattern.structural.adapter.example4;

/**
 * Created by Евгений on 10.01.2017.
 */
public class DatabaseAdapterDemo {
    public static void main(String[] args) {
        Database database = new AdapterJavaToDatabase();
        database.insert();
        database.update();
        database.select();
        database.remove();
    }
}

interface Database {
    void insert();
    void update();
    void select();
    void remove();
}

class ApplicationObject {

    public  void saveObject() {
        System.out.println("Saving Java Object...");
    }

    public  void updateObject() {
        System.out.println("Updating Java Object...");
    }

    public  void loadObject() {
        System.out.println("Loading Java Object...");
    }

    public  void deleteObject() {
        System.out.println("Deleting Java Object...");
    }
}

class AdapterJavaToDatabase extends ApplicationObject implements Database {

    @Override
    public void insert() {
        saveObject();
    }

    @Override
    public void update() {
        updateObject();
    }

    @Override
    public void select() {
        loadObject();
    }

    @Override
    public void remove() {
        deleteObject();
    }
}
