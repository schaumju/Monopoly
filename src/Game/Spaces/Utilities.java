/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: YOUR NAME
 * Section: YOUR SECTION
 * Date: 11/8/19
 * Time: 11:17 AM
 *
 * Project: csci205FinalProject
 * Package: Objects
 * Class: Utilities
 *
 * Description:
 *
 * ****************************************
 */
package Game.Spaces;

import Game.Character;

import java.io.Serializable;

/**
 * Class that represents the Utilities
 */
public class Utilities extends Space implements Buyable, Serializable {

    /**
     * The ID of the person who owns the Railroad
     */
    private int ownerID;
    /**
     * The cost of a utility space to buy
     */
    private final int UTILITY_COST=150;

    /**
     * Constructor
     *
     * @param position position of the space
     * @param name     the name of teh space
     */
    public Utilities(int position, String name) {
        super(position, name);
        ownerID=-1;
    }

    /**
     * Gets the cost to buy property
     *
     * @return the cost of the Property
     */
    @Override
    public double getCost() {
        return UTILITY_COST;
    }

    /**
     * Gets the ID of the owner
     *
     * @return the ID of the owner
     */
    @Override
    public int getOwner() {
        return ownerID;
    }

    /**
     * Sets the owner id to the input
     *
     * @param player the player buying the property
     */
    @Override
    public void buyProperty(Character player) {
        ownerID = player.getID();
        player.subtractFromBalance(getCost());
        player.buyUtility();
    }

    @Override
    public void buyProperty(int i) {
        ownerID = -1;
    }


    /**
     * Returns the rent
     * @param numOwned the number of utilities the player owns
     * @param diceRoll the value of the player's roll
     * @return the rent owed
     */
    public int getRent(int numOwned, int diceRoll) {
        int rent = 0;
        if (getOwner() == -1) {
            return 0;
        }
        switch (numOwned) {
            case 1:
                rent=diceRoll*4;
                break;
            case 2:
                rent=diceRoll*10;
                break;

        }
        return rent;
    }

    /**
     * Determines if the Utility is owned
     *
     * @return true if the utility is owned and false if it is unowned
     */
    public boolean isOwned() {
        return getOwner() != -1;
    }
}