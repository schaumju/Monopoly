package Networking;

import java.util.ArrayList;
import java.util.List;

/**
 * @ref: https://github.com/berkaybarlas/Ultimate-Monopoly-Online-Game/blob/master/Final_Project_Report.pdf
 */
public class ServerInfo {
    /**
     * Static instance of the ServerInfo used for concurrency
     */
    private static ServerInfo _instance;
    /**
     * ID of the client
     */
    private String clientID;
    /**
     * List of the clients
     */
    private List<String> clientList;
    /**
     * number of failed attempts
     */
    private int failedAttempt = 0;

    /**
     * Constructor
     */
    private ServerInfo() {
        clientList = new ArrayList<>();
    }

    /**
     * Used for concurrency
     *
     * @return
     */
    public static ServerInfo getInstance() {
        if (_instance == null) {
            _instance = new ServerInfo();
        }
        return _instance;
    }

    /**
     * Gets clientID
     */
    public String getClientID() {
        return clientID;
    }

    /**
     * Set the clientID
     *
     * @param clientID the new clientID
     */
    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    /**
     * Adds a client to the list
     *
     * @param clientId the client being added
     */
    public void addClient(String clientId) {
        clientList.add(clientId);
    }

    /**
     * Removes a client from the list
     *
     * @param clientId the id of the client being removed
     */
    public void removeClient(String clientId) {
        int clientIndex = clientList.indexOf(clientId);
        if (clientIndex == -1) {
            return;
        }
        clientList.remove(clientIndex);
    }

    /**
     * Gets the list of clients
     */
    public List<String> getClientList() {
        return clientList;
    }

    /**
     * Sets the client list to a new list of clients
     *
     * @param clientList the new list of clients
     */
    public void setClientList(List<String> clientList) {
        this.clientList = clientList;
    }

    /**
     * Gets the next client
     *
     * @return
     */
    public String next() {
        if (clientList == null || clientList.size() == 1) return "";
        if (clientList.size() < 2)
            return clientList.get(0);
        failedAttempt = (failedAttempt + 1) % clientList.size();
        return clientList.get(failedAttempt);
    }

    /**
     * Tests to make sure that there is more than 1 clients playing the game
     *
     * @return true if the server is active (more than 1 player playing) and false otherwise
     */
    public boolean isServer() {
        if (clientList == null || clientList.size() < 1) {
            return false;
        }
        return clientList.get(0).equals(clientID);
    }

    /**
     * Tests to see if the specified client is connected
     *
     * @param clientID the client being searched for
     * @return true if the client is in the list and false otherwise
     */
    public boolean isOnline(String clientID) {

        if (clientList == null || clientID == null || clientID == "") {
            return false;
        }
        return clientList.contains(clientID);
    }
}

