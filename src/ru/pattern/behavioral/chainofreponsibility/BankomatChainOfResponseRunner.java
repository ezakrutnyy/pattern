package ru.pattern.behavioral.chainofreponsibility;

/**
 * Created by Евгений on 18.01.2017.
 */
public class BankomatChainOfResponseRunner {
    public static void main(String[] args) throws Exception {
        ModuleNote modele5000 = new ModuleNote5000();
        ModuleNote modele1000 = new ModuleNote1000();
        ModuleNote modele500 = new ModuleNote500();
        ModuleNote modele100 = new ModuleNote100();
        modele5000.setNextModuleNote(modele1000);
        modele1000.setNextModuleNote(modele500);
        modele500.setNextModuleNote(modele100);
        modele5000.takeMoney(new Money(49900));
    }
}

class Note {

    public static final int R100 = 100;

    public static final int R500 = 500;

    public static final int R1000 = 1000;

    public static final int R5000 = 5000;
}

class Money {

    private int amount;

    Money(int amount) throws Exception {
        setAmount(amount);
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) throws Exception {
        if (amount > 0 && amount <= 200_000 && amount % Note.R100 == 0) {
            this.amount = amount;
        } else {
            throw new Exception("Сумма денег должна быть менее 200 000 и кратна 100");
        }
    }
}

abstract class ModuleNote {

    protected ModuleNote next;

    abstract void takeMoney(Money money) throws Exception;

    void setNextModuleNote(ModuleNote next) {
        this.next = next;
    }
}

class ModuleNote5000 extends ModuleNote {

    @Override
    void takeMoney(Money money) throws Exception {
        int countNote = money.getAmount() / Note.R5000;
        int remind = money.getAmount() % Note.R5000;
        if (countNote > 0) {
            System.out.println("Выдать 5000 рублей в кол-ве " + countNote + " штук.");
        }
        if (remind > 0 && next != null) {
            next.takeMoney(new Money(remind));
        }
    }
}

class ModuleNote1000 extends ModuleNote {

    @Override
    void takeMoney(Money money) throws Exception {
        int countNote = money.getAmount() / Note.R1000;
        int remind = money.getAmount() % Note.R1000;
        if (countNote > 0) {
            System.out.println("Выдать 1000 рублей в кол-ве " + countNote + " штук.");
        }
        if (remind > 0 && next != null) {
            next.takeMoney(new Money(remind));
        }
    }
}

class ModuleNote500 extends ModuleNote {

    @Override
    void takeMoney(Money money) throws Exception {
        int countNote = money.getAmount() / Note.R500;
        int remind = money.getAmount() % Note.R500;
        if (countNote > 0) {
            System.out.println("Выдать 500 рублей в кол-ве " + countNote + " штук.");
        }
        if (remind > 0 && next != null) {
            next.takeMoney(new Money(remind));
        }
    }
}

class ModuleNote100 extends ModuleNote {

    @Override
    void takeMoney(Money money) throws Exception {
        int countNote = money.getAmount() / Note.R100;
        int remind = money.getAmount() % Note.R100;
        if (countNote > 0) {
            System.out.println("Выдать 100 рублей в кол-ве " + countNote + " штук.");
        }
        if (remind > 0 && next != null) {
            next.takeMoney(new Money(remind));
        }
    }
}