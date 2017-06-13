package ru.testTask.booleanAlg;

import java.util.*;

/**
 * Created by Евгений on 27.11.2016.
 */
public class Test {
    public static void main(String[] args) {

        List<Integer> list = new ArrayList<Integer>(Arrays.asList(1,3,4,5,6,7,8,9));
        boolean flag = false;
        while(!flag) {
            if (list.get(0)!=7) {
                System.out.println("неравно");
                list.remove(0);
                continue;
            } else {
                System.out.println("равно выходим");
                flag = true;
            }
        }
        System.out.println("идем дальше");


    }
}

class Person {
    private String name;

    Person(String name) {
        this.name = name;
    }

    void setName(String name) {
        this.name = name;
    }

    String getName() {
        return  name;
    }

    String getDescription() {
        return "Person name = "+getName();
    }


}


class Employee extends  Person {

    private double salary;

    Employee(String name, double salary) {
        super(name);
        this.salary = salary;
    }


    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    public void raiseSalary(double byPercent) {
        double raise = salary * byPercent / 100;
        salary += raise;
    }

    String getDescription() {
        return super.getDescription()+", salary = "+getSalary();
    }
}

class Manager extends Employee {
    private int reports;

    public Manager(String name, double salary, int reports) {
        super(name, salary);
        this.reports = reports;
    }

    public int getReports() {
        return reports;
    }

    public void setReports(int reports) {
        this.reports = reports;
    }

    String getDescription() {
        return super.getDescription()+", direct reports = "+getReports();
    }
}


abstract class circle  {

    abstract  public void draw();
}