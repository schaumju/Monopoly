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
     * The Main View
     */
    private MainView mainView;

    /**
     * Constructor
     *
     * @param theModel game model
     * @param mainView
     */
    public EndTurnView(MonopolyModel theModel, MainView mainView) {
        this.theModel = theModel;
        this.mainView = mainView;
        addEndTurnButton();
    }

    /**
     * adds a end turn button to the view
     *
     * @author - justin
     */
    private void addEndTurnButton() {
        endTurnButton = new Button("End\nTurn");

        endTurnButton.setTranslateX(600);
        endTurnButton.setTranslateY(600);

        // Set the button to be disabled at first
        turnButtonOff();
        mainView.getRoot().getChildren().addAll(endTurnButton);
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

    /**
     * Updates the model
     */
    public void updateModel(MonopolyModel theModel) {
        this.theModel = theModel;
    }
}
