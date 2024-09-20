package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MainMenu extends Application {

    public static void main(String[] args) {
        launch(args);
    }
 
    private String username;
    // Add a constructor that takes the username as a parameter
    public MainMenu(String username) {
        this.username = username;
    } 
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Main Menu");
        
        // Create a DropShadow effect
        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(8);
        dropShadow.setOffsetY(8);
        dropShadow.setColor(javafx.scene.paint.Color.BLACK);
        
        // Create UI elements
        Label titleLabel = new Label("Christmas\nMario");
        titleLabel.setStyle("-fx-text-fill: white; -fx-font-family: 'Furore';"
        		+ "-fx-font-size: 100px; -fx-font-weight: bold;");
        titleLabel.setEffect(dropShadow);
        
        Button chooseCharacterButton = new Button("Choose Character");
        chooseCharacterButton.setStyle("-fx-font-size: 14px;-fx-background-color: white;"
        		+ "-fx-text-fill: black; -fx-font-family: 'Furore';"
        		+ "-fx-font-weight: bold; -fx-min-width: 200px;");
        
        Button startGameButton = new Button("Start Game");
        startGameButton.setStyle("-fx-font-size: 39px; -fx-min-width: 200px; "
        		+ "-fx-min-height: 50px; -fx-background-color: white; "
        		+ "-fx-text-fill: black; -fx-font-family: 'Furore';"
        		+ "-fx-font-weight: bold;");
        
        Button leaderboardButton = new Button("Leaderboard");
        leaderboardButton.setStyle("-fx-font-size: 14px;-fx-background-color: white;"
        		+ "-fx-text-fill: black; -fx-font-family: 'Furore';"
        		+ "-fx-font-weight: bold; -fx-min-width: 322px;");
        
        Button tutorialButton = new Button("Tutorial");
        tutorialButton.setStyle("-fx-font-size: 14px;-fx-background-color: white;"
        		+ "-fx-text-fill: black; -fx-font-family: 'Furore';"
        		+ "-fx-font-weight: bold; -fx-min-width: 111px;");
        
        Button exitButton = new Button("Exit");
        exitButton.setStyle("-fx-font-size: 14px;-fx-background-color: white;"
        		+ "-fx-text-fill: black; -fx-font-family: 'Furore';"
        		+ "-fx-font-weight: bold;");

        // Handling button actions
        chooseCharacterButton.setOnAction(e -> openChooseWindow(primaryStage));
        startGameButton.setOnAction(e -> openGameWindow(primaryStage));
        leaderboardButton.setOnAction(e -> openLeaderboardWindow(primaryStage));
        tutorialButton.setOnAction(e -> openTutorialWindow(primaryStage));
        exitButton.setOnAction(e -> primaryStage.close());
        
        // Layout setup
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(20);
        grid.setHgap(10);
        
        // Set Background Image
        grid.setStyle("-fx-background-image: url('images/background_mainmenu.png'); " +
                "-fx-background-size: cover; " +
                "-fx-background-repeat: no-repeat; " +
                "-fx-background-position: center;");
        
        grid.add(titleLabel, 0, 0, 2, 1);
        grid.add(startGameButton, 0, 1, 2, 1);
        grid.add(chooseCharacterButton, 0, 3);
        grid.add(leaderboardButton, 0, 4, 2, 1);
        grid.add(tutorialButton, 1, 3, 2, 1);
        grid.add(exitButton, 6, 4);

        // Set scene
        Scene scene = new Scene(grid, 800, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        // Add background music
        SoundPlayer.playSound("GroundTheme.wav");
    }
    private void openTutorialWindow(Stage primaryStage) {
        Tutorial tutorial = new Tutorial();
        tutorial.start(new Stage());
    }
    
    private void openLeaderboardWindow(Stage primaryStage) {
        Leaderboard leaderboard = new Leaderboard();
        leaderboard.start(new Stage());
    }
    
    private void openGameWindow(Stage primaryStage) {
        Game game = new Game(username);
        game.start(new Stage());
    }
    
    private void openChooseWindow(Stage primaryStage) {
    	ChooseCharacter chooseCharacter = new ChooseCharacter(username);
    	chooseCharacter.start(new Stage());
    }
    
}