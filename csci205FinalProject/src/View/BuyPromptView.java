package View;

import Game.Spaces.Buyable;
import Game.Spaces.Space;
import Model.MonopolyModel;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import javax.swing.*;

/**
 * Yes or no pop up window for prompting the user to buy a property
 */
public class BuyPromptView extends Application {

    /**
     * The space potentially being bought
     */
    private Buyable space;

    private JButton yesButton;
    private JButton noButton;

    private MonopolyModel theModel;

    /**
     * Constructor
     *
     * @param space the space that is potentially being bought
     */
    public BuyPromptView(Buyable space, MonopolyModel theModel) {
        this.space = space;
        this.theModel = theModel;
    }

    @Override
    public void init() throws Exception {
        super.init();
        yesButton = new JButton("Yes");
        noButton = new JButton("No");
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        //HBox root = new HBox();
        //primaryStage.setScene(new Scene(root,200,200));

        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle("Would you like to buy this property for $" + space.getCost());
        //alert.setContentText("Save?");
        ButtonType yesButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
        alert.getButtonTypes().setAll(yesButton, noButton);
        alert.showAndWait().ifPresent(type -> {
            if (type == ButtonType.OK) {
                theModel.buyProperty((Space) space);
            } else {

            }
            alert.close();
            primaryStage.close();
        });


    }
}
