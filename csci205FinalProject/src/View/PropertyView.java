package View;

import Model.MonopolyModel;
import javafx.scene.control.Button;

public class PropertyView {
    /**
     * Button to buy property
     */
    private Button buyPropertyButton;
    /**
     * Game model
     */
    private MonopolyModel theModel;
    /**
     * The Main View
     */
    private MainView mainView;

    /**
     * Constructor
     *
     * @param theModel the game model
     * @param mainView
     */
    public PropertyView(MonopolyModel theModel, MainView mainView) {
        this.theModel = theModel;
        this.mainView = mainView;
        addPropertyBuyButton();
    }

    /**
     * adds a property button to the view
     * @author - justin
     */
    private void addPropertyBuyButton() {
        buyPropertyButton = new Button("Buy \nProperty");

        buyPropertyButton.setTranslateX(600);
        buyPropertyButton.setTranslateY(550);

        // Set the button to be disabled at first
        turnButtonOff();
        mainView.getRoot().getChildren().addAll(buyPropertyButton);
    }

    public Button getBuyPropertyButton() {
        return buyPropertyButton;
    }

    /**
     * Make the button clickable
     */
    public void turnButtonOn() {
        buyPropertyButton.setDisable(false);

    }

    /**
     * disables the button
     */
    public void turnButtonOff() {
        buyPropertyButton.setDisable(true);
    }

    /**
     * Updates the model
     */
    public void updateModel(MonopolyModel theModel) {
        this.theModel = theModel;
    }
}
