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
package Objects;

/**
 * A property class that represents a property on the board.
 * @author Ashlyn Ramos
 * Reference : https://github.com/BenPollock/Monopoly/blob/master/Property.java
 */
public class Property extends Space implements Buyable  {

    int numHouses;
    int rent;
    int owner;
    int buyCost;
    int costOfHouses;
    PropertyColor color;

    public Property(int position, String name) {
        super(position, name);
    }

    /**
     * Gets the rent of the property
     * @return
     */
    @Override
    public double getRent() {
        return 0;
    }

    /**
     * If the property has an owner the userID of the owner is returned,
     * otherwise returns -1.
     * @return
     */
    @Override
    public int getOwner() {
        // if the property is owned
        if(){
            // set int owner equal to user id
        }
        else{
            this.owner = -1;
        }
    }

    /**
     * Sets the owner of the property to the player that purchases the property.
     */
    public void setOwner(){
        // set owner of the property
    }

    /**
     * Returns true if the property has an owner and false if unowned.
     * @return boolean true or false
     */
    public boolean isOwned(){
        if (getOwner()==-1){
            return false;
        }
        else{
            return true;
        }
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
     * Returns the cost of the property.
     * @return int buyCost
     */
    public int getBuyCost() {
        return buyCost;
    }

    /**
     * Returns the cost of a house on the property.
     * @return int costOfHouses
     */
    public int getCostOfHouses() {
        return costOfHouses;
    }

}
