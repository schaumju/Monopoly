package Networking.client;

import Game.Character;
import MVC.Controller.MainController;
import MVC.Model.MonopolyModel;
import MVC.View.MainView;
import Networking.TurnState;
import javafx.application.Platform;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

/**
 * Class to represent the client in a server-client protocol
 */
public class Client implements Runnable, Serializable {
    /**
     * The socket of the client
     */
    private Socket clientSocket;
    /**
     * Input Stream for reading input from the Server
     */
    private ObjectInputStream serverToClientReader;
    /**
     * Output Stream for sending messages to the Server
     */
    private ObjectOutputStream clientToServerWriter;
    /**
     * Name of the player
     */
    private String name;
    /**
     * Boolean value to determine if the game is ready to display the board
     */
    private boolean graphicsReady;
    /**
     * The game model
     */
    private MonopolyModel theModel;
    /**
     * The game graphics
     */
    private MainView theView;
    /**
     * The game controller
     */
    private MainController theController;
    /**
     * The current state of the player in the game
     */
    private TurnState turnState;

    /**
     * Constructor
     *
     * @param hostName the IP address of the host
     * @param portNumber the port number
     * @param name       the name of the player
     * @throws IOException
     */
    public Client(String hostName, int portNumber, String name) throws IOException {
        System.err.println(name);
        turnState = TurnState.WAITING;
        graphicsReady = false;
        /* Try to establish a connection to the server */
        clientSocket = new Socket(hostName, portNumber);
        /* Instantiate writers and readers to the socket */
        clientToServerWriter = new ObjectOutputStream(clientSocket.getOutputStream());
        serverToClientReader = new ObjectInputStream(clientSocket.getInputStream());
        clientToServerWriter.flush();
        /* Send name data to the server */
        this.name = name;

        clientToServerWriter.writeObject(name);
        clientToServerWriter.flush();

    }

    /**
     * Runs so that the Client is continually looking for input from the Server
     */
    public void run() {
        /* Infinite loop to update the chat log from the server */

        do {
            if (theView != null) {

                if (turnState != TurnState.IN_TURN) {
                    theView.getDiceView().getRollDiceBtn().setDisable(true);
                } else {
                    theView.getDiceView().getRollDiceBtn().setDisable(false);
                }
            }

            try {
                final Object inputFromServer = serverToClientReader.readObject();
                if (theView != null) {
                    if (turnState != TurnState.IN_TURN) {
                        theView.getDiceView().getRollDiceBtn().setDisable(true);
                    } else {
                        theView.getDiceView().getRollDiceBtn().setDisable(false);
                    }
                }

                Platform.runLater(() -> {
                    if (inputFromServer instanceof Character[]) {
                        initiateGame(inputFromServer);
                    } else if (inputFromServer instanceof MonopolyModel) {
                        theModel = (MonopolyModel) inputFromServer;
                        updateModel();
                        theView.getCharacterView().updateCharacters();

                    }
                    // If you receive a TurnState from the server that means your turn is over so update the server with your model
                    else if (inputFromServer instanceof TurnState) {

                        turnState = (TurnState) inputFromServer;
                        if (turnState != TurnState.IN_TURN) {
                            theView.getDiceView().getRollDiceBtn().setDisable(true);
                        } else {
                            theView.getDiceView().getRollDiceBtn().setDisable(false);
                        }


                    }
                });

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } while (true);
    }

    /**
     * Updates the MonopolyModel object in the MVC.View and MVC.Controller
     */
    private void updateModel() {
        theView.updateModel(theModel);
        theController.updateModel(theModel);
    }

    /**
     * Writes the MonopolyModel object to the Server
     */
    public void writeToServer() throws IOException {
        clientToServerWriter.writeObject(theModel);
        clientToServerWriter.flush();

    }

    /**
     * Creates the classes needed to run the game
     *
     * @param inputFromServer the Array of character objects from the server
     */
    private void initiateGame(Object inputFromServer) {
        theModel = new MonopolyModel((Game.Character[]) inputFromServer);
        theView = new MainView(theModel);
        theView.getDiceView().getRollDiceBtn().setDisable(true);
        theController = new MainController(theModel, theView, this);
        graphicsReady = true;

    }

    /**
     * Getter method
     */
    public boolean isGraphicsReady() {
        return graphicsReady;
    }

    /**
     * Getter method
     */
    public MainView getTheView() {
        return theView;
    }

    /**
     * Getter method
     */
    public MonopolyModel getTheModel() {
        return theModel;
    }
}
