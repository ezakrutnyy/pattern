package ru.pattern.structural.proxy;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Евгений on 16.01.2017.
 */
public class OfficeInternetAccessProxyRunner {
    public static void main(String[] args) {
        OfficeInternetAccess access = new ProxyInternetAccess("Petrov Max");
        access.grantInternetAccess();
    }
}

// Step1 Create an OfficeInternetAccess interface.
interface OfficeInternetAccess {
    void grantInternetAccess();
}

// Step2 Create a RealInternetAccess class that will implement OfficeInternetAccess interface
// for granting the permission to the specific employee.
class RealInternetAccess implements OfficeInternetAccess {

    private String employeeName;

    public RealInternetAccess(String employeeName) {
        this.employeeName = employeeName;
    }

    @Override
    public void grantInternetAccess() {
        System.out.println("Internet Access granted for employee: " + employeeName);
    }
}

// Step3 Create a ProxyInternetAccess class that will implement OfficeInternetAccess interface for providing the object
// of RealInternetAccess.
class ProxyInternetAccess implements OfficeInternetAccess {

    private RealInternetAccess realAccess;

    private String employeeName;

    final private static Set<String> personList;

    static {
        personList = new HashSet<String>();
        personList.add("Ivanov Ivan");
        personList.add("Petrov Petr");
        personList.add("Semenov Semyon");
    }

    public ProxyInternetAccess(String employeeName) {
        this.employeeName = employeeName;
    }

    @Override
    public void grantInternetAccess() {
        if (isAccessTrue(employeeName)) {
            new RealInternetAccess(employeeName).grantInternetAccess();
        } else {
            System.out.println("No Internet access granted.");
        }
    }

    public boolean isAccessTrue(String emplName) {
        if (personList.contains(emplName))
            return true;
        return false;
    }
}

