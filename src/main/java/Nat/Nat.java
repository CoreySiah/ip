package Nat;

import java.util.Scanner;

/**
 * The Nat class represents the main application for a task management chatbot.
 * It initializes core components such as the user interface, storage, and task list,
 * and handles the main command execution loop.
 */
public class Nat {
    private Ui ui;
    protected Storage storage;
    protected TaskList taskList;
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

    public String executeCommand(String command) {
        String[] commandParts = command.split(" ", 2);
        switch (commandParts[0]) {
            case "list":
                return this.taskList.performListCommand();
            case "todo":
                return this.taskList.performToDoCommand(commandParts[1]);
            case "deadline":
                return this.taskList.performDeadlineCommand(commandParts[1]);
            case "event":
                return this.taskList.performEventCommand(commandParts[1]);
            case "mark":
                return this.taskList.performMarkCommand(commandParts);
            case "unmark":
                return this.taskList.performUnmarkCommand(commandParts);
            case "delete":
                return this.taskList.performDeleteCommand(commandParts);
            case "find":
                return this.taskList.performFindCommand(commandParts[1]);
        }
        return this.ui.printErrorCommand();
    }
}
