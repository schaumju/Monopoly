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
 * Package: MVC.View
 * Class: CommunityChestChanceCardsView
 *
 * Description:
 *
 * ****************************************
 */
package MVC.View;


import MVC.Model.MonopolyModel;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;

public class CardsView {
    /**
     * Model for the game
     */
    private MonopolyModel theModel;
    /**
     * The Main View
     */
    private MainView mainView;

    /**
     * Constructor
     *
     * @param theModel model for the game
     * @param mainView the game view
     */
    public CardsView(MonopolyModel theModel, MainView mainView) {
        this.theModel = theModel;
        this.mainView = mainView;
        addCards();
    }

    /**
     * adds the community chest and the chance cards onto the board
     * @author - kerri
     */
    public void addCards() {

        Rectangle communityChest = new Rectangle(70,70, Color.WHITE);
        communityChest.setTranslateX(400);
        communityChest.setTranslateY(525);

        Label communityChestLabel = new Label("Community\nChest");
        communityChestLabel.setTextAlignment(TextAlignment.CENTER);
        communityChestLabel.setTranslateX(400);
        communityChestLabel.setTranslateY(525);

        Rectangle chance = new Rectangle(70, 70, Color.WHITE);
        chance.setTranslateX(500);
        chance.setTranslateY(525);

        Label chanceLabel = new Label("Chance");
        chanceLabel.setTextAlignment(TextAlignment.CENTER);
        chanceLabel.setTranslateX(500);
        chanceLabel.setTranslateY(525);

        mainView.getRoot().getChildren().addAll(communityChest, chance, communityChestLabel, chanceLabel);
    }

    /**
     * Updates the model
     * @param theModel the new model
     */
    public void updateModel(MonopolyModel theModel) {
        this.theModel = theModel;
    }
}