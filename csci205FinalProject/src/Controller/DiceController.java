/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Kerri Palphreyman
 * Section: 11am
 * Date: 11/18/19
 * Time: 8:30 PM
 *
 * Project: csci205FinalProject
 * Package: Controller
 * Class: DiceController
 *
 * Description:
 *
 * ****************************************
 */
package Controller;

import View.DiceView;

public class DiceController {

    protected static void handleRollDice() {

        DiceView.getRollDiceBtn().setOnMouseClicked(mouseEvent -> {
            System.out.println("roll dice button");
        });

    }

}