/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: YOUR NAME
 * Section: YOUR SECTION
 * Date: 11/11/19
 * Time: 11:40 AM
 *
 * Project: csci205FinalProject
 * Package: Objects
 * Class: GoSpace
 *
 * Description:
 *
 * ****************************************
 */
package Game.Spaces;

import Game.Character;

import java.io.Serializable;

/**
 * Representation of a Go Space
 */
public class GoSpace extends Space implements Serializable
{
    /**
     * Amount of money you get for passing go
     */
    private static final double CASH_MONEY = 200.00;

    /**
     * Default constructor
     */
    public GoSpace() {
        super(0, "GO");
    }

    /**
     * Rewards the player cash for passing go
     *
     * @param thisChar the Character passing go
     */
    public void rewardCash(Character thisChar) {
        thisChar.addToBalance(CASH_MONEY);
    }

}