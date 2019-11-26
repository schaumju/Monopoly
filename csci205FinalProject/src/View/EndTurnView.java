package View;

import Model.MonopolyModel;
import javafx.scene.control.Button;

public class EndTurnView {
    /**
     * Button to end turn
     */
    private Button endTurnButton;
    /**
     * Game model
     */
    private MonopolyModel theModel;

    /**
     * Constructor
     *
     * @param theModel game model
     */
    public EndTurnView(MonopolyModel theModel) {
        this.theModel = theModel;
        addEndTurnButton();
    }

    /**
     * adds a end turn button to the view
     *
     * @author - justin
     */
    private void addEndTurnButton() {
        endTurnButton = new Button("End Turn");

        endTurnButton.setTranslateX(600);
        endTurnButton.setTranslateY(650);

        // Set the button to be disabled at first
        turnButtonOff();
        MainView.getRoot().getChildren().addAll(endTurnButton);
    }

    public Button getEndTurnButton() {
        return endTurnButton;
    }

    /**
     * Turns the button to clickable
     */
    public void turnButtonOn() {
        endTurnButton.setDisable(false);

    }

    /**
     * Makes the button not clickable
     */
    private void turnButtonOff() {
        endTurnButton.setDisable(true);
    }
}
