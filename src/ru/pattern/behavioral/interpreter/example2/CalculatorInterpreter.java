package ru.pattern.behavioral.interpreter.example2;

import com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBaseIterators;

import java.util.Stack;

/**
 * Created by Евгений on 18.02.2017.
 */
public class CalculatorInterpreter {
    public static void main(String[] args) {
        Context context = new Context();
        Expression expr = context.evalute("3+1");
        System.out.println(expr.interpret());
    }
}

class Context {
    Expression evalute(String s) {
        int pos = s.length()-1;
        while (pos>0) {
            if (Character.isDigit(s.charAt(pos))) {
                pos--;
            } else {
                Expression leftExpression = evalute(s.substring(0,pos));
                Expression rightExpression = new Number(Integer.valueOf(s.substring(pos+1,s.length())));
                char operator = s.charAt(pos);
                switch (operator) {
                    case '+':
                        return new Addition(leftExpression,rightExpression);
                    case '-':
                        return new Subtraction(leftExpression,rightExpression);
                    case '*':
                        return new Multiply(leftExpression,rightExpression);
                    case '/':
                        return new Division(leftExpression,rightExpression);
                }
            }
        }
        int result = Integer.valueOf(s);
        return new Number(result);
    }
}


interface Expression {
    int interpret();
}

class Number implements Expression {
    private int number;

    Number(int number) {
        this.number = number;
    }

    @Override
    public int interpret() {

        return number;
    }
}


class Addition implements Expression{
    private Expression left;
    private Expression right;

    Addition(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int interpret() {
        return left.interpret()+right.interpret();
    }
}

class Subtraction implements Expression{
    private Expression left;
    private Expression right;

    public Subtraction(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int interpret() {
        return left.interpret()-right.interpret();
    }
}


class Multiply implements Expression{
    private Expression left;
    private Expression right;

    public Multiply(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int interpret() {
        return left.interpret()*right.interpret();
    }
}

class Division implements Expression{

    private Expression left;
    private Expression right;

    public Division(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int interpret() {
        return left.interpret()/right.interpret();
    }
}
