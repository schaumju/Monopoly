package Networking;

import Model.MonopolyModel;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ResponseController implements Serializable {

    private static ResponseController _instance;
    private ArrayList<Socket> listenerClients;
    private ArrayList<ObjectOutputStream> listenerClientOutputs;
    private ObjectOutputStream outObject;
    private ServerInfo serverInfo = ServerInfo.getInstance();
    private MonopolyModel model = MonopolyModel.getInstance();

    public ResponseController() {
        this.listenerClients = new ArrayList<>();
        this.listenerClientOutputs = new ArrayList<>();
    }

    public static ResponseController getInstance() {
        if (_instance == null) {
            _instance = new ResponseController();
        }
        return _instance;
    }

    /**
     * Adds a socket to the list of clientSockets
     *
     * @param socket the socket being added
     */
    public void addSocket(Socket socket) {
        listenerClients.add(socket);
        try {
            listenerClientOutputs.add(new ObjectOutputStream(socket.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sends a message to all of the clients
     *
     * @param message the message being sent
     */
    public synchronized void sendResponse(Object message) {

        for (ObjectOutputStream stream : listenerClientOutputs) {
            try {
                stream.writeObject(message);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("ERROR");
            }
        }
    }

    public void sendGameData(Socket socket) throws IOException {

        int indexOfClient = listenerClients.indexOf(socket);
        try {
            System.out.println("[ResponseController]:" + "trying to send object.");
            outObject = listenerClientOutputs.get(indexOfClient);
            List<String> clientList = serverInfo.getClientList();
            outObject.writeObject(clientList);
            //outObject.writeObject(PlayerController.getInstance());
            outObject.writeObject(model);
            outObject.writeObject(GameEngine.getInstance().getDomainBoard());
            outObject.writeObject("loadData");
            outObject.reset();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("[ResponseController]:" + "sending object failed.");
            outObject.close();
            listenerClients.remove(indexOfClient);
        } finally {
            System.out.println("[ResponseController]:" + "sending object finished.");
        }
    }


    /**
     * Closes a connection with the clients
     */
    public void closeConnections() {
        if (listenerClientOutputs != null) {
            listenerClientOutputs.forEach(out -> {
                try {
                    out.close();
                    listenerClientOutputs.remove(out);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

        if (listenerClients != null) {
            listenerClients.forEach(socket -> {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }


}
