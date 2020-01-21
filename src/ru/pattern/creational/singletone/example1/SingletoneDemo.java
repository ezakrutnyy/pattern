package ru.pattern.creational.singletone.example1;

/**
 * Created by Евгений on 08.10.2016.
 */
public class SingletoneDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread[] threads  = new Thread[1000];

        for(int i = 0; i < 1000; i++) {
            threads[i] = new Thread(new Run());
            threads[i].start();
        }
        for(int i = 0; i < 1000; i++) {
            threads[i].join();
        }
        MySingletone.showCount();
    }
}

class Run implements  Runnable {

    @Override
    public void run() {
        MySingletone.getInstance();
    }
}

class MySingletone {
    private static int count = 0;
    private static MySingletone instance;
    private MySingletone() {
        count++;
    }

    public static void showCount() {
        System.out.println("Кол-во объектов  = "+count);
    }

    final public static MySingletone getInstance() {
        if (instance==null) {
            synchronized (MySingletone.class) {
                if (instance==null)
                    instance = new MySingletone();
            }
        }
        return instance;
    }
}
