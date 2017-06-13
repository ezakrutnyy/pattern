package ru.pattern.structural.delegate;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Евгений on 28.09.2016.
 */
public class DelegateInterface {
    public static void main(String[] args) {
        Action action = new Action();
        action.setAction(new WakeUpAction());
        action.start();
        action.setAction(new ChickenIsReady());
        action.start();
    }
}

interface Task {
    void start();
}

class WakeUpAction implements  Task{
    public void start() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run()
            {
                System.out.println("ALARM!!!ALARM!!!!WAKE UPPPPPP!!!!");
                timer.cancel();
            }
        };
        timer.schedule( task, 10000 );

    }
}

class ChickenIsReady implements Task {
    public void start() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run()
            {
                System.out.println("CHECKING READY!!!");
                timer.cancel();
            }
        };
        timer.schedule( task, 5000 );

    }
}

class Action {
    Task task;

    void setAction(Task task) {
        this.task = task;
    }
    void start() {
        task.start();
    }
}