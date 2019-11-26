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
import Model.MonopolyModel;
import View.DiceView;
import View.MainView;

public class DiceController {

    private MonopolyModel theModel;
    private MainView theView;

    public DiceController(MonopolyModel theModel, MainView theView) {
        this.theModel = theModel;
        this.theView = theView;
        handleRollDice();
    }

    /**
     * handles rolling the dice
     * @author justin & kerri
     */
    private void handleRollDice() {
        DiceView.getRollDiceBtn().setOnAction(event -> {
            Dice dice = new Dice();
            dice.rollDice();
            DiceView.getDice1().roll(dice.getDie1());
            DiceView.getDice2().roll(dice.getDie2());
            theModel.getCurPlayer().move(dice.getDie1() + dice.getDie2());
            theView.getCharacterView().updateCharacters();
            try {
                theModel.interactSpace(dice.getDie1() + dice.getDie2());
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (dice.isDoubles()) {
                try {
                    theView.doubles();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                theView.endTurn();
            }
        });

    }

}