/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: YOUR NAME
 * Section: YOUR SECTION
 * Date: 11/11/19
 * Time: 2:34 PM
 *
 * Project: csci205FinalProject
 * Package: Objects
 * Class: Prisoner
 *
 * Description:
 *
 * ****************************************
 */
package Game;

public class Prisoner
{
    //Turns in jail
    private int turnsInJail;

    //Prisoner Character
    private Character thisChar;

    //Position of the jail space
    private static final int JAIL_POSITION = 9;

    //Get out of jail fine
    private static final double JAIL_FINE = 50.00;
    /**
     * Constructor
     * @param thisChar - character to be jailed
     */
    public Prisoner(Character thisChar)
    {
        this.turnsInJail = 0;
        this.thisChar = thisChar;
        this.thisChar.setPosition(JAIL_POSITION);
    }

    //Gets the player out of jail and moves numMoves steps
    public void getOutOfJailAttempt(int diceRollOne, int diceRollTwo)
    {
        //If they successfully get out
        if (diceRollOne == diceRollTwo)
        {
            thisChar.move(diceRollOne + diceRollTwo);
            //Remove the prisoners from the array
        }
        else
        {
            if(this.turnsInJail == 3)
            {
                thisChar.move(diceRollOne+diceRollTwo);
                thisChar.subtractFromBalance(JAIL_FINE);
                //Remove the prisoners from the array
            }
            else
            {
                //Ask user if he wants to pay $50 and get out or not and show them the turn in jail they are at
                turnsInJail+=1;
            }
        }
    }

    /**
     * Getters methods
     */
    public int getTurnsInJail() {
        return turnsInJail;
    }
}