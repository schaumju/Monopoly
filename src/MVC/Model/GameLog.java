package MVC.Model;

import Networking.SerializableObservableList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;

/**
 * Class to represent a log of all the moves in the game
 *
 * @author Justin
 */
public class GameLog implements Serializable {
    /**
     * The log
     */
    private SerializableObservableList log;

    /**
     * Constructor
     */
    public GameLog() {
        log = new SerializableObservableList(FXCollections.observableArrayList());
    }

    /**
     * Adds the input to the log
     *
     * @param description the String being added to the log
     */
    public void addToLog(String description) {
        log.add(description);
    }

    /**
     * Getter method
     */
    public ObservableList<String> getLog() {
        return log.getObservableList();
    }
}
