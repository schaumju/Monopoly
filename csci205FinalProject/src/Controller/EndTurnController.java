package Controller;

import Model.MonopolyModel;
import Networking.client.Client;
import View.MainView;

import java.io.IOException;

public class EndTurnController {
    /**
     * The game model
     */
    private MonopolyModel theModel;
    /**
     * View class for the graphics of the game
     */
    private MainView theView;

    private Client client;


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
     * Constructor
     *
     * @param theModel the game model
     * @param theView  view class for the graphics of the game
     * @param client   the client
     */
    public EndTurnController(MonopolyModel theModel, MainView theView, Client client) {
        this.theModel = theModel;
        this.theView = theView;
        this.client = client;
        handleEndTurn();
    }

    /**
     * handles when the player wants to end their turn
     *
     * @author justin
     */
    private void handleEndTurn() {
        theView.getEndTurnView().getEndTurnButton().setOnMouseClicked(mouseEvent -> {
            try {
                theModel.endTurn();
                client.writeToServer();
            } catch (IOException e) {
                e.printStackTrace();
            }
            theView.endTurn();
        });
    }
}
