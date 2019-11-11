/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: YOUR NAME
 * Section: YOUR SECTION
 * Date: 11/10/19
 * Time: 2:54 PM
 *
 * Project: csci205FinalProject
 * Package: Objects
 * Class: Tax
 *
 * Description:
 *
 * ****************************************
 */
package Objects;

import javafx.scene.image.Image;

/**
 * Andrew Lee
 * References: https://i3.cpcache.com/image/112154097_150x150.png
 */

public class Tax extends Space
{
    /**
     * The amount the player owes from landing on this tax
     */
    private int taxAmount;

    public Tax(String name, int position, int taxAmount) {
        super(position, name);
        this.taxAmount=taxAmount;
    }

    /**
     * Gets the amount the player owes
     * @return the amount the player owes
     */
    public int getTax() {
        return taxAmount;
    }
}



//public enum Tax
//{
////    /**
////     * So here I tried to do \% and \$ to escape the % and $ but it didnt like it so im gonna leave it like this since there's no easy way to test it
////     */
////    IncomeTax("Sorority Tax", "Pay 10% \nOR\n $200", 200.00, 10), LuxuryTax("Fraternity Tax", "Pay $75", 75.00);
////
////    //Name of the tax property
////    private String propertyName;
////
////    //Text of the tax property
////    private String taxDescription;
////
////    //Amount owed as a flat number
////    private Double amountOwedFlat;
////
////    //Amount owed as a percent of the player
////    private Integer amountOwedPercent;
////
////    //Image for the tax property square
////    private Image image;
////
////    Tax(String propertyName, String taxDescription, double tax, int percent)
////    {
////        this.propertyName = propertyName;
////        this.taxDescription = taxDescription;
////        this.amountOwedFlat = tax;
////        this.amountOwedPercent = percent;
////    }
////    Tax(String propertyName, String taxDescription, double tax)
////    {
////        this.propertyName = propertyName;
////        this.taxDescription = taxDescription;
////        this.amountOwedFlat = tax;
////        this.image = new Image("/Images/LuxuryRing.png");
////    }
////    public double getAmountOwedLuxuryTax()
////    {
////        return this.amountOwedFlat;
////    }
////    /**
////    public double getAmountOwedIncomeTax(Player p)
////    {
////        //Add a button press here
////        if (playerChoosesPercent)
////        {
////            return p.totalMoney * this.amountOwedPercent;
////        }
////        else
////        {
////            return this.amountOwedFlat;
////        }
////    }
////     */
//}