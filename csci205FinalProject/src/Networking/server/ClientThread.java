package Networking.server;

import Model.MonopolyModel;
import Networking.TurnState;
import javafx.application.Platform;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Thread class for each client
 */
public class ClientThread implements Runnable {

    /**
     * The socket of the client
     */
    private Socket clientSocket;
    /**
     * The server class
     */
    private Server baseServer;
    /**
     * Input stream to read incoming messages
     */
    private ObjectInputStream incomingMessageReader;
    /**
     * Output stream to send messages
     */
    private ObjectOutputStream outgoingMessageWriter;
    /**
     * The name of the client
     */
    private String clientName;
    /**
     * Determines if the thread is connected
     */
    private boolean connected;

    /**
     * Constructor
     *
     * @param clientSocket the client socket
     * @param baseServer   the server
     */
    public ClientThread(Socket clientSocket, Server baseServer) {
        this.setClientSocket(clientSocket);
        this.connected = false;
        this.baseServer = baseServer;
        try {

            outgoingMessageWriter = new ObjectOutputStream(clientSocket.getOutputStream());
            outgoingMessageWriter.flush();

            incomingMessageReader = new ObjectInputStream(clientSocket.getInputStream());


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Constantly runs to look for input from the clients
     */
    public void run() {
        try {
            this.clientName = getClientNameFromNetwork();
            Platform.runLater(new Runnable() {

                @Override
                public void run() {
                    baseServer.clientNames.add(clientName + " - "
                            + clientSocket.getRemoteSocketAddress());

                    if (baseServer.clientNames.size() == baseServer.NUM_PLAYERS) {
                        baseServer.closeSocket();
                    }


                }

            });
            /**
             * Reads input and determines what to do with it
             */
            Object inputToServer;
            while (true) {
                inputToServer = incomingMessageReader.readObject();
                //System.err.println(inputToServer);
                if (inputToServer instanceof MonopolyModel) {
                    // Update the server log
                    System.out.println("HERE");
                    //baseServer.log = ((MonopolyModel) inputToServer).getLog();
                    baseServer.update((MonopolyModel) inputToServer);
                    baseServer.changeTurn();
                    baseServer.writeToAllClients(baseServer.getTheModel());

                } else if (inputToServer instanceof TurnState) {
                    writeToClient(inputToServer);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes to the client
     * @param input the object being written to the client
     */
    public void writeToClient(Object input) throws IOException {
        outgoingMessageWriter.writeObject(input);
        outgoingMessageWriter.flush();
    }

    /**
     * Gets the name fo the client which is the first data it reads in always
     * @return the name of the client
     */
    public String getClientNameFromNetwork() throws IOException, ClassNotFoundException {
        /*
         * Get the name of the client, which is the first data transaction the
         * server-client make
         */
        return (String) incomingMessageReader.readObject();
    }

    /**
     * Getter method for client name
     * @return the client name
     */
    public String getClientName() {
        return this.clientName;
    }

    /**
     * Getter method for the client socket
     * @return the client socket
     */
    public Socket getClientSocket() {
        return clientSocket;
    }

    /**
     * Setter method for the client socket
     * @param clientSocket the client socket
     */
    public void setClientSocket(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    /**
     * Used to disconnect the thread appropriately
     */
    public synchronized void waitForDisconnect() {
        while (connected) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
    }

}
