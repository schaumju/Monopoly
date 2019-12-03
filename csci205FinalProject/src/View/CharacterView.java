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
 * Package: View
 * Class: CharacterView
 *
 * Description:
 *
 * ****************************************
 */
package View;

import Game.Character;
import Model.MonopolyModel;
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
     * @param mainView
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
        
        for (int i = 0; i < boardPlayerList.size(); i++)
        {
            mainView.getRoot().getChildren().add(boardPlayerList.get(i));
            //Staggering the player circles
            boardPlayerList.get(i).setTranslateY(translateAmount*i);
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
            System.out.println(playerList[i].getPosition());
            int playerMoves = 35 + (70 * (playerList[i].getPosition()%10));
            System.out.println(playerMoves);

            //Amount to move horizontally
            double translateX = 50;

            //Amount to move vertically
            double translateY = 50;

            //If player is on the top row
            if (playerList[i].getPosition() < 10) {
                translateY = 0;
                translateX = playerMoves;
            }

            //If player is on the right row
            else if (playerList[i].getPosition() >= 10 && playerList[i].getPosition() < 20)
            {
                translateY = playerMoves; //Since its going down down down down
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
}