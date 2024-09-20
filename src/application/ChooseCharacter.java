package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.*;

public class ChooseCharacter extends Application {
	private int currentCharacterIndex = 0;
	private String userName;

	public ChooseCharacter(String userName) {
        this.userName = userName;
    }

    private Image[] characters = {
            new Image("images/character/character1.png"),
            new Image("images/character/character2.png"),
            new Image("images/character/character3.png"),
            new Image("images/character/character4.png"),
            new Image("images/character/character5.png"),
            new Image("images/character/character6.png"),
            new Image("images/character/character7.png"),
            new Image("images/character/character8.png"),
            new Image("images/character/character9.png"),
            new Image("images/character/character10.png")
    };

    private ImageView characterImageView;
    private Label messageLabel;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Choose Character");
        Label titleLabel = new Label("Choose Character");
        titleLabel.setStyle("-fx-text-fill: white; -fx-font-family: 'Furore';"
        		+ "-fx-font-size: 26px; -fx-font-weight: bold;");
        BorderPane borderPane = new BorderPane();

        characterImageView = new ImageView();
        characterImageView.setImage(characters[currentCharacterIndex]);
        
        // Set the width and height of the ImageView
        double preferredWidth = 200;
        double preferredHeight = 300;
        characterImageView.setFitWidth(preferredWidth);
        characterImageView.setFitHeight(preferredHeight);
        
        messageLabel = new Label();
        messageLabel.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");

        Button nextButton = new Button("Next");
        nextButton.setOnAction(e -> showNextCharacter());
        nextButton.setStyle("-fx-font-size: 14px;-fx-background-color: white;"
        		+ "-fx-text-fill: black; -fx-font-family: 'Furore';"
        		+ "-fx-font-weight: bold; -fx-min-width: 135px;");
        
        Button selectButton = new Button("Select");
        selectButton.setOnAction(e -> selectCharacter());
        selectButton.setStyle("-fx-font-size: 14px;-fx-background-color: white;"
        		+ "-fx-text-fill: black; -fx-font-family: 'Furore';"
        		+ "-fx-font-weight: bold; -fx-min-width: 135px;");
        
        Button exitButton = new Button("Exit");
        exitButton.setOnAction(e -> {
            Stage stage = (Stage) exitButton.getScene().getWindow();
            stage.close();
        });
        exitButton.setStyle("-fx-font-size: 14px;-fx-background-color: white;"
        		+ "-fx-text-fill: black; -fx-font-family: 'Furore';"
        		+ "-fx-font-weight: bold; -fx-min-width: 135px;");
        
        HBox buttonLayout = new HBox(10);
        buttonLayout.getChildren().addAll(selectButton, nextButton, exitButton);
        buttonLayout.setPadding(new Insets(10));
        
        VBox bottomLayout = new VBox(10);
        bottomLayout.getChildren().addAll(buttonLayout, messageLabel);
        bottomLayout.setAlignment(javafx.geometry.Pos.CENTER);
               
        borderPane.setTop(titleLabel);
        BorderPane.setAlignment(titleLabel, javafx.geometry.Pos.CENTER);

        borderPane.setCenter(characterImageView);
        BorderPane.setAlignment(characterImageView, javafx.geometry.Pos.CENTER);

        borderPane.setBottom(bottomLayout);
        BorderPane.setAlignment(bottomLayout, javafx.geometry.Pos.CENTER);

        borderPane.setPadding(new Insets(10));
        
        // Set Background Image
        borderPane.setStyle("-fx-background-image: url('images/background_login.png'); " +
                "-fx-background-size: cover; " +
                "-fx-background-repeat: no-repeat; " +
                "-fx-background-position: center;");

        Scene scene = new Scene(borderPane, 500, 510);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showNextCharacter() {
        currentCharacterIndex = (currentCharacterIndex + 1) % characters.length;
        characterImageView.setImage(characters[currentCharacterIndex]);
    }
    
    public void selectCharacter() {
    	updateCharacterInFile(userName, currentCharacterIndex+1); 
    	messageLabel.setText("Character is selected");
    }
    
    private void updateCharacterInFile(String username, int currentCharacterIndex) {
        String filePath = "user_credentials.txt"; // Replace with the actual file path

        try {
            // Read all lines from the file
            File file = new File(filePath);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            StringBuilder content = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                String[] userInfo = line.split(",");
                if (userInfo.length == 4 && userInfo[0].equals(username)) {
                    line = userInfo[0] + "," + userInfo[1] + "," + userInfo[2] + "," + 
                    currentCharacterIndex;
                }
                content.append(line).append(System.lineSeparator());
            }
            reader.close();

            // Write the updated content back to the file
            FileWriter writer = new FileWriter(file);
            writer.write(content.toString());
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }   
}