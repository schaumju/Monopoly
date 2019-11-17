package Game;

import javafx.scene.paint.Color;


/**
 * Representation of a Monopoly Game
 *
 * @author Justin
 */
public class Game {

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
     * Main method
     *
     * @param args
     */
    public static void main(String[] args) {
        Character[] playerList = new Character[]{new Character("Player1", Color.RED), new Character("Player2", Color.BLUE)};
        Game game = new Game(playerList);
        Character curPlayer = playerList[0];
        do {
            Turn turn = new Turn(curPlayer, game.getBoard(), playerList);
            curPlayer = game.getNextPlayer();

        } while (!game.gameOver());

        System.out.println(game.getWinner().getName() + " won the game");
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
     * Checks to see if the player is valid. The player is invalid if they are bankrupt
     *
     * @param index the index of the player in the list of players
     * @return true if the player at that index is valid and false otherwise
     */
    public boolean isValidPlayer(int index) {
        return !playerList[index].isBankrupt();
    }
}