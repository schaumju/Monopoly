package Networking.server;

import Game.Character;
import Model.GameLog;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server implements Runnable {
    public final int NUM_PLAYERS;
    private final Color[] playerColors = new Color[]{Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW};
    public ObservableList<String> serverLog;
    public ObservableList<String> clientNames;
    public GameLog log;
    private int portNumber;
    private ServerSocket socket;
    private ArrayList<Socket> clients;
    private ArrayList<ClientThread> clientThreads;
    private Character[] playerList;

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
        clients = new ArrayList<Socket>();
        clientThreads = new ArrayList<ClientThread>();
        socket = new ServerSocket(portNumber);
        //For testing purposes because choosing port
        System.out.println(socket.getLocalPort());
    }

    public void run() {

        try {
            /* Infinite loop to accept any incoming connection requests */
            while (clients.size() < NUM_PLAYERS) {
                /* Add to log that the server's listening */


                final Socket clientSocket = socket.accept();

                /* Add the incoming socket connection to the list of clients */
                clients.add(clientSocket);
                /* Add to log that a client connected */
                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {
                        serverLog.add("Client " + clientSocket.getRemoteSocketAddress() + " connected");

                    }
                });

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

    private void createPlayerList() {
        playerList = new Character[NUM_PLAYERS];
        for (int i = 0; i < NUM_PLAYERS; i++) {
            playerList[i] = new Character(clientNames.get(i), playerColors[i]);
        }
    }


    public void clientDisconnected(ClientThread client) {

        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                serverLog.add("Client "
                        + client.getClientSocket().getRemoteSocketAddress()
                        + " disconnected");
                clients.remove(clientThreads.indexOf(client));
                clientNames.remove(clientThreads.indexOf(client));
                clientThreads.remove(client);
            }
        });


    }

    public void writeToAllClients(Object message) throws IOException {
        for (ClientThread clientThread : clientThreads) {
            clientThread.writeToClient(message);
        }
    }

    /**
     * Closes the socket once it reaches the correct number of clients
     */
    public void closeSocket() {
        try {
            socket.close();
            createPlayerList();
            writeToAllClients(playerList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
