package Game.Cards;

import Game.Character;

/**
 * Class that represents a Card (Community Chest or Chance)
 * @author Justin
 */
public class Card {

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
     * The type of the card
     */
    protected CardType type;

    /**
     * Constructor
     * @param description the description on the card
     * @param moneyValue the value the player is gaining or losing
     * @param moveSpaces the number of spaces they have to move
     * @param moveToSpace the space that the player should be moved to
     * @param type the type of the card (ENUM value)
     */
    public Card(String description, int moneyValue, int moveSpaces, int moveToSpace, CardType type) {
        this.description=description;
        this.moneyValue=moneyValue;
        this.moveSpaces=moveSpaces;
        this.type=type;
        this.moveToSpace=moveToSpace;

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
        } else {
            streetRepairs(player);
        }
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
        player.addToBalance(25*player.getNumHouses());
    }

    /**
     * Performs a transaction between the player and the bank
     * @param player the player who drew the card
     */
    private void bankTransaction(Character player) {
        player.addToBalance(this.moneyValue);
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
        for (Character character:playerList) {
            if (player != character) {
                character.addToBalance(moneyValue);
                player.addToBalance(-moneyValue);
            }
        }
    }

    public String getDescription() {
        return description;
    }

    public int getMoneyValue() {
        return moneyValue;
    }

    public int getMoveSpaces() {
        return moveSpaces;
    }

    public CardType getType() {
        return type;
    }

    /**
     * The types of community chest and chance cards that are available
     * @author Justin
     */
    public enum CardType {

        BANK_TRANSACTION, MOVE_TO, MOVE_NEAREST, STREET_REPAIRS, PLAYER_TRANSACTION, MOVE
    }

}
