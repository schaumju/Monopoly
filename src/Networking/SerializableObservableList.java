package Networking;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Stores a ObservableList so that it is Serializable
 */
public class SerializableObservableList implements Serializable {
    /**
     * The log being stored
     */
    private ArrayList<String> log;

    /**
     * Constructor
     *
     * @param log the ObservableList being stored
     */
    public SerializableObservableList(ObservableList<String> log) {
        this.log = new ArrayList<String>();
        for (String logString : log) {
            this.log.add(logString);
        }
    }

    /**
     * Returns the ObservableList<String></String>
     *
     * @return he ObservableList<String></String>
     */
    public ObservableList<String> getObservableList() {
        ObservableList<String> temp = FXCollections.observableArrayList();
        for (int i = 0; i < this.log.size(); i++) {
            temp.add(log.get(i));
        }
        return temp;
    }

    /**
     * Adds to the existing list
     *
     * @param addedString the string being added
     */
    public void add(String addedString) {
        log.add(addedString);
    }

}
