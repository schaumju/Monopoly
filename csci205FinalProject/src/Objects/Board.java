package Objects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Creates an ArrayList with all of the Spaces that represents the monopoly board
 * @author Justin
 */
public class Board {

    private ArrayList<Space> board;
    private ArrayList<Buyable> buyableProperties;

    /**
     * Default Constructor
     */
    public Board() {
        board = new ArrayList<>();
        buyableProperties = new ArrayList<>();
        init();
        sort();
    }

    /**
     * Sorts the board and buyableProperties arrayLists by the position on the board
     */
    private void sort() {
        // This may need to be reversed we will see
        Comparator<Space> positionSort = (s1, s2) -> Integer.compare(s1.position,s2.position);
        Collections.sort(board, positionSort);
        for (Space space:board) {
            System.out.println(space);
            System.out.println();
        }
    }

    /**
     * Initializes all the aspects of the board
     */
    private void init() {
        //initProperties();
        initUtilities();
        initRailroads();
        initTaxes();
        initCornerSpaces();
        initChanceSpaces();
        initCommunityChestSpaces();
    }

    /**
     * Initializes the properties and adds them to the board and buyableProperties ArrayLists
     */
    private void initProperties() {
        //Order I put right now is name, color, position, price, rent, price per house
        /*Property property = new Property();
        board.add(property);
        property = new Property();
        board.add(property);
        property = new Property();
        board.add(property);
        property = new Property();
        board.add(property);
        property = new Property();
        board.add(property);
        property = new Property();
        board.add(property);
        property = new Property();
        board.add(property);
        property = new Property();
        board.add(property);
        property = new Property();
        board.add(property);
        property = new Property();
        board.add(property);
        property = new Property();
        board.add(property);
        property = new Property();
        board.add(property);
        property = new Property();
        board.add(property);
        property = new Property();
        board.add(property);
        property = new Property();
        board.add(property);
        property = new Property();
        board.add(property);
        property = new Property();
        board.add(property);
        property = new Property();
        board.add(property);
        property = new Property();
        board.add(property);
        property = new Property();
        board.add(property);
        property = new Property();
        board.add(property);
        property = new Property();
        board.add(property);*/
    }

    /**
     * Initializes the Community Chest Spaces
     */
    private void initCommunityChestSpaces() {
        CommunityChest space = new CommunityChest(2);
        board.add(space);
        space = new CommunityChest(17);
        board.add(space);
        space = new CommunityChest(33);
        board.add(space);
    }
    /**
     * Initializes the Corner Spaces (Go, Jail, Free Parking, go to jail
     */
    private void initCornerSpaces() {
        GoSpace space = new GoSpace();
        board.add(space);
        JailSpace space2 = new JailSpace();
        board.add(space2);
        FreeParkingSpace space3 = new FreeParkingSpace();
        board.add(space3);

    }
    /**
     * Initializes the Chance Spaces
     */
    private void initChanceSpaces() {
        Chance space = new Chance(7);
        board.add(space);
        space = new Chance(22);
        board.add(space);
        space = new Chance(36);
        board.add(space);
    }
    /**
     * Initializes the Tax Spaces
     */
    private void initTaxes() {
        Tax tax = new Tax("Fraternity Row",4,200);
        board.add(tax);
        tax = new Tax("Hunt Hall",38,75);
        board.add(tax);
    }
    /**
     * Initializes the Railroad Spaces
     */
    private void initRailroads() {
        Railroads space = new Railroads(5, "Bostwick Marketplace");
        buyableProperties.add(space);
        board.add(space);
        space = new Railroads(15, "7th Street Cafe");
        buyableProperties.add(space);
        board.add(space);
        space = new Railroads(25, "Bison Cafe");
        buyableProperties.add(space);
        board.add(space);
        space = new Railroads(35, "The Commons Cafe");
        buyableProperties.add(space);
        board.add(space);
    }
    /**
     * Initializes the Utility Spaces
     */
    private void initUtilities() {
         Utilities space = new Utilities(12, "Science Quad");
         buyableProperties.add(space);
         board.add(space);
         space = new Utilities(28, "Malesardi Quadrangle");
         buyableProperties.add(space);
         board.add(space);
    }

    public static void main(String[] args) {
        Board board = new Board();
    }


}
