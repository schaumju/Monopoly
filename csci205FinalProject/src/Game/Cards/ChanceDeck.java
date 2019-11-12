package Game.Cards;

public class ChanceDeck extends Deck {
    /**
     * Initializes the deck with Cards
     */
    @Override
    protected void init() {
        shuffle();
    }
}
