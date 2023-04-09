package Shared.Objects;

import java.awt.Color;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import Shared.Interfaces.ClientServiceIF;

/*
 * Player object should contain all the information regarding a player.
 * This includes: name, color, money, properties and more.
 */
public class Player implements Serializable {

    private String name;
    private Color color;
    private double money;
    private Property[] properties;
    private ClientServiceIF _clientService;

    // constructor
    public Player(String name, Color color) {
        this.name = name;
        this.color = color;
    }

    public boolean connectClient(String clientAddress, int clientPort, int id) {
        boolean isSuccess;
        // lookup method to find reference of remote object
        try {
            _clientService = (ClientServiceIF) Naming
                    .lookup("rmi://" + clientAddress + ":" + clientPort + "/" + name + id);
            isSuccess = true;
        } catch (MalformedURLException | RemoteException | NotBoundException e) {
            System.out.println(e);
            isSuccess = false;
        }
        return isSuccess;
    }

    // getters and setters
    public ClientServiceIF getClient() {
        return _clientService;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

}
