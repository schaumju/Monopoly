package Game.Spaces;

import Game.Character;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RailroadsTest {

    private Railroads railroad;
    private Character player;

    @BeforeEach
    void setUp() {
        railroad = new Railroads(5, "Reading Railroad");
        player = new Character("Joe", Color.RED);
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void getRent() {
        assertEquals(0, railroad.getRent(player.getNumRailroads()));
        player.buyRailroad();
        assertEquals(25, railroad.getRent(player.getNumRailroads()));
        player.buyRailroad();
        assertEquals(50, railroad.getRent(player.getNumRailroads()));
        player.buyRailroad();
        assertEquals(100, railroad.getRent(player.getNumRailroads()));
        player.buyRailroad();
        assertEquals(200, railroad.getRent(player.getNumRailroads()));
    }
}