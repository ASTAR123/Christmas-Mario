package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Tutorial extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Instructions
        Label instructionsLabel = new Label("This game is some kind of Mario Game "
        		+ "generator in\nChristmas Edition.\n" +
                "\nUse arrows to move and jump." +
                "\nYou can add new items by pressing:\n" +
                "E - add Enemy\n" +
                "B - add Box (80% BoxCoin and 20% PowerUp)\n" +
                "W - add Wall\n" +
                "P - add big Pipe\n" +
                "O - add small Pipe\n" +
                "C - add Coin\n" +
                "Z - clear area\n" +
                "\nRules:\n" +
                "Kill enemies by jumping on them.\n" +
                "Player at level 0 can't destroy Walls.\n" +
                "Max Mario level is 2 - white uniform.\n" +
        		"Player die if he gets hit by enemy.");
        instructionsLabel.setStyle("-fx-text-fill: white; -fx-font-family: 'Furore';");
        
        // Exit button
        Button exitButton = new Button("Exit to Main Menu");
        exitButton.setOnAction(e -> ((Stage) instructionsLabel.getScene().getWindow()).close());
        exitButton.setStyle("-fx-font-size: 14px;-fx-background-color: white;"
        		+ "-fx-text-fill: black; -fx-font-family: 'Furore';"
        		+ "-fx-font-weight: bold;");
        
        // Create layout
        VBox root = new VBox(10);
        root.setPadding(new Insets(20));
        root.getChildren().addAll(instructionsLabel, exitButton);
        root.setStyle("-fx-background-image: url('images/background_register.png'); " +
                "-fx-background-size: cover; " +
                "-fx-background-repeat: no-repeat; " +
                "-fx-background-position: center;");
        
        // Create the Scene
        Scene scene = new Scene(root, 415, 385);

        // Set the Scene and show the Stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("Tutorial");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}