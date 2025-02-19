package nat;

/**
 * The Nat class represents the main application for a task management chatbot.
 * It initializes core components such as the user interface, storage, and task list,
 * and handles the main command execution loop.
 */
public class Nat {
    protected Storage storage;
    protected TaskList taskList;

    /**
     * Constructor for the Nat class.
     * Initializes the task list, sets the number of items to zero,
     * and prepares the scanner for user input.
     */
    public Nat() {
        this.storage = new Storage("src/main/java/data/data.txt");
        this.taskList = new TaskList(storage.load());

        // Assertion: TaskList should never be null after initialization
        assert this.taskList != null : "TaskList should not be null!";

    }

    /**
     * Execute the input command from MainWindow class
     * @param command String input command
     * @return Completed message after executing command
     */
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
        case "sort":
            return this.taskList.performSortCommand();
        default:
            return "Oh no! I do not understand that command; please try again!";
        }
    }
}
