package View;

import Model.MonopolyModel;
import javafx.scene.control.Button;

public class PropertyView {

    private Button buyPropertyButton;
    private MonopolyModel theModel;

    public PropertyView(MonopolyModel theModel) {
        this.theModel = theModel;
        addPropertyBuyButton();
    }

    /**
     * adds a property button to the view
     * @author - justin
     */
    public void addPropertyBuyButton() {
        buyPropertyButton = new Button("Buy \nProperty");

        buyPropertyButton.setTranslateX(600);
        buyPropertyButton.setTranslateY(550);

        // Set the button to be disabled at first
        turnButtonOff();
        MainView.getRoot().getChildren().addAll(buyPropertyButton);
    }

    public Button getBuyPropertyButton() {
        return buyPropertyButton;
    }

    public void turnButtonOn() {
        buyPropertyButton.setDisable(false);

    }

    public void turnButtonOff() {
        buyPropertyButton.setDisable(true);
    }
}
