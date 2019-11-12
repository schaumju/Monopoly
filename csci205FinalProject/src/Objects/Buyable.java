package Objects;

/**
 * Interface for Spaces on the board that can be bought
 * @author Justin
 */
public interface Buyable {

    /**
     * Gets the cost to buy property
     * @return the cost of the Property
     */
    public double getCost();

    /**
     * Gets the ID of the owner
     * @return the ID of the owner
     */
    public int getOwner();


}
