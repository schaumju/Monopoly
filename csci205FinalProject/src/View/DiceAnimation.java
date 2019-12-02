package View;

import javafx.animation.RotateTransition;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * Creates the animation of the dice rolling
 *
 * @author Justin
 * @ref https://github.com/AlmasB/FXTutorials/blob/master/src/com/almasb/tutorial5/Dice.java
 */
public class DiceAnimation extends StackPane {

    /**
     * Property to store the value of the die
     */
    public final SimpleIntegerProperty valueProperty = new SimpleIntegerProperty();

    /**
     * sets up the dice animation
     * @author justin
     */
    public DiceAnimation() {

        Rectangle rect = new Rectangle(50, 50);
        rect.setFill(Color.WHITE);

        Text text = new Text();
        text.setFill(Color.BLACK);
        text.textProperty().bind(valueProperty.asString());

        this.setAlignment(Pos.CENTER);
        getChildren().addAll(rect, text);

    }

    /**
     * does the rolling of the dice
     * @param roll the sum of the dice roll
     * @author - justin
     */
    public void roll(int roll) {
        RotateTransition rt = new RotateTransition(Duration.seconds(.9), this);
        rt.setFromAngle(0);
        rt.setToAngle(360);
        rt.setOnFinished(event -> {
            valueProperty.set((roll));
        });
        rt.play();
    }
}