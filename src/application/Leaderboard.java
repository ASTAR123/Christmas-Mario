package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Leaderboard extends Application {

    private List<User> users;

    public Leaderboard() {
        users = new ArrayList<>();
        readUserDataFromFile();
    }

    private void readUserDataFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("user_credentials.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    String username = parts[0];
                    String password = parts[1];
                    int coins = Integer.parseInt(parts[2]);
                    users.add(new User(username, password, coins));
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public void start(Stage stage) {
        // Layout setup
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(5);
        grid.setHgap(5);
        
        Label titleLabel = new Label("Leaderboard");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: white; "
        		+ "-fx-font-family: 'Furore';");
        
        // Exit button
        Button exitButton = new Button("Exit to Main Menu");
        exitButton.setOnAction(e -> ((Stage) titleLabel.getScene().getWindow()).close());
        exitButton.setStyle("-fx-font-size: 14px;-fx-background-color: white;"
        		+ "-fx-text-fill: black; -fx-font-family: 'Furore';"
        		+ "-fx-font-weight: bold;");
        
        grid.add(titleLabel, 0, 0);
        grid.add(exitButton, 0, 5);
        
        grid.setStyle("-fx-background-image: url('images/background_register.png'); " +
                "-fx-background-size: cover; " +
                "-fx-background-repeat: no-repeat; " +
                "-fx-background-position: center;");
        
        users.sort(Comparator.comparingInt(User::getCoins).reversed());

        for (int i = 0; i < users.size(); i++) {
            Label userLabel = new Label((i + 1) + ". Username: " + users.get(i).getUsername() +
                    ", Coins: " + users.get(i).getCoins());
            userLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: white; ");
            grid.add(userLabel, 0, i+1);
        }

        Scene scene = new Scene(grid, 400, 300);
        stage.setScene(scene);
        stage.setTitle("Leaderboard"); 
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}