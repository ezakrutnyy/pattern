package ru.pattern.creational.abstractfactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;

/**
 * Created by Евгений on 03.01.2017.
 */

public class CreditCalculatorRunner {
    public static void main(String args[]) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Bank name:");
        String bankName = br.readLine();
        System.out.println("Type loan:");
        String loanName = br.readLine();

        AbstractFactory bankFactory = FactoryCreator.getFactory("Bank");
        Bank b = bankFactory.getBank(bankName);

        String search = (b.getBankName() + "_" + loanName).toUpperCase();

        double rate = Rate.valueOf(search).getValue();
        System.out.println("В выбранном банке " + b.getBankName() +
                " процентная ставка для типа кредита " + loanName + " = " + rate);


        System.out.println("Сумма кредита: ");
        double loanAmount = Double.parseDouble(br.readLine());
        System.out.println("Срок (в годах): ");
        int years = Integer.parseInt(br.readLine());

        System.out.println("Вы взяли кредит в банке: " + b.getBankName());

        AbstractFactory loanFactory = FactoryCreator.getFactory("Loan");
        Loan l = loanFactory.getLoan(loanName);
        l.getInterestRate(rate);
        l.calculateLoanPayment(loanAmount, years);

    }
}

enum Rate {

    SBERBANK_LOAN(19.5),
    SBERBANK_AUTO(10),
    SBERBANK_MORTGAGE(7.5),

    ALFABANK_LOAN(20),
    ALFABANK_AUTO(17),
    ALFABANK_MORTGAGE(12),


    TINKOFF_LOAN(15),
    TINKOFF_AUTO(7),
    TINKOFF_MORTGAGE(6.7);

    private final double rate;

    Rate(double rate) {
        this.rate = rate;
    }


    double getValue() {
        return rate;
    }
}

interface Bank {
    String getBankName();
}

class SberBank implements Bank {
    private final String name = "Sberbank";

    @Override
    public String getBankName() {
        return name;
    }
}

class AlfaBank implements Bank {
    private final String name = "Alfabank";

    @Override
    public String getBankName() {
        return name;
    }

}

class Tinkoff implements Bank {
    private final String name = "Tinkoff";

    @Override
    public String getBankName() {
        return name;
    }

}

abstract class Loan {
    protected double rate;

    abstract void getInterestRate(double rate);

    public void calculateLoanPayment(double loanamount, int years) {
        /*
              to calculate the monthly loan payment i.e. EMI

              rate=annual interest rate/12*100;
              n=number of monthly installments;
              1year=12 months.
              so, n=years*12;

            */
        double pay;
        int n;

        n = years * 12;
        rate = rate / 1200;
        pay = ((rate * Math.pow((1 + rate), n)) / ((Math.pow((1 + rate), n)) - 1)) * loanamount;

        System.out.println("Ежемесячный платеж составит:" + new BigDecimal(pay).setScale(2, BigDecimal.ROUND_HALF_UP));
    }
}

class Loans extends Loan {
    public void getInterestRate(double r) {
        rate = r;
    }
}

class Auto extends Loan {
    public void getInterestRate(double r) {
        rate = r;
    }

}

class Mortgage extends Loan {
    public void getInterestRate(double r) {
        rate = r;
    }
}

abstract class AbstractFactory {
    public abstract Bank getBank(String bank);

    public abstract Loan getLoan(String loan);
}

class BankFactory extends AbstractFactory {
    public Bank getBank(String bank) {
        if (bank == null) {
            return null;
        }
        if (bank.equalsIgnoreCase("sberbank")) {
            return new SberBank();
        } else if (bank.equalsIgnoreCase("alfabank")) {
            return new AlfaBank();
        } else if (bank.equalsIgnoreCase("tinkoff")) {
            return new Tinkoff();
        }
        return null;
    }

    public Loan getLoan(String loan) {
        return null;
    }
}

class LoanFactory extends AbstractFactory {
    public Bank getBank(String bank) {
        return null;
    }

    public Loan getLoan(String loan) {
        if (loan == null) {
            return null;
        }
        if (loan.equalsIgnoreCase("loan")) {
            return new Loans();
        } else if (loan.equalsIgnoreCase("auto")) {
            return new Auto();
        } else if (loan.equalsIgnoreCase("mortgage")) {
            return new Mortgage();
        }
        return null;
    }

}

class FactoryCreator {
    public static AbstractFactory getFactory(String choice) {
        if (choice.equalsIgnoreCase("Bank")) {
            return new BankFactory();
        } else if (choice.equalsIgnoreCase("Loan")) {
            return new LoanFactory();
        }
        return null;
    }
}