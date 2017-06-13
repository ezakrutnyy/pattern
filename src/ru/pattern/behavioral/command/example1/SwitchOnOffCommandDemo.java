package ru.pattern.behavioral.command.example1;

/**
 * Created by Евгений on 18.01.2017.
 */
public class SwitchOnOffCommandDemo
{
    public static void main(String[] args) {

        Command on;
        Command off;
        Command reset;
        Invoke invoker;

        on = new TurnLightOn(new Light());
        off = new TurnLightOff(new Light());
        invoker = new Invoke(on,off);
        invoker.on();
        invoker.off();

        on = new TurnAirConditionOn(new AirCondition());
        off = new TurnAirConditionOff(new AirCondition());
        invoker = new Invoke(on,off);
        invoker.on();
        invoker.off();

        on = new TurnComputerOn(new Computer());
        off = new TurnComputerOff(new Computer());
        reset = new TurnComputerReset(new Computer());
        invoker = new Invoke(on,off,reset);
        invoker.on();
        invoker.off();
        invoker.onReset();
    }
}

//Create a command interface.
interface Command {
    void execute();
}

/*the Invoker class*/
class Invoke {
    Command on;
    Command off;
    Command reset;

    public Invoke(Command on,Command off) {
        this.on = on;
        this.off = off;
    }

    public Invoke(Command on,Command off, Command reset) {
        this.on = on;
        this.off = off;
        this.reset = reset;
    }

    public void on() {
        on.execute();
    }

    public void off() {
        off.execute();
    }

    public void onReset() {
        reset.execute();
    }
}


/*Receiver class*/
class Light {
    void turnOn() {
        System.out.println("Включили свет");
    }

    void turnOff() {
        System.out.println("Выключили свет");
    }
}

class AirCondition {
    void onCondition() {
        System.out.println("Включили кондиционер");
    }
    void offCondition() {
        System.out.println("Выключили кондиционер");
    }
}

class Computer {
    void on() {
        System.out.println("Включили компьютер");
    }

    void off() {
        System.out.println("Выключили компьютер");
    }

    void reset() {
        System.out.println("Перезагрузили компьютер");
    }
}


//Concreate Command

class TurnLightOn implements Command {
    Light  light;


    public TurnLightOn(Light light) {
        this.light = light;
    }

    @Override

    public void execute() {
        light.turnOn();
    }
}


class TurnLightOff implements Command{

    Light  light;


    public TurnLightOff(Light light) {
        this.light = light;
    }

    @Override

    public void execute() {
        light.turnOff();
    }
}

class TurnAirConditionOn implements Command{
    AirCondition condition;

    public TurnAirConditionOn(AirCondition condition) {
        this.condition = condition;
    }

    @Override
    public void execute() {
        condition.onCondition();
    }
}

class TurnAirConditionOff implements Command{

    AirCondition condition;

    public TurnAirConditionOff(AirCondition condition) {
        this.condition = condition;
    }

    @Override
    public void execute() {
        condition.offCondition();
    }
}

class TurnComputerOn implements Command{
    Computer comp;

    public TurnComputerOn(Computer comp) {
        this.comp = comp;
    }

    @Override
    public void execute() {
        comp.on();
    }
}

class TurnComputerOff implements Command{

    Computer comp;

    public TurnComputerOff(Computer comp) {
        this.comp = comp;
    }

    @Override
    public void execute() {
        comp.off();
    }
}

class TurnComputerReset implements Command{
    Computer comp;

    public TurnComputerReset(Computer comp) {
        this.comp = comp;
    }

    @Override
    public void execute() {
        comp.reset();
    }
}