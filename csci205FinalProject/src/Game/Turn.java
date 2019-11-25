package Game;

import Game.Cards.Card;
import Game.Spaces.*;

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
    /**
     * The list of the players in the game
     */
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
        if (player.isInJail()) {
            getOutOfJailAttempt();

        } else {
            playTurn();
        }

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
     * Allows the user to interact with a Utility space
     * @param space the Utility space they are currently on
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
        System.out.println(player.getName() + " rolled a " + numRolls);
        //If they successfully get out
        if (dice.isDoubles())
        {
            System.out.println("Its doubles! You're out!");
            player.move(numRolls);
            player.leaveJail();
            interactSpace();

        }
        else
        {
            if(player.getTurnsInJail() == 2)
            {
                player.move(numRolls);
                System.out.println("You've served your sentence! Pay $50 and leave!");
                player.subtractFromBalance(JAIL_FINE);

                //CHECK BANKRUPT

                player.leaveJail();
                interactSpace();
            }
            else
            {
                System.out.println("Its not a double! Stay in jail");
                //TODO
                //Ask user if he wants to pay $50 and get out or not and show them the turn in jail they are at

                player.incrementTurnsInJail();
                System.out.println("Turns in jail: " + player.getTurnsInJail());
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
     * @param space the Chance space they are currently on
     */
    private void interactChance(Chance space) {
        Card card = board.getChanceDeck().draw();
        int beforePosition = player.getPosition();
        System.out.println("You drew the Chance card " + card.getDescription());
        card.preformAction(player, playerList);
        // If the player moved as a result of the chance card
        if (beforePosition != player.getPosition()) {
            interactSpace();
        }

    }

    /**
     * Allows the player to interact with a Community Chest space
     * @param space the Community Chest space they are currently on
     */
    private void interactCommunityChest(CommunityChest space) {
        Card card = board.getCommunityChestDeck().draw();
        int beforePosition = player.getPosition();
        System.out.println("You drew the Community Chest card " + card.getDescription());
        card.preformAction(player, playerList);
        // If the player moved as a result of the Community chest card
        if (beforePosition != player.getPosition()) {
            interactSpace();
        }
    }

    /**
     * Allows the player to interact with a tax space
     * @param space the Tax space they are currently on
     */
    private void interactTax(Tax space) {
        player.subtractFromBalance(space.getTax());
        System.out.println(player.getName() + " paid " + space.getTax());
    }

    /**
     * Allows the player to interact with a Railroad space
     * @param space the Railroad space they are currently on
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
     * Allows the player to interact with a property space
     * @param space the Property space they are currently on
     */
    private void interactProperty(Property space) {

        if (space.isOwned()) {
            Character owner = playerList[space.getOwner()];
            if (player.isMonopoly(space.getPropertyColor()) && space.getNumHouses() == 0) {
                System.out.println("The property is owned by " + owner.getName() + " You owe " + owner.getName() + " " + 2 * space.getRent());
                player.payPlayer(owner, 2 * space.getRent());
            } else {
                System.out.println("The property is owned by " + owner.getName() + " You owe " + owner.getName() + " " + 2 * space.getRent());
                player.payPlayer(owner, space.getRent());
            }

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

            ((Property) space).buyProperty(player);
            player.subtractFromBalance(((Property) space).getCost());

        } else if (space instanceof Railroads) {
            ((Railroads) space).buyProperty(player);
            player.subtractFromBalance(((Railroads) space).getCost());
            player.buyRailroad();

        } else if (space instanceof Utilities) {
            ((Utilities) space).buyProperty(player);
            player.subtractFromBalance(((Utilities) space).getCost());
            player.buyUtility();

        }

        System.out.println("You have bought this property from the bank");
        System.out.println("Your new balance is $" + player.getBalance());
    }

    /**
     * Called only if a player is bankrupted. Returns all his properties to unowned
     */
    private void bankrupt() {
        int id = player.getID();

        for (Space space : board.getBuyableProperties()) {
            Buyable property = (Buyable) space;
            // If it is the player who went bankrupt's property
            if (property.getOwner() == id) {
                // set the property to unowned
                property.buyProperty(-1);
                // How to handle monopolies here?
            }
        }

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
            if (player.isBankrupt()) {
                bankrupt();
                break;
            }
            if (player.isInJail())
            {
                break;
            }

        } while (dice.isDoubles());

        if (numRolls == 3 && dice.isDoubles()) {
            //Move the player to jail because they rolled 3 doubles in a row
            System.out.println(player.getName() + " rolled 3 doubles in a row and got put in jail");

            GoToJailSpace jailSpace = (GoToJailSpace) Board.getBoard().get(30);
            player.goToJail();
        }

        System.out.println(player.getName() + " is done rolling this turn");

    }


}
