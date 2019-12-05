package Controller;

import Model.MonopolyModel;
import View.MainView;

/**
 * Controller for buying properties
 */
public class BuyPropertyController {

    /**
     * The game model
     */
    private MonopolyModel theModel;
    /**
     * View class for the graphics of the game
     */
    private MainView theView;
    /**
     * Main controller object
     */
    private MainController theController;

    /**
     * Constructor
     *
     * @param theModel the game model
     * @param theView  graphics for the game
     */
    public BuyPropertyController(MonopolyModel theModel, MainView theView, MainController theController) {
        this.theModel = theModel;
        this.theView = theView;
        this.theController = theController;
        handleBuyProperty();
    }

    /**
     * handles when the player wants to buy a property
     * @author justin
     */
    private void handleBuyProperty() {
        theView.getPropertyView().getBuyPropertyButton().setOnMouseClicked(mouseEvent -> {
            theView.getTheModel().buyProperty(theModel.getGame().getBoard().getBoard().get(theModel.getCurPlayer().getPosition()));
            theView.getPropertyView().turnButtonOff();
            theView.getLogView().updateLog();
        });
        theController.getBoardController().update();
    }

    /**
     * Updates the model
     *
     * @param theModel the new Model
     */
    public void updateModel(MonopolyModel theModel) {
        this.theModel = theModel;
    }
}
