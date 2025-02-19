package nat;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The main JavaFX application class for Nat.
 * <p>
 * This class initializes the graphical user interface (GUI) using FXML and manages the main window.
 * It extends {@link Application}, making it the entry point for launching the JavaFX GUI.
 * </p>
 */
public class Main extends Application {

    private Nat nat = new Nat();

    /**
     * Starts the JavaFX application and sets up the main GUI window.
     *
     * @param stage The primary stage for the application window.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);

            stage.setTitle("Nat");
            stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/images/DaNat.png")));
            stage.setScene(scene);

            fxmlLoader.<MainWindow>getController().setNat(nat);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves tasks before the application exits.
     * <p>
     * This method ensures that any changes made to the task list are stored before the program closes.
     * </p>
     */
    @Override
    public void stop() {
        System.out.println("Saving tasks before exiting...");
        nat.storage.save(nat.taskList.getTaskList());
    }
}
