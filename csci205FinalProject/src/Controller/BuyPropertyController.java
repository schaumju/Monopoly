package Controller;

import View.PropertyView;

public class BuyPropertyController {

    protected static void handleBuyProperty() {
        PropertyView.getBuyPropertyButton().setOnMouseClicked(mouseEvent -> {
            System.out.println("Buy Property");
        });
    }
}
