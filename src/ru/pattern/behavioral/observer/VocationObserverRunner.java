package ru.pattern.behavioral.observer;

import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class VocationObserverRunner {

    public static void main(String[] args) {

        // Создаем портал с вакансиями
        HeadHunterSuite suite = new HeadHunterSuite();

        // Создаем подписчиков
        Employee javaEmployee = new BackEmployee("Java developer");
        Employee reactEmployee = new FrontEmployee("ReactJs developer");


        // Подписываемся на рассылку
        suite.addSubscriber(javaEmployee);
        suite.addSubscriber(reactEmployee);


        // Приходят вакансии
        Vacancy vacancy1 = new Vacancy("back", "Luxoft", new BigDecimal(200000));
        Vacancy vacancy2 = new Vacancy("back", "Sber", new BigDecimal(220000));
        Vacancy vacancy3 = new Vacancy("tester", "Vtb", new BigDecimal(100000));
        Vacancy vacancy4 = new Vacancy("front", "Vtb", new BigDecimal(300000));
        suite.addVacancy(vacancy1);
        suite.removeVacancy(vacancy1);
        suite.addVacancy(vacancy2);
        suite.addVacancy(vacancy3);
        suite.addVacancy(vacancy4);
    }
}


interface VocationPublisher {

    boolean addSubscriber(Subscriber subscriber);

    boolean removeSubscriber(Subscriber subscriber);

    void notifySubscriber();
}

class HeadHunterSuite implements VocationPublisher {


    private List<Subscriber> subscribers;

    private List<Vacancy> vacancies;

    public HeadHunterSuite() {
        this.subscribers = Lists.newArrayList();
        this.vacancies = Lists.newArrayList();
    }

    public boolean addVacancy(Vacancy vacancy) {
        boolean rs = this.vacancies.add(vacancy);
        if (rs) notifySubscriber();
        return rs;
    }

    public boolean removeVacancy(Vacancy vacancy) {
        boolean rs = this.vacancies.remove(vacancy);
        if (rs) notifySubscriber();
        return rs;
    }

    @Override
    public boolean addSubscriber(Subscriber subscriber) {
        return this.subscribers.add(subscriber);
    }

    @Override
    public boolean removeSubscriber(Subscriber subscriber) {
        return this.subscribers.remove(subscriber);
    }

    @Override
    public void notifySubscriber() {
        for (Subscriber subscriber : subscribers) {
            subscriber.handle(vacancies);
        }
    }
}


interface Subscriber {

    void handle(List<Vacancy> vacancies);
}

class Employee implements Subscriber {

    private final String name;

    Employee(String name) {
        this.name = name;
    }

    @Override
    public void handle(List<Vacancy> vacancies) {
        System.out.println(String.format("Dear %s! We change our vacancies [%s]. Look at!!!", name, vacancies));
    }
}

class BackEmployee extends Employee {

    BackEmployee(String name) {
        super(name);
    }


    public void handle(List<Vacancy> vacancies) {
        List<Vacancy> filterVacancies = vacancies.stream()
                .filter(vacancy -> vacancy.getScope().equalsIgnoreCase("back"))
                .collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(filterVacancies)) super.handle(filterVacancies);
    }
}

class FrontEmployee extends Employee {

    FrontEmployee(String name) {
        super(name);
    }

    public void handle(List<Vacancy> vacancies) {
        List<Vacancy> filterVacancies = vacancies.stream()
                .filter(vacancy -> vacancy.getScope().equalsIgnoreCase("front"))
                .collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(filterVacancies)) super.handle(filterVacancies);
    }
}

class Vacancy {

    private final String scope;

    private final String name;

    private final BigDecimal income;

    public Vacancy(String scope, String name, BigDecimal income) {
        this.scope = scope;
        this.name = name;
        this.income = income;
    }


    public String getScope() {
        return scope;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getIncome() {
        return income;
    }

    @Override
    public String toString() {
        return "Vacancy{" +
                "scope='" + scope + '\'' +
                ", name='" + name + '\'' +
                ", income=" + income +
                '}';
    }
}
