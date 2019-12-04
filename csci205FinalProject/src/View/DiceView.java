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
    /**
     * Button to roll the dice
     */
    private Button rollDiceBtn;
    /**
     * Animation for the first die
     */
    private DiceAnimation dice1;
    /**
     * Animation for the second die
     */
    private DiceAnimation dice2;
    /**
     * The game model
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
     */
    public DiceView(MonopolyModel theModel, MainView mainView) {
        this.theModel = theModel;
        this.mainView = mainView;
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
     * @param dice1 first dice roll
     * @param dice2 second dice roll
     * @author justin
     */
    private void addRollDiceBtn(DiceAnimation dice1, DiceAnimation dice2) {
        rollDiceBtn = new Button("Roll dice");

        rollDiceBtn.setTranslateX(600);
        rollDiceBtn.setTranslateY(550);

        SimpleBooleanProperty bool = new SimpleBooleanProperty();
        bool.bind(target.isEqualTo(dice1.valueProperty.add(dice2.valueProperty)));

        mainView.getRoot().getChildren().addAll(dice1, dice2, rollDiceBtn);
    }

    // Getter methods
    public Button getRollDiceBtn() {
        return rollDiceBtn;
    }

    public DiceAnimation getDice1() {
        return dice1;
    }

    public DiceAnimation getDice2() {
        return dice2;
    }

    /**
     * Updates the model
     */
    public void updateModel(MonopolyModel theModel) {
        this.theModel = theModel;
    }
}