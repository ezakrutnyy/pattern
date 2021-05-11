package ru.pattern.behavioral.command;

import org.apache.commons.collections4.CollectionUtils;

import java.util.Objects;
import java.util.Stack;

public class UnitBattleCommandRunner {

    public static void main(String[] args) {
        System.out.println("***********************");
        Unit goblin = new Goblin();
        System.out.println("Initialization user: " + goblin);

        SpellCommand shrink = new ShrinkSpell();
        SpellCommand invisible = new InvisibilitySpell();

        Invoker invoker = new Invoker();
        System.out.println("***********************");
        invoker.invoke(goblin, shrink);
        System.out.println(goblin);

        System.out.println("***********************");
        invoker.invoke(goblin, invisible);
        System.out.println(goblin);

        System.out.println("***********************");
        invoker.undo();
        System.out.println(goblin);

        System.out.println("***********************");
        invoker.undo();
        System.out.println(goblin);
    }
}


// Invoker
class Invoker {

    private Stack<SpellCommand> history = new Stack<>();

    public void invoke(Unit unit, SpellCommand command) {
        command.execute(unit);
        history.push(command);
    }

    public void undo() {
        if (CollectionUtils.isNotEmpty(history)) {
            SpellCommand command = history.pop();
            command.undo();
        }
    }

    public void redo() {
        if (CollectionUtils.isNotEmpty(history)) {
            SpellCommand command = history.peek();
            history.push(command);
            command.redo();
        }
    }

}


// Commands
abstract class SpellCommand {

    protected Unit unit;

    public abstract void execute(Unit unit);

    public abstract void redo();

    public abstract void undo();

    public abstract String toString();
}

class InvisibilitySpell extends SpellCommand {

    @Override
    public void execute(Unit unit) {
        this.unit = unit;
        this.unit.setVisibility(Visibility.INVISIBLE);
    }

    @Override
    public void redo() {
        if (Objects.nonNull(unit)) {
            unit.setVisibility(unit.getVisibility());
        }
    }

    @Override
    public void undo() {
        if (Objects.nonNull(unit)) {
            if (unit.getVisibility() == Visibility.VISIBLE) {
                unit.setVisibility(Visibility.INVISIBLE);
            } else if (unit.getVisibility() == Visibility.INVISIBLE) {
                unit.setVisibility(Visibility.VISIBLE);
            }
        }
    }

    @Override
    public String toString() {
        return "Invisibility spell";
    }
}

class ShrinkSpell extends SpellCommand {

    private Size prevSize;

    @Override
    public void execute(Unit unit) {
        this.unit = unit;
        this.prevSize = this.unit.getSize();
        this.unit.setSize(Size.SMALL);
    }

    @Override
    public void redo() {
        if (unit != null) {
            Size currentSize = unit.getSize();
            if (currentSize == Size.LARGE) {
                unit.setSize(Size.NORMAL);
            } else if (currentSize == Size.NORMAL) {
                unit.setSize(Size.SMALL);
            }
            prevSize = currentSize;
        }
    }

    @Override
    public void undo() {
        if (prevSize != null && unit != null) {
            Size currentSize = unit.getSize();
            unit.setSize(prevSize);
            prevSize = currentSize;
        }
    }

    @Override
    public String toString() {
        return "Shrink spell";
    }
}


// Receiver
abstract class Unit {

    private Size size;

    private Visibility visibility;

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }

    @Override
    public abstract String toString();
}


class Goblin extends Unit {

    public Goblin() {
        setSize(Size.NORMAL);
        setVisibility(Visibility.VISIBLE);
    }

    @Override
    public String toString() {
        return String.format("I'm a Goblin! Size[%s], Visibility[%s]", getSize(), getVisibility());
    }
}


enum Size {

    SMALL("small"),

    NORMAL("normal"),

    LARGE("large"),

    UNDEFINED("");

    private String title;

    Size(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}

enum Visibility {

    VISIBLE("visible"),

    INVISIBLE("invisible"),

    UNDEFINED("");

    private String title;

    Visibility(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}