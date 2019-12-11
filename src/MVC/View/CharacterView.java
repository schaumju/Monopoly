/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Kerri Palphreyman
 * Section: 11am
 * Date: 11/18/19
 * Time: 11:42 AM
 *
 * Project: csci205FinalProject
 * Package: MVC.View
 * Class: CharacterView
 *
 * Description:
 *
 * ****************************************
 */
package MVC.View;

import Game.Character;
import MVC.Model.MonopolyModel;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.List;

public class CharacterView {

    /**
     * List of circle objects representing the players
     */
    private List<Circle> boardPlayerList;

    /**
     * List of the characters in the game
     */
    private Character[] playerList;
    /**
     * Model object
     */
    private MonopolyModel theModel;
    /**
     * The Main View
     */
    private MainView mainView;

    /**
     * Constructor
     *
     * @param theModel model object
     * @param mainView the game view
     */
    public CharacterView(MonopolyModel theModel, MainView mainView) {
        this.theModel = theModel;
        this.mainView = mainView;
        this.playerList = theModel.getPlayerList();
        this.boardPlayerList = new ArrayList<>();
        convertPlayers();
        addCharacters();
    }

    /**
     * Creates circle objects for all the players in the game with their colors
     */
    private void convertPlayers() {
        //Fills the circle list using the players in playerList.
        for (Character player : playerList)
        {
            boardPlayerList.add(new Circle(10, player.getColor()));
        }
    }

    /**
     * Adds the characters to the board
     */
    private void addCharacters()
    {
        //Amount to stagger the individual circles by
        int translateAmount = 8;

        for (Circle circle : boardPlayerList) {
            mainView.getRoot().getChildren().add(circle);
            //Staggering the player circles
            circle.setTranslateX(35);
        }
    }

    /**
     * Updates the character's position
     */
    public void updateCharacters()
    {
        for(int i = 0; i < boardPlayerList.size(); i++)
        {
            //Removes the previous circle from the board
            mainView.getRoot().getChildren().remove(boardPlayerList.get(i));

            //Non-vector amount to move
            int playerMoves = 35 + (70 * (playerList[i].getPosition()%10));

            //Amount to move horizontally
            double translateX;

            //Amount to move vertically
            double translateY;

            //If player is on the top row
            if (playerList[i].getPosition() < 10) {
                translateY = 0;
                translateX = playerMoves;
            }

            //If player is on the right row
            else if (playerList[i].getPosition() >= 10 && playerList[i].getPosition() < 20)
            {
                translateY = playerMoves - 35;
                translateX = 735;
            }

            //If the player is on the bottom row
            else if (playerList[i].getPosition() >= 20 && playerList[i].getPosition() < 30)
            {
                translateX = 770 - playerMoves;
                translateY = 735;
            }

            //If the player is on the left row
            else
            {
                translateX = 0;
                translateY = 700-playerMoves;
            }

            if (playerList[i].isInJail())
            {
                System.out.println("Public Safety caught you drinking! ...for the fourth time! Go to Jail!");
            }
            boardPlayerList.get(i).setTranslateX(translateX);
            boardPlayerList.get(i).setTranslateY(translateY);

            //Also not sure where to add it in the scene, it kinda blocks the name of the property.
            mainView.getRoot().getChildren().add(boardPlayerList.get(i));
        }
    }

    /**
     * Getter method
     */
    public Character[] getPlayerList() {
        return playerList;
    }

    /**
     * Updates the model
     * @param theModel the new model
     */
    public void updateModel(MonopolyModel theModel) {
        this.theModel = theModel;
        this.playerList = theModel.getPlayerList();
        for (Circle circle : boardPlayerList) {
            mainView.getRoot().getChildren().remove(circle);
        }
        this.boardPlayerList = new ArrayList<>();


        convertPlayers();
        addCharacters();

    }
}