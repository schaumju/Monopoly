package View;

import javafx.scene.control.Button;

public class PropertyView {

    static Button buyPropertyButton;

    public static void addPropertyBuyButton() {
        Button btn = new Button("Buy Property");

        btn.setTranslateX(600);
        btn.setTranslateY(600);


        MainView.getRoot().getChildren().addAll(btn);
    }

    public static Button getBuyPropertyButton() {
        return buyPropertyButton;
    }
}
