package Game.Spaces;

import Game.Character;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UtilitiesTest {

    private Utilities utility;
    private Character player;

    @BeforeEach
    void setUp() {
        utility = new Utilities(12, "Electric Company");
        player = new Character("Joe", Color.RED);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getRent() {
        int roll = 10;
        assertEquals(0, utility.getRent(player.getNumUtilities(), roll));
        utility.buyProperty(0);
        player.buyUtility();
        assertEquals(40, utility.getRent(player.getNumUtilities(), roll));
        player.buyUtility();
        assertEquals(100, utility.getRent(player.getNumUtilities(), roll));


    }
}