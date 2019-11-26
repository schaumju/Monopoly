/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Kerri Palphreyman
 * Section: 11am
 * Date: 11/22/19
 * Time: 11:13 AM
 *
 * Project: csci205FinalProject
 * Package: View
 * Class: CommunityChestChanceCardsView
 *
 * Description:
 *
 * ****************************************
 */
package View;


import Model.MonopolyModel;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CardsView {

    private MonopolyModel theModel;

    public CardsView(MonopolyModel theModel) {
        this.theModel = theModel;
        addCards();
    }

    /**
     * adds the community chest and the chance cards onto the board
     * @author - kerri
     */
    public static void addCards() {

        Rectangle communityChest = new Rectangle(70,70, Color.WHITE);
        communityChest.setTranslateX(400);
        communityChest.setTranslateY(525);

        Label communityChestLabel = new Label("Community\nChest");
        communityChestLabel.setTranslateX(400);
        communityChestLabel.setTranslateY(525);

        Rectangle chance = new Rectangle(70, 70, Color.WHITE);
        chance.setTranslateX(500);
        chance.setTranslateY(525);

        Label chanceLabel = new Label("Chance");
        chanceLabel.setTranslateX(500);
        chanceLabel.setTranslateY(525);

        MainView.getRoot().getChildren().addAll(communityChest, chance, communityChestLabel, chanceLabel);
    }
}