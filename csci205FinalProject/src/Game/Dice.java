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

    int die1;
    int die2;

    public Dice()
    {

    }

    public Integer rollDice() {
        Random rand = new Random();
        die1 = rand.nextInt(6) + 1;
        die2 = rand.nextInt(6) + 1;
        return die1 + die2;
    }

    public boolean isDoubles() {
        if (die1 == die2) { return true; }
        else { return false; }
    }
}