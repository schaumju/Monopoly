package Model;

import Game.Board;
import Controller.BoardController;
import Controller.MainController;
import Game.Board;
import Game.Character;
import Game.Game;
import Game.Spaces.Property;
import View.BoardView;
import View.MainView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MonopolyModel extends Application {

    private MainView theView;
    private MainController theController;
    private MonopolyModel theModel;
    private Board theBoard;
    private Game Game;

    public static void main(String[] args) { launch(args); }

    /**
     * starts the graphic
     * @param primaryStage
     * @author kerri
     */
    @Override
    public void start(Stage primaryStage) {

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
        this.theBoard = new Board();
        this.theView = new MainView(theModel);

        //TESTING MOVING CHARACTERS
        this.Game = new Game(new Character[]{new Character("Player1", Color.RED), new Character("Player2", Color.BLUE)});
        this.Game.getCharacters().addCharacters();

        //Moves the first player by 3 moves
        this.Game.getPlayerList()[0].move(3);
        this.Game.getCharacters().updateCharacters();

        /**
        //Testing popup window ownership
        ((Property)theBoard.getBuyableProperties().get(3)).buyProperty(Game.getPlayerList()[0]);
        System.out.println(((Property) theBoard.getBuyableProperties().get(3)).getOwner());
        */
        this.theController = new MainController(theModel, theView);

    }
    public Game getGame()
    {
        return this.Game;
    }

    public Board getTheBoard() {
        return theBoard;
    }
}
