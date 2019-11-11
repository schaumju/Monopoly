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

public class Property extends Space implements Buyable  {

    int numHouses;

    public Property(int position, String name) {
        super(position, name);
    }



    public double getRent() {
        return 0;
    }

    /**
     * Gets the cost to buy property
     *
     * @return the cost of the Property
     */
    @Override
    public double getCost() {
        return 0;
    }

    @Override
    public int getOwner() {
        return 0;
    }
}