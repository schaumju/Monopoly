/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: YOUR NAME
 * Section: YOUR SECTION
 * Date: 11/11/2019
 * Time: 11:07 AM
 *
 * Project: csci205FinalProject
 * Package: Objects
 * Class: Property
 *
 * Description:
 *
 * ****************************************
 */
package Game.Spaces;

import Game.Spaces.Buyable;
import Game.Spaces.PropertyColor;
import Game.Spaces.Space;

/**
 * A property class that represents a property on the board.
 * @author Ashlyn Ramos
 * Reference : https://github.com/BenPollock/Monopoly/blob/master/Property.java
 */
public class Property extends Space implements Buyable {

    /**
     * The number of houses on the property
     */
    private int numHouses;
    /**
     * The rent of this property (contains the rent for every number of houses)
     */
    private double[] rent;
    /**
     * The owner of the property
     */
    private int owner;
    /**
     * The price of the property to buy
     */
    private int price;
    /**
     * The cost of a house on the property
     */
    private int costOfHouses;
    /**
     * The color of the property
     */
    private PropertyColor color;
    /**
     * boolean to determine if the property is mortgaged
     */
    private boolean isMortgaged;

    public Property(int position, String name, PropertyColor color, int price, double[] rent, int costOfHouses) {
        super(position, name);
        this.color=color;
        this.price=price;
        this.rent=rent;
        this.costOfHouses=costOfHouses;
        // The property is originally unowned
        owner=-1;
    }

    /**
     * Mortgages the property and returns the amount of money the user gets
     * @return the price of the property divided by 2 (mortgage value)
     */
    private int mortgage() {
        isMortgaged=true;
        return price/2;
    }
    /**
     * UnMortgages the property and returns the amount of money the user has to pay
     * @return the price of the property
     */
    private double unMortgage() {
        isMortgaged=false;
        return getCost();
    }

    /**
     * Returns the rent that is owed depending on the number of houses on it.
     * If there are no houses and it is part of a monopoly the rent is doubled
     * @param isMonopoly boolean value denoting whether the property is part of an owned monopoly
     * @return the rent that is owed
     */
    public double getRent(boolean isMonopoly) {
        if (isMortgaged) {
            return 0;
        }
        if (isMonopoly && numHouses == 0) {
            return rent[numHouses]*2;
        }
        return rent[numHouses];
    }

    /**
     * Gets the cost to buy property
     *
     * @return the cost of the Property
     */
    @Override
    public double getCost() {
        return price;
    }

    /**
     * If the property has an owner the userID of the owner is returned,
     * otherwise returns -1.
     * @return
     */
    @Override
    public int getOwner() {
        return owner;
    }

    /**
     * Sets the owner of the property to the player that purchases the property.
     * @param playerID the id of the player who is buying the property
     */
    public void buyProperty(int playerID) {
        owner = playerID;
    }

    /**
     * Returns true if the property has an owner and false if unowned.
     * @return boolean true or false
     */
    public boolean isOwned(){
        return getOwner() != -1;
    }

    /**
     * Returns the number of houses of the property
     * @return int numHouses
     */
    public int getNumHouses() {
        return numHouses;
    }

    /**
     * Adds a house to the total number of houses on the property.
     */
    public void incrementNumHouses(){
        this.numHouses++;
    }

    /**
     * Returns the color of the property.
     * @return PropertyColor color
     */
    public PropertyColor getPropertyColor(){
        return color;
    }


    /**
     * Returns the cost of a house on the property.
     * @return int costOfHouses
     */
    public int getCostOfHouses() {
        return costOfHouses;
    }

}
