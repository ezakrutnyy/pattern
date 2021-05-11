package ru.pattern.structural.adapter;

/**
 * Created by Евгений on 09.01.2017.
 */
public class UnitFightAdapterRunner {
    public static void main(String[] args) {
        CommandUnit command;
        //command = new CommandUnit(new SavageAction());
        command = new CommandUnit(new AdapterMessionerActionObject(new MessionerUnit()));
        command.move();
        command.fight();
    }
}


interface ActionFight {
    void move();

    void fight();
}

class SavageAction implements ActionFight {

    @Override
    public void move() {
        System.out.println("2 ШАГА ВПЕРЕД!");
    }

    @Override
    public void fight() {
        System.out.println("УДАР ДУБИНКОЙ!");
    }
}


interface ActionReligion {

    void move();

    void education();
}


class MessionerUnit implements ActionReligion {

    @Override
    public void move() {
        System.out.println("4 ШАГА ВПЕРЕД!");
    }

    @Override
    public void education() {
        System.out.println("РАСПОСТРАНЯЕМ РЕЛИГИЮ!");
    }
}


class AdapterMessionerActionObject implements ActionFight {

    MessionerUnit unit;

    public AdapterMessionerActionObject(MessionerUnit unit) {
        this.unit = unit;
    }

    @Override
    public void move() {
        unit.move();
    }

    @Override
    public void fight() {
        unit.education();
    }
}


class CommandUnit {

    private ActionFight actionFight;


    public CommandUnit(ActionFight actionFight) {
        this.actionFight = actionFight;
    }


    public void move() {
        actionFight.move();
    }

    public void fight() {
        actionFight.fight();
    }
}

