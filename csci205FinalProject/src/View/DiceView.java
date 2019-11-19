/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Kerri Palphreyman
 * Section: 11am
 * Date: 11/18/19
 * Time: 11:35 AM
 *
 * Project: csci205FinalProject
 * Package: View
 * Class: DiceView
 *
 * Description:
 *
 * ****************************************
 */
package View;

import Game.Dice;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Button;

public class DiceView {

    private SimpleIntegerProperty target = new SimpleIntegerProperty();

    /**
     * adds the view of dice to the gridpane
     * @author - kerri
     */
    protected void addDice() {
        /*int die1 = 5; //Dice.getDie1();
        int die2 = 3; //Dice.getDie2();

        Label die1Label = new Label("  " + String.valueOf(die1));
        die1Label.setAlignment(Pos.CENTER);

        Label die2Label = new Label("  " + String.valueOf(die2));
        die2Label.setAlignment(Pos.CENTER);

        Rectangle rect1 = new Rectangle(20,20);
        rect1.setFill(Color.WHITE);
        rect1.setStroke(Color.BLACK);
        rect1.setStrokeWidth(2);

        Rectangle rect2 = new Rectangle(20,20);
        rect2.setFill(Color.WHITE);
        rect2.setStroke(Color.BLACK);
        rect2.setStrokeWidth(2);

        MainView.getRoot().add(rect1, MainView.getRoot().getColumnCount()/2, MainView.getRoot().getRowCount()/2);
        MainView.getRoot().add(die1Label, MainView.getRoot().getColumnCount()/2, MainView.getRoot().getRowCount()/2);

        MainView.getRoot().add(rect2, MainView.getRoot().getColumnCount()/2 + 1, MainView.getRoot().getRowCount()/2);
        MainView.getRoot().add(die2Label, MainView.getRoot().getColumnCount()/2 + 1, MainView.getRoot().getRowCount()/2);*/

        DiceAnimation dice1 = new DiceAnimation();
        DiceAnimation dice2 = new DiceAnimation();

        dice1.setTranslateX(400);
        dice1.setTranslateY(600);

        dice2.setTranslateX(460);
        dice2.setTranslateY(600);


        Button btn = new Button("Roll dice");
        btn.setOnAction(event -> {
            Dice dice = new Dice();
            dice.rollDice();
            dice1.roll(Dice.getDie1());
            dice2.roll(Dice.getDie2());
        });
        btn.setTranslateX(600);
        btn.setTranslateY(600);

        SimpleBooleanProperty bool = new SimpleBooleanProperty();
        bool.bind(target.isEqualTo(dice1.valueProperty.add(dice2.valueProperty)));

        MainView.getRoot().getChildren().addAll(dice1, dice2, btn);

    }

}