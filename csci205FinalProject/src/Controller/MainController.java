/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Kerri Palphreyman
 * Section: 11am
 * Date: 11/18/19
 * Time: 8:28 PM
 *
 * Project: csci205FinalProject
 * Package: Controller
 * Class: MainController
 *
 * Description:
 *
 * ****************************************
 */
package Controller;

import Model.MonopolyModel;
import View.DiceView;
import View.MainView;

public class MainController {

    private MonopolyModel theModel;
    private MainView theView;

    /**
     * constructor
     * @param theModel
     * @param theView
     * @author kerri
     */
    public MainController(MonopolyModel theModel, MainView theView) {
        this.theModel = theModel;
        this.theView = theView;
        
        //handle roll dice
        DiceController.handleRollDice();
    }



}