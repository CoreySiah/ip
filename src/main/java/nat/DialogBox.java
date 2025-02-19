package nat;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

/**
 * Represents a dialog box containing an image and a label displaying text.
 * This is used to show conversations between the user and the chatbot.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a DialogBox with the specified text, image, and alignment.
     *
     * @param text   The text to display in the dialog box.
     * @param img    The image representing the speaker.
     * @param isUser {@code true} if the dialog belongs to the user, {@code false} if it belongs to the chatbot.
     * @param isError {@code true} if the message represents an error response.
     */
    private DialogBox(String text, Image img, boolean isUser, boolean isError) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        dialog.setFont(Font.font("Verdana", 14));
        displayPicture.setImage(img);

        if (isUser) {
            this.setStyle("-fx-background-color: #CCE5FF; -fx-padding: 10; -fx-background-radius: 0;");
            this.setAlignment(Pos.CENTER_RIGHT);
        } else {
            this.setStyle("-fx-background-color: #CCFFE3; -fx-padding: 10; -fx-background-radius: 0;");
            this.setAlignment(Pos.CENTER_LEFT);
        }
    }

    /**
     * Flips the dialog box so that the image appears on the left and text on the right.
     * Used to differentiate between user and chatbot messages.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Creates a dialog box representing a user message.
     *
     * @param text The text entered by the user.
     * @param img  The user's profile image.
     * @return A styled {@code DialogBox} representing the user's message.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img, true, false);
    }

    /**
     * Creates a dialog box representing a response from the chatbot.
     *
     * @param text The chatbot's response.
     * @param img  The chatbot's profile image.
     * @return A styled {@code DialogBox} representing the chatbot's response.
     */
    public static DialogBox getNatDialog(String text, Image img) {
        var db = new DialogBox(text, img, false, false);
        db.flip();
        return db;
    }

    public static DialogBox getErrorDialog(String text, Image img) {
        var db = new DialogBox(text, img, false, true);
        db.flip();
        db.setStyle("-fx-background-color: #FFCCCC; -fx-padding: 10; -fx-border-color: #FF0000;"); // Highlight errors
        return db;
    }
}
