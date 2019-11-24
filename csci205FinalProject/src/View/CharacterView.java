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
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.List;

public class CharacterView {

    //List of Circle objects
    private List<Circle> boardPlayerList;

    //List of Character objects
    private Character[] playerList;

    public CharacterView(Character[] playerList)
    {
        this.playerList = playerList;
        this.boardPlayerList = new ArrayList<>();
        convertPlayers(playerList);
    }

    private void convertPlayers(Character[] playerList) {
        //Fills the circle list using the players in playerList.
        for (Character player : playerList)
        {
            boardPlayerList.add(new Circle(30, player.getColor()));
        }
    }

    //Adds the characters contained in the list
    public void addCharacters()
    {
        for (int i = 0; i < boardPlayerList.size(); i++)
        {
            MainView.getRoot().getChildren().add(boardPlayerList.get(i));
        }
    }

    //Updates the characters positions
    public void updateCharacters()
    {
        for(int i = 0; i < boardPlayerList.size(); i++)
        {
            MainView.getRoot().getChildren().remove(boardPlayerList.get(i));
            int playerMoves = 70 * playerList[i].getPosition();
            boardPlayerList.get(i).setTranslateX(4+playerMoves);
            MainView.getRoot().getChildren().add(boardPlayerList.get(i));
        }
    }
}