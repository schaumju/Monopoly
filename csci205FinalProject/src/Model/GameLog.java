package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Class to represent a log of all the moves in the game
 *
 * @author Justin
 */
public class GameLog {

    private ObservableList<String> log;

    /**
     * Constructor
     */
    public GameLog() {
        log = FXCollections.observableArrayList();
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
        return log;
    }
}
