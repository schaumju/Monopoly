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

    public CharacterView(Character[] playerList)
    {
        this.boardPlayerList = new ArrayList<>();
        convertPlayers(playerList);
        addCharacters();
    }

    private void convertPlayers(Character[] playerList) {
        //Fills the circle list using the players in playerList.
        for (Character player : playerList)
        {
            boardPlayerList.add(new Circle(100, player.getColor()));
        }
    }

    //Adds the characters contained in the
    public void addCharacters()
    {
        for (Circle playerCircle: boardPlayerList)
        {
            MainView.getRoot().getChildren().addAll(playerCircle);
        }
    }
}