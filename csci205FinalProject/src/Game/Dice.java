/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Kerri Palphreyman
 * Section: 11am
 * Date: 11/15/19
 * Time: 11:20 AM
 *
 * Project: csci205FinalProject
 * Package: Game
 * Class: Dice
 *
 * Description:
 *
 * ****************************************
 */
package Game;

import java.util.Random;

/**
 * Representation of a dice roll
 *
 * @author Kerri
 */
public class Dice {

    /**
     * The value of the first dice roll
     */
    static int die1;
    /**
     * The value of the second dice roll
     */
    static int die2;

    public Dice() {
        die1 = 0;
        die2 = 0;
    }

    /**
     * rolls the dice by generating 2 random numbers
     * @return - the total dice roll
     * @author - kerri
     */
    public Integer rollDice() {
        Random rand = new Random();
        die1 = rand.nextInt(6) + 1;
        die2 = rand.nextInt(6) + 1;

        return die1 + die2;
    }

    /**
     * Returns whether the dice are doubles or not (doubles means the dice are the same number
     * @return true if the dice are doubles and false otherwise
     * @author - kerri
     */
    public boolean isDoubles() {
        return getDie1() == getDie2();
    }

    public int getDie1() {
        return die1;
    }

    public int getDie2() {
        return die2;
    }
}