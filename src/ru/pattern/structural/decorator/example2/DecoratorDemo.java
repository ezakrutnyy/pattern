package ru.pattern.structural.decorator.example2;

/**
 * Created by Евгений on 08.10.2016.
 */
public class DecoratorDemo {
    public static void main(String[] args) {
        //PrinterInterface printer = new RightBrecket(new LeftBrecket(new Quote(new Printer("Привет"))));
        PrinterInterface printer = new DecoratorQuote(new Printer("Hello"));
        printer.print();
    }
}

interface PrinterInterface {
    void print();
}

class Printer implements  PrinterInterface {
    String text;
    Printer(String text) {
        this.text = text;
    }
    public void print() {
        System.out.print(text);
    }
}

class DecoratorQuote implements PrinterInterface {
    PrinterInterface component;
    DecoratorQuote(PrinterInterface component) {
       this.component =component;
    }

    @Override
    public void print() {
        System.out.print("\"");
        component.print();
        System.out.print("\"");
    }
}
//
//abstract class Decorator implements PrinterInterface {
//    PrinterInterface component;
//    Decorator(PrinterInterface component) {
//        this.component = component;
//    }
//    public void print() {
//        component.print();
//    }
//}
//
//class Quote extends  Decorator {
//
//    Quote(PrinterInterface component) {
//        super(component);
//    }
//
//    public void print() {
//        System.out.print("\"");
//        super.print();
//        System.out.print("\"");
//    }
//}
//
//
//class LeftBrecket extends  Decorator {
//
//    LeftBrecket(PrinterInterface component) {
//        super(component);
//    }
//
//    public void print() {
//        System.out.print("[");
//        super.print();
//    }
//}
//
//class RightBrecket extends  Decorator {
//
//    RightBrecket(PrinterInterface component) {
//        super(component);
//    }
//
//    public void print() {
//        super.print();
//        System.out.print("]");
//    }
//}




