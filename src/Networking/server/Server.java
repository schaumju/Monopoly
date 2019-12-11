package Networking.server;

import Game.Character;
import MVC.Controller.MainController;
import MVC.Model.GameLog;
import MVC.Model.MonopolyModel;
import MVC.View.MainView;
import Networking.TurnState;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server implements Runnable {
    /**
     * Number of players in the game
     */
    protected final int NUM_PLAYERS;
    /**
     * Array of the possible colors for the players
     */
    private final Color[] playerColors = new Color[]{Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW};
    /**
     * List of client names
     */
    protected ObservableList<String> clientNames;
    /**
     * the game log
     */
    protected GameLog log;
    /**
     * The server log
     */
    private ObservableList<String> serverLog;
    /**
     * The port number being used for the server
     */
    private int portNumber;
    /**
     * The server socket
     */
    private ServerSocket socket;
    /**
     * List of client sockets
     */
    private ArrayList<Socket> clients;
    /**
     * List of client threads
     */
    private ArrayList<ClientThread> clientThreads;
    /**
     * List of players
     */
    private Character[] playerList;
    /**
     * The game model
     */
    private MonopolyModel theModel;
    /**
     * The game view
     */
    private MainView theView;
    /**
     * The game controller
     */
    private MainController theController;

    /**
     * Constructor
     *
     * @param portNumber      the port number the server is being run on
     * @param numberOfPlayers the number of players that are going to connect
     * @throws IOException
     */
    public Server(int portNumber, int numberOfPlayers) throws IOException {
        this.portNumber = portNumber;
        log = new GameLog();
        NUM_PLAYERS = numberOfPlayers;
        serverLog = FXCollections.observableArrayList();
        clientNames = FXCollections.observableArrayList();
        clients = new ArrayList<>();
        clientThreads = new ArrayList<>();
        socket = new ServerSocket(portNumber);
        //For testing purposes because choosing port
        System.out.println(socket.getLocalPort());
        InetAddress address = InetAddress.getLocalHost();
        String hostIP = address.getHostAddress();

        System.out.println("Your IP is: " + hostIP);
    }

    /**
     * Runs continuously to allow clients to join the server
     */
    public void run() {

        try {
            /* Infinite loop to accept any incoming connection requests */
            while (clients.size() < NUM_PLAYERS) {
                /* Add to log that the server's listening */


                final Socket clientSocket = socket.accept();

                /* Add the incoming socket connection to the list of clients */
                clients.add(clientSocket);
                /* Add to log that a client connected */
                Platform.runLater(() -> serverLog.add("Client " + clientSocket.getRemoteSocketAddress() + " connected"));

                ClientThread clientThreadHolderClass = new ClientThread(clientSocket, this);
                Thread clientThread = new Thread(clientThreadHolderClass);
                clientThreads.add(clientThreadHolderClass);
                clientThread.setDaemon(true);
                clientThread.start();
                ServerApplication.threads.add(clientThread);
                clientThreadHolderClass.waitForDisconnect();


            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * Creates a list of players with colors
     */
    private void createPlayerList() {
        playerList = new Character[NUM_PLAYERS];
        for (int i = 0; i < NUM_PLAYERS; i++) {
            playerList[i] = new Character(clientNames.get(i).substring(0, clientNames.get(i).indexOf(" ")), playerColors[i]);
            playerList[i].setID(i);
        }
    }

    /**
     * disconnects the clientthread from the server
     *
     * @param client the client thread being disconnected
     */
    public void clientDisconnected(ClientThread client) {

        Platform.runLater(() -> {
            serverLog.add("Client "
                    + client.getClientSocket().getRemoteSocketAddress()
                    + " disconnected");
            clients.remove(clientThreads.indexOf(client));
            clientNames.remove(clientThreads.indexOf(client));
            clientThreads.remove(client);
        });


    }

    /**
     * Writes to all the client threads
     *
     * @param message the message being sents
     * @throws IOException
     */
    public void writeToAllClients(Object message) throws IOException {

        for (ClientThread clientThread : clientThreads) {
            clientThread.writeToClient(message);
        }
    }

    /**
     * Write to the client whose turn it was an change their turn state to waiting
     */
    public void changeTurn() throws IOException {
        clientThreads.get(theModel.getGame().getCurPlayer().getID()).writeToClient(TurnState.WAITING);
        System.out.println("Turn over");
        System.out.println(clientThreads.get(theModel.getGame().getCurPlayer().getID()).getClientName());
        theModel.getGame().getNextPlayer();
        clientThreads.get(theModel.getGame().getCurPlayer().getID()).writeToClient(TurnState.IN_TURN);
        System.err.println("turn starting");
        System.err.println(clientThreads.get(theModel.getGame().getCurPlayer().getID()).getClientName());
        System.out.println();

    }

    /**
     * Closes the socket once it reaches the correct number of clients
     */
    public void closeSocket() {
        try {
            socket.close();
            createPlayerList();
            startGame();
            writeToAllClients(playerList);
            System.err.println("turn starting");
            System.err.println(clientThreads.get(theModel.getGame().getCurPlayer().getID()).getClientName());
            clientThreads.get(theModel.getGame().getCurPlayer().getID()).writeToClient(TurnState.IN_TURN);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates the objects necessary to start the game
     */
    private void startGame() {
        theModel = new MonopolyModel(playerList);
        theView = new MainView(theModel);
        theController = new MainController(theModel, theView);


    }

    /**
     * Updates the model
     *
     * @param theModel the new model
     */
    public void update(MonopolyModel theModel) {
        this.theModel = theModel;
        //theModel.testPropertyOwner(theModel.getGame().getBoard().getBoard().get(6));
        this.log = theModel.getLog();
    }

    /**
     * Getter method for the model
     * @return the game model
     */
    public MonopolyModel getTheModel() {
        return theModel;
    }
}
