/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: YOUR NAME
 * Section: YOUR SECTION
 * Date: 11/11/19
 * Time: 11:53 AM
 *
 * Project: csci205FinalProject
 * Package: Objects
 * Class: JailSpace
 *
 * Description:
 *
 * ****************************************
 */
package Game.Spaces;

import java.io.Serializable;

/**
 * Representation of the Jail Space
 */
public class JailSpace extends Space implements Serializable
{
    /**
     * Default constructor
     */
    public JailSpace() {
        super(10, "JAIL");
    }

}