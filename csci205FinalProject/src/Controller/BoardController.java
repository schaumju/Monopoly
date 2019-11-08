/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Kerri Palphreyman
 * Section: 11am
 * Date: 11/8/19
 * Time: 4:14 PM
 *
 * Project: csci205FinalProject
 * Package: Controller
 * Class: BoardControl
 *
 * Description:
 *
 * ****************************************
 */
package Controller;

import Model.MonopolyModel;
import View.BoardView;

public class BoardController {

    private MonopolyModel theModel;
    private BoardView theView;

    /**
     * constructor
     * @param theModel
     * @param theView
     * @author kerri
     */
    public BoardController(MonopolyModel theModel, BoardView theView) {
        this.theModel = theModel;
        this.theView = theView;
    }
}