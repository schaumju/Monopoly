package Controller;

import View.PropertyView;

public class BuyPropertyController {

    /**
     * handles when the player wants to buy a property
     * @author justin
     */
    protected static void handleBuyProperty() {
        PropertyView.getBuyPropertyButton().setOnMouseClicked(mouseEvent -> {
            System.out.println("Buy Property");
        });
    }
}
