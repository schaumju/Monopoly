package Game.Cards;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;

/**
 * Representation of a deck
 */
public abstract class Deck implements Serializable {


    /**
     * The deck of cards
     */
    protected Stack<Card> deck;
    /**
     * The discard pile
     */
    protected Card[] discard;
    /**
     * The number of card in a deck
     */
    private static final int numCards=16;

    /**
     * Constructor
     */
    public Deck() {
        deck = new Stack<>();
        discard = new Card[numCards];
        init();
    }

    /**
     * Initializes the deck with Cards
     */
    protected abstract void init();

    /**
     * Draw a card from the deck and then add it to the discard pile
     * @return the card that is drawn
     */
    public Card draw() {
        Card card = deck.pop();
        discard[deck.size()] = card;
        return card;
    }

    /**
     * Shuffles the discard deck and then adds it back to the deck
     */
    protected void shuffle() {
        // Make sure the deck is empty
        while (deck.size() != 0) {
            draw();
        }
        // Shuffle the deck
        Collections.shuffle(Arrays.asList(discard));
        for (Card card:discard) {
            deck.push(card);
        }
    }
}
