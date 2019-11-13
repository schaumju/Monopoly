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

import Game.Prisoner;
import Game.Character;

public class GoToJailSpace extends Space
{
    //Prisoner object
    private Prisoner newPrisoner;

    public GoToJailSpace() {
        super(30, "GO TO JAIL!");
    }

    /**
     * Go to jail
     * @param thisChar - jailed inmate
     */
    public void goToJail(Character thisChar)
    {
        newPrisoner = new Prisoner(thisChar);
    }

}