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

public class Window extends Application {

    private static final int WIDTH = 1200;
    private static final int HEIGHT = 950;
    private static final String GAME_TITLE = "Space Invaders";
    private static final String AUTHORS = "Aditya Ranjan, Kasim Morsel, Yaseen Alam, Yusuf Rahman";
    private GameDisplay gameDisplay; // Canvas for game graphics
    private MenuBar menuBar;
    private VBox gameMenuBox;
    private VBox gameOverBox;

    @Override
    public void start(Stage stage) {
        stage.setTitle(GAME_TITLE);
        Font pixelFont = Font.loadFont("file:fonts/Pixels.ttf", 60);

        // Initialise interface components
        initialiseMenuBar(stage);
        initialiseGameMenu(pixelFont, stage);
        initialiseGameOver(stage);     
        this.gameDisplay = new GameDisplay(WIDTH, HEIGHT, this); 

        // Layout for game display and menu
        BorderPane gamePane = new BorderPane();
        gamePane.setTop(menuBar);
        gamePane.setCenter(gameDisplay.getCanvas());
        
        // Use StackPane as a root for layering game, menu, and game over screen
        StackPane root = new StackPane();
        root.getChildren().addAll(gamePane, gameMenuBox, gameOverBox);

        Scene scene = new Scene(root, WIDTH, HEIGHT + 20);
        stage.setScene(scene);
        stage.show();

        // For testing purposes, you can show the game over screen with:
        // gameOverBox.setVisible(true);
    }

    /**
     * Sets up the menu bar with File and Help menus.
     */
    private void initialiseMenuBar(Stage stage) {
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

        this.menuBar = menuBar;
    }

    /**
     * Sets up the game menu to start playing.
     */
    private void initialiseGameMenu(Font pixelFont, Stage stage) {
        ImageView titleImage = new ImageView(new Image("file:images/titleSi.png"));
        titleImage.setFitHeight(200);
        titleImage.setPreserveRatio(true);

        HBox row1 = createScoreRow("file:images/small.png", "= 10 PTS", Color.WHITE, pixelFont);
        HBox row2 = createScoreRow("file:images/medium.png", "= 20 PTS", Color.WHITE, pixelFont);
        HBox row3 = createScoreRow("file:images/large.png", "= 40 PTS", Color.WHITE, pixelFont);
        HBox row4 = createScoreRow("file:images/SuperAlien.png", "= ??? PTS", Color.WHITE, pixelFont);

        Button playButton = createStyledButton("PLAY");
        playButton.setOnAction(e -> {
            gameMenuBox.setVisible(false);
            gameDisplay.startGame();
        });

        Button quitButton = createStyledButton("QUIT");
        quitButton.setOnAction(e -> {
            stage.close();
            gameDisplay.stopGame();
        });

        VBox menuBox = new VBox(20, titleImage, row1, row2, row3, row4, playButton, quitButton);
        menuBox.setAlignment(javafx.geometry.Pos.CENTER);
        menuBox.setStyle("-fx-background-color: black;");
        menuBox.setPrefSize(WIDTH, HEIGHT);

        this.gameMenuBox = menuBox;
    }

    /**
     * Sets up the game over screen.
     */
    private void initialiseGameOver(Stage stage) {
        Label gameOverLabel = new Label("GAME OVER");
        gameOverLabel.setFont(Font.font("Pixels", FontWeight.BOLD, 48));
        gameOverLabel.setTextFill(Color.RED);

        Button backToMenuButton = createStyledButton("BACK TO MENU");
        backToMenuButton.setOnAction(e -> {
            gameOverBox.setVisible(false);
            gameMenuBox.setVisible(true);
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
    }

    /**
     * Creates a box displaying the score
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
     */
    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setFont(Font.font("Pixels", 30));
        button.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-font-family: 'Pixels'; -fx-font-size: 30px; -fx-cursor: hand;");
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: transparent; -fx-text-fill: cyan; -fx-font-family: 'Pixels'; -fx-font-size: 30px; -fx-cursor: hand;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-font-family: 'Pixels'; -fx-font-size: 30px; -fx-cursor: hand;"));
        return button;
    }


    private void showAboutDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("Game Title: " + GAME_TITLE);
        alert.setContentText("Authors: " + AUTHORS + "\nVersion: 2.1");
        alert.showAndWait();
    }

    public void showGameOverScreen() {
        gameOverBox.setVisible(true);
    }

    public static void main(String[] args) {
        launch(args);
    }
} 
