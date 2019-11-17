package Game;

import Game.Cards.Card;
import Game.Spaces.*;
import javafx.scene.paint.Color;

import java.util.Scanner;

/**
 * Runs a single turn for a player
 *
 * @author Justin
 */
public class Turn {

    private static final int JAIL_FINE = 50;
    /**
     * The player whose turn it is currently
     */
    private Character player;
    /**
     * The number of rolls the user has done this turn
     */
    private int numRolls;
    /**
     * The game board
     */
    private Board board;
    /**
     * The sum of the user's roll
     */
    private int roll;

    private Character[] playerList;


    /**
     * Constructor
     *
     * @param player the player whose turn it is
     */
    public Turn(Character player, Board board, Character[] playerList) {
        this.player = player;
        numRolls = 0;
        this.board = board;
        this.playerList = playerList;
        playTurn();
    }

    public static void main(String[] args) {
        Board board = new Board();
        Character player = new Character("Joe", Color.RED);
        Character[] characterList = new Character[1];
        characterList[0] = player;
        player.setID(0);


        Scanner in = new Scanner(System.in);
        do {
            System.out.println("BEFORE: " + player.getPosition());
            Turn turn = new Turn(player, board, characterList);
            System.out.println("AFTER: " + player.getPosition());
            System.err.println("TURN OVER");
            System.out.println();
        } while (true);

    }

    /**
     * Method that runs the player's turn
     */
    private void playTurn() {
        Dice dice;
        do {
            //Create a new Dice object
            dice = new Dice();
            //Roll the dice
            int roll = dice.rollDice();
            System.out.println(player.getName() + " rolled " + roll + " isDoubles= " + dice.isDoubles());
            numRolls++;
            if (numRolls == 3 && dice.isDoubles()) {
                break;
            }
            //move spaces
            player.move(roll);
            //interact with square
            interactSpace();

        } while (dice.isDoubles());

        if (numRolls == 3) {
            //Move the player to jail because they rolled 3 doubles in a row
            System.out.println(player.getName() + " rolled 3 doubles in a row and got put in jail");

            GoToJailSpace jailSpace = (GoToJailSpace)board.getBoard().get(30);
            player.goToJail();
        }

        System.out.println(player.getName() + " is done rolling this turn");

    }

    /**
     * Determines what space the player landed on and performs the appropriate action
     */
    private void interactSpace() {

        Space space = Board.getBoard().get(player.getPosition());
        System.out.println(player.getName() + " landed on " + space.getName());

        if (space instanceof Property) {
            interactProperty((Property) space);
        } else if (space instanceof Railroads) {
            interactRailroad((Railroads) space);
        } else if (space instanceof Utilities) {
            interactUtilities((Utilities) space);
        } else if (space instanceof GoToJailSpace) {
            interactGoToJail();
        } else if (space instanceof Chance) {
            interactChance((Chance) space);
        } else if (space instanceof CommunityChest) {
            interactCommunityChest((CommunityChest) space);
        } else if (space instanceof Tax) {
            interactTax((Tax) space);
        }

    }

    /**
     * Allows the player to interact with the Utility
     */
    private void interactUtilities(Utilities space) {
        if (space.isOwned()) {
            Character owner = playerList[space.getOwner()];
            System.out.println("The property is owned by " + owner.getName() + " You owe " + owner.getName() + " " + space.getRent(owner.getNumUtilities(), roll));
            player.payPlayer(owner, space.getRent(owner.getNumUtilities(), roll));
        } else if (player.getBalance() < space.getCost()) {
            System.out.println("You do not have enough money to buy this property");
        } else {

            Scanner in = new Scanner(System.in);
            System.out.println("The property is unowned. Would you like to buy it from the bank?");
            if (in.nextLine().equalsIgnoreCase("Yes")) {
                buyProperty(space);


            }
        }
    }

    /**
     * Get out of jail attempt
     */
    public void getOutOfJailAttempt()
    {
        Dice dice = new Dice();
        int numRolls = dice.rollDice();
        //If they successfully get out
        if (dice.isDoubles())
        {
            player.move(numRolls);
            player.leaveJail();
        }
        else
        {
            if(player.getTurnsInJail() == 3)
            {
                player.move(numRolls);

                //TODO
                //Add case for if they're bankrupt

                player.subtractFromBalance(JAIL_FINE);
                player.leaveJail();
            }
            else
            {

                //TODO
                //Ask user if he wants to pay $50 and get out or not and show them the turn in jail they are at

                player.incrementTurnsInJail();
            }
        }
    }

    /**
     * Allows the player to interact with the go to jail space
     */
    private void interactGoToJail()
    {
        player.goToJail();
    }

    /**
     * Allows the player to interact with the chance space
     */
    private void interactChance(Chance space) {
        Card card = board.getChanceDeck().draw();
        System.out.println("You drew the Chance card " + card.getDescription());
        card.preformAction(player, playerList);
        interactSpace();
    }

    /**
     * Allows the player to interact with the community chest space
     */
    private void interactCommunityChest(CommunityChest space) {
        Card card = board.getCommunityChestDeck().draw();
        System.out.println("You drew the Community Chest card " + card.getDescription());
        card.preformAction(player, playerList);
        interactSpace();
    }

    /**
     * Allows the player to interact with the tax space
     */
    private void interactTax(Tax space) {
        player.subtractFromBalance(space.getTax());
    }

    /**
     * Allows the player to interact with the railroad space
     */
    private void interactRailroad(Railroads space) {
        if (space.isOwned()) {
            Character owner = playerList[space.getOwner()];
            System.out.println("The property is owned by " + owner.getName() + " You owe " + owner.getName() + " " + space.getRent(owner.getNumRailroads()));
            player.payPlayer(owner, space.getRent(owner.getNumRailroads()));
        } else if (player.getBalance() < space.getCost()) {
            System.out.println("You do not have enough money to buy this property");
        } else {

            Scanner in = new Scanner(System.in);
            System.out.println("The property is unowned. Would you like to buy it from the bank?");
            if (in.nextLine().equalsIgnoreCase("Yes")) {
                buyProperty(space);


            }
        }
    }

    /**
     * Allows the player to interact with a property
     */
    private void interactProperty(Property space) {

        if (space.isOwned()) {
            Character owner = playerList[space.getOwner()];
            System.out.println("The property is owned by " + owner.getName() + " You owe " + owner.getName() + " " + space.getRent());
            player.payPlayer(owner, space.getRent());
        } else if (player.getBalance() < space.getCost()) {
            System.out.println("You do not have enough money to buy this property");
        } else {

            Scanner in = new Scanner(System.in);
            System.out.println("The property is unowned. Would you like to buy it from the bank?");
            if (in.nextLine().equalsIgnoreCase("Yes")) {
                buyProperty(space);


            }
        }
    }

    /**
     * Allows the user to buy the space
     *
     * @param space the space that is being bought
     */
    private void buyProperty(Space space) {

        if (space instanceof Property) {

            ((Property) space).buyProperty(player.getID());
            player.subtractFromBalance(((Property) space).getCost());

        } else if (space instanceof Railroads) {
            ((Railroads) space).buyProperty(player.getID());
            player.subtractFromBalance(((Railroads) space).getCost());

        } else if (space instanceof Utilities) {
            ((Utilities) space).buyProperty(player.getID());
            player.subtractFromBalance(((Utilities) space).getCost());

        }

        System.out.println("You have bought this property from the bank");
        System.out.println("Your new balance is " + player.getBalance());
    }

}
