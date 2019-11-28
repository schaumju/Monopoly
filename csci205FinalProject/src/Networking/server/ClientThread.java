package Networking.server;

import Model.MonopolyModel;
import javafx.application.Platform;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/* Thread Class for each incoming client */
public class ClientThread implements Runnable {

    /* The socket of the client */
    private Socket clientSocket;
    /* Server class from which thread was called */
    private Server baseServer;
    /* Reader and writer for sending messages*/
    private ObjectInputStream incomingMessageReader;
    private ObjectOutputStream outgoingMessageWriter;
    /* The name of the client */
    private String clientName;
    private boolean connected;

    public ClientThread(Socket clientSocket, Server baseServer) {
        this.setClientSocket(clientSocket);
        this.connected = false;
        this.baseServer = baseServer;
        try {

            /* Writer to write outgoing messages from the server to the client */
            outgoingMessageWriter = new ObjectOutputStream(clientSocket.getOutputStream());
            outgoingMessageWriter.flush();
            /*
             * Reader to get all incoming messages that the client passes to the
             * server
             */
            incomingMessageReader = new ObjectInputStream(clientSocket.getInputStream());


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
            Object inputToServer;
            while (true) {
                inputToServer = incomingMessageReader.readObject();
                System.err.println(inputToServer);
                if (inputToServer instanceof MonopolyModel) {
                    // Update the server log
                    baseServer.log = ((MonopolyModel) inputToServer).getLog();

                    writeToClient(inputToServer);

                }

            }
        } /*catch (SocketException e) {
			baseServer.clientDisconnected(this);

		} catch (IOException e) {
			e.printStackTrace();
		}*/ catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeToClient(Object input) throws IOException {
        outgoingMessageWriter.writeObject(input);
        outgoingMessageWriter.flush();
    }

    public String getClientNameFromNetwork() throws IOException, ClassNotFoundException {
        /*
         * Get the name of the client, which is the first data transaction the
         * server-client make
         */
        return (String) incomingMessageReader.readObject();
    }

    public String getClientName() {
        return this.clientName;
    }

    public Socket getClientSocket() {
        return clientSocket;
    }

    public void setClientSocket(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }


    public synchronized void waitForDisconnect() {
        while (connected) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
    }

}
