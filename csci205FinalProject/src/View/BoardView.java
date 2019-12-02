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

import Game.Spaces.Space;
import Model.MonopolyModel;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;

public class BoardView {

    /**
     * Number of spaces on the board
     */
    public static final int NUM_SPACES = 40;
    /**
     * Model for the game
     */
    private MonopolyModel theModel;
    /**
     * ArrayList of all the labels
     */
    private ArrayList<Label> listOfLabels = new ArrayList<>();
    /**
     * ArrayList of all the spaces
     */
    private ArrayList<Space> listOfSpaces = new ArrayList<>();
    /**
     * The Main View
     */
    private MainView mainView;

    /**
     * Constructor
     *
     * @param theModel the model for the game
     * @param mainView
     */
    public BoardView(MonopolyModel theModel, MainView mainView) {
        this.theModel = theModel;
        this.mainView = mainView;
        addBoardSpaces();
    }

    /**
     * adds all of the board spaces to the gridpane
     * @author kerri
     */
    private void addBoardSpaces() {
        for (int i = 0; i < NUM_SPACES; i++) {
            Rectangle square = createSquare();
            Rectangle colorRectangle = createColorRectangle(i);
            String spaceNum = String.valueOf(i);

            ArrayList theBoard = theModel.getGame().getBoard().getBoard();
            Space curSpace = (Space) theBoard.get(i);
            Label spaceName = new Label(curSpace.getName());

            formatSpaceName(curSpace, spaceName);


            if (i < 11) {
                addRowCol(i);
                handleRow(square, colorRectangle, i, 0, 0, spaceNum);
                mainView.getRoot().add(spaceName, i, 0);
            } else if (i < 21) {

                handleColumn(square, colorRectangle, 10, i-10, 90, spaceNum);
                mainView.getRoot().add(spaceName, 10, i - 10);
            } else if (i < 31) {
                handleRow(square, colorRectangle, 30 - i, 10, 180, spaceNum);
                mainView.getRoot().add(spaceName, 30 - i, 10);
            } else {
                handleColumn(square, colorRectangle, 0, 40 - i, 270, spaceNum);
                mainView.getRoot().add(spaceName, 0, 40 - i);
            }

            listOfLabels.add(spaceName);
            listOfSpaces.add(curSpace);

        }
        addTurnHistoryColumn();
    }

    /**
     * adds the column on the right side that keeps track and displays text of what each player is doing so that
     * all of the players stay updated with what the others have done
     * @author kerri
     */
    private void addTurnHistoryColumn() {
        //MainView.getRoot().addColumn(MainView.getRoot().getColumnCount(),new Label("   what has happened in the game   "));
    }

    /**
     * formats the space name so it fits in the square
     * @param curSpace - the current space we are on
     * @param spaceName - the name of the space
     * @author - kerri
     */
    private void formatSpaceName(Space curSpace, Label spaceName) {
        if (curSpace.getName().contains(" ")) {
            spaceName.setText(spaceName.getText().replace(" ", "\n"));
        }

        spaceName.setAlignment(Pos.CENTER);
        spaceName.setMaxSize(70, 70);
        Font font = Font.font("TimesRoman", FontWeight.EXTRA_BOLD, 12);
        spaceName.setFont(font);
        spaceName.setTextAlignment(TextAlignment.CENTER);
    }

    /**
     * adds all the rows and columns in the gridpane
     * @param i - the number row/column the for loop is on
     * @author kerri
     */
    private void addRowCol(int i) {
        mainView.getRoot().addColumn(i);
        mainView.getRoot().addRow(i);
    }

    /**
     * handles creating a row on the board
     * @param square - a square to add into the gridpane
     * @param colorRectangle - the colored rectangle to put on the square
     * @param colIndex - the column the square will be put at
     * @param rowIndex - the row the square will be put at
     * @param angleToRotate - how much the colored rectangle will be rotated
     * @param spaceNum - where the space is on the board
     * @author kerri
     */
    private void handleRow(Rectangle square, Rectangle colorRectangle, int colIndex, int rowIndex, int angleToRotate, String spaceNum) {
        mainView.getRoot().add(square, colIndex, rowIndex);
        if (Integer.parseInt(spaceNum)%10 != 0) {
            mainView.getRoot().add(colorRectangle, colIndex, rowIndex);
            colorRectangle.setRotate(angleToRotate);
        }
    }

    /**
     * handles creating a column on the board
     * @param square - a square to add into the gridpane
     * @param colorRectangle - the colored rectangle to put on the square
     * @param colIndex - the column the square will be put at
     * @param rowIndex - the row the square will be put at
     * @param angleToRotate - how much the colored rectangle will be rotated
     * @param spaceNum - where the space is on the board
     * @author kerri
     */
    private void handleColumn(Rectangle square, Rectangle colorRectangle, int colIndex, int rowIndex, int angleToRotate, String spaceNum) {

        mainView.getRoot().add(square, colIndex, rowIndex);
        if (Integer.parseInt(spaceNum)%10 != 0) {
            mainView.getRoot().add(colorRectangle, colIndex, rowIndex);
            colorRectangle.setRotate(angleToRotate);
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
        square.setWidth(70);
        square.setHeight(70);

        return square;
    }

    /**
     * creates a colored rectangle to put on the board
     * @return - the rectangle
     * @author kerri
     */
    private Rectangle createColorRectangle(int spaceNum) {
        Rectangle colorRectangle = new Rectangle();
        setColor(colorRectangle, spaceNum);
        colorRectangle.setWidth(70);
        colorRectangle.setHeight(30);

        return colorRectangle;
    }

    /**
     * sets the color of the rectangle depending on the space it is on
     * @param colorRectangle - the rectangle that will be set a color
     * @param spaceNum - the position it is on the board
     * @author kerri
     */
    private void setColor(Rectangle colorRectangle, int spaceNum) {
        if (spaceNum == 1 || spaceNum == 3) { colorRectangle.setFill(Color.BROWN); }
        else if (spaceNum == 6 || spaceNum == 8 || spaceNum == 9) { colorRectangle.setFill(Color.LIGHTBLUE); }
        else if (spaceNum == 11 || spaceNum == 13 || spaceNum == 14) { colorRectangle.setFill(Color.PINK); }
        else if (spaceNum == 16 || spaceNum == 18 || spaceNum == 19) { colorRectangle.setFill(Color.ORANGE); }
        else if (spaceNum == 21 || spaceNum == 23 || spaceNum == 24) { colorRectangle.setFill(Color.RED); }
        else if (spaceNum == 26 || spaceNum == 27 || spaceNum == 29) { colorRectangle.setFill(Color.YELLOW); }
        else if (spaceNum == 31 || spaceNum == 32 || spaceNum == 34) { colorRectangle.setFill(Color.GREEN); }
        else if (spaceNum == 39 || spaceNum == 37) { colorRectangle.setFill(Color.BLUE); }
        else  { colorRectangle.setFill(Color.WHITE); }
    }

    //Getters

    public ArrayList<Label> getListOfLabels() {
        return listOfLabels;
    }

    public ArrayList<Space> getListOfSpaces() {
        return listOfSpaces;
    }
}