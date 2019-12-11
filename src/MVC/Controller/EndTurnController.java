package MVC.Controller;

import MVC.Model.MonopolyModel;
import MVC.View.MainView;
import Networking.client.Client;

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
                theView.getLogView().updateLog();
                if(client != null) {
                    client.writeToServer();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            theView.endTurn();
        });
    }

    /**
     * Getter method for the client
     *
     * @return the client
     */
    public Client getClient() {
        return client;
    }

    /**
     * Updates the model
     *
     * @param theModel the new MVC.Model
     */
    public void updateModel(MonopolyModel theModel) {
        this.theModel = theModel;
    }
}