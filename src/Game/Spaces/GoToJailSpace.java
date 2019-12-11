/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: YOUR NAME
 * Section: YOUR SECTION
 * Date: 11/13/19
 * Time: 11:16 AM
 *
 * Project: csci205FinalProject
 * Package: Game.Spaces
 * Class: GoToJailSpace
 *
 * Description:
 *
 * ****************************************
 */
package Game.Spaces;

import java.io.Serializable;

/**
 * Representation of a Jail Space
 */
public class GoToJailSpace extends Space implements Serializable
{
    /**
     * Default Constructor
     */
    public GoToJailSpace() {
        super(30, "GO TO JAIL!");
    }

}