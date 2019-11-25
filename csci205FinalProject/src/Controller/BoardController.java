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
import Game.Spaces.Property;
import Game.Spaces.Railroads;
import Game.Spaces.Space;
import Game.Spaces.Tax;
import Model.MonopolyModel;
import View.BoardView;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class BoardController
{
    //Monopoly model
    private MonopolyModel theModel;

    //Monopoly view
    private BoardView theView;

    public BoardController(MonopolyModel theModel, BoardView theView)
    {
        this.theModel = theModel;
        this.theView = theView;
        generateActionEvents();
    }

    public void generateActionEvents()
    {
        //Add all action events here
        createPopups();
    }

    public void createPopups()
    {
        for (int i = 0; i < BoardView.NUM_SPACES; i++)
        {
            if (BoardView.getListOfSpaces().get(i) instanceof Property || BoardView.getListOfSpaces().get(i) instanceof Railroads)
            {
                pricePopupOnClick(BoardView.getListOfLabels().get(i), BoardView.getListOfSpaces().get(i));
            }
            if(BoardView.getListOfSpaces().get(i) instanceof Tax)
            {

            }
        }
    }

    /**
     * Creates a popup window for property prices
     * @param label - the text object in the square
     * @param curSpace - the board space
     */
    private void pricePopupOnClick(Label label, Space curSpace)
    {
        label.setOnMouseClicked(mouseEvent ->
        {
            Stage dialog = new Stage();
            VBox popup = new VBox(20);


            Text propPrices = new Text(curSpace.getName() + " Price");
            Text rentPrice;
            if (curSpace instanceof Property)
            {
                rentPrice = new Text("Rent Price: $" + ((Property) curSpace).getRent() + ".00");
            }
            else
            {
                rentPrice = new Text("Rent Price for One Railroad Owned: $" + ((Railroads) curSpace).getRent(1) + "\nRent Price for Two Railroads Owned: $" + ((Railroads) curSpace).getRent(2) + "\nRent Price for Three Railroads Owned: $" + ((Railroads) curSpace).getRent(3) + "\nRent Price for Four Railroads Owned: $" + (((Railroads) curSpace).getRent(4)));
            }
            //String propertyOwner = getPropertyOwners(theModel.getGame().getBoard());
            //Text owner = new Text("Owner: " + propertyOwner);
            popup.getChildren().add(propPrices);
            popup.getChildren().add(rentPrice);
            //popup.getChildren().add(owner);
            popup.setAlignment(Pos.TOP_CENTER);
            Scene dialogScene = new Scene(popup, 300, 100);
            dialog.setScene(dialogScene);
            dialog.show();
        });
    }

    /**
     * Finds the property owner
     * @param theBoard - space in question
     * @return - string of owner
     */
    private String getPropertyOwners(Board theBoard)
    {
        System.out.println(theBoard.getBuyableProperties());
        int owner = ((Property)theBoard.getBuyableProperties().get(2)).getOwner();
        if(owner == -1)
        {
            //Testing
            System.out.println("RAN_PROPERTY_OWNERS_UNOWNED");
            return " None";
        }
        else
        {
            //Testing
            System.out.println("RAN_PROPERTY_OWNERS_OWNED");
            return theModel.getGame().getPlayerList()[owner].getName();
        }
    }
}