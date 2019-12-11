package Game.Cards;

import java.io.Serializable;

/**
 * Class to represent a deck of ChanceCard objects
 *
 * @author Justin
 */
public class ChanceDeck extends Deck implements Serializable {
    /**
     * Initializes the deck with Cards
     */
    @Override
    protected void init() {
        ChanceCard card = new ChanceCard("Advance to Freas Hall (Collect $200)", 0, 0, 0, "none", Card.CardType.MOVE_TO);
        deck.add(card);
        card = new ChanceCard("Go back 3 spaces", 0, -3, 0, "none", Card.CardType.MOVE);
        deck.add(card);
        card = new ChanceCard("You got hungry at 2am in the morning and decided to buy Dominos. Pay $15", -15, 0, 0, "none", Card.CardType.BANK_TRANSACTION);
        deck.add(card);
        card = new ChanceCard("You have won a crossword competition- Collect $100", 100, 0, 0, "none", Card.CardType.BANK_TRANSACTION);
        deck.add(card);
        card = new ChanceCard("Make general repairs on all your property-For each house pay $25-For each hotel $100", 0, 0, 0, "none", Card.CardType.STREET_REPAIRS);
        deck.add(card);
        card = new ChanceCard("Advance to Dana- If you pass Freas Hall, collect $200", 0, 0, 24, "none", Card.CardType.MOVE_TO);
        deck.add(card);
        card = new ChanceCard("Advance to Larison- If you pass Freas Hall, collect $200", 0, 0, 11, "none", Card.CardType.MOVE_TO);
        deck.add(card);
        card = new ChanceCard("You forget to do your project and are bailed out by your classmates. Pay each player $50", 50, 0, 24, "none", Card.CardType.PLAYER_TRANSACTION);
        deck.add(card);
        card = new ChanceCard("You got first selection in the housing lottery. Advance to the Senior Apartments.", 0, 0, 39, "none", Card.CardType.MOVE_TO);
        deck.add(card);
        card = new ChanceCard("Housing ran out of dorms to put you. Advance to Vedder. - If you pass Freas Hall, collect $200", 0, 0, 1, "none", Card.CardType.MOVE_TO);
        deck.add(card);
        card = new ChanceCard("You got caught drinking in your dorm by P-Safe. Pay fee of $50", -50, 0, 24, "none", Card.CardType.BANK_TRANSACTION);
        deck.add(card);
        card = new ChanceCard("You left your car on Moore Ave overnight and got a ticket. Pay fee of $20", -20, 0, 24, "none", Card.CardType.BANK_TRANSACTION);
        deck.add(card);
        card = new ChanceCard("You got caught swiping someone else into the caf. Pay fee of $15", -15, 0, 24, "none", Card.CardType.BANK_TRANSACTION);
        deck.add(card);
        card = new ChanceCard("Your mom sent you a care package. Advance 2 spaces.", 0, 2, 24, "none", Card.CardType.MOVE);
        deck.add(card);
        card = new ChanceCard("Facilities found mold in your air conditioning unit. Pay fee of $30", -30, 0, 24, "none", Card.CardType.BANK_TRANSACTION);
        deck.add(card);
        card = new ChanceCard("You got the internship you applied for! Collect $200.", 200, 0, 24, "none", Card.CardType.BANK_TRANSACTION);
        deck.add(card);

        shuffle();
    }
}
