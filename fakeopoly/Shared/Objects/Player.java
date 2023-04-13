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
    private int money;
    private Property[] properties;
    private ClientServiceIF _clientService;
    private boolean isReady;
    private int location;
    private int lastRoll;

    private boolean inJail = false;
    private int doubles = 0;
    private int jailCount = 0;

    // constructor
    public Player(String name, Color color) {
        this.name = name;
        this.color = color;
        this.isReady = false;
        this.location = 0;
        this.money = 1500;
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

    public boolean getJail() {
        return inJail;
    }

    public void setJail(boolean jail) {
        inJail = jail;
    }

    public int increaseDoubles() {
        doubles++;
        return doubles;
    }

    public int getDoubles() {
        return doubles;
    }

    public void setDoubles(int x) {
        doubles = x;
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

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public void increaseMoney(int money) {
        this.money += money;
    }

    public Boolean getIsReady() {
        return isReady;
    }

    public void setIsReady(Boolean isReady) {
        this.isReady = isReady;
    }

    public int getLastRoll() {
        return lastRoll;
    }

    public void setLastRoll(int lastRoll) {
        this.lastRoll = lastRoll;
    }

    public int increaseJailCount() {
        jailCount++;
        return jailCount;
    }

    public void setJailCount(int count) {
        jailCount = count;
    }
}
