package Game.Spaces;

import java.io.Serializable;

/**
 * Representation of a Community Chest space
 */
public class CommunityChest extends Space implements Serializable {
    /**
     * Constructor
     *
     * @param position the position of the space
     */
    public CommunityChest(int position) {
        super(position, "Community Chest");
    }
}
