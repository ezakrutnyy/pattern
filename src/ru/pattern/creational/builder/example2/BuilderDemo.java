package ru.pattern.creational.builder.example2;

/**
 * Created by Евгений on 06.01.2017.
 */
public class BuilderDemo {
    public static void main(String[] args) {

        CivilEngineer engineer = new CivilEngineer(new IglooHouseBuilder());
        engineer.constructHouse();
        House house = engineer.getHouse();
        System.out.println("Builder constructed: " + house);
    }
}

class CivilEngineer {

    private HouseBuilder houseBuilder;

    CivilEngineer(HouseBuilder houseBuildzer) {
        this.houseBuilder = houseBuildzer;
    }

    public House getHouse() {
        return this.houseBuilder.getHouse();
    }

    public void constructHouse() {
        this.houseBuilder.buildBasement();
        this.houseBuilder.buildStructure();
        this.houseBuilder.bulidRoof();
        this.houseBuilder.buildInterior();
    }
}


interface HouseBuilder {

    public void buildBasement();

    public void buildStructure();

    public void bulidRoof();

    public void buildInterior();

    public House getHouse();
}

class IglooHouseBuilder implements HouseBuilder {

    private House house;

    public IglooHouseBuilder() {
        this.house = new House();
    }

    public void buildBasement() {
        house.setBasement("Ice Bars");
    }

    public void buildStructure() {
        house.setStructure("Ice Blocks");
    }

    public void buildInterior() {
        house.setInterior("Ice Carvings");
    }

    public void bulidRoof() {
        house.setRoof("Ice Dome");
    }

    public House getHouse() {
        return this.house;
    }
}

class TipiHouseBuilder implements HouseBuilder {
    private House house;

    public TipiHouseBuilder() {
        this.house = new House();
    }

    public void buildBasement() {
        house.setBasement("Wooden Poles");
    }

    public void buildStructure() {
        house.setStructure("Wood and Ice");
    }

    public void buildInterior() {
        house.setInterior("Fire Wood");
    }

    public void bulidRoof() {
        house.setRoof("Wood, caribou and seal skins");
    }

    public House getHouse() {
        return this.house;
    }
}

interface HousePlan {

    public void setBasement(String basement);

    public void setStructure(String structure);

    public void setRoof(String roof);

    public void setInterior(String interior);
}

class House implements HousePlan {

    private String basement;
    private String structure;
    private String roof;
    private String interior;

    public void setBasement(String basement) {
        this.basement = basement;
    }

    public void setStructure(String structure) {
        this.structure = structure;
    }

    public void setRoof(String roof) {
        this.roof = roof;
    }

    public void setInterior(String interior) {
        this.interior = interior;
    }

    public String toString() {
        return "House [basement=" + basement + ", structure=" + structure
                + ", roof=" + roof + ", interior=" + interior + "]";
    }
}