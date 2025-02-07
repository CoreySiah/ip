package Nat;

import java.util.Scanner;

/**
 * The Nat class represents the main application for a task management chatbot.
 * It initializes core components such as the user interface, storage, and task list,
 * and handles the main command execution loop.
 */
public class Nat {
    private Ui ui;
    private Storage storage;
    private TaskList taskList;
    private Scanner scanner;
    private static final String HORIZONTAL_LINE = "    ____________________________________________________________";
    private static final String SPACER = "    ";

    /**
     * Constructor for the Nat class.
     * Initializes the task list, sets the number of items to zero,
     * and prepares the scanner for user input.
     */
    public Nat() {
        this.ui = new Ui();
        this.storage = new Storage("src/main/java/data/data.txt");
        this.taskList = new TaskList(storage.load());
        this.scanner = new Scanner(System.in);
    }

    /**
     * The main method serves as the entry point for the program.
     * It initializes the application by creating an instance of the Nat
     * class and starts its execution loop.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        Nat NATcb = new Nat();
        NATcb.run();
    }

    /**
     * Starts the main program execution loop for the Nat application.
     */
    public void run() {
        // Print program startup information
        this.ui.printLogo();
        this.ui.printGreeting();

        // Prompt user for a command
        String command = this.scanner.nextLine();

        // Continue running until the user specifies "bye"
        while (!command.equals("bye")) {
            String[] commandParts = command.split(" ", 2);
            switch (commandParts[0]) {
                case "list":
                    this.taskList.performListCommand();
                    break;
                case "todo":
                    this.taskList.performToDoCommand(commandParts[1]);
                    break;
                case "deadline":
                    this.taskList.performDeadlineCommand(commandParts[1]);
                    break;
                case "event":
                    this.taskList.performEventCommand(commandParts[1]);
                    break;
                case "mark":
                    this.taskList.performMarkCommand(commandParts);
                    break;
                case "unmark":
                    this.taskList.performUnmarkCommand(commandParts);
                    break;
                case "delete":
                    this.taskList.performDeleteCommand(commandParts);
                    break;
                case "find":
                    this.taskList.performFindCommand(commandParts[1]);
                    break;
                default:
                    this.ui.printErrorCommand();
                    break;
            }

            command = this.scanner.nextLine();
            System.out.println(HORIZONTAL_LINE);
        }

        // Save the loaded task list into a .txt file
        this.storage.save(this.taskList.getTaskList());

        // Exit the program
        this.ui.printGoodbye();
    }
}
