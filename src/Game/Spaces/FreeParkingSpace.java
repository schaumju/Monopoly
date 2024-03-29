/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: YOUR NAME
 * Section: YOUR SECTION
 * Date: 11/11/19
 * Time: 11:51 AM
 *
 * Project: csci205FinalProject
 * Package: Objects
 * Class: FreeParkingSpace
 *
 * Description:
 *
 * ****************************************
 */
package Game.Spaces;

import java.io.Serializable;

/**
 * Representation of a Free Parking Space
 */
public class FreeParkingSpace extends Space implements Serializable
{
    /**
     * Default Constructor
     */
    public FreeParkingSpace() {
        super(19, "FREE PARKING");
    }
}