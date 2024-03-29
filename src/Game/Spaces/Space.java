/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: YOUR NAME
 * Section: YOUR SECTION
 * Date: 11/11/2019
 * Time: 11:05 AM
 *
 * Project: csci205FinalProject
 * Package: Objects
 * Class: Space
 *
 * Description:
 *
 * ****************************************
 */
package Game.Spaces;

import java.io.Serializable;

/**
 * An abstract class that represents a space on the board
 * @author Ashlyn Ramos
 */
public abstract class Space implements Serializable {
    /**
     * The position of the space
     */
    protected int position;
    /**
     * The name of the spaces
     */
    protected String name;

    /**
     * Constructor
     *
     * @param position int value of the position of space
     * @param name     the name of the space
     */
    public Space(int position, String name) {
        this.position = position;
        this.name = name;
    }

    /*Getter methods */
    public int getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Space{" +
                "position=" + position +
                ", name='" + name + '\'' +
                '}';
    }
}