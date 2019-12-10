package Main;

import Networking.client.ClientApplication;
import Networking.server.ServerApplication;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Main extends Application {

    /**
     * The button to join a server
     */
    private Button client;
    /**
     * The button to host a server
     */
    private Button server;
    /**
     * The HBox containing all the buttons
     */
    private HBox buttons;
    /**
     * The root node of the scene
     */
    private VBox root;
    /**
     * The text that is displayed on the screen
     */
    private Text text;
    /**
     * The white background that the text is displayed on
     */
    private HBox textBackground;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Monopoly");
        rootInit();
        createText();
        createButtons();
        generateBackground();
        createActionEvents(primaryStage);

        Scene initScene = new Scene(root);

        primaryStage.setScene(initScene);
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    /**
     * Creates the buttons and adds them to the root
     */
    private void createButtons() {
        buttons = new HBox(20);
        buttons.setAlignment(Pos.CENTER);
        client = new Button("Join Game");
        client.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3))));
        client.setBackground(new Background(new BackgroundFill(Color.rgb(255, 255, 255), CornerRadii.EMPTY, Insets.EMPTY)));
        client.setPrefSize(100, 50);
        server = new Button("Host Game");
        server.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3))));
        server.setBackground(new Background(new BackgroundFill(Color.rgb(255, 255, 255), CornerRadii.EMPTY, Insets.EMPTY)));
        server.setPrefSize(100, 50);
        buttons.getChildren().add(server);
        buttons.getChildren().add(client);
        root.getChildren().add(buttons);
    }

    /**
     * Creates the background
     *
     * @ref https://stackoverflow.com/questions/9738146/javafx-how-to-set-scene-background-image
     */
    private void generateBackground() {
        Image image = new Image("https://www.dailydot.com/wp-content/uploads/541/74/b2cf1a04abab0e46.jpg");
        BackgroundImage myBI = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        root.setBackground(new Background(myBI));
    }

    /**
     * Creates the text and adds it to the root
     */
    private void createText() {
        text = new Text("Welcome to Monopoly. Would you like to host or join a game?");
        text.setFill(Color.BLACK);
        text.setTextAlignment(TextAlignment.CENTER);
        text.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 30));
        textBackground = new HBox();
        textBackground.setPadding(new Insets(10));
        textBackground.setBackground(new Background(new BackgroundFill(Color.rgb(255, 255, 255), CornerRadii.EMPTY, Insets.EMPTY)));
        textBackground.setLayoutX(root.getLayoutX());
        textBackground.setLayoutY(root.getLayoutY());
        textBackground.setPrefHeight(text.getFont().getSize());
        textBackground.setPrefWidth(root.getWidth());
        textBackground.setAlignment(Pos.CENTER);
        textBackground.getChildren().add(text);
        root.getChildren().add(textBackground);
    }

    /**
     * Creates the root
     */
    private void rootInit() {
        root = new VBox(100);
        root.setPrefHeight(600);
        root.setPrefWidth(1000);
        root.setAlignment(Pos.TOP_CENTER);
    }


    /**
     * generates the action events for the buttons
     *
     * @param primaryStage the Stage that the future windows will be on
     */
    private void createActionEvents(Stage primaryStage) {
        client.setOnAction(event -> {
            ClientApplication application = new ClientApplication();
            try {
                primaryStage.close();
                application.init();
                application.start(primaryStage);

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        server.setOnAction(event -> {
            ServerApplication application = new ServerApplication();
            try {
                primaryStage.close();
                application.init();
                application.start(primaryStage);
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
    }
}
