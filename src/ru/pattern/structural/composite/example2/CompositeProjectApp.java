package ru.pattern.structural.composite.example2;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Евгений on 12.11.2016.
 */
public class CompositeProjectApp {
    public static void main(String[] args) {

        Project projectOne = new Project();
        projectOne.setName("Alfa Bank");
        projectOne.setDescription("Задачи связанные с Альфа Банком");

        Task task = new Task();
        task.setName("Кредитный договор создание и изменения API");
        task.setTimeRequired(80);

        Task task1 = new Task();
        task1.setName("Кредитный договор создание и изменения Интерфейс");
        task1.setTimeRequired(40);

        Task task2 = new Task();
        task2.setName("Справочник связанных документов API");
        task2.setTimeRequired(8);

        Task task3 = new Task();
        task3.setName("Справочник связанных документов API");
        task3.setTimeRequired(16);

        projectOne.addProjectItem(task);
        projectOne.addProjectItem(task1);
        projectOne.addProjectItem(task2);
        projectOne.addProjectItem(task3);
        projectOne.print();
        System.out.println(projectOne.getTimeRequired());



    }
}

interface ProjectItems {
    double getTimeRequired();
}

class Project implements ProjectItems {

    private ArrayList<ProjectItems> projectltems = new ArrayList();

    private String name;
    private String description;

    public Project() {}

    public Project(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public double getTimeRequired() {
        double totalTime = 0;
        Iterator items = projectltems.iterator();
        while (items.hasNext()) {
            ProjectItems item = (ProjectItems) items.next();
            totalTime += item.getTimeRequired();
        }
        return  totalTime;
    }

    public ArrayList<ProjectItems> getProjectltems() {
        return projectltems;
    }

    public void addProjectItem(ProjectItems item) {
        if (!projectltems.contains(item)) {
            projectltems.add(item);
        }
    }
    public void print() {
        Iterator items = projectltems.iterator();
        while (items.hasNext()) {
            ProjectItems item = (ProjectItems) items.next();
            System.out.println(item);
        }
    }

    public void removeProjectItem(ProjectItems item) {
        projectltems.remove(item);
    }

}

class Task implements ProjectItems {
    private String name;

    public Task() {}

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", details='" + details + '\'' +
                ", projectltems=" + projectltems +
                ", timeRequired=" + timeRequired +
                '}';
    }

    public Task(String name, String details, double timeRequired) {
        this.name = name;
        this.details = details;
        this.timeRequired = timeRequired;
    }

    private String details;
    private ArrayList<ProjectItems> projectltems = new ArrayList();
    private double timeRequired;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public ArrayList<ProjectItems> getProjectltems() {
        return projectltems;
    }


    @Override
    public double getTimeRequired() {
        double totalTime = timeRequired;
        Iterator<ProjectItems> items = projectltems.iterator();
        while(items.hasNext()) {
            ProjectItems item = (ProjectItems)items.next();
            totalTime += item.getTimeRequired();
        }
        return totalTime;
    }

    public void setTimeRequired(double timeRequired) {
        this.timeRequired = timeRequired;
    }

    public void addProjectltem(ProjectItems item) {
        if (projectltems.contains(item)) {
            projectltems.add(item);
        }
    }

    public void removeProjectltem(ProjectItems item) {
        projectltems.remove(item);
    }
}
