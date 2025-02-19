package nat;

import javafx.application.Application;

/**
 * A launcher class that serves as an entry point for starting the application.
 * <p>
 * This class is required to work around JavaFX classpath issues when packaging the application into a JAR file.
 * The {@code Application.launch()} method is called here instead of directly in {@link Main}, ensuring compatibility
 * when running JavaFX applications in certain environments.
 * </p>
 */
public class Launcher {
    /**
     * The main method that starts the JavaFX application.
     * Calls {@link Application#launch(Class, String...)} with {@link Main} as the primary class.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
