package ru.pattern.creational.builder;


/**
 * Created by Евгений on 15.01.2017.
 */
public class CreateUnitBuilderRunner {
    public static void main(String[] args) {
        Director director = new Director();
        director.setBuilder(new FrUniqueUnitBuilder());

        Unit rusUniqueUnit = director.build();
        System.out.println(rusUniqueUnit);
    }
}

class Unit {

    private String name;

    private String leader;

    private String place;

    public void setName(String name) {
        this.name = name;
    }

    public void setLeader(LeaderEnum leader) {
        this.leader = leader.getName();
    }

    public void setPlace(PlaceEnum place) {
        this.place = place.getName();
    }

    @Override
    public String toString() {
        return "Unit{" +
                "name='" + name + '\'' +
                ", leader=" + leader +
                ", place=" + place +
                '}';
    }
}

abstract class UniqueUnitBuilder {
    Unit unit;

    void createUnit() {
        unit = new Unit();
    }

    abstract void buildName();

    abstract void buildLeader();

    abstract void buildPlace();

    Unit getUnit() {
        return unit;
    }
}

class RusUniqueUnitBuilder extends UniqueUnitBuilder {

    @Override
    void buildName() {
        unit.setName("Казак");
    }

    @Override
    void buildLeader() {
        unit.setLeader(LeaderEnum.RUSSIA);
    }

    @Override
    void buildPlace() {
        unit.setPlace(PlaceEnum.RUSSIA);
    }
}


class FrUniqueUnitBuilder extends UniqueUnitBuilder {

    @Override
    void buildName() {
        unit.setName("Мушкетер");
    }

    @Override
    void buildLeader() {
        unit.setLeader(LeaderEnum.FRANCE);
    }

    @Override
    void buildPlace() {
        unit.setPlace(PlaceEnum.FRANCE);
    }
}


class Director {
    private UniqueUnitBuilder builder;

    public void setBuilder(UniqueUnitBuilder builder) {
        this.builder = builder;
    }

    Unit build() {
        builder.createUnit();
        builder.buildName();
        builder.buildLeader();
        builder.buildPlace();
        return builder.getUnit();
    }
}

enum LeaderEnum {
    RUSSIA("Петр I"),
    FRANCE("Шарль де Голль"),
    GERMANY("Бисмарк"),
    INDIA("Ганди"),
    EGYPT("Рамзес II");

    private final String name;

    LeaderEnum(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }
}

enum PlaceEnum {
    RUSSIA("Кремль"),
    FRANCE("Эйфелева башня"),
    GERMANY("Рейхстаг"),
    INDIA("Тадж-Махал"),
    EGYPT("Пирамида Хеопса");

    private final String name;

    PlaceEnum(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }
}