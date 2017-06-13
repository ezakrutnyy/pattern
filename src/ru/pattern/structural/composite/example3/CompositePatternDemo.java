package ru.pattern.structural.composite.example3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Евгений on 17.01.2017.
 */
public class CompositePatternDemo {
    public static void main(String[] args) {
        Employee emp1=new Cashier(101,"Sohan Kumar", 20000.0);
        Employee emp2=new Cashier(102,"Mohan Kumar", 25000.0);
        Employee emp3=new Accountant(103,"Seema Mahiwal", 30000.0);
        Employee manager1=new BankManager(100,"Ashwani Rajput",100000.0);
        manager1.add(emp1);
        manager1.add(emp2);
        manager1.add(emp3);
        manager1.print();
    }
}

interface Employee {
    public  int getId();
    public String getName();
    public double getSalary();
    public void print();
    public void add(Employee employee);
    public void remove(Employee employee);
    public Employee getChild(int i);
}// End of the Employee interface.

class BankManager implements Employee {
    private int id;
    private String name;
    private double salary;

    public BankManager(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    List<Employee> employees = new ArrayList<Employee>();

    @Override
    public void add(Employee employee) {
        employees.add(employee);
    }

    @Override
    public Employee getChild(int i) {
        return employees.get(i);
    }

    @Override
    public void remove(Employee employee) {
        employees.remove(employee);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getSalary() {
        return salary;
    }

    @Override
    public void print() {
        System.out.println("=======BankManager===========");
        System.out.println("Id =" + getId());
        System.out.println("Name =" + getName());
        System.out.println("Salary =" + getSalary());
        System.out.println("==========================");

        Iterator<Employee> it = employees.iterator();

        while (it.hasNext()) {
            Employee employee = it.next();
            employee.print();
        }
    }
}

class Cashier extends  BankManager implements Employee{

    public Cashier(int id,String name,double salary)  {
        super(id, name, salary);
    }

    @Override
    public void print() {
        System.out.println("=========Cashier============ ");
        System.out.println("Id ="+getId());
        System.out.println("Name ="+getName());
        System.out.println("Salary ="+getSalary());
        System.out.println("==========================");
    }
}


class Accountant extends BankManager implements Employee{

    public Accountant(int id,String name,double salary)  {
        super(id, name, salary);
    }
    @Override
    public void print() {
        System.out.println("=========Accountant=========");
        System.out.println("Id ="+getId());
        System.out.println("Name ="+getName());
        System.out.println("Salary ="+getSalary());
        System.out.println("=========================");
    }
}