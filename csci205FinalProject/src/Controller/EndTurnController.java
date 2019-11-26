package Controller;

import Model.MonopolyModel;
import View.MainView;

public class EndTurnController {
    /**
     * The game model
     */
    private MonopolyModel theModel;
    /**
     * View class for the graphics of the game
     */
    private MainView theView;

    /**
     * Constructor
     *
     * @param theModel the game model
     * @param theView  view class for the graphics of the game
     */
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
    private void handleEndTurn() {
        theView.getEndTurnView().getEndTurnButton().setOnMouseClicked(mouseEvent -> {
            theModel.endTurn();
            theView.endTurn();
        });
    }
}
