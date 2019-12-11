package Networking;

import javafx.scene.paint.Color;

import java.io.Serializable;

/**
 * Converts JavaFX color to an object that can be sent over ObjectStreams
 */
public class SerializableColor implements Serializable {
    /**
     * Red value of the color
     */
    private double red;
    /**
     * Green value of the color
     */
    private double green;
    /**
     * Blue value of the color
     */
    private double blue;
    /**
     * Opacity of the color
     */
    private double alpha;

    /**
     * Constructor
     *
     * @param color the color being converted
     */
    public SerializableColor(Color color) {
        this.red = color.getRed();
        this.green = color.getGreen();
        this.blue = color.getBlue();
        this.alpha = color.getOpacity();
    }

    /**
     * Returns the JavaFx color associated with the Serializable color
     *
     * @return JavaFX color
     */
    public Color getFXColor() {
        return new Color(red, green, blue, alpha);
    }

}
