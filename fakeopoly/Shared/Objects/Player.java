package Shared.Objects;

import java.awt.Color;
import java.io.Serializable;

/*
 * Player object should contain all the information regarding a player.
 * This includes: name, color, money, properties and more.
 */
public class Player implements Serializable {

    private String name;
    private Color color;
    private double money;
    private Property[] properties;

    // constructor
    public Player(String name, Color color) {
        this.name = name;
        this.color = color;
    }

    // getters and setters
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
