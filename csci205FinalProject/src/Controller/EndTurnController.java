package Controller;

import Model.MonopolyModel;
import View.MainView;

public class EndTurnController {
    private MonopolyModel theModel;
    private MainView theView;

    public EndTurnController(MonopolyModel theModel, MainView theView) {
        this.theModel = theModel;
        this.theView = theView;
        handleEndTurn();
    }

    /**
     * handles when the player wants to end their turn
     *
     * @author justin
     */
    public void handleEndTurn() {
        theView.getEndTurnView().getEndTurnButton().setOnMouseClicked(mouseEvent -> {
            theModel.endTurn();
            theView.endTurn();
        });
    }
}
