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

import Game.Character;
import Model.MonopolyModel;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class MainView {

    private MonopolyModel theModel;
    private static GridPane root;

    public MainView(MonopolyModel theModel) {
        this.theModel = theModel;

        setUpRoot();

        BoardView.addBoardSpaces();

        DiceView.addDice();

        //new CharacterView(new Character[]{new Character("Player1", Color.RED), new Character("Player2", Color.BLUE)});

        PropertyView.addPropertyBuyButton();
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
}