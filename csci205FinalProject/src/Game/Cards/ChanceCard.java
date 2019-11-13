package Game.Cards;

/**
 * Class that represents a Chance Card
 * @author Justin
 */
public class ChanceCard extends Card
{
    /**
     * Constructor
     *
     * @param description the description on the card
     * @param moneyValue  the value the player is gaining or losing
     * @param moveSpaces  the number of spaces they have to move
     * @param moveToSpace the space the player needs to move to for the card
     * @param type        the type of the card (ENUM value)
     */
    public ChanceCard(String description, int moneyValue, int moveSpaces, int moveToSpace,String moveNearest, CardType type) {
        super(description, moneyValue, moveSpaces, moveToSpace, moveNearest, type);
    }
}
