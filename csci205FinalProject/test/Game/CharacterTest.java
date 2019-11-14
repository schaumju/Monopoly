package Game;

import javafx.scene.paint.Color;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CharacterTest {

    private Character character;

    @BeforeEach
    void setUp() {
        character = new Character("Joe", Color.RED);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void move() {
        assertEquals(0, character.getPosition());
        character.move(10);
        assertEquals(10, character.getPosition());
        character.move(30);
        assertEquals(0, character.getPosition());
    }

    @Test
    void addToBalance() {
        assertEquals(1500.00, character.getBalance());
        character.addToBalance(500);
        assertEquals(2000, character.getBalance());
    }

    @Test
    void subtractFromBalance() {
        assertEquals(1500.00, character.getBalance());
        character.subtractFromBalance(500);
        assertEquals(1000, character.getBalance());
    }

    @Test
    void isBankrupt() {
        assertFalse(character.isBankrupt());
        character.subtractFromBalance(character.getBalance());
        assertTrue(character.isBankrupt());
        character.addToBalance(500);
        assertFalse(character.isBankrupt());

    }

}