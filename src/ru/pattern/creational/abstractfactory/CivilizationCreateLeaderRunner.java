package ru.pattern.creational.abstractfactory;

/**
 * Created by Евгений on 03.01.2017.
 */


public class CivilizationCreateLeaderRunner {
    public static void main(String[] args) {
        String nation = "Russia";
        CreateUnitAbstractFactory factory = CreateUnitAbstractFactory.choiseFactory(nation);
        Leader leader = factory.createLeader();
        Place place = factory.createPlace();
        System.out.println(leader.getName());
        System.out.println(place.getName());
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


abstract class Leader {
    private String name;

    Leader(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }
}

class RussianLeader extends Leader {
    RussianLeader() {
        super(LeaderEnum.RUSSIA.getName());
    }
}

class IndianLeader extends Leader {
    IndianLeader() {
        super(LeaderEnum.INDIA.getName());
    }
}

class EgyptianLeader extends Leader {
    EgyptianLeader() {
        super(LeaderEnum.EGYPT.getName());
    }
}

class FrancianLeader extends Leader {
    FrancianLeader() {
        super(LeaderEnum.FRANCE.getName());
    }
}

class GermanianLeader extends Leader {
    GermanianLeader() {
        super(LeaderEnum.GERMANY.getName());
    }
}

abstract class Place {
    private String name;

    Place(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }
}

class RussianPlace extends Place {
    RussianPlace() {
        super(PlaceEnum.RUSSIA.getName());
    }
}

class IndianPlace extends Place {
    IndianPlace() {
        super(PlaceEnum.INDIA.getName());
    }
}

class EgyptianPlace extends Place {
    EgyptianPlace() {
        super(PlaceEnum.EGYPT.getName());
    }
}

class FrancianPlace extends Place {
    FrancianPlace() {
        super(PlaceEnum.FRANCE.getName());
    }
}

class GermanianPlace extends Place {
    GermanianPlace() {
        super(PlaceEnum.GERMANY.getName());
    }
}


abstract class CreateUnitAbstractFactory {
    abstract Leader createLeader();

    abstract Place createPlace();

    static CreateUnitAbstractFactory choiseFactory(String nation) {
        switch (nation) {
            case "Russia":
                return new CreateRussianUnit();
            case "India":
                return new CreateIndianUnit();
            case "Egypt":
                return new CreateEgyptianUnit();
            case "France":
                return new CreateFrancianUnit();
            case "Germany":
                return new CreateGermanianUnit();
            default:
                return new CreateGermanianUnit();
        }
    }

}

class CreateRussianUnit extends CreateUnitAbstractFactory {

    @Override
    Leader createLeader() {
        return new RussianLeader();
    }

    @Override
    Place createPlace() {
        return new RussianPlace();
    }
}

class CreateIndianUnit extends CreateUnitAbstractFactory {

    @Override
    Leader createLeader() {
        return new IndianLeader();
    }

    @Override
    Place createPlace() {
        return new IndianPlace();
    }
}

class CreateEgyptianUnit extends CreateUnitAbstractFactory {

    @Override
    Leader createLeader() {
        return new EgyptianLeader();
    }

    @Override
    Place createPlace() {
        return new EgyptianPlace();
    }
}

class CreateFrancianUnit extends CreateUnitAbstractFactory {

    @Override
    Leader createLeader() {
        return new FrancianLeader();
    }

    @Override
    Place createPlace() {
        return new FrancianPlace();
    }
}

class CreateGermanianUnit extends CreateUnitAbstractFactory {

    @Override
    Leader createLeader() {
        return new GermanianLeader();
    }

    @Override
    Place createPlace() {
        return new GermanianPlace();
    }
}

