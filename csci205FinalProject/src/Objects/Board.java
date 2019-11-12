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
        initProperties();
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

        Property property = new Property(1,"Vedder",PropertyColor.BROWN, 60, new double[]{2,10,30,90,160,250},50);
        board.add(property);
        property = new Property(3,"The Mods",PropertyColor.BROWN, 60, new double[]{4,20,60,180,3250,450},50);
        board.add(property);
        property = new Property(6,"Smith",PropertyColor.LIGHT_BLUE, 100, new double[]{6,30,90,270,400,550},50);
        board.add(property);
        property = new Property(8,"Swartz",PropertyColor.LIGHT_BLUE, 100, new double[]{6,30,90,270,400,550},50);
        board.add(property);
        property = new Property(9,"McDonnell Hall",PropertyColor.LIGHT_BLUE, 120, new double[]{8,40,100,300,450,600},50);
        board.add(property);
        property = new Property(11,"Larison",PropertyColor.PURPLE, 140, new double[]{10,50,150,450,625,750},100);
        board.add(property);
        property = new Property(13,"Harris",PropertyColor.PURPLE, 140, new double[]{10,50,150,450,625,750},100);
        board.add(property);
        property = new Property(14,"Affinity Housing",PropertyColor.PURPLE, 160, new double[]{12,60,180,500,700,900},100);
        board.add(property);
        property = new Property(16," Vaughan Lit",PropertyColor.ORANGE, 180, new double[]{14,70,200,550,750,950},100);
        board.add(property);
        property = new Property(18,"Coleman",PropertyColor.ORANGE, 180, new double[]{14,70,200,550,750,950},100);
        board.add(property);
        property = new Property(19,"Bertrand Library",PropertyColor.ORANGE, 200, new double[]{16,80,220,600,800,1000},100);
        board.add(property);
        property = new Property(21,"Olin",PropertyColor.RED, 220, new double[]{18,90,250,700,875,1050},150);
        board.add(property);
        property = new Property(23,"Rooke",PropertyColor.RED, 220, new double[]{18,90,250,700,875,1050},150);
        board.add(property);
        property = new Property(24,"Dana",PropertyColor.RED, 240, new double[]{20,100,300,750,925,1100},150);
        board.add(property);
        property = new Property(26,"Kress",PropertyColor.YELLOW, 260, new double[]{22,110,330,800,975,1150},150);
        board.add(property);
        property = new Property(27,"Trax",PropertyColor.YELLOW, 260, new double[]{22,110,330,800,975,1150},150);
        board.add(property);
        property = new Property(29,"Roberts",PropertyColor.YELLOW, 280, new double[]{24,120,360,850,1025,1200},150);
        board.add(property);
        property = new Property(31,"Off-campus housing",PropertyColor.GREEN, 300, new double[]{26,130,390,900,1100,1275},200);
        board.add(property);
        property = new Property(32,"Gateways",PropertyColor.GREEN, 300, new double[]{26,130,390,900,1100,1275},200);
        board.add(property);
        property = new Property(34,"Senior Apartments",PropertyColor.GREEN, 320, new double[]{26,130,390,900,1100,1275},200);
        board.add(property);
        property = new Property(37,"Academic West",PropertyColor.DARK_BLUE, 350, new double[]{35,175,500,1100,1300,1500},200);
        board.add(property);
        property = new Property(39,"Academic East",PropertyColor.DARK_BLUE, 400, new double[]{50,200,600,1400,1700,2000},200);
        board.add(property);
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
