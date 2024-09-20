package application;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Register extends Application {

    private TextField regUsernameField;
    private PasswordField regPasswordField;
    private Label regMessageLabel;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) { 
    	primaryStage.setTitle("Register Page");

        // Registration form components 
        Label regUsernameLabel = new Label("New Username:");
        regUsernameField = new TextField();
        regUsernameLabel.setStyle("-fx-text-fill: white; -fx-font-weight: bold; ");
        
        Label regPasswordLabel = new Label("New Password:");
        regPasswordField = new PasswordField();
        regPasswordLabel.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");
        
        Button registerButton = new Button("Register");
        registerButton.setOnAction(e -> handleRegistration());

        regMessageLabel = new Label();
        regMessageLabel.setStyle("-fx-text-fill: white;");

        // Layout for registration form
        GridPane regGrid = new GridPane();
        regGrid.setPadding(new Insets(10, 10, 10, 10));
        regGrid.setVgap(8);
        regGrid.setHgap(10);
        
        // Set Background Image
        regGrid.setStyle("-fx-background-image: url('images/background_register.png'); " +
                "-fx-background-size: cover; " +
                "-fx-background-repeat: no-repeat; " +
                "-fx-background-position: center;");
        
        GridPane.setConstraints(regUsernameLabel, 0, 0);
        GridPane.setConstraints(regUsernameField, 1, 0);
        GridPane.setConstraints(regPasswordLabel, 0, 1);
        GridPane.setConstraints(regPasswordField, 1, 1);
        GridPane.setConstraints(registerButton, 1, 2);
        GridPane.setConstraints(regMessageLabel, 0, 3, 2, 1);
        regGrid.getChildren().addAll(regUsernameLabel, regUsernameField, regPasswordLabel, 
        		regPasswordField, registerButton, regMessageLabel);

        Scene regScene = new Scene(regGrid, 290, 150);

        primaryStage.setScene(regScene);
        primaryStage.show();
    }

    private void handleRegistration() {
        String newUsername = regUsernameField.getText();
        String newPassword = regPasswordField.getText();

        if (!newUsername.isEmpty() && !newPassword.isEmpty()) {
            boolean success = registerUser(newUsername, newPassword);

            if (success) {
                regMessageLabel.setText("Registration successful!"); 
             // Close the application after successful registration
                ((Stage) regMessageLabel.getScene().getWindow()).close(); 
            } else {
                regMessageLabel.setText("Username already exists!");
            }
        } else {
            regMessageLabel.setText("Invalid username and password!");
        }
    }

    private boolean registerUser(String username, String password) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("user_credentials.txt", true))) {
            // Check if user name already exists
            if (checkUsernameExists(username)) {
                return false; // User name exists
            }

            // Add new user
            bw.write(username + "," + password + ",0," + "1");
            bw.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean checkUsernameExists(String username) {
        try (BufferedReader br = new BufferedReader(new FileReader("user_credentials.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
            	String[] parts = line.split(",");
            	if (parts.length >= 4 && parts[0].equals(username)) {
            	    return true; // User name exists
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); 
        }
        return false; // User name doesn't exist
    }
}