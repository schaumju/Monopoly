package View;

import Model.MonopolyModel;
import javafx.scene.control.Button;

public class EndTurnView {
    private Button endTurnButton;
    private MonopolyModel theModel;

    public EndTurnView(MonopolyModel theModel) {
        this.theModel = theModel;
        addEndTurnButton();
    }

    /**
     * adds a end turn button to the view
     *
     * @author - justin
     */
    public void addEndTurnButton() {
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

    public void turnButtonOn() {
        endTurnButton.setDisable(false);

    }

    public void turnButtonOff() {
        endTurnButton.setDisable(true);
    }
}
