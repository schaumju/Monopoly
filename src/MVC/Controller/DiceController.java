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
 * Package: MVC.Controller
 * Class: DiceController
 *
 * Description:
 *
 * ****************************************
 */
package MVC.Controller;

import Game.Dice;
import MVC.Model.MonopolyModel;
import MVC.View.MainView;

/**
 * Controller for the dice
 */
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

            if (dice.isDoubles()) {
                theModel.getCurPlayer().setNumRollsInRow(theModel.getCurPlayer().getNumRollsInRow() + 1);
                if (theModel.getCurPlayer().getNumRollsInRow() == 3) {
                    theView.getPropertyView().turnButtonOff();
                    theView.getEndTurnView().turnButtonOn();
                    theView.getDiceView().getRollDiceBtn().setDisable(true);
                    theModel.getCurPlayer().goToJail();
                    theModel.getLog().addToLog(theModel.getCurPlayer().getName() + " rolled 3 doubles in a row and got sent to jail");
                    theView.getLogView().updateLog();
                    theView.getCharacterView().updateCharacters();
                    return;
                }
            } else {
                theModel.getCurPlayer().setNumRollsInRow(0);
            }


            //Player in jail and did not roll doubles
            if (theModel.getCurPlayer().isInJail() && !dice.isDoubles()) {
                //They just had their 3rd attempt at getting out of jail
                if (theModel.getCurPlayer().getTurnsInJail() == 2) {
                    System.out.println("You have served your time! Pay the $50 fine and leave!");
                    theModel.getCurPlayer().subtractFromBalance(50);
                    theModel.getCurPlayer().leaveJail();
                }
                //Not 3rd attempt, so still in jail
                else {
                    System.out.println("Did not roll doubles! Stay in jail!");
                    theModel.getCurPlayer().incrementTurnsInJail();
                }

            }

            //They have rolled doubles and are now out of jail but still have the jail status
            if (theModel.getCurPlayer().isInJail()) {
                System.out.println("Congrats! You rolled doubles and are now out of jail!");
                theModel.getCurPlayer().leaveJail();
            }
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
     * @param theModel the new MVC.Model
     */
    public void updateModel(MonopolyModel theModel) {
        this.theModel = theModel;
    }

}