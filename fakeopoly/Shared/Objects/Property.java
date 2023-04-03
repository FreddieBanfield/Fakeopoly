package Shared.Objects;

import java.awt.Color;
import java.io.Serializable;

/*
 * Property object should contain all the information regarding a property.
 * This includes: name, color, price, tier, values
 */
public class Property implements Serializable {

    // Generic information
    private String name;
    private Color color;
    private double price; // Value of the property when a player initially buys it
    private int tier; // What level of houses are on the property
    private Player owner;
    private boolean isMortgaged;

    // Tier 0
    private double tierZeroValue; // No houses on property
    // Tier 1
    private double tierOneValue; // One Green house on property
    // Tier 2
    private double tierTwoValue; // Two Green house on property
    // Tier 3
    private double tierThreeValue; // Three Green house on property
    // Tier 4
    private double tierFourValue; // Four Green house on property
    // Tier 5
    private double tierFiveValue; // Red house on property

    // Computed Values
    private double mortgageValue; // Value a player gets when mortgaging a property
    private double tierOneSetValue; // Value when a player has all properties of that color

    // constructor
    // we can instantiate the properties programmatically from pulling the data from
    // a database (allows us to save games in future)
    public Property(String name, Color color, double price, int tier, Player owner, boolean isMortgaged,
            double tierOneValue, double tierTwoValue, double tierThreeValue, double tierFourValue) {
        this.name = name;
        this.color = color;
        this.price = price;
        this.tier = tier;
        this.owner = owner;
        this.isMortgaged = isMortgaged;
        this.tierOneValue = tierOneValue;
        this.tierTwoValue = tierTwoValue;
        this.tierThreeValue = tierThreeValue;
        this.tierFourValue = tierFourValue;

        this.mortgageValue = price / 2;
        this.tierOneSetValue = tierOneValue * 2;
    }

    // getters and setters
    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public double getPrice() {
        return price;
    }

    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }

    public Player getOwner() {
        return owner;
    }

    public boolean getIsMortgage() {
        return isMortgaged;
    }

    public void setIsMortgage(boolean isMortgaged) {
        this.isMortgaged = isMortgaged;
    }

    public double getTierZeroValue() {
        return tierZeroValue;
    }

    public double getTierOneValue() {
        return tierOneValue;
    }

    public double getTierTwoValue() {
        return tierTwoValue;
    }

    public double getTierThreeValue() {
        return tierThreeValue;
    }

    public double getTierFourValue() {
        return tierFourValue;
    }

    public double getTierFiveValue() {
        return tierFiveValue;
    }

    public double getMortgageValue() {
        return mortgageValue;
    }

    public double getTierOneSetValue() {
        return tierOneSetValue;
    }

}
