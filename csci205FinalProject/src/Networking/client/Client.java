package Networking.client;

import Controller.MainController;
import Model.MonopolyModel;
import View.MainView;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.net.Socket;

public class Client implements Runnable {
    public ObservableList<String> chatLog;
    /* The Socket of the Client */
    private Socket clientSocket;
    private ObjectInputStream serverToClientReader;
    private ObjectOutputStream clientToServerWriter;
    private String name;
    private boolean graphicsReady;
    private MonopolyModel theModel;
    private MainView theView;
    private MainController theController;

    public Client(String hostName, int portNumber, String name) throws IOException {
        graphicsReady = false;
        /* Try to establish a connection to the server */
        clientSocket = new Socket(hostName, portNumber);
        /* Instantiate writers and readers to the socket */


        clientToServerWriter = new ObjectOutputStream(clientSocket.getOutputStream());
        serverToClientReader = new ObjectInputStream(clientSocket.getInputStream());
        clientToServerWriter.flush();
        chatLog = FXCollections.observableArrayList();
        /* Send name data to the server */
        this.name = name;

        clientToServerWriter.writeObject(name);
        clientToServerWriter.flush();

    }


    public void run() {
        /* Infinite loop to update the chat log from the server */

        while (true) {
            try {
                final Object inputFromServer = serverToClientReader.readObject();
                System.err.println(inputFromServer);
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        if (inputFromServer instanceof Array) {
                            initiateGame(inputFromServer);
                        } else if (inputFromServer instanceof MonopolyModel) {
                            theModel = (MonopolyModel) inputFromServer;
                        }
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
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
        theController = new MainController(theModel, theView);
        graphicsReady = true;

    }

    public boolean isGraphicsReady() {
        return graphicsReady;
    }

    public MainView getTheView() {
        return theView;
    }
}
