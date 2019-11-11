/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: YOUR NAME
 * Section: YOUR SECTION
 * Date: 11/11/19
 * Time: 11:40 AM
 *
 * Project: csci205FinalProject
 * Package: Objects
 * Class: cornerSpaceGO
 *
 * Description:
 *
 * ****************************************
 */
package Objects;

public class cornerSpaceGO extends Space
{
    //Money you get for passing GO
    private static final double CASH_MONEY = 200.00;

    //Reward money for passing go
    private double rewardMoney;

    public cornerSpaceGO()
    {
        super(0, "GO");
        this.rewardMoney = CASH_MONEY;
    }

    public void rewardCash(Character thisChar)
    {
        thisChar.addToBalance(this.rewardMoney);
    }

}