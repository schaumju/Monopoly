package Game.Cards;

import Game.Character;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CardTest {

    private Card card1;
    private Card card2;
    private Card card3;
    private Card card4;
    private Character character1;
    private Character character2;
    private Character character3;
    private Character character4;
    private Character[] playerList;

    @BeforeEach
    void setUp() {
        card1 = new Card("Go back 3 spaces", 0, -3, 0, "None", Card.CardType.MOVE);
        card2 = new Card("You got hungry at 2am in the morning and decided to buy dominos. Pay $15", -15, 0, 0, "None", Card.CardType.BANK_TRANSACTION);
        card3 = new Card("Advance to Freas Hall (Collect $200)", 0, 0, 0, "None", Card.CardType.MOVE_TO);
        card4 = new Card("You forget to do your project and are bailed out by your classmates. Pay each player $50", 50, 0, 0, "None", Card.CardType.PLAYER_TRANSACTION);
        character1 = new Character("Joe", Color.RED);
        character2 = new Character("Mike", Color.YELLOW);
        character3 = new Character("Brad", Color.GREEN);
        character4 = new Character("Jack", Color.BLUE);
        playerList = new Character[4];
        playerList[0] = character1;
        playerList[1] = character2;
        playerList[2] = character3;
        playerList[3] = character4;


    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void preformAction() {
        double player1Bal = character1.getBalance();
        character1.setPosition(7);
        card1.preformAction(character1, playerList);
        assertEquals(player1Bal, character1.getBalance());
        assertEquals(4, character1.getPosition());

        double player2Bal = character2.getBalance();
        character2.setPosition(7);
        card2.preformAction(character2, playerList);
        assertEquals(player2Bal - 15, character2.getBalance());
        assertEquals(7, character2.getPosition());
        character2.addToBalance(15);

        double player3Bal = character3.getBalance();
        character3.setPosition(7);
        card3.preformAction(character3, playerList);
        assertEquals(player3Bal, character3.getBalance());
        assertEquals(0, character3.getPosition());


        player1Bal = character1.getBalance();
        player2Bal = character2.getBalance();
        player3Bal = character3.getBalance();
        double player4Bal = character4.getBalance();
        character4.setPosition(7);
        card4.preformAction(character4, playerList);
        assertEquals(player4Bal - 50 * (playerList.length - 1), character4.getBalance());
        assertEquals(player3Bal + 50, character3.getBalance());
        assertEquals(player2Bal + 50, character2.getBalance());
        assertEquals(player1Bal + 50, character1.getBalance());
        assertEquals(7, character4.getPosition());


    }
}