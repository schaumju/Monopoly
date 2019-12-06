Team Name:
Monopoly Masters

Team members:
Justin Schaumberger, Kerri Palphreyman, Andrew Lee, Ashlyn Ramos

Project:
    Our project is to create a working Monopoly project that can connect through IP to other computers. 
    The game functionality includes connecting with a player name, buying properties, going to Jail, rolling dice to move, paying rent, managing a balance, and community chest and chance cards.
    The game uses JavaFX and the MVC design to create the gooey. 
    The gooey shows the board, player log, and characters on the board.
    
Package Structure:
    In our src, we have our Game package, MVC package, and Networking package.
    src:
        Game Package:
            Cards Package:
                Card - Constructor for a base card (either community chest or chance)
                Chance Card - Extends card and contains a constructor method for the card.
                Chance Deck - Initializes the deck and adds all the chance cards to the deck and shuffles the cards.
                Community Chest Card - Extends card and contains a constructor method for the card.
                Community Chest Deck - Initializes the deck and adds all the community chest cards to the deck and shuffles the cards.
                Deck - Abstract class with a constructor method, abstract init() method, draw method, and shuffle method
            Spaces package:
                [I]Buyable - Contains getCost, getOwner, buyProperty
                Chance - constructor for a Chance space
                Community Chest - constructor for a Community Chest space
                Free Parking Space - constructor for a Free Parking Space
                GoSpace - constructor for the GO space
                GoToJailSpace - constructor for the GoToJailSpace
                JailSpace - constructor for the Jail space
                Property - constructor for a default property space
                PropertyColor - initializes the property color
                Railroads - constructor for a railroad space
                Space - Abstract class for a space
                Tax - constructor for the tax space
                Utilities - Constructor for the utilities space
            Board - Contains the constructor method for the Board object containing all the different property spaces (utilities, tax, properties, railraods, etc.)
            Character - Contains the constructor method for the Character object containing all the attributes of a player (numHouses, balance, position, name, etc.)
            Dice - Contains the constructor method for the Dice containing the ability to roll the dice and get the rolls
            Game - Contains the constructor method for the Game object containing a list of players (Character objects), the Board object, and current player.
            
        MVC:
        
    
    
