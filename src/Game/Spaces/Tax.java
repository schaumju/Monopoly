/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: YOUR NAME
 * Section: YOUR SECTION
 * Date: 11/10/19
 * Time: 2:54 PM
 *
 * Project: csci205FinalProject
 * Package: Objects
 * Class: Tax
 *
 * Description:
 *
 * ****************************************
 */
package Game.Spaces;

import java.io.Serializable;

/**
 * Andrew Lee
 * References: https://i3.cpcache.com/image/112154097_150x150.png
 */

public class Tax extends Space implements Serializable
{
    /**
     * The amount the player owes from landing on this tax
     */
    private int taxAmount;

    /**
     * Constructor
     *
     * @param name      name of the space
     * @param position  position of the space
     * @param taxAmount the amount of tax owed
     */
    public Tax(String name, int position, int taxAmount) {
        super(position, name);
        this.taxAmount=taxAmount;
    }

    /**
     * Gets the amount the player owes
     * @return the amount the player owes
     */
    public int getTax() {
        return taxAmount;
    }
}



