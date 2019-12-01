package Game.Spaces;

import java.io.Serializable;

/**
 * Representation of a Chance space
 *
 * @author Justin
 */
public class Chance extends Space implements Serializable {
    /**
     * Constructor
     * @param position the position of the Chance Space on the board
     */
    public Chance(int position) {
        super(position, "Chance");
    }
}
