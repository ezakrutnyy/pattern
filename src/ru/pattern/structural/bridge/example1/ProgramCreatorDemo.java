package ru.pattern.structural.bridge.example1;

/**
 * Created by Евгений on 10.01.2017.
 */
public class ProgramCreatorDemo {
    public static void main(String[] args) {
        Program[] programs = {
                new CalculatorApp(new CppDeveloper()),
                new ChessGameApp(new JavaDeveloper()),
        };
        for (Program program : programs) {
            program.developProgram();

        }
    }
}

abstract class Program {
    protected Developer developer;

    public Program(Developer developer) {
        this.developer = developer;
    }

    protected abstract void developProgram();
}

class CalculatorApp extends  Program {

    public CalculatorApp(Developer developer) {
        super(developer);
    }

    @Override
    protected void developProgram() {
        System.out.println("CalculatorApp processing...");
        developer.writeCode();
    }
}

class ChessGameApp extends  Program {

    public ChessGameApp(Developer developer) {
        super(developer);
    }

    @Override
    protected void developProgram() {
        System.out.println("Game Chess processing...");
        developer.writeCode();
    }
}

interface Developer {
    void writeCode();
}

class JavaDeveloper implements  Developer {

    @Override
    public void writeCode() {
        System.out.println("Java developer writing Java code...");
    }
}


class CppDeveloper implements  Developer {

    @Override
    public void writeCode() {
        System.out.println("C++ developer writing C++ code...");
    }
}