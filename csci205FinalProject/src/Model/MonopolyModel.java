package Model;

import Game.Board;
import Game.Cards.Card;
import Game.Character;
import Game.Game;
import Game.Spaces.*;

public class MonopolyModel {
    /**
     * List of player in the game
     */
    private Character[] playerList;
    /**
     * The game object
     */
    private Game game;

    /**
     * Constructor
     * @param playerList the list of player's in the game
     */
    public MonopolyModel(Character[] playerList) {
        this.playerList = playerList;
        this.game = new Game(playerList);

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

        Space space = Board.getBoard().get(getCurPlayer().getPosition());
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
    private void interactUtilities(Utilities space, int roll) throws Exception {
        if (space.isOwned()) {
            Character owner = playerList[space.getOwner()];
            System.out.println("The property is owned by " + owner.getName() + " You owe " + owner.getName() + " " + space.getRent(owner.getNumUtilities(), roll));
            getCurPlayer().payPlayer(owner, space.getRent(owner.getNumUtilities(), roll));
        } else if (getCurPlayer().getBalance() < space.getCost()) {
            System.out.println("You do not have enough money to buy this property");
        } else {
            // Get input for whether they want to buy the property or not
            /**
             * For now auto buy the property
             */
            //buyProperty(space);

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
        System.out.println("You drew the Chance card " + card.getDescription());
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
        System.out.println("You drew the Community Chest card " + card.getDescription());
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
        System.out.println(getCurPlayer().getName() + " paid " + space.getTax());
    }

    /**
     * Allows the player to interact with a Railroad space
     *
     * @param space the Railroad space they are currently on
     */
    private void interactRailroad(Railroads space) throws Exception {
        if (space.isOwned()) {
            Character owner = playerList[space.getOwner()];
            System.out.println("The property is owned by " + owner.getName() + " You owe " + owner.getName() + " " + space.getRent(owner.getNumRailroads()));
            getCurPlayer().payPlayer(owner, space.getRent(owner.getNumRailroads()));
        } else if (getCurPlayer().getBalance() < space.getCost()) {
            System.out.println("You do not have enough money to buy this property");
        } else {

            System.out.println("You have the option to buy this property");
            // Get input for whether they want to buy the property or not
            /**
             * For now auto buy the property
             */
            //buyProperty(space);

        }
    }

    /**
     * Allows the player to interact with a property space
     *
     * @param space the Property space they are currently on
     */
    private void interactProperty(Property space) throws Exception {

        if (space.isOwned()) {
            Character owner = playerList[space.getOwner()];
            if (getCurPlayer().isMonopoly(space.getPropertyColor()) && space.getNumHouses() == 0) {
                System.out.println("The property is owned by " + owner.getName() + " You owe " + owner.getName() + " " + 2 * space.getRent());
                getCurPlayer().payPlayer(owner, 2 * space.getRent());
            } else {
                System.out.println("The property is owned by " + owner.getName() + " You owe " + owner.getName() + " " + 2 * space.getRent());
                getCurPlayer().payPlayer(owner, space.getRent());
            }

        } else if (getCurPlayer().getBalance() < space.getCost()) {
            System.out.println("You do not have enough money to buy this property");
        } else {

            // Get input for whether they want to buy the property or not

        }
    }


    /**
     * Allows the user to buy the space
     *
     * @param space the space that is being bought
     */
    public void buyProperty(Space space) {

        System.out.println("HERE");
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
        System.out.println("Your new balance is $" + getCurPlayer().getBalance());
    }

    /**
     * Determines whether the space is buyable or not
     *
     * @return true if the space is an instance of the Buyable class
     */
    public boolean isBuyable() {
        return Board.getBoard().get(getCurPlayer().getPosition()) instanceof Buyable;
    }

    /**
     * Determines if the space is unowned
     *
     * @return true if the property is unowned (available to buy)
     */
    public boolean isAvailable() {
        if (isBuyable()) {
            Buyable space = (Buyable) Board.getBoard().get(getCurPlayer().getPosition());
            return space.getOwner() == -1;
        }
        return false;

    }

    /**
     * Ends the turn by switching the current player to the next player (changes the turn)
     */
    public void endTurn() {
        game.getNextPlayer();
    }

}
