package Main;

import Controller.MainController;
import Game.Character;
import Model.MonopolyModel;
import View.MainView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    private MonopolyModel theModel;
    private MainView theView;
    private MainController theController;

    @Override
    public void init() throws Exception {
        super.init();
        /**
         * For testing purposes for now leave be
         */
        theModel = new MonopolyModel(new Character[]{new Character("Player 1", Color.RED), new Character("Player 2", Color.BLUE)});
        theView = new MainView(theModel);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        theController = new MainController(theModel, theView);
        primaryStage.setTitle("Monopoly");
        primaryStage.setScene(new Scene(MainView.getRoot()));
        primaryStage.show();

    }


}
