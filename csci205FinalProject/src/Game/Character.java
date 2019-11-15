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

import javafx.scene.paint.Color;

/**
 *  Methods:
 *  move(int) - moves the character x turns
 *  addToBalance(double) - self explanatory
 *  subtractFromBalance(double) - self explanatory
 *  isBankrupt() - self explanatory
 */
public class Character
{
    //Balance of the character
    private static final double STARTING_BALANCE = 1500.00;

    //Total board spaces
    private static final int TOTAL_BOARD_SPACES = 40;


    //Name of the character
    private String name;

    //Color of their character
    private Color color;

    //Place on board
    private int position;

    //Railroads owned
    private int numRailroads;

    //Balance of the character
    private double balance;

    //Utilities owned
    private int numUtilities;

    //Num houses
    private int numHouses;

    //Jailed?
    private boolean isInJail;

    public Character(String name, Color color)
    {
        this.name = name;
        this.color = color;
        this.position = 0;
        this.numRailroads = 0;
        this.numUtilities = 0;
        this.balance = STARTING_BALANCE;
        this.numHouses = 0;
        this.isInJail = false;
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
            this.position = (this.position + turnsToMove)%TOTAL_BOARD_SPACES;
        }
        else
        {
            //They no passed Go
            this.position = this.position+turnsToMove;
        }
        this.position += turnsToMove;

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
        this.balance -= subtractedMoney;
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
     * Sets player to jailed
     */
    public void setJailed(boolean trueFalse)
    {
        this.isInJail = trueFalse;
    }
    //Add more methods here as needed


    /**
     * Setter methods
     */
    public void setPosition(int position)
    {
        this.position = position;
    }

    public boolean isInJail() {
        return isInJail;
    }

    /**
     * Getter methods
     */

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
        return color;
    }

    public int getNumRailroads()
    {
        return numRailroads;
    }

    public int getNumUtilities()
    {
        return numUtilities;
    }

    public double getBalance()
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


}