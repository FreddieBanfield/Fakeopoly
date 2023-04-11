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
    private String color;
    private int price; // Value of the property when a player initially buys it
    private int tier; // What level of houses are on the property
    private Player owner;
    private boolean isMortgaged;

    // Housing
    private int pricePerHouse;

    // Tier 0
    private int tierZeroValue; // No houses on property
    // Tier 1
    private int tierOneValue; // One Green house on property
    // Tier 2
    private int tierTwoValue; // Two Green house on property
    // Tier 3
    private int tierThreeValue; // Three Green house on property
    // Tier 4
    private int tierFourValue; // Four Green house on property
    // Tier 5
    private int tierFiveValue; // Red house on property

    // Computed Values
    private int mortgageValue; // Value a player gets when mortgaging a property
    private int tierOneSetValue; // Value when a player has all properties of that color

    /**
     * Constructor
     * we can instantiate the properties programmatically from pulling the data from
     * a database (allows us to save games in future)
     */
    public Property(String name, String color, int price, int tier, int pricePerHouse, Player owner,
            boolean isMortgaged, int tierZeroValue,
            int tierOneValue, int tierTwoValue, int tierThreeValue, int tierFourValue, int tierFiveValue) {
        this.name = name;
        this.color = color;
        this.price = price;
        this.tier = tier;
        this.pricePerHouse = pricePerHouse;
        this.owner = owner;
        this.isMortgaged = isMortgaged;
        this.tierZeroValue = tierZeroValue;
        this.tierOneValue = tierOneValue;
        this.tierTwoValue = tierTwoValue;
        this.tierThreeValue = tierThreeValue;
        this.tierFourValue = tierFourValue;
        this.tierFiveValue = tierFiveValue;

        this.mortgageValue = price / 2;
        this.tierOneSetValue = tierOneValue * 2;
    }

    // Default constructor for json conversion
    public Property() {
    }

    // getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }

    public int getPricePerHouse() {
        return pricePerHouse;
    }

    public void setPricePerHouse(int pricePerHouse) {
        this.pricePerHouse = pricePerHouse;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public boolean getIsMortgaged() {
        return isMortgaged;
    }

    public void setIsMortgaged(boolean isMortgaged) {
        this.isMortgaged = isMortgaged;
    }

    public double getTierZeroValue() {
        return tierZeroValue;
    }

    public void setTierZeroValue(int value) {
        this.tierZeroValue = value;
    }

    public double getTierOneValue() {
        return tierOneValue;
    }

    public void setTierOneValue(int value) {
        this.tierOneValue = value;
    }

    public double getTierTwoValue() {
        return tierTwoValue;
    }

    public void setTierTwoValue(int value) {
        this.tierTwoValue = value;
    }

    public double getTierThreeValue() {
        return tierThreeValue;
    }

    public void setTierThreeValue(int value) {
        this.tierThreeValue = value;
    }

    public double getTierFourValue() {
        return tierFourValue;
    }

    public void setTierFourValue(int value) {
        this.tierFourValue = value;
    }

    public double getTierFiveValue() {
        return tierFiveValue;
    }

    public void setTierFiveValue(int value) {
        this.tierFiveValue = value;
    }

    public double getMortgageValue() {
        return mortgageValue;
    }

    public void setMortgageValue(int value) {
        this.mortgageValue = value;
    }

    // When a player has all properties of a set
    public double getTierOneSetValue() {
        return tierOneSetValue;
    }

    // When a player has all properties of a set
    public void setTierOneSetValue(int value) {
        this.tierOneSetValue = value;
    }

}
