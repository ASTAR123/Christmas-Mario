package application;

import java.io.BufferedReader;
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

public class Main extends Application {

    private TextField usernameField;
    private PasswordField passwordField;
    private Label messageLabel;
    int coins; // Variable to store coins
    String username;
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
    	 primaryStage.setTitle("Login Page");
    	 
         // Create UI elements and change style
         Label titleLabel = new Label("Login Page");
         titleLabel.setStyle("-fx-text-fill: white; -fx-font-family: 'Furore';"
         		+ "-fx-font-size: 24px; -fx-font-weight: bold;");

         Label usernameLabel = new Label("Username:");
         usernameLabel.setStyle("-fx-text-fill: white; -fx-font-size: 14px;"
          		+ "-fx-font-weight: bold;");
         
         usernameField = new TextField();
         
         Label passwordLabel = new Label("Password:");
         passwordField = new PasswordField();
         passwordLabel.setStyle("-fx-text-fill: white; -fx-font-size: 14px;"
          		+ "-fx-font-weight: bold;");

         Button loginButton = new Button("Login");
         loginButton.setOnAction(e -> handleLogin());

         Button registerButton = new Button("New User? Register Yourself");
         registerButton.setOnAction(e -> openRegisterWindow(primaryStage));
         
         messageLabel = new Label();
         messageLabel.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");

         // Layout setup
         GridPane grid = new GridPane();
         grid.setPadding(new Insets(20));
         grid.setVgap(10);
         grid.setHgap(10);
    	 
    	 // Set Background Image
    	 grid.setStyle("-fx-background-image: url('images/background_login.png'); " +
                 "-fx-background-size: cover; " +
                 "-fx-background-repeat: no-repeat; " +
                 "-fx-background-position: center;");

         grid.add(titleLabel, 0, 0, 2, 1);
         grid.add(usernameLabel, 0, 1);
         grid.add(usernameField, 1, 1);
         grid.add(passwordLabel, 0, 2);
         grid.add(passwordField, 1, 2);
         grid.add(loginButton, 1, 3, 2, 1);
         grid.add(registerButton, 1, 4);
         grid.add(messageLabel, 0, 5, 2, 1);

         Scene scene = new Scene(grid, 400, 240);
         primaryStage.setScene(scene);
         primaryStage.show();
    }

    private void handleLogin() {
        username = usernameField.getText();
        String password = passwordField.getText();

        LoginResult result = checkCredentials(username, password);

        if (result.isAuthenticated) {
        	username = result.getUsername(); // Access the username
        	
            messageLabel.setText("Login successful!");
 
            // Close the window after successful login
            ((Stage) messageLabel.getScene().getWindow()).close(); 
            MainMenu mainMenu = new MainMenu(username);
            mainMenu.start(new Stage());
        } else {
            messageLabel.setText("Invalid username or password!");
        }
    }

    public LoginResult checkCredentials(String username, String password) {
        try (BufferedReader br = new BufferedReader(new 
        		FileReader("user_credentials.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 4 && parts[0].equals(username) && 
                		parts[1].equals(password)) {
                	coins = Integer.parseInt(parts[2]);
                	 return new LoginResult(username, true, coins);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        	}
        return new LoginResult(username, false, 0);
    }
    
    public static class LoginResult {
        private final String username;
    	private final boolean isAuthenticated;
        private final int coins;

        public LoginResult(String username, boolean isAuthenticated, int coins) {
            this.username = username;
        	this.isAuthenticated = isAuthenticated;
            this.coins = coins;
        }

        public boolean isAuthenticated() {
            return isAuthenticated;
        }

        public int getCoins() {
            return coins;
        }
        
        public String getUsername() {
            return username;
        }
    }
  
    private void openRegisterWindow(Stage primaryStage) {
        Register register = new Register();
        register.start(new Stage());
    }
}