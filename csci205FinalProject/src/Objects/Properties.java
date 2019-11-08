package Objects;

/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Justin Schaumberger, Andrew Lee, Kerry Palphreyman, Ashlyn Ramos
 * Section: 11 AM
 *
 * Project: CSCI205_FINAL
 * Package: Objects
 * Class: Properties
 *
 * Description: Property objects
 *
 * ****************************************
 */

public class Properties
{
    //Property name
    private String propertyName;

    //Property Color
    private Paint color;

    //Property ownership
    private int owner;

    //Property place
    private int propertyPlace;

    //Property houses
    private int numHouses;

    //Property of hotels
    private int numHotels;

    //Property rent
    private double rent;

    //Property house cost
    private double houseCost;

    //Property hotel cost
    private double hotelCost;

    //Property costs
    private double propertyCost;

    public Properties(String propertyName, Paint color, int owned, int boardPlace, int numHouses,
               int numHotels, double rent, double houseCost, double hotelCost, double propertyCost)
    {
        this.propertyName = propertyName;
        this.color = color;
        this.owner = owned;
        this.propertyPlace = boardPlace;
        this.numHouses = numHouses;
        this.numHotels = numHotels;
        this.rent = rent;
        this.houseCost = houseCost;
        this.hotelCost = hotelCost;
        this.propertyCost = propertyCost;

    }

}
