import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * Generates the window where all game functions occur in.
 *
 * @author Yaseen Alam, Aditya Ranjan, Kasim Morsel, Yusuf Rahman
 * @version 2.1
 */

public class Window extends Application {
    private static final int WIDTH = 1200;
    private static final int HEIGHT = 950;
    private static final String GAME_TITLE = "Space Invaders";
    private static final String AUTHORS = "Aditya Ranjan, Kasim Morsel, Yaseen Alam, Yusuf Rahman";
    private VBox menuBox;
    private VBox gameOverBox;
    private int bestScore = 0;
    private Label bestScoreLabel;
    private GameDisplay gameDisplay;

    /**
     * Initialises the start of the game.
     * @param stage Requires a stage to display on.
     */
    
    @Override
    
    public void start(Stage stage) {
        stage.setTitle(GAME_TITLE);

        // Load font
        Font pixelFont = Font.loadFont("file:fonts/Pixels.ttf", 60);

        // Menu bar
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");
        MenuItem quitItem = new MenuItem("Quit");
        quitItem.setOnAction(e -> stage.close());
        fileMenu.getItems().add(quitItem);

        Menu helpMenu = new Menu("Help");
        MenuItem aboutItem = new MenuItem("About");
        aboutItem.setOnAction(e -> showAboutDialog());
        helpMenu.getItems().add(aboutItem);

        menuBar.getMenus().addAll(fileMenu, helpMenu);

        // Game display placeholder (empty canvas for now)
        gameDisplay = new GameDisplay(WIDTH, HEIGHT, this, bestScore);
        BorderPane gamePane = new BorderPane();
        gamePane.setTop(menuBar);
        gamePane.setCenter(gameDisplay.getCanvas());

        // Menu content
        ImageView titleImage = new ImageView(new Image("file:images/title.png"));
        titleImage.setFitHeight(325);
        titleImage.setPreserveRatio(true);

        HBox row1 = createScoreRow("file:images/SmallALien1.png", "= 10 PTS", Color.WHITE, pixelFont);
        HBox row2 = createScoreRow("file:images/MediumAlien1.png", "= 20 PTS", Color.WHITE, pixelFont);
        HBox row3 = createScoreRow("file:images/LargeAlien1.png", "= 40 PTS", Color.WHITE, pixelFont);
        HBox row4 = createScoreRow("file:images/SuperAlien.png", "= ??? PTS", Color.WHITE, pixelFont);

        Button playButton = createStyledButton("PLAY");
        playButton.setOnAction(e -> {
            menuBox.setVisible(false);
            gameDisplay.startGame();
        });

        Button quitButton = createStyledButton("QUIT");
        quitButton.setOnAction(e -> {
            stage.close();
            gameDisplay.stopGame();
        });

        VBox.setMargin(quitButton, new javafx.geometry.Insets(-30, 0, 0, 0));

        
        bestScoreLabel = new Label("Best Score: 0");
        bestScoreLabel.setFont(pixelFont);
        bestScoreLabel.setTextFill(Color.CYAN);
        
        menuBox = new VBox(15, titleImage, row1, row2, row3, row4, bestScoreLabel, playButton, quitButton);
        menuBox.setAlignment(javafx.geometry.Pos.CENTER);
        menuBox.setStyle("-fx-background-color: black;");
        menuBox.setPrefSize(WIDTH, HEIGHT);

        // Game Over Screen
        Label gameOverLabel = new Label("GAME OVER");
        gameOverLabel.setFont(Font.font("Pixels", FontWeight.BOLD, 250));
        gameOverLabel.setTextFill(Color.RED);

        Button backToMenuButton = createStyledButton("BACK TO MENU");
        backToMenuButton.setOnAction(e -> {
            gameOverBox.setVisible(false);
            menuBox.setVisible(true);
            gameDisplay.startGame();
        });

        Button gameOverQuitButton = createStyledButton("QUIT");
        gameOverQuitButton.setOnAction(e -> {
            stage.close();
            gameDisplay.stopGame();
        });

        gameOverBox = new VBox(20, gameOverLabel, backToMenuButton, gameOverQuitButton);
        gameOverBox.setAlignment(javafx.geometry.Pos.CENTER);
        gameOverBox.setStyle("-fx-background-color: rgba(0, 0, 0, 0.85);");
        gameOverBox.setPrefSize(WIDTH, HEIGHT);
        gameOverBox.setVisible(false);

        BorderPane root = new BorderPane();
        root.setTop(menuBar);

        // Use StackPane for layering game, menu, and game over screen
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(gamePane, menuBox, gameOverBox);
        root.setCenter(stackPane);

        Scene scene = new Scene(root, WIDTH, HEIGHT + 20);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Creates a box displaying the score.
     * 
     * @param imagePath Path of the image
     * @param text Displays relevant text
     * @param font Specifies font of text
     * @return row Returns the score row at the top to be drawn
     */
    
    private HBox createScoreRow(String imagePath, String text, Color textColor, Font font) {
        ImageView alienIcon = new ImageView(new Image(imagePath));
        alienIcon.setFitWidth(30);
        alienIcon.setFitHeight(30);

        Label label = new Label(text);
        label.setFont(font);
        label.setTextFill(textColor);

        HBox row = new HBox(10, alienIcon, label);
        row.setAlignment(javafx.geometry.Pos.CENTER);
        return row;
    }

    /**
     * Creates a coloured button with hover effects.
     * @param text Sets the relevant text for button
     * @return button Returns the button object to be displayed.
     */
    
    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setFont(Font.font("Pixels", 30));
        button.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-font-family: 'Pixels'; -fx-font-size: 55px; -fx-cursor: hand;");
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: transparent; -fx-text-fill: cyan; -fx-font-family: 'Pixels'; -fx-font-size: 55px; -fx-cursor: hand;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-font-family: 'Pixels'; -fx-font-size: 55px; -fx-cursor: hand;"));
        return button;
    }

    /**
     * Displays pop up menu when the about button is clicked from menu bar.
     */
    
    private void showAboutDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("Game Title: " + GAME_TITLE);
        alert.setContentText("Authors: " + AUTHORS + "\nVersion: 2.1");
        alert.showAndWait();
    }

    /**
     * Displays the game over screen and sets the high score.
     */
    
    public void showGameOverScreen() {
        int currentScore = gameDisplay.getCurrentScore();
        if (currentScore > bestScore) {
            bestScore = currentScore;
            gameDisplay.setBestScore(bestScore);
        }
        bestScoreLabel.setText("High Score: " + bestScore);
        gameOverBox.setVisible(true);
    }

    /**
     * Starts the javafx application.
     */
    
    public static void main(String[] args) {
        launch(args);
    }
} 
