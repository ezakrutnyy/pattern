package ru.pattern.creational.prototype;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Евгений on 20.12.2016.
 * <p>
 * Создадим приложение создающее новые цивилизации юнитов
 * UnitUniqueIF - интерфейс , который реализует Clonable, в нем основные методы для всех расс
 */
public class CreateUnitPrototypeRunner {
    private static Random random = new Random();
    private static int amount = 400;

    public static void main(String[] args) {
        CompositeUnit listUnitIdeal = new CompositeUnit();
        File cavalryFile = new File("C:\\Users\\zakru\\IdeaProjects\\JavaTask\\resources\\nationfile\\Cavalry.txt");
        File archerFile = new File("C:\\Users\\zakru\\IdeaProjects\\JavaTask\\resources\\nationfile\\Archer.txt");
        File infantryFile = new File("C:\\Users\\zakru\\IdeaProjects\\JavaTask\\resources\\nationfile\\Infantry.txt");

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(cavalryFile));
            String parseStr = null;
            do {
                parseStr = reader.readLine();
                if (parseStr != null) {
                    String[] t = parseStr.split("/");
                    listUnitIdeal.addUnit(new Cavalry(t[0], t[1], Integer.parseInt(t[2]), Integer.parseInt(t[3]), Integer.parseInt(t[4]), Integer.parseInt(t[5])));
                }
            } while (parseStr != null);


            reader = new BufferedReader(new FileReader(archerFile));
            parseStr = null;
            do {
                parseStr = reader.readLine();
                if (parseStr != null) {
                    String[] t = parseStr.split("/");
                    listUnitIdeal.addUnit(new Archer(t[0], t[1], Integer.parseInt(t[2]), Integer.parseInt(t[3]), Integer.parseInt(t[4]), Integer.parseInt(t[5])));
                }
            } while (parseStr != null);


            reader = new BufferedReader(new FileReader(infantryFile));
            parseStr = null;
            do {
                parseStr = reader.readLine();
                if (parseStr != null) {
                    String[] t = parseStr.split("/");
                    listUnitIdeal.addUnit(new Infantry(t[0], t[1], Integer.parseInt(t[2]), Integer.parseInt(t[3]), Integer.parseInt(t[4]), Integer.parseInt(t[5])));
                }
            } while (parseStr != null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        List<PrototypeTypeUnit> packetUnit = new ArrayList<>();

        while (amount >= listUnitIdeal.getMinAmount()) {
            int genKey = random.nextInt(listUnitIdeal.getAllunit().size());
            PrototypeTypeUnit unit = listUnitIdeal.getAllunit().get(genKey);
            if (unit.getCost() > amount)
                continue;
            packetUnit.add(unit.clone());
            amount -= unit.getCost();

        }
        System.out.println("самый дешевый юнит = " + listUnitIdeal.getMinAmount());
        System.out.println("Остаток = " + amount);
        System.out.println("В пакете следующие юниты");
        System.out.println(packetUnit);
    }


}

class PrototypeTypeUnit implements Cloneable {
    private String name;
    private String nations;
    private Integer cost;
    private Integer near;

    public Integer getNear() {
        return near;
    }

    public void setNear(Integer near) {
        this.near = near;
    }

    public Integer getFar() {
        return far;
    }

    public void setFar(Integer far) {
        this.far = far;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    public PrototypeTypeUnit(String name, String nations, Integer cost, Integer near, Integer far, Integer step) {
        this.name = name;
        this.nations = nations;
        this.cost = cost;
        this.near = near;
        this.far = far;
        this.step = step;
    }

    private Integer far;
    private Integer step;


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setNations(String nations) {
        this.nations = nations;
    }

    public String getNations() {
        return nations;
    }


    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Integer getCost() {
        return cost;
    }

    public PrototypeTypeUnit clone() {
        try {
            return (PrototypeTypeUnit) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new InternalError();
        }
    }

    public String toString() {
        return String.format("Юнит: %s Нация: %s Стоимость: %d  Ближний бой: %s Дальний бой: %s Шаги: %s\n", name, nations, cost, near, far, step);
    }
}

class Cavalry extends PrototypeTypeUnit {

    private Integer speed;

    public Cavalry(String name, String nations, Integer cost, Integer near, Integer far, Integer step) {
        super(name, nations, cost, near, far, step);
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    Integer getSpeed() {
        return speed;
    }
}

class Archer extends PrototypeTypeUnit {
    private Integer farBattle;

    public Archer(String name, String nations, Integer cost, Integer near, Integer far, Integer step) {
        super(name, nations, cost, near, far, step);
    }

    public void setFarBattle(Integer farBattle) {
        this.farBattle = farBattle;
    }

    Integer getFarBattle() {
        return farBattle;
    }
}

class Infantry extends PrototypeTypeUnit {
    private Integer infighting;

    public Infantry(String name, String nations, Integer cost, Integer near, Integer far, Integer step) {
        super(name, nations, cost, near, far, step);
    }

    public void setInfighting(Integer infighting) {
        this.infighting = infighting;
    }

    Integer getInfighting() {
        return infighting;
    }
}

class CompositeUnit {
    List<PrototypeTypeUnit> listUnitIdeal = new ArrayList<>();

    void addUnit(PrototypeTypeUnit unit) {
        listUnitIdeal.add(unit);
    }

    void removeUnit(PrototypeTypeUnit unit) {
        listUnitIdeal.remove(unit);
    }

    List<PrototypeTypeUnit> getAllunit() {
        return listUnitIdeal;
    }

    int getMinAmount() {
        int min = listUnitIdeal.get(0).getCost();
        for (PrototypeTypeUnit concreteUnit : listUnitIdeal) {
            if (min > concreteUnit.getCost()) {
                min = concreteUnit.getCost();
            }
        }

        return min;
    }

}
