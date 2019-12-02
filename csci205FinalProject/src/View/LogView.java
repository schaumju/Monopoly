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
     * The Main View
     */
    private MainView mainView;


    /**
     * Constructor
     *
     * @param theModel the model of the game
     * @param mainView
     */
    public LogView(MonopolyModel theModel, MainView mainView) {
        this.theModel = theModel;
        /* Make the server log ListView */
        Label logLabel = new Label("Game log");
        ListView<String> logView = new ListView<>();
        this.mainView = mainView;
        ObservableList<String> logList = theModel.getLog().getLog();
        logView.setItems(logList);
        mainView.getRoot().addColumn(mainView.getRoot().getColumnCount(), logView);
    }
}
