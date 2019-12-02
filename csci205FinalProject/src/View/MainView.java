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
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import Game.Character;

public class MainView {

    private MonopolyModel theModel;
    private static GridPane root;

    private BoardView boardView;
    private CardsView cardsView;
    private DiceView diceView;
    private CharacterView characterView;
    private PropertyView propertyView;

    /**
     * sets up the main view
     * @param theModel
     * @author kerri
     */
    public MainView(MonopolyModel theModel) {
        this.theModel = theModel;
        setUpRoot();
        setUpWelcome();
        boardView = new BoardView(theModel);
        cardsView = new CardsView(theModel);
        characterView = new CharacterView(theModel);
        propertyView = new PropertyView(theModel);
        diceView = new DiceView(theModel);


        // BoardView.addBoardSpaces();

        //DiceView.addDice();

        //new CharacterView(new Character[]{new Character("Player1", Color.RED), new Character("Player2", Color.BLUE)});

        //PropertyView.addPropertyBuyButton();

        //CardsView.addCards();

    }

    /**
     * Setup welcome message
     */
    private void setUpWelcome()
    {
        //Stage dialog = new Stage();
        VBox popup = new VBox(20);

        //Text
        Text welcomeMessage = new Text("Welcome to Monopoly!");
        Text playerCount = new Text("Currents Players are: \n");
        popup.getChildren().add(welcomeMessage);
        popup.getChildren().add(playerCount);
        for (Character player: theModel.getGame().getPlayerList())
        {
            popup.getChildren().add(new Text(player.getName() + "\n"));
        }
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

    /**
     * Updates the scene for the player if the player rolled doubles (roll again)
     */
    public void doubles() throws Exception {
        DiceView.getRollDiceBtn().setDisable(false);

    }

    /**
     * Makes the only thing enabled the endTurn button
     */
    public void endTurn() {
        DiceView.getRollDiceBtn().setDisable(true);
        PropertyView.getBuyPropertyButton().setDisable(true);
    }
}