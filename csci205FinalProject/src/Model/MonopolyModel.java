package Model;

import Controller.BoardController;
import Game.Board;
import View.BoardView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MonopolyModel extends Application {

    private BoardView theView;
    private BoardController theController;
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

        this.theController = new BoardController(theModel, theView);

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
        this.theView = new BoardView(theModel);
    }
}
