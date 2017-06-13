package ru.pattern.creational.builder.example4;

/**
 * Created by Евгений on 15.01.2017.
 */
public class BuilderDemoSimpleParams {
    public static void main(String[] args) {
        //UnitCavalery concreteUnit1 = new Getair("Гетайр",5,14,0);
        UnitCavalery concreteUnit1 = new BuilderGetair().buildStep(7).buildNearFight(30).build();
        UnitCavalery concreteUnit2 = new Sypah("Сипах",5,25,0);
        System.out.println(concreteUnit1);
        System.out.println(concreteUnit2);

    }
}

abstract class UnitCavalery {
    private String name;

    public UnitCavalery(String name, int step, int nearFight, int farFight) {
        this.name = name;
        this.step = step;
        this.nearFight = nearFight;
        this.farFight = farFight;
    }

    private int step;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public int getNearFight() {
        return nearFight;
    }

    public void setNearFight(int nearFight) {
        this.nearFight = nearFight;
    }

    public int getFarFight() {
        return farFight;
    }

    public void setFarFight(int farFight) {
        this.farFight = farFight;
    }

    private int nearFight;
    private int farFight;

    @Override
    public String toString() {
        return "UnitCavalery{" +
                "name='" + name + '\'' +
                ", step=" + step +
                ", nearFight=" + nearFight +
                ", farFight=" + farFight +
                '}';
    }
}

class Getair extends UnitCavalery {
    public Getair(String name, int step, int nearFight, int farFight) {
        super(name, step, nearFight, farFight);
    }
}

class Sypah extends UnitCavalery {
    public Sypah(String name, int step, int nearFight, int farFight) {
        super(name, step, nearFight, farFight);
    }
}

class BuilderGetair {
    private String name = "Гетайр";
    private int step = 5;
    private int nearFight = 14;
    private int farFight = 0;

    BuilderGetair buildStep(int step) {
        this.step = step;
        return this;
    }

    BuilderGetair buildNearFight(int nearFight) {
        this.nearFight = nearFight;
        return this;
    }

    UnitCavalery build() {
        return new Getair(name,step,nearFight,farFight);
    }

}


