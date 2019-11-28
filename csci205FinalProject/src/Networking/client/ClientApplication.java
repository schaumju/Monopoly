package Networking.client;

import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
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

public class ClientApplication extends Application {
    private ArrayList<Thread> threads;
    private Client client;
    private Scene setUpScene;
    private Scene waitingScene;
    private Scene playingScene;
    private Button submitClientInfoButton;
    private TextField nameField;
    private TextField hostNameField;
    private TextField portNumberField;
    private Label errorLabel;

    private BooleanProperty gameReady = new SimpleBooleanProperty();
    private Button everyoneReadyButton;


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
