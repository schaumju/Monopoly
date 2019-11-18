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

public class Dice {

    static int die1;
    static int die2;

    public Dice() {
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
     * checks if a double was rolled
     * @return - true or false
     * @author - kerri
     */
    public boolean isDoubles() {
        if (die1 == die2) { return true; }
        else { return false; }
    }

    public static int getDie1() {
        return die1;
    }

    public static int getDie2() {
        return die2;
    }
}