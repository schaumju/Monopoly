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
 * Class: cornerSpaceJAIL
 *
 * Description:
 *
 * ****************************************
 */
package Objects;

import java.util.ArrayList;

public class cornerSpaceJAIL extends Space
{
    //ArrayList of prisoners
    public ArrayList<Prisoner> inmates;

    public cornerSpaceJAIL()
    {
        super(9, "JAIL");
        inmates = new ArrayList<Prisoner>();
    }

    //Adds a prisoner to an arrayList of prisoners
    public void addInmates(Prisoner prisoner)
    {
        inmates.add(prisoner);
    }

    //Removes a prisoner from the arrayList
    public void removeInmates(Prisoner prisoner)
    {
        inmates.remove(prisoner);
    }
}