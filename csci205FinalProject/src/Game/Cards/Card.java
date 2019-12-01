package Game.Cards;

import Game.Character;

import java.io.Serializable;

/**
 * Class that represents a Card (Community Chest or Chance)
 * @author Justin
 */
public class Card implements Serializable {

    /**
     * Description on the card
     */
    private String description;
    /**
     * The money the player is gaining or losing
     */
    private int moneyValue;
    /**
     * The number of spaces the player has to move from their current space
     */
    private int moveSpaces;
    /**
     * The space that the player should be moved to
     */
    private int moveToSpace;
    /**
     * The space that the player has to move to (for CardType.MOVE_NEAREST)
     */
    private String moveNearest;

    /**
     * The type of the card
     */
    private CardType type;

    /**
     * Position of the railroad spaces on the board
     */
    private final int[] railRoadSpaces = new int[]{5,15,25,35};
    /**
     * Position of the utility spaces on the board
     */
    private final int[] utilitySpaces = new int[]{12,28};

    /**
     * Constructor
     * @param description the description on the card
     * @param moneyValue the value the player is gaining or losing
     * @param moveSpaces the number of spaces they have to move
     * @param moveToSpace the space that the player should be moved to
     * @param type the type of the card (ENUM value)
     */
    public Card(String description, int moneyValue, int moveSpaces, int moveToSpace, String moveNearest, CardType type) {
        this.description=description;
        this.moneyValue=moneyValue;
        this.moveSpaces=moveSpaces;
        this.type=type;
        this.moveToSpace=moveToSpace;
        this.moveNearest=moveNearest;

    }

    /**
     * Performs the appropriate action dependent on the card type
     * @param player the player who drew the card
     */
    public void preformAction(Character player, Character[] playerList) {

        if (type == CardType.BANK_TRANSACTION) {
            bankTransaction(player);
        } else if (type ==CardType.MOVE) {
            move(player);
        } else if (type==CardType.MOVE_TO) {
            moveTo(player);
        } else if (type == CardType.PLAYER_TRANSACTION) {
            playerTransaction(player, playerList);
        } else if (type == CardType.STREET_REPAIRS) {
            streetRepairs(player);
        } else if (type == CardType.MOVE_NEAREST) {
            moveNearest(player);
        } else if (type==CardType.GO_TO_JAIL) {
            moveToJail(player);
        } else {
            System.out.println("We have an issue");
        }
    }

    /**
     * Moves the player to jail
     * @param player the player that has to move
     */
    private void moveToJail(Character player) {
        player.goToJail();
    }

    /**
     * Moves the player to the nearest space of a certain type
     * @param player the player to be moved
     */
    private void moveNearest(Character player) {
        int curPosition = player.getPosition();
        if (moveNearest.equalsIgnoreCase("Railroad")) {
            player.move(findClosest(curPosition,railRoadSpaces));

        } else {
            findClosest(curPosition,utilitySpaces);
            player.move(findClosest(curPosition,utilitySpaces));
        }

    }

    /**
     * Finds the closest space of a certain type
     * @param curPosition the position of the player currently
     * @param spaceArray the array containing the spaces of the specified type
     * @return
     */
    private int findClosest(int curPosition, int[] spaceArray) {
        int closestPosition = Math.abs(curPosition-spaceArray[0])%40;
        for (int i = 1; i <spaceArray.length ; i++) {
            if (closestPosition >Math.abs(curPosition-spaceArray[i])%40) {
                closestPosition=Math.abs(curPosition-spaceArray[i])%40;
            }
        }
        return closestPosition;
    }

    /**
     * Moves the player to the specified space
     * @param player the player who drew the card
     */
    private void moveTo(Character player) {
        if (moveToSpace > player.getPosition()) {
            player.move(moveToSpace - player.getPosition());
        } else {
            player.move(40-Math.abs(moveToSpace - player.getPosition()));
        }
    }

    /**
     * Subtracts from the player's balance the correct amount depending on the number of houses and hotels they have
     * @param player The player who drew the card
     */
    private void streetRepairs(Character player) {
        player.subtractFromBalance(25 * player.getNumHouses());
    }

    /**
     * Performs a transaction between the player and the bank
     * @param player the player who drew the card
     */
    private void bankTransaction(Character player) {

        if (this.moneyValue > 0) {
            player.subtractFromBalance(this.moneyValue);

        } else {
            player.addToBalance(this.moneyValue);
        }
    }

    /**
     * Moves the player the specified number of spaces
     * @param player the player who drew the card
     */
    private void move(Character player) {
        player.move(moveSpaces);
    }

    /**
     * Perform transactions where all players are involved (i.e: Pay each player $50, receive $50 from each player)
     * @param player the player who drew the card
     * @param playerList the list of the all the players in the game
     */
    private void playerTransaction(Character player, Character[] playerList) {
        if (moneyValue > 0) {
            for (Character character : playerList) {
                if (player != character) {
                    character.addToBalance(moneyValue);
                    player.subtractFromBalance(moneyValue);
                }
            }
        } else {
            for (Character character : playerList) {
                if (player != character) {
                    character.subtractFromBalance(moneyValue);
                    player.addToBalance(moneyValue);
                }
            }
        }

    }

    /**
     * Getter for the description on the card
     *
     * @return the String description on the card
     */
    public String getDescription() {
        return description;
    }

    /**
     * The types of community chest and chance cards that are available
     * @author Justin
     */
    public enum CardType {

        BANK_TRANSACTION, MOVE_TO, MOVE_NEAREST, STREET_REPAIRS, PLAYER_TRANSACTION, MOVE, GO_TO_JAIL
    }

}
