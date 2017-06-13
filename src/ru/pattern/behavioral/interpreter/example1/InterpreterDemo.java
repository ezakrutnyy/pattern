package ru.pattern.behavioral.interpreter.example1;

//Step1 Create an expression interface.

interface Expression {
    public boolean interpret(String context);
}

//Step2 Create concrete classes implementing the above interface.
 class TerminalExpression implements Expression {
    private String data;

    public TerminalExpression(String data) {
        this.data = data;
    }

    @Override
    public boolean interpret(String context) {
        if (context.contains(data)) {
            return true;
        }
        return false;
    }
}

class OrExpression implements  Expression {
    private Expression one;
    private Expression two;

    public OrExpression(Expression one, Expression two) {
        this.one = one;
        this.two = two;
    }

    @Override
    public boolean interpret(String context) {
        return one.interpret(context) || two.interpret(context);
    }
}

class AndExpression implements  Expression {
    private Expression one;
    private Expression two;

    public AndExpression(Expression one, Expression two) {
        this.one = one;
        this.two = two;
    }

    @Override
    public boolean interpret(String context) {
        return one.interpret(context) &&  two.interpret(context);
    }
}

//Step3 InterpreterDemo uses Expression class to create rules and then parse them.
public class InterpreterDemo {
    //Rule: Robert and John are male
    public static Expression getMaleExpression(){
        Expression robert = new TerminalExpression("Robert");
        Expression john = new TerminalExpression("John");
        return new OrExpression(robert, john);
    }

    //Rule: Julie is a married women
    public static Expression getMarriedWomanExpression(){
        Expression julie = new TerminalExpression("Julie");
        Expression married = new TerminalExpression("Married");
        return new AndExpression(julie, married);
    }

    public static void main(String[] args) {
        Expression isMale = getMaleExpression();
        System.out.println("is Male ?"+isMale.interpret("John"));

        Expression isMaried = getMarriedWomanExpression();
        System.out.println("is Maried ?"+isMaried.interpret("Julie Married"));
    }
}
