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
 * Manages user interactions, message handling, and chatbot responses.
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

    /**
     * Initializes the GUI by binding the scroll pane to automatically scroll to the latest message.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the Nat instance and displays a welcome message.
     *
     * @param nat The chatbot instance.
     */
    public void setNat(Nat nat) {
        this.nat = nat;

        // Return welcome message after Nat is initialized
        String welcomeMessage = "Good day! I'm Nat!\nHow may I assist you today?\n";
        dialogContainer.getChildren().add(
                DialogBox.getNatDialog(welcomeMessage, natImage)
        );
    }

    /**
     * Handles user input by creating two dialog boxes:
     * one displaying the user's message and another showing the chatbot's response.
     * The user input field is cleared after processing.
     * If the user enters "bye", the application saves tasks and exits.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = nat.executeCommand(input);

        // Add user input to the dialog
        dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));

        // Show error message in a different format, else show normal reply
        if (response.startsWith("Oh no!")) {
            dialogContainer.getChildren().add(DialogBox.getErrorDialog(response, natImage));
        } else {
            dialogContainer.getChildren().add(DialogBox.getNatDialog(response, natImage));
        }

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
