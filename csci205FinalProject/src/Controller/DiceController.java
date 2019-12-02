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

import Game.Dice;
import View.DiceAnimation;
import View.DiceView;

public class DiceController {

    /**
     * handles rolling the dice
     * @author justin & kerri
     */
    protected static void handleRollDice() {
        DiceView.getRollDiceBtn().setOnAction(event -> {
            Dice dice = new Dice();
            dice.rollDice();
            DiceView.getDice1().roll(Dice.getDie1());
            DiceView.getDice2().roll(Dice.getDie2());

            // model needs to move the player based on the dice roll
        });

    }

}