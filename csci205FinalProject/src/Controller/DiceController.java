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
import Game.Spaces.Chance;
import Game.Spaces.CommunityChest;
import Model.MonopolyModel;
import View.MainView;

public class DiceController {

    /**
     * The game model
     */
    private MonopolyModel theModel;
    /**
     * View class for the graphics of the game
     */
    private MainView theView;

    /**
     * Constructor
     *
     * @param theModel the game model
     * @param theView  view class for the graphics of the game
     */
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
        theView.getDiceView().getRollDiceBtn().setOnAction(event -> {
            Dice dice = new Dice();
            dice.rollDice();
            theView.getDiceView().getDice1().roll(dice.getDie1());
            theView.getDiceView().getDice2().roll(dice.getDie2());
            theModel.getCurPlayer().move(dice.getDie1() + dice.getDie2());
            int totalRoll = dice.getDie1() + dice.getDie2();
            theModel.getLog().addToLog(theModel.getCurPlayer().getName() + " rolled a " + totalRoll);
            theView.getLogView().updateLog();
            theView.getCharacterView().updateCharacters();
            try {
                theModel.interactSpace(dice.getDie1() + dice.getDie2());
                theView.getLogView().updateLog();

            } catch (Exception e) {
                e.printStackTrace();
            }
            theView.getCharacterView().updateCharacters();
            // If the given space is available to purchase
            if (theModel.isAvailable()) {
                theView.getPropertyView().turnButtonOn();
            } else {
                theView.getPropertyView().turnButtonOff();
            }

            theView.getDiceView().getRollDiceBtn().setDisable(true);

            if (dice.isDoubles()) {
                try {
                    theView.doubles();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {
                theView.getEndTurnView().turnButtonOn();
            }
            theView.getLogView().updateLog();
        });

    }

    /**
     * Updates the model
     *
     * @param theModel the new Model
     */
    public void updateModel(MonopolyModel theModel) {
        this.theModel = theModel;
    }

}