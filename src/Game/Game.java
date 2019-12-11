package Game;

import java.io.Serializable;

/**
 * Representation of a Monopoly Game
 *
 * @author Justin
 */
public class Game implements Serializable {

    /**
     * The maximum amount of turns the users want to play in a game
     */
    private int maxTurns;
    /**
     * A list containing all the players
     */
    private Character[] playerList;
    /**
     * The number of turns that have been played already in the game
     */
    private int numTurns;
    /**
     * The game board
     */
    private Board board;

    /**
     * The index of the player whose turn it currently is
     */
    private int curPlayerTurn;


    /**
     * Constructor
     *
     * @param playerList the list of all the players in the game
     */
    public Game(Character[] playerList) {
        this.playerList = playerList;
        this.numTurns = 0;
        curPlayerTurn = 0;
        board = new Board();
    }



    /**
     * Determines if the game should end
     *
     * @return true if the game is over or false if it is not
     */
    public boolean gameOver() {
        int numPlayersRemaining = 0;
        for (Character player : playerList) {
            if (!player.isBankrupt()) {
                numPlayersRemaining++;
            }
        }
        return numPlayersRemaining == 1;
    }

    /**
     * Determines who won the game
     *
     * @return the Character object of the winning player
     */
    public Character getWinner() {
        for (Character character : playerList) {
            if (character.getBalance() != 0) {
                return character;
            }
        }
        return null;
    }

    /**
     * Getter method for the Board
     *
     * @return a board object
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Determines who the next player should be to go
     *
     * @return the player who plays next
     */
    public Character getNextPlayer() {
        do {
            curPlayerTurn++;
            if (curPlayerTurn == playerList.length) {
                curPlayerTurn = 0;
            }
        } while (!isValidPlayer(curPlayerTurn));
        return playerList[curPlayerTurn];
    }

    /**
     * Gets the list of players
     * @return the player list
     */
    public Character[] getPlayerList() {
        return playerList;
    }

    /**
     * Checks to see if the player is valid. The player is invalid if they are bankrupt
     *
     * @param index the index of the player in the list of players
     * @return true if the player at that index is valid and false otherwise
     */
    private boolean isValidPlayer(int index) {
        return !playerList[index].isBankrupt();
    }

    /**
     * Gets the character object containing the current player
     *
     * @return returns the character object with the player whose turn it is
     */
    public Character getCurPlayer() {
        return playerList[curPlayerTurn];
    }
}
