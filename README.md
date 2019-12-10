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
            Game - Contains the constructor method for the Game object containing a list of players (Character objects), the Board object, and current playerMVC:
            Controller:
                BoardController - Class that creates board popups and buttons
                BuyPropertyController - Class that handles buying a property and updating the board 
                DiceController - Class that handles rolling the dice and running the turn
                EndTurnController - Class that handles ending a players turn
                MainController - Creates instances of all the other controller classes
            Model:
                GameLog - Generates the game log and adds phrases to the log
                MonopolyModel - contains the running game object, player list, and methods to interact with each space.
            View:
                BoardView - Creates the board and all the correct spaces
                CardsView - Adds community chest and chance cards to the deck
                CharacterView - Creates the character objects and places them on the board, also handles moving players and updating their visual on the board
                DiceAnimation - Creates the animation for the dice
                DiceView - Creates the dice and the corresponding visual on the board
                EndTurnView - Ends the current players turn
                LogView - Creates and adds the log object
                MainView - contains all other views and sets up the scene
                PropertyView - Contains the buy property button and its methods 
        Networking:
            client:
                Client - Creates a player in a client to play with others
                ClientApplication - Runs the game as a client
            server:
                ClientThread - Thread class for the client
                Server - The Server that handles multiplayer 
                ServerApplication - Runs the game as a host
            SerializableColor - Converts JavaFX color to an object that can be sent over ObjectStreams
            SerializableObservableList - Stores an ObservableList as a Serializable so it can be sent through ObjectStreams
            TurnState - Constants representing player state in them

Java Libraries Used:
    java.io.Serializable 
    javafx.scene.paint.Color
    javafx.scene.paint.Paint
    java.util.ArrayList
    java.util.Comparator
    javafx.geometry.Pos
    javafx.scene.Scene 
    javafx.scene.control.Label
    javafx.scene.layout.VBox
    javafx.scene.text.Text
    javafx.stage.Stage
    java.io.IOException
    javafx.collections.FXCollections
    javafx.collections.ObservableList
    javafx.scene.shape.Rectangle
    javafx.scene.text.Font
    javafx.scene.text.FontWeight
    javafx.scene.text.TextAlignment
    java.util.List
    javafx.scene.shape.Circle
    javafx.animation.RotateTransition
    javafx.beans.property.SimpleIntegerProperty
    javafx.scene.layout.StackPane
    javafx.util.Duration
    javafx.beans.property.SimpleBooleanProperty
    javafx.scene.control.Button
    javafx.scene.control.ListView
    javafx.scene.layout.GridPane
    javafx.application.Platform
    java.io.ObjectInputStream
    java.io.ObjectOutputStream
    java.net.Socket
    javafx.geometry.Insets
    javafx.application.Application
    javafx.event.ActionEvent
    javafx.event.EventHandler
    java.net.ConnectException
    java.net.InetAddress

Instructions to run and compile:

                
                
                
                
                
                
                
                
        
    
    
