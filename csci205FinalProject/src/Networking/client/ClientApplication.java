package Networking.client;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.ConnectException;
import java.util.ArrayList;

/**
 * Handles graphics for the client side
 */
public class ClientApplication extends Application {
    /**
     * An arrayList of thread objects
     */
    private ArrayList<Thread> threads;
    /**
     * The client object
     */
    private Client client;
    /**
     * The scene to get input for the set up and connection to the server
     */
    private Scene setUpScene;
    /**
     * The scene that is displayed while the server is being started
     */
    private Scene waitingScene;
    /**
     * The scene that is displayed while the game is being played
     */
    private Scene playingScene;
    /**
     * Button to submit information to connect to the server
     */
    private Button submitClientInfoButton;
    /**
     * TextField to get input of client's name
     */
    private TextField nameField;
    /**
     * TextField to get input of host's name
     */
    private TextField hostNameField;
    /**
     * TextField to get input of the port number
     */
    private TextField portNumberField;
    /**
     * Error label to display any issues with the user's input
     */
    private Label errorLabel;
    /**
     * Button the user presses to start the graphics once the server is ready
     */
    private Button everyoneReadyButton;

    /**
     * Main method
     *
     * @param args String Array
     */
    public static void main(String[] args) {
        launch();
    }

    /**
     * The application initialization method. This method is called immediately
     * after the Application class is loaded and constructed. An application may
     * override this method to perform initialization prior to the actual starting
     * of the application.
     *
     * <p>
     * The implementation of this method provided by the Application class does nothing.
     * </p>
     *
     * <p>
     * NOTE: This method is not called on the JavaFX Application Thread. An
     * application must not construct a Scene or a Stage in this
     * method.
     * An application may construct other JavaFX objects in this method.
     * </p>
     *
     * @throws Exception if something goes wrong
     */
    @Override
    public void init() throws Exception {
        super.init();
        setUpScene = makeInitScene();
        try {
            waitingScene = getWaitingScene();
        } catch (Exception e) {
            System.out.println(e.getStackTrace().toString());
        }

    }

    /**
     * Generates the action events for when the buttons are pressed
     * @param primaryStage the current stage being used
     */
    private void generateActionEvents(Stage primaryStage) {
        submitClientInfoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent Event) {
                /* Instantiate the client class and start it's thread */
                try {
                    client = new Client(hostNameField.getText(), Integer.parseInt(portNumberField.getText()), nameField.getText());
                    Thread clientThread = new Thread(client);
                    clientThread.setDaemon(true);
                    clientThread.start();
                    threads.add(clientThread);

                    /* Change the scene of the primaryStage */
                    primaryStage.close();
                    primaryStage.setScene(waitingScene);
                    primaryStage.show();

                } catch (ConnectException e) {
                    errorLabel.setTextFill(Color.RED);
                    errorLabel.setText("Invalid host name, try again");
                } catch (NumberFormatException | IOException e) {
                    errorLabel.setTextFill(Color.RED);
                    errorLabel.setText("Invalid port number, try again");
                }

            }
        });

        everyoneReadyButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (client.isGraphicsReady()) {
                    try {
                        /* Change the scene of the primaryStage */
                        primaryStage.close();
                        primaryStage.setScene(setUpPlayingScene());
                        primaryStage.setWidth(1100);
                        primaryStage.setHeight(800);
                        // For testing rn
                        primaryStage.setTitle(nameField.getText());
                        primaryStage.setResizable(false);
                        primaryStage.show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("Not everyone is ready");
                }
            }
        });


    }

    /**
     * Sets up the playingScene
     * @return the Scene containing the graphics for playing the game
     */
    private Scene setUpPlayingScene() {
        return new Scene(client.getTheView().getRoot());
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        threads = new ArrayList<Thread>();
        primaryStage.setTitle("Monopoly Client");
        generateActionEvents(primaryStage);
        primaryStage.setScene(setUpScene);
        primaryStage.show();
    }

    /**
     * Creates the scene to get input from the user
     * @return the Scene that gets input to allow the client to connect to the server
     */
    public Scene makeInitScene() {
        /* Make the root pane and set properties */
        GridPane rootPane = new GridPane();
        rootPane.setPadding(new Insets(20));
        rootPane.setVgap(10);
        rootPane.setHgap(10);
        rootPane.setAlignment(Pos.CENTER);

        /* Make the text fields and set properties */
        nameField = new TextField();
        hostNameField = new TextField();
        portNumberField = new TextField();

        /* Make the labels and set properties */
        Label nameLabel = new Label("Name ");
        Label hostNameLabel = new Label("Host Name");
        Label portNumberLabel = new Label("Port Number");
        errorLabel = new Label();
        /* Make the button and its handler */
        submitClientInfoButton = new Button("Done");

        /*
         * Add the components to the root pane arguments are (Node, Column
         * Number, Row Number)
         */
        rootPane.add(nameField, 0, 0);
        rootPane.add(nameLabel, 1, 0);
        rootPane.add(hostNameField, 0, 1);
        rootPane.add(hostNameLabel, 1, 1);
        rootPane.add(portNumberField, 0, 2);
        rootPane.add(portNumberLabel, 1, 2);
        rootPane.add(submitClientInfoButton, 0, 3, 2, 1);
        rootPane.add(errorLabel, 0, 4);
        /* Make the Scene and return it */
        return new Scene(rootPane, 400, 400);
    }

    /**
     * Scene that is shown while the client is waiting for the server to be ready
     * @return the Scene that is shown while the client is waiting for the server to be ready
     */
    private Scene getWaitingScene() {

        // Create UI.
        StackPane pane = new StackPane();
        pane.getChildren().add(new Label("Waiting for game to begin"));
        everyoneReadyButton = new Button("Press when everyone is ready");
        pane.getChildren().add(everyoneReadyButton);
        Scene scene;
        scene = new Scene(pane, 400, 400);
        scene.setFill(Color.LIGHTGRAY);
        return scene;
    }

}
