/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: YOUR NAME
 * Section: YOUR SECTION
 * Date: 11/10/19
 * Time: 4:34 PM
 *
 * Project: csci205FinalProject
 * Package: Objects
 * Class: Character
 *
 * Description:
 *
 * ****************************************
 */
package Game;

import Game.Spaces.Property;
import Game.Spaces.PropertyColor;
import Networking.SerializableColor;
import javafx.scene.paint.Color;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *  Methods:
 *  move(int) - moves the character x turns
 *  addToBalance(double) - self explanatory
 *  subtractFromBalance(double) - self explanatory
 *  isBankrupt() - self explanatory
 */
public class Character implements Serializable {
    //Balance of the character
    private static final int STARTING_BALANCE = 1500;

    //Total board spaces
    private static final int TOTAL_BOARD_SPACES = 40;

    //Name of the character
    private String name;

    //Color of their character
    private SerializableColor color;

    //Place on board
    private int position;

    //Railroads owned
    private int numRailroads;

    //Balance of the character
    private int balance;

    //Utilities owned
    private int numUtilities;

    //Num houses
    private int numHouses;

    //Jailed?
    private boolean isInJail;

    //Player id
    private int iD;

    //Turns the player has been in jail for
    private int turnsInJail;

    //ArrayList of property objects that the player owns
    private ArrayList<Property> ownedProperties;
    // number of rolls in a row
    private int numRollsInRow;

    public Character(String name, Color color)
    {
        this.name = name;
        this.color = new SerializableColor(color);
        this.position = 0;
        this.numRailroads = 0;
        this.numUtilities = 0;
        this.numRollsInRow = 0;
        this.balance = STARTING_BALANCE;
        this.numHouses = 0;
        this.isInJail = false;
        this.turnsInJail = 0;
        this.ownedProperties = new ArrayList<>();
    }

    /**
     * Moves the character a certain amount of steps
     * @param turnsToMove - amount of steps to move
     */
    public void move(int turnsToMove)
    {
        if(this.position + turnsToMove >= TOTAL_BOARD_SPACES)
        {
            //They passed Go
            this.balance += 200.00;
            System.out.println(this.getName() + " PASSED GO! COLLECT $200");
            this.position = (this.position + turnsToMove)%TOTAL_BOARD_SPACES;
        }
        else
        {
            //They no passed Go
            this.position = this.position+turnsToMove;
        }
        //this.position += turnsToMove;

    }

    /**
     * Increments turns in jail
     */
    public void incrementTurnsInJail()
    {
        turnsInJail+=1;
    }

    /**
     * Resets turnsInJail attribute to 0
     */
    public void resetJailTurn()
    {
        turnsInJail = 0;
    }

    /**
     * Places player into jail
     */
    public void goToJail()
    {
        this.position = 10;
        this.isInJail = true;
    }

    /**
     * Leaves jail
     */
    public void leaveJail()
    {
        this.isInJail = false;
        resetJailTurn();
    }

    /**
     * Adds money to the balance
     * @param addedMoney - amount of money added
     */
    public void addToBalance(double addedMoney)
    {
        this.balance += addedMoney;
    }

    /**
     * Subtracts money from balance
     * @param subtractedMoney - amount of money subtracted
     */
    public void subtractFromBalance(double subtractedMoney)
    {
        if (this.getBalance() < subtractedMoney) {
            this.balance = 0;

        } else {
            this.balance -= subtractedMoney;
        }


    }

    /**
     * Returns if the player is bankrupt or not
     * @return - true if player balance is <= 0, else false
     */
    public boolean isBankrupt()
    {
        return this.balance <= 0;
    }

    /**
     * Adds a property
     */
    public void addProperty(Property prop)
    {
        this.ownedProperties.add(prop);
    }

    /**
     * Remove a property
     */
    public void removeProperty(Property prop)
    {
        if (ownedProperties.contains(prop)) {
            this.ownedProperties.remove(prop);
        } else {
            System.out.println(prop.getName()+" is not owned by "+this.getName());
        }
    }

    //Add more methods here as needed

    /**
     * Setter methods
     */
    public void setPosition(int position)
    {
        this.position = position;
    }


    /**
     * Getter methods
     */
    public ArrayList<Property> getOwnedProperties()
    {
        return ownedProperties;
    }
    public int getTurnsInJail() {
        return turnsInJail;
    }

    public boolean isInJail() {
        return isInJail;
    }

    public int getPosition()
    {
        return this.position;
    }

    public String getName()
    {
        return name;
    }

    public Color getColor()
    {
        return color.getFXColor();
    }

    public int getNumRailroads()
    {
        return numRailroads;
    }

    public int getNumUtilities()
    {
        return numUtilities;
    }

    public int getBalance()
    {
        return balance;
    }

    public int getNumHouses()
    {
        return numHouses;
    }

    /**
     * Increases the number of railroads the player owns by 1
     */
    public void buyRailroad() {
        this.numRailroads += 1;
    }

    /**
     * Increases the number of Utilities the player owns by 1
     */
    public void buyUtility() {
        this.numUtilities += 1;
    }

    /**
     * Increases the number of houses the player owns by 1
     */
    public void setNumHouses() {
        this.numHouses += 1;
    }

    /**
     * Allows a player to pay another player a set amount of monye
     *
     * @param player the player receiving the money
     * @param amount the amount of money that is owed
     */
    public void payPlayer(Character player, int amount) {
        if (this.getBalance() < amount) {
            player.addToBalance(this.getBalance());
            subtractFromBalance(this.getBalance());
        } else {
            player.addToBalance(amount);
            subtractFromBalance(amount);
        }

    }

    public int getID() {
        return iD;
    }

    /**
     * Sets the id
     *
     * @param id the player's id
     */
    public void setID(int id) {
        this.iD = id;
    }

    /**
     * Checks to see if the Player has a Monopoly in the specific color
     *
     * @param propertyColor the color of the property being checked
     * @return true if the player has a monopoly in the color and false otherwise
     */
    public boolean isMonopoly(PropertyColor propertyColor) {

        int count = 0;
        for (Property property : ownedProperties) {
            if (property.getPropertyColor() == propertyColor) {
                count++;
            }
        }
        return count == propertyColor.getNumberOfProperties();
    }

    public int getNumRollsInRow() {
        return numRollsInRow;
    }

    public void setNumRollsInRow(int numRollsInRow) {
        this.numRollsInRow = numRollsInRow;
    }
}