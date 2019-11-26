package Controller;

import Game.Board;
import Model.MonopolyModel;
import View.MainView;

public class BuyPropertyController {


    private MonopolyModel theModel;
    private MainView theView;

    public BuyPropertyController(MonopolyModel theModel, MainView theView) {
        this.theModel = theModel;
        this.theView = theView;
        handleBuyProperty();
    }

    /**
     * handles when the player wants to buy a property
     * @author justin
     */
    public void handleBuyProperty() {
        theView.getPropertyView().getBuyPropertyButton().setOnMouseClicked(mouseEvent -> {
            theModel.buyProperty(Board.getBoard().get(theModel.getCurPlayer().getPosition()));
            theView.getPropertyView().turnButtonOff();
        });
    }
}
