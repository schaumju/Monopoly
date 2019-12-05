package Model;

import Controller.BoardController;
import Game.Cards.Card;
import Game.Character;
import Game.Game;
import Game.Spaces.*;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.io.Serializable;

public class MonopolyModel implements Serializable {
    /**
     * List of player in the game
     */
    private Character[] playerList;
    /**
     * The game object
     */
    private Game game;

    /**
     * Log of the events in the game
     */
    private GameLog log;



    /**
     * Constructor
     * @param playerList the list of player's in the game
     */
    public MonopolyModel(Character[] playerList) {
        this.playerList = playerList;
        this.game = new Game(playerList);
        log = new GameLog();
        log.addToLog(game.getCurPlayer().getName() + " is starting their turn");

    }


    /**
     * Getter Method
     */
    public Character[] getPlayerList() {
        return playerList;
    }

    /**
     * Getter Method
     */
    public Game getGame() {
        return game;
    }

    /**
     * Gets the player whose turn it is
     *
     * @return the character object of the player whose turn it is
     */
    public Character getCurPlayer() {
        return game.getCurPlayer();
    }

    /**
     * Determines what space the player landed on and performs the appropriate action
     */
    public void interactSpace(int roll) throws Exception {

        Space space = game.getBoard().getBoard().get(getCurPlayer().getPosition());
        log.addToLog(getCurPlayer().getName() + " landed on " + space.getName());
        System.out.println(getCurPlayer().getName() + " landed on " + space.getName());

        if (space instanceof Property) {
            interactProperty((Property) space);
        } else if (space instanceof Railroads) {
            interactRailroad((Railroads) space);
        } else if (space instanceof Utilities) {
            interactUtilities((Utilities) space, roll);
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
     *
     * @param space the Utility space they are currently on
     */
    private void interactUtilities(Utilities space, int roll) {
        if (space.isOwned()) {
            Character owner = playerList[space.getOwner()];
            log.addToLog("The property is owned by " + owner.getName() + " You owe " + owner.getName() + " " + space.getRent(owner.getNumUtilities(), roll));
            System.out.println("The property is owned by " + owner.getName() + " " + getCurPlayer().getName() + " owes " + owner.getName() + " " + space.getRent(owner.getNumUtilities(), roll));
            getCurPlayer().payPlayer(owner, space.getRent(owner.getNumUtilities(), roll));
        } else if (getCurPlayer().getBalance() < space.getCost()) {
            System.out.println("You do not have enough money to buy this property");
            log.addToLog(getCurPlayer().getName() + " do not have enough money to buy this property");
        }
    }


    /**this.theController = new MainController(theModel, theView);
     * Allows the player to interact with the go to jail space
     */
    private void interactGoToJail() {
        getCurPlayer().goToJail();
    }

    /**
     * Allows the player to interact with the chance space
     *
     * @param space the Chance space they are currently on
     */
    private void interactChance(Chance space) throws Exception {
        Card card = game.getBoard().getChanceDeck().draw();
        int beforePosition = getCurPlayer().getPosition();
        BoardController.cardPopUp("You drew the Chance card\n" + card.getDescription());
        System.out.println("You drew the Chance card " + card.getDescription());
        log.addToLog(getCurPlayer().getName() + "drew the Chance card " + card.getDescription());
        card.preformAction(getCurPlayer(), playerList);
        // If the player moved as a result of the chance card
        if (beforePosition != getCurPlayer().getPosition()) {
            interactSpace(0);
        }

    }

    /**
     * Allows the player to interact with a Community Chest space
     *
     * @param space the Community Chest space they are currently on
     */
    private void interactCommunityChest(CommunityChest space) throws Exception {
        Card card = game.getBoard().getCommunityChestDeck().draw();
        int beforePosition = getCurPlayer().getPosition();
        BoardController.cardPopUp("You drew the Community Chest card\n" + card.getDescription());
        System.out.println("You drew the Community Chest card " + card.getDescription());
        log.addToLog(getCurPlayer().getName() + "drew the Community Chest card " + card.getDescription());
        card.preformAction(getCurPlayer(), playerList);
        // If the player moved as a result of the Community chest card
        if (beforePosition != getCurPlayer().getPosition()) {
            interactSpace(0);
        }
    }

    /**
     * Allows the player to interact with a tax space
     *
     * @param space the Tax space they are currently on
     */
    private void interactTax(Tax space) {
        getCurPlayer().subtractFromBalance(space.getTax());
        log.addToLog(getCurPlayer().getName() + " paid " + space.getTax());
        System.out.println(getCurPlayer().getName() + " paid " + space.getTax());
    }

    /**
     * Allows the player to interact with a Railroad space
     *
     * @param space the Railroad space they are currently on
     */
    private void interactRailroad(Railroads space) {
        if (space.isOwned()) {
            Character owner = playerList[space.getOwner()];
            log.addToLog("The property is owned by " + owner.getName() + " " + getCurPlayer().getName() + " owe " + owner.getName() + " " + space.getRent(owner.getNumRailroads()));
            System.out.println("The property is owned by " + owner.getName() + " You owe " + owner.getName() + " " + space.getRent(owner.getNumRailroads()));
            getCurPlayer().payPlayer(owner, space.getRent(owner.getNumRailroads()));
        } else if (getCurPlayer().getBalance() < space.getCost()) {
            System.out.println("You do not have enough money to buy this property");
            log.addToLog(getCurPlayer().getName() + " do not have enough money to buy this property");
        }

    }

    /**
     * Allows the player to interact with a property space
     *
     * @param space the Property space they are currently on
     */
    private void interactProperty(Property space) {

        if (space.isOwned()) {
            Character owner = playerList[space.getOwner()];
            if (getCurPlayer().isMonopoly(space.getPropertyColor()) && space.getNumHouses() == 0) {
                System.out.println("The property is owned by " + owner.getName() + " You owe " + owner.getName() + " " + 2 * space.getRent());
                log.addToLog("The property is owned by " + owner.getName() + " " + getCurPlayer().getName() + " owe " + owner.getName() + " " + 2 * space.getRent());
                getCurPlayer().payPlayer(owner, 2 * space.getRent());
            } else {
                System.out.println("The property is owned by " + owner.getName() + " You owe " + owner.getName() + " " + 2 * space.getRent());
                log.addToLog("The property is owned by " + owner.getName() + " " + getCurPlayer().getName() + " owe " + owner.getName() + " " + space.getRent());
                getCurPlayer().payPlayer(owner, space.getRent());
            }

        } else if (getCurPlayer().getBalance() < space.getCost()) {
            System.out.println("You do not have enough money to buy this property");
            log.addToLog(getCurPlayer().getName() + " does not have enough money to buy this property");
        }
    }


    /**
     * Allows the user to buy the space
     *
     * @param space the space that is being bought
     */
    public void buyProperty(Space space) {

        if (space instanceof Property) {

            ((Property) space).buyProperty(getCurPlayer());
            getCurPlayer().subtractFromBalance(((Property) space).getCost());

        } else if (space instanceof Railroads) {
            ((Railroads) space).buyProperty(getCurPlayer());
            getCurPlayer().subtractFromBalance(((Railroads) space).getCost());
            getCurPlayer().buyRailroad();

        } else if (space instanceof Utilities) {
            ((Utilities) space).buyProperty(getCurPlayer());
            getCurPlayer().subtractFromBalance(((Utilities) space).getCost());
            getCurPlayer().buyUtility();

        }

        System.out.println("You have bought this property from the bank");
        log.addToLog(getCurPlayer().getName() + " has bought this property from the bank");
        System.out.println("Your new balance is $" + getCurPlayer().getBalance());
        log.addToLog("Their new balance is $" + getCurPlayer().getBalance());
    }

    public void testPropertyOwner(Space space) {
        if (space instanceof Property) {

            Character temp = new Character("temp", Color.GREEN);
            temp.setID(-7);
            ((Property) space).buyProperty(temp);
        }

    }


    /**
     * Determines whether the space is buyable or not
     *
     * @return true if the space is an instance of the Buyable class
     */
    private boolean isBuyable() {
        return game.getBoard().getBoard().get(getCurPlayer().getPosition()) instanceof Buyable;
    }

    /**
     * Determines if the space is unowned
     *
     * @return true if the property is unowned (available to buy)
     */
    public boolean isAvailable() {
        if (isBuyable()) {
            Buyable space = (Buyable) game.getBoard().getBoard().get(getCurPlayer().getPosition());
            return space.getOwner() == -1;
        }
        return false;

    }

    /**
     * Ends the turn by switching the current player to the next player (changes the turn)
     */
    public void endTurn() throws IOException {
        log.addToLog(game.getCurPlayer().getName() + " ended their turn");
        //client.writeToServer();
        //game.getNextPlayer();
    }

    public GameLog getLog() {
        return log;
    }

    public void setLog(GameLog log) {
        this.log = log;
    }

}
