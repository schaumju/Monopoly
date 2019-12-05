package MVC.View;

import MVC.Model.MonopolyModel;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;


/**
 * MVC.View for the GameLog
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
     * The column number where the log is located on the screen
     */
    private int column;
    /**
     * The label of the log
     */
    private Label logLabel;
    /**
     * List of things in the log
     */
    private ListView<String> logView;
    /**
     * Observable list of the log
     */
    private ObservableList<String> logList;


    /**
     * Constructor
     *
     * @param theModel the model of the game
     * @param mainView the game view
     */
    public LogView(MonopolyModel theModel, MainView mainView) {
        this.theModel = theModel;
        /* Make the server log ListView */
        logLabel = new Label("Game log");
        logView = new ListView<>();
        this.mainView = mainView;
        logList = theModel.getLog().getLog();
        logView.setItems(logList);
        column = mainView.getRoot().getColumnCount();
        mainView.getRoot().addColumn(column, logView);
    }

    /**
     * Updates the model
     * @param theModel the new model
     */
    public void updateModel(MonopolyModel theModel) {
        this.theModel = theModel;
        updateLog();
    }

    /**
     * Updates the log
     */
    public void updateLog() {
        logList = theModel.getLog().getLog();
        logView.setItems(logList);
        logView.scrollTo(logList.get(logList.size() - 1));
    }


}
