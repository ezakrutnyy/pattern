package ru.pattern.behavioral.chainofresponsibily.example1;

/**
 * Created by Евгений on 18.01.2017.
 */
public class LoggerChainDemo {
    public static void main(String[] args) {
        Logger bank = new BankLogger(1);
        Logger dev = new DeveloperLogger(2);
        Logger local = new LocalLogger(3);

        bank.setNext(dev);
        dev.setNext(local);

        bank.writeLog("Message only for you......!",Level.INFO);
        bank.writeLog("Debuging in progress....!",Level.DEBUG);
        bank.writeLog("Null pointer Exceprion....!",Level.ERROR);

    }
}

 class Level {
    public static final int ERROR = 1;
    public static final int DEBUG = 2;
    public static final int INFO = 3;
}

abstract class Logger {
    private Logger next;
    private int priority;

    public Logger(int priority) {
        this.priority = priority;
    }

    int level;
    void setNext(Logger next) {
        this.next = next;
    }

    void writeLog(String s, int level) {
        if (level<=priority) {
            write(s);
        }
        if (next!=null) {
            next.writeLog(s,level);
        }
    }
    protected abstract void write(String s);
}

class BankLogger extends Logger {

    public BankLogger(int priority) {
        super(priority);
    }

    @Override
    protected void write(String s) {
        System.out.println("Bank server: "+s);
    }
}

class DeveloperLogger extends Logger {

    public DeveloperLogger(int priority) {
        super(priority);
    }

    @Override
    protected void write(String s) {
        System.out.println("Developer server: "+s);
    }
}

class LocalLogger extends Logger {

    public LocalLogger(int priority) {
        super(priority);
    }

    @Override
    protected void write(String s) {
        System.out.println("Local server: "+s);
    }
}