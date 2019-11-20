package View;

import javafx.scene.control.Button;

public class PropertyView {

    static Button buyPropertyButton;

    public static void addPropertyBuyButton() {
        buyPropertyButton = new Button("Buy \nProperty");

        buyPropertyButton.setTranslateX(600);
        buyPropertyButton.setTranslateY(550);


        MainView.getRoot().getChildren().addAll(buyPropertyButton);
    }

    public static Button getBuyPropertyButton() {
        return buyPropertyButton;
    }
}
