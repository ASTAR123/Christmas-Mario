package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Game extends Application implements PlayerII{
	ArrayList<String> input = new ArrayList<>();
    List<Enemy> enemies = new ArrayList<>();
    List<Box> boxes = new ArrayList<>();
    List<Wall> walls = new ArrayList<>(); 
    List<Pipe> pipes = new ArrayList<>();
    List<Coin> coins = new ArrayList<>();
    Random rand = new Random();
    int counter;  

    private Text coinCountText = new Text("Coins: " + counter);
    
    private String userName; 

    // Add a constructor that takes the username as a parameter
    public Game(String userName) {
        this.userName = userName;
    }

    private int userCoin = getCoins(userName);

    public static void main(String[] args) {
        launch(args);
    }
        
    @Override
    public void start(Stage stage) {
        try {
            Player player = new Player(this, checkCharacter(userName));
            
            Group root = new Group();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Christmas Mario");

            Canvas canvas = new Canvas(800, 550);
            root.getChildren().add(canvas);

            Button exitButton = new Button("Exit");
            exitButton.setStyle("-fx-font-size: 14px;-fx-background-color: white;"
    			+ "-fx-text-fill: black; -fx-font-family: 'Furore';"
    			+ "-fx-font-weight: bold;");
    		
    		// Positioning the button at the bottom right using a StackPane
    		StackPane stackPane = new StackPane();
    		stackPane.getChildren().add(exitButton);
    		StackPane.setAlignment(exitButton, Pos.TOP_RIGHT);
    		StackPane.setMargin(exitButton, new Insets(500, 10, 10, 700)); // Adjust margins as needed
    		exitButton.setFocusTraversable(false);
            
            coinCountText.setFill(Color.WHITE);
            coinCountText.setStyle("-fx-font-weight: bold;");
            coinCountText.setX(10);
            coinCountText.setY(20);
            root.getChildren().add(coinCountText);   
            root.getChildren().add(stackPane);
            exitButton.setOnAction(e -> gameOver());

            // Initialize objects
            for (int i = 0; i < 10; i++) {
                Enemy enemy = new Enemy();
                enemy.setX(600+i*400);
                enemies.add(enemy);
                }

            for(int j = 0; j < 3; j++) {
                int starX = j*3200;
                walls.add(new Wall(265+starX, 250));
                walls.add(new Wall(335+starX, 250));
                walls.add(new Wall(370+starX, 250));
                walls.add(new Wall(405+starX, 250));
                walls.add(new Wall(550+starX, 250));
                boxes.add(new Box(300+starX, 250));
                boxes.add(new Box(515+starX, 250));
                boxes.add(new Box(600+starX, 10));
                coins.add(new Coin(100+starX, 150));
                coins.add(new Coin(400+starX, 200));
                coins.add(new Coin(500+starX, 400));
                pipes.add(new Pipe(650+starX, new Image("images/pipe/pipeSmall.png")));
                pipes.add(new Pipe(700+starX, new Image("images/pipe/pipeBig.png")));

                walls.add(new Wall(935+starX, 250));
                walls.add(new Wall(970+starX, 250));
                walls.add(new Wall(1005+starX, 250));
                walls.add(new Wall(1200+starX, 400));
                walls.add(new Wall(1235+starX, 400));
                walls.add(new Wall(1235+starX, 365));
                walls.add(new Wall(1270+starX, 400));
                walls.add(new Wall(1270+starX, 365));
                walls.add(new Wall(1270+starX, 330));
                walls.add(new Wall(1305+starX, 400));
                walls.add(new Wall(1305+starX, 365));
                walls.add(new Wall(1305+starX, 330));
                walls.add(new Wall(1305+starX, 295));
                walls.add(new Wall(1305+starX, 10));
                walls.add(new Wall(1340+starX, 10));
                walls.add(new Wall(1375+starX, 10));
                walls.add(new Wall(1445+starX, 10));
                walls.add(new Wall(1480+starX, 10));
                boxes.add(new Box(900+starX, 250));
                boxes.add(new Box(900+starX, 10));
                boxes.add(new Box(1410+starX, 10));
                boxes.add(new Box(1600+starX, 250));
                coins.add(new Coin(1305+starX, 260));
                coins.add(new Coin(1400+starX, 400));
                pipes.add(new Pipe(1100+starX, new Image("images/pipe/pipeSmall.png")));
                pipes.add(new Pipe(1600+starX, new Image("images/pipe/pipeSmall.png")));
                pipes.add(new Pipe(1465+starX, new Image("images/pipe/pipeBig.png")));

                for (int i = 0; i < 10; i++) {
                    walls.add(new Wall(1700 + i * 35 + starX, 10));
                }
                for (int i = 0; i < 15; i++) {
                    walls.add(new Wall(1700 + i * 35 + starX, 250));
                }
                boxes.add(new Box(2050+starX, 10));
                boxes.add(new Box(2300+starX, 200));
                boxes.add(new Box(2300+starX, 10));
                coins.add(new Coin(2000+starX, 400));
                coins.add(new Coin(2320+starX, 260));
                coins.add(new Coin(2400+starX, 150));
                pipes.add(new Pipe(2300+starX, new Image("images/pipe/pipeBig.png")));
                pipes.add(new Pipe(2400+starX, new Image("images/pipe/pipeSmall.png")));

                for (int i = 0; i < 11; i++) {
                    walls.add(new Wall(2600 + i * 35 + starX, 400));
                }
                for (int i = 0; i < 5; i++) {
                    walls.add(new Wall(2600+starX, 365 - i * 35));
                }
                for (int i = 0; i < 5; i++) {
                    walls.add(new Wall(2950+starX, 365 - i * 35));
                }
                for (int i = 0; i < 4; i++) {
                    walls.add(new Wall(2635+starX, 365 - i * 35));
                }
                for (int i = 0; i < 4; i++) {
                    walls.add(new Wall(2915+starX, 365 - i * 35));
                }
                for (int i = 0; i < 3; i++) {
                    walls.add(new Wall(2670+starX, 365 - i * 35));
                }
                for (int i = 0; i < 3; i++) {
                    walls.add(new Wall(2880+starX, 365 - i * 35));
                }
                for (int i = 0; i < 2; i++) {
                    walls.add(new Wall(2705+starX, 365 - i * 35));
                }
                for (int i = 0; i < 2; i++) {
                    walls.add(new Wall(2845+starX, 365 - i * 35));
                }
                walls.add(new Wall(2740+starX, 365));
                walls.add(new Wall(2810+starX, 365));
                for (int i = 0; i < 4; i++) {
                    walls.add(new Wall(3000 + i * 35 + starX, 250));
                }
                boxes.add(new Box(2775+starX, 150));
                boxes.add(new Box(2740+starX, 150));
                boxes.add(new Box(2810+starX, 150));
                boxes.add(new Box(3140+starX, 250));
                coins.add(new Coin(2780+starX, 330));
                coins.add(new Coin(3200+starX, 250));
                pipes.add(new Pipe(3300+starX, new Image("images/pipe/pipeBig.png")));
            }
            scene.setOnKeyPressed(e -> {
                String code = e.getCode().toString();

                // only add once... prevent duplicates
                if (!input.contains(code)) {
                    input.add(code);
                }
            });
            
            scene.setOnKeyReleased(e -> {
                String code = e.getCode().toString();
                input.remove(code);

                // add Enemy
                if (code.equals("E")) {
                    Enemy enemy = new Enemy();
                    enemies.add(enemy);
                }

                // add Box || collision box - wall, box, coin
                if (code.equals("B")) {
                    Box box = new Box(rand.nextInt(800), 250);
                    for (int i = 0; i < boxes.size() || i < walls.size() || i < coins.size(); i++) {
                        if (i < boxes.size()) {
                            if (box.collision(boxes.get(i))) {
                                i = -1;
                                box.setX(rand.nextInt(800));
                            }
                        }
                        if (i >= 0 && i < walls.size()) {
                            if (box.collision(walls.get(i))) {
                                i = -1;
                                box.setX(rand.nextInt(800));
                            }
                        }
                        if (i >= 0 && i < coins.size()) {
                            if (box.collision(coins.get(i))) {
                                i = -1;
                                box.setX(rand.nextInt(800));
                            }
                        }
                    }
                    boxes.add(box);
                }

                // add Wall (brick) || collision wall - box, wall, coin
                if (code.equals("W")) {
                    Wall wall = new Wall(rand.nextInt(800), 250);
                    for (int i = 0; i < walls.size() || i < boxes.size() || i < coins.size(); i++) {
                        if (i < walls.size()) {
                            if (wall.collision(walls.get(i))) {
                                i = -1;
                                wall.setX(rand.nextInt(800));
                            }
                        }
                        if (i >= 0 && i < boxes.size()) {
                            if (wall.collision(boxes.get(i))) {
                                i = -1;
                                wall.setX(rand.nextInt(800));
                            }
                        }
                        if (i >= 0 && i < coins.size()) {
                            if (wall.collision(coins.get(i))) {
                                i = -1;
                                wall.setX(rand.nextInt(800));
                            }
                        }
                    }
                    walls.add(wall);
                }

                // add small or big Pipe || collision pipe - enemy, pipe,
                // coin (pipe - coin not working well)
                if (code.equals("O") || code.equals("P")) {
                    Image image;
                    if (code.equals("O"))
                        image = new Image("images/pipe/pipeSmall.png");
                    else
                        image = new Image("images/pipe/pipeBig.png");
                    Pipe pipe = new Pipe(rand.nextInt(760), image);
                    for (int i = 0; i < enemies.size() || i < pipes.size() || i < coins.size(); i++) {
                        if (i < enemies.size()) {
                            if (pipe.collision(enemies.get(i))) {
                                i = -1;
                                pipe.setX(rand.nextInt(760));
                            }
                        }
                        if (i >= 0 && i < pipes.size()) {
                            if (pipe.collision(pipes.get(i))) {
                                i = -1;
                                pipe.setX(rand.nextInt(760));
                            }
                        }
                        if (i >= 0 && i < coins.size()) {
                            if (pipe.collision(coins.get(i))) {
                                i = -1;
                                pipe.setX(rand.nextInt(760));
                            }
                        }
                    }
                    pipes.add(pipe);
                }

                // add Coin || collision coin - box, wall, coin, pipe
                if (code.equals("C")) {
                    Coin coin = new Coin(rand.nextInt(800), rand.nextInt(400));
                    for (int i = 0; i < boxes.size() || i < walls.size() || i < coins.size()
                            || i < pipes.size(); i++) {
                        if (i < boxes.size()) {
                            if (coin.collision(boxes.get(i))) {
                                i = -1;
                                coin.setX(rand.nextInt(800));
                            }
                        }
                        if (i >= 0 && i < walls.size()) {
                            if (coin.collision(walls.get(i))) {
                                i = -1;
                                coin.setX(rand.nextInt(800));
                            }
                        }
                        if (i >= 0 && i < coins.size()) {
                            if (coin.collision(coins.get(i))) {
                                i = -1;
                                coin.setX(rand.nextInt(800));
                            }
                        }
                        if (i >= 0 && i < pipes.size()) {
                            if (coin.collision(pipes.get(i))) {
                                i = -1;
                                coin.setX(rand.nextInt(800));
                            }
                        }
                        if (coin.getY() > 400 - 85 - coin.getImage().getHeight()) {
                            i = -1;
                            coin.setY(rand.nextInt(800));
                        }

                    }
                    coins.add(coin);
                }

                // Clear area
                if (code.equals("Z")) {
                    boxes.clear();
                    enemies.clear();
                    walls.clear();
                    pipes.clear();
                    coins.clear();
                    player.setFloor((int) (550 - 85 - player.getImage().getHeight()));
                    player.checkJumping();
                }

                if (code.equals("UP") && !player.getFalling()) {
                	SoundPlayer.playSound("Jump.wav");
                }

            });

            GraphicsContext gc = canvas.getGraphicsContext2D();

            Image background = new Image("images/background.png");

            new AnimationTimer() {
                public void handle(long currentNanoTime) {
                    // moving screen
                    if (player.getX() >= 700) {
                        player.setX(700 - player.getMoveLength());
                        coins.forEach(coin -> coin.setX(coin.getX() - player.getMoveLength()));
                        boxes.forEach(box -> {
                            box.setX(box.getX() - player.getMoveLength());
                            if (box.getBoxCoin() != null) {
                                box.getBoxCoin().setX(box.getBoxCoin().getX() - player.getMoveLength());
                            }
                            if (box.getPowerUp() != null) {
                                box.getPowerUp().setX(box.getPowerUp().getX() - player.getMoveLength());
                            }
                        });
                        walls.forEach(wall -> wall.setX(wall.getX() - player.getMoveLength()));
                        pipes.forEach(pipe -> pipe.setX(pipe.getX() - player.getMoveLength()));
                        enemies.forEach(enemy -> enemy.setX(enemy.getX() - player.getMoveLength()));
                    }
                    player.checkJumping();
                    if (player.getGameOver()) {
                
                    } else {
                        player.move(input);
                        // move enemy and check collisions player - enemy, enemy
                        for (int i = 0; i < enemies.size(); i++) {
                            Enemy enemy = enemies.get(i);
                            enemy.move();
                            player.collision(enemy);
                            for (int j = i + 1; j < enemies.size(); j++) {
                                enemy.collision(enemies.get(j));
                            }
                            // collision enemy - pipe
                            pipes.forEach(enemy::collision);
                        }
                        // collision player - pipe
                        pipes.forEach(player::collision);
                        // collision player - box, animate box
                        boxes.forEach(box -> {
                            box.animate();
                            player.collision(box);
                            // animate BoxCoin
                            if (box.getBoxCoin() != null) {
                                box.getBoxCoin().animate();
                            }
                            // collision player - PowerUp and move PowerUp
                            if (box.getPowerUp() != null) {
                                PowerUp powerUp = box.getPowerUp();
                                // falling PowerUp if not collision with Box
                                if (!powerUp.collision(box) && !powerUp.getFalling() && 
                                		box.getY() > powerUp.getY()) {
                                    powerUp.setFalling(true);
                                }
                                box.getPowerUp().move();
                                pipes.forEach(pipe -> box.getPowerUp().collision(pipe));
                                player.collision(box.getPowerUp());
                            }
                        });
                        // collision player - wall
                        walls.forEach(player::collision);
                        // collision player - coin, animate coin
                        coins.forEach(coin -> {
                            player.collision(coin);
                            coin.animate();
                        });
                        // if x < 0 it's like toDelete
                        // removing walls
                        for (int i = 0; i < walls.size(); i++)
                            if (walls.get(i).getX() < 0)
                                walls.remove(i);
                        // removing coins
                        for (int i = 0; i < coins.size(); i++)
                            if (coins.get(i).getX() < 0)
                                coins.remove(i);
                        // removing enemies
                        for (int i = 0; i < enemies.size(); i++)
                            if (enemies.get(i).getX() < 0)
                                enemies.remove(i);
                        // removing BoxCoins
                        for (Box boxe : boxes) {
                            if (boxe.getBoxCoin() != null && boxe.getBoxCoin().getX() < 0) {
                                boxe.setBoxCoin(null);
                            }
                            if (boxe.getPowerUp() != null && boxe.getPowerUp().getX() < 0) {
                                boxe.setPowerUp(null);
                            }
                        }
                    }

                    gc.drawImage(background, 0, 0, 800, 550);
                    coins.forEach(coin -> gc.drawImage(coin.getImage(), coin.getX(), coin.getY()));
                    pipes.forEach(pipe -> gc.drawImage(pipe.getImage(), pipe.getX(), pipe.getY()));
                    walls.forEach(wall -> gc.drawImage(wall.getImage(), wall.getX(), wall.getY()));
                    boxes.forEach(box -> {
                        gc.drawImage(box.getImage(), box.getX(), box.getY());
                        if (box.getBoxCoin() != null) {
                            gc.drawImage(box.getBoxCoin().getImage(), box.getBoxCoin().getX(), 
                            		box.getBoxCoin().getY());
                        }
                        if (box.getPowerUp() != null) {
                            gc.drawImage(box.getPowerUp().getImage(), box.getPowerUp().getX(), 
                            		box.getPowerUp().getY());
                        }
                    });
                    enemies.forEach(enemy -> gc.drawImage(enemy.getImage(), enemy.getX(), enemy.getY()));
                    gc.drawImage(player.getImage(), player.getX(), player.getY());
                }

            }.start();

            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
     
    private void updateCoinsInFile(String username, int coins) {
        String filePath = "user_credentials.txt";
        File inputFile = new File(filePath);
        File tempFile = new File("temp.txt");

        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userInfo = line.split(",");
                if (userInfo.length == 4 && userInfo[0].equals(username)) {
                    // Update the line with new coin value
                    line = userInfo[0] + "," + userInfo[1] + "," + coins + "," + userInfo[3];
                }
                writer.write(line + System.lineSeparator());
            }

            writer.close();
            reader.close();

            // Delete the original file
            if (!inputFile.delete()) {
                System.out.println("Could not delete the original file.");
                return;
            }

            // Rename the temporary file to the original file name
            if (!tempFile.renameTo(inputFile)) {
                System.out.println("Could not rename the temporary file.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void collisionWithCoin() {
        counter++;
        coinCountText.setText("Coins: " + counter);
    }

    @Override
    public void gameOver() {
    	userCoin = getCoins(userName);
		int totalCoins = userCoin + counter;
        updateCoinsInFile(userName, totalCoins);
        // Show game over dialog
        Platform.runLater(() -> {
            Alert gameOverDialog = new Alert(Alert.AlertType.INFORMATION);
            gameOverDialog.setTitle("Game Over");
            gameOverDialog.setHeaderText("Total Coins Collected: " + counter);
            gameOverDialog.setContentText("Do you want to exit the game?");
            gameOverDialog.getButtonTypes().setAll(ButtonType.YES);

            gameOverDialog.showAndWait().ifPresent(response -> {
                if (response == ButtonType.YES) {
                	counter = 0;
                    coinCountText.setText("Coins: " + counter);
                	((Stage) coinCountText.getScene().getWindow()).close();
                } 
            });
       });
    }
    
    public int getCoins(String username) {
        try (BufferedReader br = new BufferedReader(new FileReader("user_credentials.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 4 && parts[0].equals(username)) {
                	int userCoins = Integer.parseInt(parts[2]);
                	 return userCoins;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        	}
        return 0;
    }
    
    public int checkCharacter(String username) {
        try (BufferedReader br = new BufferedReader(new FileReader("user_credentials.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 4 && parts[0].equals(username)) {
                	int characters = Integer.parseInt(parts[3]);
                	return characters;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        	}
		return 1;
    }
}