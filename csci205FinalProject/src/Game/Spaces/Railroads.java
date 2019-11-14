package Game.Spaces;

/**
 * Class that represents the 4 railroads on the board
 * @author Justin
 */
public class Railroads extends Space implements Buyable {

    /**
     * The ID of the person who owns the Railroad
     */
    private int ownerID;

    private final int RAILROAD_COST=200;

    /**
     * Constructor
     * @param position of the the railroad
     * @param name the name of the railroad
     */
    public Railroads(int position, String name) {
        super(position, name);
        // -1 means that it is unowned
        ownerID=-1;
    }


    /**
     * Gets the cost to buy property
     *
     * @return the cost of the Property
     */
    @Override
    public double getCost() {
        return RAILROAD_COST;
    }

    /**
     * Gets the ID of the owner
     *
     * @return the ID of the owner
     */
    @Override
    public int getOwner() {
        return ownerID;
    }

    /**
     * Sets the owner id to the input
     *
     * @param id the id of the player buying the property
     */
    @Override
    public void buyProperty(int id) {
        ownerID = id;

    }

    /**
     * Returns the rent
     * @param numOwned the number of railroads the player owns
     * @return the price of the railroad
     */
    public double getRent(int numOwned) {
        double rent=0;
        switch (numOwned) {
            case 1:
                rent=25;
                break;
            case 2:
                rent=50;
                break;
            case 3:
                rent=100;
                break;
            case 4:
                rent=200;
                break;
        }
        return rent;
    }
}
