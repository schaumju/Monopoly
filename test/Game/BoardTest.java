package Game;

import Game.Spaces.Space;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class BoardTest {

    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board();

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void correctOrder() {
        boolean correctOrder = true;
        for (int i = 0; i < board.getBoard().size() - 1; i++) {
            Space space1 = board.getBoard().get(i);
            Space space2 = board.getBoard().get(i + 1);
            if (space1.getPosition() > space2.getPosition()) {
                correctOrder = false;
            }
        }
        assertTrue(correctOrder);
    }
}