import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;



/**
 * A view of the game. This class is responsible for displaying the game state.
 * Manages the window and the canvas.
 *
 * @author Yusuf Rahman
 * @version 1.0
 */
public class Window extends Application {

    private static final int WIDTH = 1000;
    private static final int HEIGHT = 800;
    private static final String GAME_TITLE = "Space Invaders";
    private static final String AUTHORS = "Aditya Ranjan"; 
    private static final int PLAYER_SIZE = 60;


    /**
     * Create a window for the game.
     */
    @Override
    public void start(Stage stage) {
        stage.setTitle(GAME_TITLE);

        // Create MenuBar
        MenuBar menuBar = new MenuBar();

        // File Menu
        Menu fileMenu = new Menu("File");
        MenuItem quitItem = new MenuItem("Quit");
        quitItem.setOnAction(e -> stage.close());
        fileMenu.getItems().add(quitItem);

        // Help Menu
        Menu helpMenu = new Menu("Help");
        MenuItem aboutItem = new MenuItem("About");
        aboutItem.setOnAction(e -> showAboutDialog());
        helpMenu.getItems().add(aboutItem);

        menuBar.getMenus().addAll(fileMenu, helpMenu);

        // Game Display Panel
        GameDisplay gameDisplay = new GameDisplay(WIDTH, HEIGHT);

        // Layout
        BorderPane root = new BorderPane();
        root.setTop(menuBar);
        root.setCenter(gameDisplay.getCanvas());

        // Scene Setup
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        stage.setScene(scene);
        stage.show();
        
        
        // Start the game loop
        stage.show();
    }

    /**
     * Show an about dialog.
     */
    private void showAboutDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("Game Title: " + GAME_TITLE);
        alert.setContentText("Authors: " + AUTHORS + "\nVersion: 1.0");
        alert.showAndWait();
    }

    /**
     * Launch the game.
     */
    public static void main(String[] args) {
        launch(args);
    }
}