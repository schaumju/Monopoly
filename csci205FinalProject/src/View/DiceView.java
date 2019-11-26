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

import Model.MonopolyModel;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Button;

public class DiceView {

    private SimpleIntegerProperty target = new SimpleIntegerProperty();
    private Button rollDiceBtn;
    private DiceAnimation dice1;
    private DiceAnimation dice2;
    private MonopolyModel theModel;

    public DiceView(MonopolyModel theModel) {
        this.theModel = theModel;
        addDice();
    }

    /**
     * adds the view of dice to the gridpane
     * @author - kerri
     */
    private void addDice() {

        dice1 = new DiceAnimation();
        dice2 = new DiceAnimation();

        dice1.setTranslateX(400);
        dice1.setTranslateY(600);

        dice2.setTranslateX(460);
        dice2.setTranslateY(600);


        addRollDiceBtn(dice1, dice2);

    }

    /**
     * adds the roll dice button to the view
     * @param dice1
     * @param dice2
     * @author justin
     */
    private void addRollDiceBtn(DiceAnimation dice1, DiceAnimation dice2) {
        rollDiceBtn = new Button("Roll dice");

        rollDiceBtn.setTranslateX(600);
        rollDiceBtn.setTranslateY(600);

        SimpleBooleanProperty bool = new SimpleBooleanProperty();
        bool.bind(target.isEqualTo(dice1.valueProperty.add(dice2.valueProperty)));

        MainView.getRoot().getChildren().addAll(dice1, dice2, rollDiceBtn);
    }

    public Button getRollDiceBtn() {
        return rollDiceBtn;
    }

    public DiceAnimation getDice1() {
        return dice1;
    }

    public DiceAnimation getDice2() {
        return dice2;
    }
}