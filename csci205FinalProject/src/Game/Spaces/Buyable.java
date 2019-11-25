package Game.Spaces;

import Game.Character;

/**
 * Interface for Spaces on the board that can be bought
 * @author Justin
 */
public interface Buyable {

    /**
     * Gets the cost to buy property
     * @return the cost of the Property
     */
    double getCost();

    /**
     * Gets the ID of the owner
     * @return the ID of the owner
     */
    int getOwner();

    /**
     * Sets the owner id to the input
     *
     * @param player the player buying the property
     */
    void buyProperty(Character player);

    //For setting the owner to none
    void buyProperty(int i);
}
