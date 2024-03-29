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
 * Package: MVC.View
 * Class: MainView
 *
 * Description:
 *
 * ****************************************
 */
package MVC.View;

import MVC.Model.MonopolyModel;
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
        boardView = new BoardView(theModel, this);
        cardsView = new CardsView(theModel, this);
        characterView = new CharacterView(theModel, this);
        propertyView = new PropertyView(theModel, this);
        diceView = new DiceView(theModel, this);
        endTurnView = new EndTurnView(theModel, this);
        logView = new LogView(theModel, this);


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
    public GridPane getRoot() {
        return root;
    }


    /* Getter methods*/
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

    public LogView getLogView() {
        return logView;
    }

    /**
     * Updates the scene for the player if the player rolled doubles (roll again)
     */
    public void doubles() {
        if (!theModel.getCurPlayer().isInJail())
        {
            diceView.getRollDiceBtn().setDisable(false);
        }

    }

    /**
     * Resets the buttons for the next turn
     */
    public void endTurn() {
        diceView.getRollDiceBtn().setDisable(true);
        endTurnView.getEndTurnButton().setDisable(true);
        propertyView.turnButtonOff();
    }

    /**
     * Updates the model
     * @param theModel the new model
     */
    public void updateModel(MonopolyModel theModel) {
        this.theModel = theModel;
        boardView.updateModel(theModel);
        cardsView.updateModel(theModel);
        characterView.updateModel(theModel);
        diceView.updateModel(theModel);
        endTurnView.updateModel(theModel);
        logView.updateModel(theModel);
        propertyView.updateModel(theModel);
    }


}