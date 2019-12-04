/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Kerri Palphreyman
 * Section: 11am
 * Date: 11/8/19
 * Time: 4:14 PM
 *
 * Project: csci205FinalProject
 * Package: Controller
 * Class: BoardControl
 *
 * Description:
 *
 * ****************************************
 */
package Controller;


import Game.Board;
import Game.Spaces.*;
import Model.MonopolyModel;
import View.BoardView;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Controller class for the Board
 */
public class BoardController
{
    /**
     * Model for the game
     */
    private MonopolyModel theModel;

    /**
     * View for the Board
     */
    private BoardView boardView;

    /**
     * Constructor
     *
     * @param theModel  model for the game
     * @param boardView view for the board
     */
    public BoardController(MonopolyModel theModel, BoardView boardView) {
        this.theModel = theModel;
        this.boardView = boardView;
        generateActionEvents();
    }

    /**
     * Generates the action events
     */
    private void generateActionEvents() {
        //Add all action events here
        createPopups();
    }

    /**
     * Generates popups when the player clicks on a space
     */
    private void createPopups() {
        for (int i = 0; i < BoardView.NUM_SPACES; i++) {
            if (boardView.getListOfSpaces().get(i) instanceof Property || boardView.getListOfSpaces().get(i) instanceof Railroads) {
                pricePopupOnClick(boardView.getListOfLabels().get(i), boardView.getListOfSpaces().get(i), i);
            }
            if (boardView.getListOfSpaces().get(i) instanceof Tax) {
                taxPopupOnClick(boardView.getListOfLabels().get(i), boardView.getListOfSpaces().get(i));
            }

        }
    }

    /**
     * Creates a popup window for tax prices
     * @param label - label to be clicked on for popup window to show
     * @param curSpace - space with information to display
     */
    private void taxPopupOnClick(Label label, Space curSpace) {
        label.setOnMouseClicked(mouseEvent ->
        {
            Stage dialog = new Stage();
            VBox popup = new VBox(20);
            Text propPrices = new Text(curSpace.getName() + " Tax");
            Text taxAmount = new Text("Tax amount: $" + ((Tax)curSpace).getTax());
            popup.getChildren().add(propPrices);
            popup.getChildren().add(taxAmount);
            popup.setAlignment(Pos.TOP_CENTER);
            Scene dialogScene = new Scene(popup, 300, 100);
            dialog.setScene(dialogScene);
            dialog.show();
        });
    }

    /**
     * Creates a popup window for property/railroad prices
     * @param label - the text object in the square
     * @param curSpace - the board space
     * @param i the index of the space
     */
    private void pricePopupOnClick(Label label, Space curSpace, int i) {
        label.setOnMouseClicked(mouseEvent ->
        {
            Stage dialog = new Stage();
            VBox popup = new VBox(20);


            Text propPrices = new Text(curSpace.getName() + " Price");
            Text rentPrice;
            if (curSpace instanceof Property) {
                rentPrice = new Text("Rent Price: $" + ((Property) curSpace).getRent());
            } else {
                rentPrice = new Text("Rent Price for One Railroad Owned: $" + ((Railroads) curSpace).getRent(1) + "\nRent Price for Two Railroads Owned: $" + ((Railroads) curSpace).getRent(2) + "\nRent Price for Three Railroads Owned: $" + ((Railroads) curSpace).getRent(3) + "\nRent Price for Four Railroads Owned: $" + (((Railroads) curSpace).getRent(4)));
            }
            String propertyOwner = getPropertyOwners(theModel.getGame().getBoard(), i);
            Text owner = new Text("Owner: " + propertyOwner);
            popup.getChildren().add(propPrices);
            popup.getChildren().add(rentPrice);
            popup.getChildren().add(owner);
            popup.setAlignment(Pos.TOP_CENTER);
            Scene dialogScene = new Scene(popup, 300, 100);
            dialog.setScene(dialogScene);
            dialog.show();
        });
    }

    public static void cardPopUp(String popUpText) {

        Stage card = new Stage();
        VBox popup = new VBox(20);

        Text cardText = new Text(popUpText);

        popup.getChildren().add(cardText);

        popup.setAlignment(Pos.TOP_CENTER);
        Scene cardScene = new Scene(popup, 400, 100);
        card.setScene(cardScene);
        card.show();
    }

    /**
     * Finds the property owner
     * @param theBoard - space in question
     * @param i the index of the space
     * @return - string of owner
     */
    private String getPropertyOwners(Board theBoard, int i) {
        int owner = ((Buyable) theBoard.getBoard().get(i)).getOwner();
        if (owner == -1) {
            //Testing
            //System.out.println("RAN_PROPERTY_OWNERS_UNOWNED");
            return " None";
        } else {
            //Testing
            //System.out.println("RAN_PROPERTY_OWNERS_OWNED");
            return theModel.getGame().getPlayerList()[owner].getName();
        }
    }

    /**
     * Updates the information in the popups
     */
    public void update() {
        generateActionEvents();
    }

    /**
     * Updates the model
     *
     * @param theModel the new Model
     */
    public void updateModel(MonopolyModel theModel) {
        this.theModel = theModel;
        update();
    }
}