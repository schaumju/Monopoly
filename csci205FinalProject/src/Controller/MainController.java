/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Kerri Palphreyman
 * Section: 11am
 * Date: 11/18/19
 * Time: 8:28 PM
 *
 * Project: csci205FinalProject
 * Package: Controller
 * Class: MainController
 *
 * Description:
 *
 * ****************************************
 */
package Controller;

import Model.MonopolyModel;
import View.MainView;

public class MainController {

    private MonopolyModel theModel;
    private MainView theView;
    private BoardController boardController;

    /**
     * constructor
     * @param theModel
     * @param theView
     * @author kerri
     */
    public MainController(MonopolyModel theModel, MainView theView) {
        this.theModel = theModel;
        this.theView = theView;
        this.boardController = new BoardController(theModel, theView.getBoardView());
        
        //handle roll dice
        //DiceController.handleRollDice();
        DiceController diceController = new DiceController(theModel, theView);

        BuyPropertyController.handleBuyProperty();
    }



}