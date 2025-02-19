package nat;

/**
 * The core class for the Nat chatbot, responsible for task management and command execution.
 * This class initializes essential components such as storage and the task list.
 */
public class Nat {
    protected Storage storage;
    protected TaskList taskList;

    /**
     * Initializes the Nat chatbot, loading stored tasks and preparing for user interaction.
     * The task list is retrieved from persistent storage.
     * <p>
     * An assertion is used to ensure that the task list is not null after initialization.
     * </p>
     */
    public Nat() {
        this.storage = new Storage("src/main/java/data/data.txt");
        this.taskList = new TaskList(storage.load());

        // Assertion: TaskList should never be null after initialization
        assert this.taskList != null : "TaskList should not be null!";
    }

    /**
     * Processes and executes the given command string.
     * <p>
     * The method interprets the command and delegates execution to the corresponding task operation.
     * If the command is unrecognized, an error message is returned.
     * </p>
     *
     * @param command The command input by the user.
     * @return A response message indicating the result of the command execution.
     */
    public String executeCommand(String command) {
        String[] commandParts = command.split(" ", 2);
        switch (commandParts[0]) {
        case "list":
            return this.taskList.performListCommand();
        case "todo":
            return this.taskList.performToDoCommand(commandParts);
        case "deadline":
            return this.taskList.performDeadlineCommand(command);
        case "event":
            return this.taskList.performEventCommand(command);
        case "mark":
            return this.taskList.performMarkCommand(commandParts);
        case "unmark":
            return this.taskList.performUnmarkCommand(commandParts);
        case "delete":
            return this.taskList.performDeleteCommand(commandParts);
        case "find":
            return this.taskList.performFindCommand(commandParts);
        case "sort":
            return this.taskList.performSortCommand();
        default:
            return "Oh no! I do not understand that command; please try again!";
        }
    }
}
