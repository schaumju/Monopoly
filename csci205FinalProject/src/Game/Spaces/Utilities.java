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

/**
 * Class that represents the Utilities
 */
public class Utilities extends Space implements Buyable {

    /**
     * The ID of the person who owns the Railroad
     */
    private int ownerID;

    private final int UTILITY_COST=150;

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
     * @param id the id of the player buying the property
     */
    @Override
    public void buyProperty(int id) {
        ownerID = id;

    }


    /**
     * Returns the rent
     * @param numOwned the number of utilities the player owns
     * @param diceRoll the value of the player's roll
     * @return the rent owed
     */
    public double getRent(int numOwned, int diceRoll) {
        double rent=0;
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
}