package ru.pattern.creational.factorymethod.example3;

/**
 * Created by Евгений on 03.01.2017.
 */

enum TYPECLIENT {

    ENGINEER(0),
    SALESMAN(1),
    MANAGER(2);

    private int value;

    TYPECLIENT(int value) {
        this.value = value;
    }

    int getValue() {
        return value;
    }
}
public class FactoryMethodDemo {
    public static void main(String[] args) {
        Employee employee = new Employee(TYPECLIENT.ENGINEER.getValue());
        //Employee employee = Employee.createEmployee(TYPECLIENT.SALESMAN.getValue());
        employee.showType();
    }
}

class Employee {
    private int type;

    Employee(int type) {
        this.type = type;
    }
    Employee(){}
    void showType() {
        System.out.println("I'm Employee");
    }

    static Employee createEmployee(int type) {
        switch(type) {
            case 0:
                return new Engineer();
            case 1:
                return new Salesman();
            case 2:
                return new Manager();
            default:
                return  new Employee();
        }
    }
}

class  Engineer extends Employee {
    void showType() {
        System.out.println("I'm Engineer");
    }
}
class  Salesman extends Employee {
    void showType() {
        System.out.println("I'm Salesman");
    }
}
class  Manager extends Employee {
    void showType() {
        System.out.println("I'm Manager");
    }
}