package MVC.Controller;

import MVC.Model.MonopolyModel;
import MVC.View.MainView;

/**
 * Controller for buying properties
 */
public class BuyPropertyController {

    /**
     * The game model
     */
    private MonopolyModel theModel;
    /**
     * The game view
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
            theController.getEndTurnController().getClient().getTheModel().buyProperty();
            theView.getPropertyView().turnButtonOff();
            theView.getLogView().updateLog();
        });
        theController.getBoardController().update();
    }

    /**
     * Updates the model
     *
     * @param theModel the new model
     */
    public void updateModel(MonopolyModel theModel) {
        this.theModel = theModel;
    }
}
