package ru.pattern.structural.adapter.example3;

/**
 * Created by Евгений on 09.01.2017.
 */
public class AdapterPatternUnitFightDemo {
    public static void main(String[] args) {
        CommandUnit command;
        //command = new CommandUnit(new SavageAction());
        command = new CommandUnit(new AdapterMessionerAction());
        //command = new CommandUnit(new AdapterMessionerActionObject(new MessionerUnit()));
        command.move();
        command.fight();
    }
}

interface ActionFight {
    void move();
    void fight();
}

interface ActionReligion {
    void move();
    void education();
}

class SavageAction implements  ActionFight {

    @Override
    public void move() {
        System.out.println("2 ШАГА ВПЕРЕД!");
    }

    @Override
    public void fight() {
        System.out.println("УДАР ДУБИНКОЙ!");
    }
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

class AdapterMessionerAction extends MessionerUnit implements ActionFight  {

    @Override
    public void move() {
        super.move();
    }

    @Override
    public void fight() {
        super.education();
    }
}



class AdapterMessionerActionObject implements ActionFight  {

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



 class CommandUnit  {

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

