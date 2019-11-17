package Game.Cards;

/**
 * Class to represent a deck of CommunityChestCard objects
 */
public class CommunityChestDeck extends Deck {
    /**
     * Initializes the deck with Cards
     */
    @Override
    protected void init() {
        CommunityChestCard card = new CommunityChestCard("It is Sunday night and you still have not started your homework. Move back 1 space", 0, -1, 0, "none", Card.CardType.MOVE);
        deck.add(card);
        card = new CommunityChestCard("You have a huge project due at midnight. Move back 2 spaces", 0, -2, 0, "none", Card.CardType.MOVE);
        deck.add(card);
        card = new CommunityChestCard("Go to Jail. Do not pass Freas Hall. Do not collect $200", -15, 0, 0, "none", Card.CardType.GO_TO_JAIL);
        deck.add(card);
        card = new CommunityChestCard("You got sick and had to go to Student Health. Pay a fee of $25", -25, 0, 0, "none", Card.CardType.BANK_TRANSACTION);
        deck.add(card);
        card = new CommunityChestCard("You gave everyone your study guide for the exam. Collect $50 from each player", -50, 0, 0, "none", Card.CardType.PLAYER_TRANSACTION);
        deck.add(card);
        card = new CommunityChestCard("Go to Jail. Do not pass Freas Hall. Do not collect $200", -15, 0, 0, "none", Card.CardType.GO_TO_JAIL);
        deck.add(card);
        card = new CommunityChestCard("Books this semester are expensive. Pay $100", -100, 0, 11, "none", Card.CardType.BANK_TRANSACTION);
        deck.add(card);
        card = new CommunityChestCard("You got a good financial aid package! Collect $50", 50, 0, 24, "none", Card.CardType.BANK_TRANSACTION);
        deck.add(card);
        card = new CommunityChestCard("You got first selection in the housing lottery. Advance to the Senior Apartments.", 0, 0, 39, "none", Card.CardType.MOVE_TO);
        deck.add(card);
        card = new CommunityChestCard("Your parents are letting you keep a car on campus next year. Collect $200", 200, 0, 1, "none", Card.CardType.BANK_TRANSACTION);
        deck.add(card);
        card = new CommunityChestCard("Your RA found alcohol in your room. Pay $25 per house and $100 per hotel", -50, 0, 24, "none", Card.CardType.STREET_REPAIRS);
        deck.add(card);
        card = new CommunityChestCard("You got an A on your Calc test. Collect $50", 50, 0, 24, "none", Card.CardType.BANK_TRANSACTION);
        deck.add(card);
        card = new CommunityChestCard("Your group did the entire project for you. Advance 2 spaces", -15, 0, 2, "none", Card.CardType.MOVE);
        deck.add(card);
        card = new CommunityChestCard("Your mom sent you a care package. Advance 2 spaces.", 0, 2, 24, "none", Card.CardType.MOVE);
        deck.add(card);
        card = new CommunityChestCard("Facilities found mold in your air conditioning unit. Pay fee of $30", -30, 0, 24, "none", Card.CardType.BANK_TRANSACTION);
        deck.add(card);
        card = new CommunityChestCard("You got the internship you applied for! Collect $200.", 200, 0, 24, "none", Card.CardType.BANK_TRANSACTION);
        deck.add(card);
        shuffle();
    }
}
