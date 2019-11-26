/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Kerri Palphreyman
 * Section: 11am
 * Date: 11/18/19
 * Time: 11:37 AM
 *
 * Project: csci205FinalProject
 * Package: View
 * Class: MainView
 *
 * Description:
 *
 * ****************************************
 */
package View;

import Model.MonopolyModel;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;

public class MainView {

    /**
     * The root pane for the graphics
     */
    private static GridPane root;
    /**
     * Game model
     */
    private MonopolyModel theModel;
    /**
     * View class for board
     */
    private BoardView boardView;
    /**
     * View class for cards
     */
    private CardsView cardsView;
    /**
     * View class for dice
     */
    private DiceView diceView;
    /**
     * View class for characters
     */
    private CharacterView characterView;
    /**
     * View class for property
     */
    private PropertyView propertyView;
    /**
     * View class for the end of the turn
     */
    private EndTurnView endTurnView;
    /**
     * View class for the game log
     */
    private LogView logView;

    /**
     * sets up the main view
     * @param theModel the game model
     * @author kerri
     */
    public MainView(MonopolyModel theModel) {
        this.theModel = theModel;
        setUpRoot();
        boardView = new BoardView(theModel);
        cardsView = new CardsView(theModel);
        characterView = new CharacterView(theModel);
        propertyView = new PropertyView(theModel);
        diceView = new DiceView(theModel);
        endTurnView = new EndTurnView(theModel);
        logView = new LogView(theModel);


        // BoardView.addBoardSpaces();

        //DiceView.addDice();

        //new CharacterView(new Character[]{new Character("Player1", Color.RED), new Character("Player2", Color.BLUE)});

        //PropertyView.addPropertyBuyButton();

        //CardsView.addCards();

    }


    /**
     * sets up a gridpane that holds the board
     * @author kerri
     */
    private void setUpRoot() {
        root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setGridLinesVisible(false);
    }

    /**
     * Getter method for grid pane
     */
    public static GridPane getRoot() {
        return root;
    }


    /**
     * Getter Methods
     */

    public MonopolyModel getTheModel() {
        return theModel;
    }

    public BoardView getBoardView() {
        return boardView;
    }

    public CardsView getCardsView() {
        return cardsView;
    }

    public DiceView getDiceView() {
        return diceView;
    }

    public CharacterView getCharacterView() {
        return characterView;
    }

    public PropertyView getPropertyView() {
        return propertyView;
    }

    public EndTurnView getEndTurnView() {
        return endTurnView;
    }

    /**
     * Updates the scene for the player if the player rolled doubles (roll again)
     */
    public void doubles() {
        diceView.getRollDiceBtn().setDisable(false);

    }

    /**
     * Resets the buttons for the next turn
     */
    public void endTurn() {
        diceView.getRollDiceBtn().setDisable(false);
        endTurnView.getEndTurnButton().setDisable(true);
        propertyView.turnButtonOff();
    }
}