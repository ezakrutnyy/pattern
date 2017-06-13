package ru.pattern.creational.abstractfactory.example3;

/**
 * Created by Евгений on 03.01.2017.
 */
public class AbstractFactoryDemo {
    public static void main(String[] args) {
        String countryCode = "CA";


        Order order = new Order(30.0,150);

        OrderProcessor orderProcessor = null;
        FinancialToolsFactory factory = null;

        if (countryCode == "EU") {
            factory = new EuropeFinancialToolsFactory();
        } else if (countryCode == "CA") {
            factory = new CanadaFinancialToolsFactory();
        }

        orderProcessor = new OrderProcessor(factory);
        orderProcessor.processOrder(order);
    }
}

class Order {
    private double totalPrice;
    private double weight;
    public Order(double totalPrice, double weight) {
        this.totalPrice = totalPrice;
        this.weight = weight;
    }
    public String toString() {
        return "Order [totalPrice=" + totalPrice + ", weight=" + weight + "]";
    }
}

class OrderProcessor {
    TaxProcessor taxProcessor;
    ShipFeeProcessor shipFeeProcessor;

    public OrderProcessor(FinancialToolsFactory factory) {
        taxProcessor = factory.createTaxProcessor();
        shipFeeProcessor = factory.createShipFeeProcessor();
    }

    public void processOrder (Order order)  {
        taxProcessor.calculateTaxes(order);
        shipFeeProcessor.calculateShipFee(order);
    }
}

abstract class FinancialToolsFactory {
    public abstract TaxProcessor createTaxProcessor();
    public abstract ShipFeeProcessor createShipFeeProcessor();
}

class CanadaFinancialToolsFactory extends FinancialToolsFactory {
    public TaxProcessor createTaxProcessor() {
        return new CanadaTaxProcessor();
    }
    public ShipFeeProcessor createShipFeeProcessor() {
        return new CanadaShipFeeProcessor();
    }
}

class EuropeFinancialToolsFactory extends FinancialToolsFactory {
    public TaxProcessor createTaxProcessor() {
        return new EuropeTaxProcessor();
    }
    public ShipFeeProcessor createShipFeeProcessor() {
        return new EuropeShipFeeProcessor();
    }
}

abstract class TaxProcessor {
    abstract void calculateTaxes(Order order);
}

class CanadaTaxProcessor extends TaxProcessor {
    public void calculateTaxes(Order order) {
        System.out.println("Calculating taxes for Canada.....");
    }
}

class EuropeTaxProcessor extends TaxProcessor {
    public void calculateTaxes(Order order) {

        System.out.println("Calculating taxes for EU.....");
    }
}

abstract class ShipFeeProcessor {
    abstract void calculateShipFee(Order order);
}

class CanadaShipFeeProcessor extends ShipFeeProcessor {
    public void calculateShipFee(Order order) {
        System.out.println("Calculating shipping fees for Canada.....");
    }
}

class EuropeShipFeeProcessor extends ShipFeeProcessor {
    public void calculateShipFee(Order order) {

        System.out.println("Calculating shipping fees for EU.....");
    }
}