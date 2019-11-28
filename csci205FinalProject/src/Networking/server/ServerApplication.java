package Networking.server;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;


public class ServerApplication extends Application {
    public static ArrayList<Thread> threads;
    private final int MAX_PLAYERS = 4;
    private final int MIN_PLAYERS = 2;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        threads = new ArrayList<Thread>();
        primaryStage.setTitle("Monopoly Server");
        primaryStage.setScene(makePortUI(primaryStage));
        primaryStage.show();

    }


    public Scene makePortUI(Stage primaryStage) {
        /* Make the root and set properties */
        GridPane rootPane = new GridPane();
        rootPane.setPadding(new Insets(20));
        rootPane.setVgap(10);
        rootPane.setHgap(10);
        rootPane.setAlignment(Pos.CENTER);

        /* Text label and field for port Number */
        Text portText = new Text("Port Number");
        Label errorLabel = new Label();
        errorLabel.setTextFill(Color.RED);
        TextField portTextField = new TextField();
        portText.setFont(Font.font("Tahoma"));

        /* Text label and field for number of players */
        Text numPlayers = new Text("Number of Players");
        Label errorLabel2 = new Label();
        errorLabel2.setTextFill(Color.RED);
        TextField numberPlayers = new TextField();
        numPlayers.setFont(Font.font("Tahoma"));
        System.out.println("HERE");
        /*
         * "Done" button and its click handler When clicked, another method is
         * called
         */
        Button portApprovalButton = new Button("Done");
        portApprovalButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                /* Make the server and it's thread, and run it */
                try {
                    int port = Integer.parseInt(portTextField.getText());
                    int players = Integer.parseInt(numberPlayers.getText());
                    //CHECK FOR RIGHT NUMBER OF PLAYERS
                    if (players < MIN_PLAYERS || players > MAX_PLAYERS) {
                        throw new NumberPlayersException("Invalid number of players");
                    }
                    Server server = new Server(port, players);
                    Thread serverThread = (new Thread(server));
                    serverThread.setName("Server Thread");
                    serverThread.setDaemon(true);
                    serverThread.start();
                    threads.add(serverThread);
                    /* Change the view of the primary stage */
                    primaryStage.hide();
                    primaryStage.setScene(makeServerUI(server));
                    primaryStage.show();
                } catch (IllegalArgumentException e) {
                    errorLabel.setText("Invalid port number");
                } catch (IOException e) {
                    e.getStackTrace();

                } catch (NumberPlayersException e) {
                    errorLabel.setText("Invalid number of players");
                }

            }
        });

        /* Add the views to the pane */
        rootPane.add(portText, 0, 0);
        rootPane.add(portTextField, 0, 1);
        rootPane.add(numPlayers, 0, 2);
        rootPane.add(numberPlayers, 0, 3);
        rootPane.add(portApprovalButton, 0, 4);
        rootPane.add(errorLabel, 0, 5);
        /*
         * Make the Scene and return it Scene has constructor (Parent, Width,
         * Height)
         */
        return new Scene(rootPane, 400, 300);
    }

    public Scene makeServerUI(Server server) {
        /* Make the root pane and set properties */
        GridPane rootPane = new GridPane();
        rootPane.setAlignment(Pos.CENTER);
        rootPane.setPadding(new Insets(20));
        rootPane.setHgap(10);
        rootPane.setVgap(10);

        /* Make the server log ListView */
        Label logLabel = new Label("Server Log");
        ListView<String> logView = new ListView<String>();
        ObservableList<String> logList = server.log.getLog();
        logView.setItems(logList);

        /* Make the client list ListView */
        Label clientLabel = new Label("Clients Connected");
        ListView<String> clientView = new ListView<String>();
        ObservableList<String> clientList = server.clientNames;
        clientView.setItems(clientList);

        /* Add the view to the pane */
        rootPane.add(logLabel, 0, 0);
        rootPane.add(logView, 0, 1);
        rootPane.add(clientLabel, 0, 2);
        rootPane.add(clientView, 0, 3);

        /* Make scene and return it,
         * Scene has constructor (Parent, Width, Height)
         *  */
        return new Scene(rootPane, 400, 600);
    }

    /**
     * Class for an invalid input of the number of players
     */
    public class NumberPlayersException extends Exception {
        public NumberPlayersException(String message) {
            super(message);
        }
    }

}
