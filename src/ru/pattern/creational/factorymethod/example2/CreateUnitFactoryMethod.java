package ru.pattern.creational.factorymethod.example2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Евгений on 29.12.2016.
 */
public class CreateUnitFactoryMethod {
    public static void main(String[] args) throws Exception {

        List<TypeUnit> units = new ArrayList<TypeUnit>();

        FactoryMaker maker = getFactoryMaker(UnitTypes.CAVALRY);
        units.add( maker.createUnit());

        maker = getFactoryMaker(UnitTypes.ARCHER);
        units.add( maker.createUnit());
        units.add( maker.createUnit());

        maker = getFactoryMaker(UnitTypes.INFANTRY);
        units.add( maker.createUnit());
        units.add( maker.createUnit());
        units.add( maker.createUnit());
        for(TypeUnit unit : units) {
            unit.showUnit();
        }
    }

    static  FactoryMaker getFactoryMaker(UnitTypes type) throws Exception {
        if (type.equals(UnitTypes.CAVALRY)) {
            return new CavalryMaker();
        } else if (type.equals(UnitTypes.ARCHER)) {
            return new ArcherMaker();
        } else if (type.equals(UnitTypes.INFANTRY)) {
            return new InfantryMaker();
        } else {
            throw new Exception("This type is not supported");
        }
    }
}

enum UnitTypes {
    CAVALRY,
    ARCHER,
    INFANTRY
}

interface FactoryMaker {
    TypeUnit createUnit();
}

class CavalryMaker implements  FactoryMaker {

    @Override
    public TypeUnit createUnit() {
        return new Cavalry();
    }
}
class ArcherMaker implements  FactoryMaker {

    @Override
    public TypeUnit createUnit() {
        return new Archer();
    }
}
class InfantryMaker implements  FactoryMaker {

    @Override
    public TypeUnit createUnit() {
        return new Infantry();
    }
}


interface TypeUnit {
    void showUnit();
}

class Cavalry implements TypeUnit {
    @Override
    public void showUnit() {
        System.out.println("Кавалерист");
    }
}

class Archer implements TypeUnit {
    @Override
    public void showUnit() {
        System.out.println("Стрелок");
    }
}

class Infantry implements TypeUnit {
    @Override
    public void showUnit() {
        System.out.println("Ближний бой");
    }
}