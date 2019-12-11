package Game.Spaces;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.io.Serializable;

/**
 * The colors of the properties on the board. The colors are all constants so we used ENUMS
 * @author Justin
 */
public enum PropertyColor implements Serializable {

    BROWN(Color.BROWN, 2), LIGHT_BLUE(Color.LIGHTBLUE, 3), PURPLE(Color.PURPLE, 3), ORANGE(Color.ORANGE, 3), RED(Color.RED, 3), YELLOW(Color.YELLOW, 3), GREEN(Color.GREEN, 3), DARK_BLUE(Color.DARKBLUE, 3);

    /**
     * The color
     */
    private Paint color;
    /**
     * The number of properties of the color in a monopoly
     */
    private int numberOfProperties;


    PropertyColor(Paint color, int numberOfProperties) {
        this.color = color;
        this.numberOfProperties = numberOfProperties;
    }


    /**
     * Gets Color
     *
     * @return the color
     */
    public Paint getColor() {
        return color;
    }

    /**
     * Gets the number of properties in the monopoly
     *
     * @return the number of properties in the monopoly
     */
    public int getNumberOfProperties() {
        return numberOfProperties;
    }
}
