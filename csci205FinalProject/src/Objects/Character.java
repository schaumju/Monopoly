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
package Objects;

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

    public Character(String name, Color color)
    {
        this.name = name;
        this.color = color;
        this.position = 0;
        this.numRailroads = 0;
        this.numUtilities = 0;
        this.balance = STARTING_BALANCE;
        this.numHouses = 0;
    }

    /**
     * Moves the character a certain amount of steps
     * @param turnsToMove - amount of steps to move
     */
    public void move(int turnsToMove)
    {
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

    //Add more methods here as needed


    /**
     * Getter methods
     */
    public int getPosition()
    {
        //RETURNS TOTAL % BOARD SPACES
        return this.position%TOTAL_BOARD_SPACES;
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
}