package ru.pattern.structural.decorator.example3;

import java.math.BigDecimal;

/**
 * Created by Евгений on 15.01.2017.
 */
public class AddSpecialResponsibleDemo {
    public static void main(String[] args) {
        Unit unit = new DecorateScience(new DecorateReligion(new StandartUnit()));
        System.out.println("Responsible: "+unit.name());
        System.out.println("Price: "+unit.price());
    }
}


interface Unit {
    BigDecimal price();
    String name();
}

class StandartUnit implements Unit {

    @Override
    public BigDecimal price() {
        return new BigDecimal(10);
    }

    @Override
    public String name() {
        return "Юнит.";
    }
}

abstract  class DecoratorUnit implements Unit {
    Unit unit;

    public DecoratorUnit(Unit unit) {
        this.unit = unit;
    }

}

class DecorateScience extends  DecoratorUnit{

    public DecorateScience(Unit unit) {
        super(unit);
    }

    @Override
    public BigDecimal price() {
        return unit.price().add(new BigDecimal(30));
    }

    @Override
    public String name() {
        return unit.name()+" Научный деятель.";
    }
}

class DecorateReligion extends  DecoratorUnit{

    public DecorateReligion(Unit unit) {
        super(unit);
    }

    @Override
    public BigDecimal price() {
        return unit.price().add(new BigDecimal(15));
    }

    @Override
    public String name() {
        return unit.name()+" Религиозный деятель.";
    }
}