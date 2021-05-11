package ru.pattern.structural.facade;

public class SetWirelessNetworkFacadeRunner {
    public static void main(String[] args) {
        WirelessNetworkFacade wirelessNetworkFacade = new WirelessNetworkFacade();
        wirelessNetworkFacade.switchOnNetwork();
        System.out.println();
        wirelessNetworkFacade.switchOffNetwork();
    }
}


class CableModem {
    void switchOn() {
        System.out.println("Switch On Cable Modem");
    }

    void switchOff() {
        System.out.println("Switch Off Cable Modem");
    }

    void connectToWirelessRouter() {
        System.out.println("Connect to wireless router");
    }
}

class WirelessRouter {
    void switchOn() {
        System.out.println("Wireless Router - Switch On");
    }

    public void switchOff() {
        System.out.println("Wireless Router - Switch Off");
    }

    public void acceptInputFromModem() {
        System.out.println("Accept input from Cable Modem");
    }

    public void sendWirelessSignals() {
        System.out.println("Send wireless signals");
    }
}

class Laptop {
    public void switchOn() {
        System.out.println("Laptop - Switch On");
    }

    public void switchOff() {
        System.out.println("Laptop - Switch Off");
    }

    public void connectToWiFi() {
        System.out.println("Laptop - Connect to Wireless network");
    }
}

class Mobile {
    public void switchOn() {
        System.out.println("Mobile - Switch On");
    }

    public void switchOff() {
        System.out.println("Mobile - Switch Off");
    }

    public void connectToWiFi() {
        System.out.println("Mobile - Connect to Wireless network");
    }
}

class Desktop {
    public void switchOn() {
        System.out.println("Desktop - Switch On");
    }

    public void switchOff() {
        System.out.println("Desktop - Switch Off");
    }

    public void connectToWiFi() {
        System.out.println("Desktop - Connect to Wireless network");
    }
}

class WirelessNetworkFacade {

    private Desktop desktop;

    private Laptop laptop;

    private Mobile mobile;

    private WirelessRouter wirelessRouter;

    private CableModem cableModem;

    public WirelessNetworkFacade() {
        this.desktop = new Desktop();
        this.laptop = new Laptop();
        this.mobile = new Mobile();
        this.wirelessRouter = new WirelessRouter();
        this.cableModem = new CableModem();
    }


    public void switchOnNetwork() {
        this.cableModem.switchOn();
        this.wirelessRouter.switchOn();
        this.cableModem.connectToWirelessRouter();
        this.wirelessRouter.acceptInputFromModem();
        this.wirelessRouter.sendWirelessSignals();
        this.desktop.switchOn();
        this.laptop.switchOn();
        this.mobile.switchOn();
    }

    public void switchOffNetwork() {
        this.desktop.switchOff();
        this.laptop.switchOff();
        this.mobile.switchOff();
        this.wirelessRouter.switchOff();
        this.cableModem.switchOff();
    }
}