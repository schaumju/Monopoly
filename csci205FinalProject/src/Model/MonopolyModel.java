package Model;

import Controller.MainController;
import Game.Board;
import Game.Cards.Card;
import Game.Character;
import Game.Game;
import Game.Spaces.Buyable;
import Game.Spaces.Space;
import View.MainView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MonopolyModel extends Application {

    private MainView theView;
    private MainController theController;
    private MonopolyModel theModel;
    private Game game;

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
        primaryStage.setScene(new Scene(MainView.getRoot()));
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
        // remove later
        Character[] playerList = new Character[]{new Character("Player 1", Color.BLUE), new Character("Player 2", Color.BLUE)};
        this.game = new Game(playerList);
        this.theView = new MainView(theModel);
    }

    public void buyProperty() {
        Character curPlayer = game.getCurPlayer();
        int curPosition = curPlayer.getPosition();
        Space curSpace = Board.getBoard().get(curPosition);

        if (curSpace instanceof Buyable) {
            Buyable space = (Buyable) curSpace;
            space.buyProperty(curPlayer);
        } else {
            System.err.println("YOUR CAN'T BUY THIS SPACE");
        }
    }

    public void drawChanceCard() {
        Card card = game.getBoard().getChanceDeck().draw();
        Character curPlayer = game.getCurPlayer();
        card.preformAction(curPlayer, game.getPlayerList());
    }

    public void drawCommunityChestCard() {
        Card card = game.getBoard().getCommunityChestDeck().draw();
        Character curPlayer = game.getCurPlayer();
        card.preformAction(curPlayer, game.getPlayerList());
    }

    public void move(int diceRoll) {
        Character curPlayer = game.getCurPlayer();
        curPlayer.move(diceRoll);
    }
}
