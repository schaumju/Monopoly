package View;

import Model.MonopolyModel;
import javafx.scene.control.Button;

public class PropertyView {

    static Button buyPropertyButton;
    private MonopolyModel theModel;

    public PropertyView(MonopolyModel theModel) {
        this.theModel = theModel;
        addPropertyBuyButton();
    }

    /**
     * adds a property button to the view
     * @author - justin
     */
    public static void addPropertyBuyButton() {
        buyPropertyButton = new Button("Buy \nProperty");

        buyPropertyButton.setTranslateX(600);
        buyPropertyButton.setTranslateY(550);

        // Set the button to be disabled at first
        buyPropertyButton.setDisable(true);
        MainView.getRoot().getChildren().addAll(buyPropertyButton);
    }

    public static Button getBuyPropertyButton() {
        return buyPropertyButton;
    }
}
