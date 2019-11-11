/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Kerri Palphreyman
 * Section: 11am
 * Date: 11/8/19
 * Time: 4:05 PM
 *
 * Project: csci205FinalProject
 * Package: View
 * Class: BoardView
 *
 * Description:
 *
 * ****************************************
 */
package View;

import Model.MonopolyModel;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BoardView {

    private MonopolyModel theModel;
    private GridPane root;

    private final int NUM_SPACES = 40;

    /**
     * constructor
     * @param theModel
     * @author kerri
     */
    public BoardView(MonopolyModel theModel) {
        this.theModel = theModel;

        setUpRoot();

        addBoardSpaces();
    }

    /**
     * adds all of the board spaces to the gridpane
     * @author kerri
     */
    private void addBoardSpaces() {
        for (int i = 0; i < NUM_SPACES; i++) {
            Rectangle square = createSquare();
            Rectangle colorRectangle = createColorRectangle();
            if (i < 11) {
                root.addColumn(i);
                root.addRow(i);
                root.add(square, i, 0);
                if (i%10 != 0) {
                    root.add(colorRectangle, i, 0);
                }
                root.add(new Label(String.valueOf(i)),i,0);
            } else if (i < 21) {
                root.add(square, 10, i-10);
                if (i%10 != 0) {
                    root.add(colorRectangle, 10, i-10);
                    colorRectangle.setRotate(90);
                }
                root.add(new Label(String.valueOf(i)),10,i-10);
            } else if (i < 31) {
                root.add(square, 30-i, 10);
                if (i%10 != 0) {
                    root.add(colorRectangle, 30-i, 10);
                    colorRectangle.setRotate(180);
                }
                root.add(new Label(String.valueOf(i)),30-i,10);
            } else {
                root.add(square, 0, 40-i);
                if (i%10 != 0) {
                    root.add(colorRectangle, 0, 40-i);
                    colorRectangle.setRotate(270);
                }
                root.add(new Label(String.valueOf(i)),0,40-i);
            }
        }
    }

    /**
     * creates a new square
     * @return - the new square
     * @author kerri
     */
    private Rectangle createSquare() {
        Rectangle square = new Rectangle();
        square.setStroke(Color.BLACK);
        square.setFill(Color.WHITE);
        square.setWidth(50);
        square.setHeight(50);

        return square;
    }

    private Rectangle createColorRectangle() {
        Rectangle colorRectangle = new Rectangle();
        colorRectangle.setFill(Color.RED);
        colorRectangle.setWidth(50);
        colorRectangle.setHeight(18);

        return colorRectangle;
    }

    /**
     * sets up a gridpane that holds the board
     * @author kerri
     */
    private void setUpRoot() {
        root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setGridLinesVisible(false);
    }

    public GridPane getRoot() {
        return root;
    }
}