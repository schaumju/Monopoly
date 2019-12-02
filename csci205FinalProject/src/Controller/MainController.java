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

    /**
     * The game model
     */
    private MonopolyModel theModel;
    /**
     * View class for the graphics of the game
     */
    private MainView theView;
    /**
     * Controller for the Board
     */
    private BoardController boardController;
    /**
     * Controller for the dice
     */
    private DiceController diceController;
    /**
     * Controller to buy properties
     */
    private BuyPropertyController buyPropertyController;
    /**
     * Controller to handle the end of a turn
     */
    private EndTurnController endTurnController;

    /**
     * constructor
     * @param theModel the game model
     * @param theView view class for the graphics of the game
     * @author kerri
     */
    public MainController(MonopolyModel theModel, MainView theView) {
        this.theModel = theModel;
        this.theView = theView;
        this.boardController = new BoardController(theModel, theView.getBoardView());

        // Create instances of all the othre controllers
        diceController = new DiceController(theModel, theView);
        buyPropertyController = new BuyPropertyController(theModel, theView, this);
        endTurnController = new EndTurnController(theModel, theView);

    }

    public BoardController getBoardController() {
        return boardController;
    }

    public DiceController getDiceController() {
        return diceController;
    }

    public BuyPropertyController getBuyPropertyController() {
        return buyPropertyController;
    }

    public EndTurnController getEndTurnController() {
        return endTurnController;
    }
}