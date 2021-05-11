package ru.pattern.structural.composite;

import com.google.common.collect.Lists;

import java.util.List;

public class ProjectAppCompositeRunner {

    public static void main(String[] args) {

        Task project = new Task("VTB", "Project for VTB");
        Task apiContractTask = new Task("Api. Contract", "Create API for contract functionality", 80);
        Task interfaceContractTask = new Task("Interface. Contract", "Create Interface for contract functionality", 45);
        Task apiDictionaryForContractTask = new Task("Api. Dictionary. Contract", "Create api dictionaries for contract", 16);
        Task interfaceDictionaryForContractTask = new Task("Interface. Dictionary. Contract", "Create interface dictionaries for contract", 16);

        project.addTask(apiContractTask);
        project.addTask(interfaceContractTask);
        project.addTask(apiDictionaryForContractTask);
        project.addTask(interfaceDictionaryForContractTask);

        System.out.println("All time days: " + project.calculateDays());
    }
}


interface Calculator {
    int calculateDays();
}

class Task implements Calculator {

    final private List<Calculator> compose = Lists.newArrayList();

    private final String name;

    private final String details;

    private final int days;

    public Task(String name, String details) {
        this.name = name;
        this.details = details;
        this.days = 0;
    }

    public Task(String name, String details, int days) {
        this.name = name;
        this.details = details;
        this.days = days;
    }

    public void addTask(Task task) {
        compose.add(task);
    }

    public void removeTask(Task task) {
        compose.remove(task);
    }

    @Override
    public int calculateDays() {
        int allDays = this.days;
        for (Calculator item : compose) {
            allDays += item.calculateDays();
        }
        return allDays;
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", details='" + details + '\'' +
                ", compose=" + compose +
                ", days=" + days +
                '}';
    }
}

