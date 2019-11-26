package View;

import Model.MonopolyModel;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;


/**
 * View for the GameLog
 */
public class LogView {

    /**
     * The model of the game
     */
    private MonopolyModel theModel;

    /**
     * Constructor
     *
     * @param theModel the model of the game
     */
    public LogView(MonopolyModel theModel) {
        this.theModel = theModel;
        /* Make the server log ListView */
        Label logLabel = new Label("Game log");
        ListView<String> logView = new ListView<>();
        ObservableList<String> logList = theModel.getLog().getLog();
        logView.setItems(logList);
        MainView.getRoot().addColumn(MainView.getRoot().getColumnCount(), logView);
    }
}
