package Model;

import Controller.BoardController;
import Controller.MainController;
import Game.Board;
import View.BoardView;
import View.MainView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MonopolyModel extends Application {

    private MainView theView;
    private MainController theController;
    private MonopolyModel theModel;
    private Board theBoard;

    public static void main(String[] args) { launch(args); }

    /**
     * starts the graphic
     * @param primaryStage
     * @author kerri
     */
    @Override
    public void start(Stage primaryStage) {

        this.theController = new MainController(theModel, theView);

        // add the scene graph to the stage
        primaryStage.setScene(new Scene(theView.getRoot()));
        primaryStage.sizeToScene();

        // set the title for the main window
        primaryStage.setTitle("Monopoly");

        // display the screen
        primaryStage.show();
    }

    /**
     * initializes the model and view
     * @throws Exception
     * @author kerri
     */
    @Override
    public void init() throws Exception {
        super.init();
        this.theModel = new MonopolyModel();
        this.theBoard = new Board();
        this.theView = new MainView(theModel);
    }
}
