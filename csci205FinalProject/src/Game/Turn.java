package Game;

import Game.Spaces.Property;
import Game.Spaces.Space;

/**
 * Runs a single turn for a player
 *
 * @author Justin
 */
public class Turn {

    /**
     * The player whose turn it is currently
     */
    private Character player;

    private boolean isDoublesRoll;
    /**
     * The game board
     */
    private Board board;


    /**
     * Constructor
     *
     * @param player the player whose turn it is
     */
    public Turn(Character player, Board board) {
        this.player = player;
        isDoublesRoll = false;
        this.board = board;
        playTurn();
    }

    /**
     * Method that runs the player's turn
     */
    private void playTurn() {
        //Create a new Dice object
        //Roll the dice
        //move spaces
        //interact with square
    }

    private void interactSpace() {

        Space space = Board.getBoard().get(player.getPosition());

        if (space instanceof Property) {
            interactProperty();
        }

    }

    private void interactProperty() {
    }

}
