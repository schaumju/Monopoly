package Objects;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * The colors of the properties on the board. The colors are all constants so we used ENUMS
 * @author Justin
 */
public enum PropertyColor {

    BROWN(Color.BROWN), LIGHT_BLUE(Color.LIGHTBLUE), PURPLE(Color.PURPLE), ORANGE(Color.ORANGE), RED(Color.RED), YELLOW(Color.YELLOW), GREEN(Color.GREEN),DARK_BLUE(Color.DARKBLUE);

    /**
     * The color
     */
    private Paint color;


    PropertyColor(Paint color) {
        this.color = color;
    }


    /**
     * Gets Color
     *
     * @return
     */
    public Paint getColor() {
        return color;
    }

}
