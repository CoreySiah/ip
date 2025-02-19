package nat;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Nat nat;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image natImage = new Image(this.getClass().getResourceAsStream("/images/DaNat.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Nat instance */
    public void setNat(Nat nat) {
        this.nat = nat;

        // Return welcome message after Nat is initialized
        String welcomeMessage = "Good day! I'm Nat!\nHow may I assist you today?\n";
        dialogContainer.getChildren().add(
                DialogBox.getNatDialog(welcomeMessage, natImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Nat.Duke's reply and then appends
     * them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = nat.executeCommand(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getNatDialog(response, natImage)
        );

        userInput.clear();

        // Exit the application if the user inputs "bye"
        if (input.equalsIgnoreCase("bye")) {
            System.out.println("Saving tasks before exiting...");
            nat.storage.save(nat.taskList.getTaskList());
            Platform.exit();
            System.exit(0);
        }
    }
}
