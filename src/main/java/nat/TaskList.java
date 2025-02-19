package nat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Manages a list of tasks, providing operations to add, remove, modify, and search tasks.
 */
public class TaskList {
    private int numOfItems;
    private ArrayList<Task> taskList;

    /**
     * Creates an empty task list.
     */
    public TaskList() {
        this.numOfItems = 0;
        this.taskList = new ArrayList<>();
    }

    /**
     * Creates a task list preloaded with saved tasks.
     *
     * @param taskList The list of tasks to initialize the task list with.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
        this.numOfItems = taskList.size();
    }

    /**
     * Returns a formatted string of all tasks in the task list.
     *
     * @return A numbered list of tasks.
     */
    public String performListCommand() {
        String fullList = "Here are the tasks in your list:\n";
        for (int i = 0; i < this.numOfItems; i++) {
            int printIndex = i + 1;
            fullList = fullList + printIndex + "." + this.taskList.get(i) + "\n";
        }
        return fullList;
    }

    /**
     * Adds a ToDo task to the task list.
     *
     * @param commandParts The user input split into command and task name.
     * @return A success message or an error message if the input is invalid.
     */
    public String performToDoCommand(String[] commandParts) {
        if (!isValidCommandParts(commandParts, 2)) {
            return "Invalid format. Use: todo <task name>";
        }
        return this.performAddTaskCommand(new ToDo(commandParts[1].trim()));
    }

    /**
     * Adds a Deadline task to the task list with proper error handling.
     *
     * @param newTask The task description including the deadline.
     * @return A success message or an error message if the input is invalid.
     */
    public String performDeadlineCommand(String newTask) {
        String[] taskParts = newTask.split(" /by ", 2);

        // Validate that both task name and deadline exist
        if (taskParts.length < 2 || taskParts[1].trim().isEmpty()) {
            return "Invalid format. Use: deadline <task name> /by <date> <time>\nExample: deadline finish project /by 5/12/2034 1800";
        }

        String taskName = taskParts[0].trim();
        String dueDate = taskParts[1].trim();

        // Validate date format before proceeding
        if (!isValidDateTimeFormat(dueDate)) {
            return "Invalid date format! Please use: d/M/yyyy HHmm (e.g., 5/12/2034 1800)";
        }

        return this.performAddTaskCommand(new Deadline(taskName, dueDate));
    }

    /**
     * Adds an Event task to the task list with proper error handling.
     *
     * @param newTask The task description including start and end dates.
     * @return A success message or an error message if the input is invalid.
     */
    public String performEventCommand(String newTask) {
        String[] taskParts = newTask.split(" /from ", 2);

        // Validate that task name and start date exist
        if (taskParts.length < 2 || taskParts[1].trim().isEmpty() || !taskParts[1].contains(" /to ")) {
            return "Invalid format. Use: event <task name> /from <start date> <start time> /to <end date> <end time>\nExample: event conference /from 5/12/2034 0900 /to 5/12/2034 1800";
        }

        String taskName = taskParts[0].trim();
        String[] dateParts = taskParts[1].split(" /to ", 2);
        String startDate = dateParts[0].trim();
        String endDate = dateParts[1].trim();

        // Validate both date formats before proceeding
        if (!isValidDateTimeFormat(startDate) || !isValidDateTimeFormat(endDate)) {
            return "Invalid date format! Please use: d/M/yyyy HHmm (e.g., 5/12/2034 0900 /to 5/12/2034 1800)";
        }

        return this.performAddTaskCommand(new Event(taskName, startDate, endDate));
    }

    /**
     * Adds a new task to the task list.
     *
     * @param newTask The task to be added.
     * @return A success message confirming the addition.
     */
    private String performAddTaskCommand(Task newTask) {
        this.taskList.add(newTask);
        this.numOfItems++;
        return "Got it. I've added this task:\n"
                + newTask + "\n"
                + "Now you have " + this.numOfItems + " tasks in the list.";
    }

    /**
     * Marks a specific task as completed.
     *
     * @param commandParts The user input split into command and task number.
     * @return A success message or an error message if the format is incorrect.
     */
    public String performMarkCommand(String[] commandParts) {
        if (!isValidCommandParts(commandParts, 2) || !isValidIndex(Integer.parseInt(commandParts[1]))) {
            return "Invalid format. Use: mark <task number>";
        }

        int index = Integer.parseInt(commandParts[1]) - 1;
        if (!isValidIndex(index)) {return "Invalid task number!";}
        this.taskList.get(index).markAsDone();
        return "Nice! I've marked this task as done:\n" + this.taskList.get(index);
    }

    /**
     * Unmarks a specific task as incomplete.
     *
     * @param commandParts The user input split into command and task number.
     * @return A success message or an error message if the format is incorrect.
     */
    public String performUnmarkCommand(String[] commandParts) {
        if (!isValidCommandParts(commandParts, 2) || !isValidIndex(Integer.parseInt(commandParts[1]))) {
            return "Invalid format. Use: unmark <task number>";
        }

        int index = Integer.parseInt(commandParts[1]) - 1;
        if (!isValidIndex(index)) {return "Invalid task number!";}
        this.taskList.get(index).unmarkAsDone();
        return "Boo! I've marked this task as not done yet:\n" + this.taskList.get(index);
    }

    /**
     * Deletes a specific task from the task list.
     *
     * @param commandParts The user input split into command and task number.
     * @return A success message or an error message if the format is incorrect.
     */
    public String performDeleteCommand(String[] commandParts) {
        if (!isValidCommandParts(commandParts, 2) || !isValidIndex(Integer.parseInt(commandParts[1]))) {
            return "Invalid format. Use: delete <task number>";
        }

        int index = Integer.parseInt(commandParts[1]) - 1;
        if (!isValidIndex(index)) {return "Invalid task number!";}
        String removedTask = this.taskList.get(index).toString();
        this.taskList.remove(index);
        this.numOfItems--;
        return "Disappeario! I've removed this task:\n"
                + removedTask + "\n"
                + "Now you have " + this.numOfItems + " tasks in the list.";
    }

    /**
     * Searches for tasks containing a specific keyword.
     *
     * @param commandParts The user input split into command and keyword.
     * @return A formatted string listing matching tasks, or an appropriate error message.
     */
    public String performFindCommand(String[] commandParts) {
        // Validate input (check if keyword exists)
        if (!isValidCommandParts(commandParts, 2)) {
            return "Invalid format. Use: find <keyword>";
        }

        // Extract the keyword and validate task list
        String keyword = commandParts[1].trim();
        if (taskList.isEmpty()) {
            return "Your task list is empty! Add some tasks first.";
        }

        // Search for matching tasks
        int printIndex = 1;
        StringBuilder foundMessage = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < this.numOfItems; i++) {
            if (this.taskList.get(i).getTaskName().toLowerCase().contains(keyword.toLowerCase())) {
                foundMessage.append(printIndex).append(". ").append(this.taskList.get(i)).append("\n");
                printIndex++;
            }
        }

        // If no matches found, return an appropriate message
        return (printIndex == 1) ? "No matching tasks found!" : foundMessage.toString();
    }

    /**
     * Sorts the task list alphabetically by task name.
     *
     * @return A success message with the sorted task list or an error message if the list is empty.
     */
    public String performSortCommand() {
        if (taskList.isEmpty()) {
            return "Your task list is empty! Nothing to sort.";
        }

        Collections.sort(taskList, Comparator.comparing(Task::getTaskName));
        return "Tasks sorted alphabetically:\n" + this.performListCommand();
    }

    /**
     * Returns the current list of tasks.
     *
     * @return The task list as an {@code ArrayList<Task>}.
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Checks if the user input is valid.
     *
     * @param commandParts The split user command input.
     * @param requiredParts The required number of parts.
     * @return true if valid, false otherwise.
     */
    private boolean isValidCommandParts(String[] commandParts, int requiredParts) {
        return commandParts.length >= requiredParts && commandParts[1] != null && !commandParts[1].trim().isEmpty();
    }

    /**
     * Checks if the provided index is valid for the task list.
     *
     * @param index The task number as a string.
     * @return true if the index is valid, false otherwise.
     */
    private boolean isValidIndex(int index) {
        return index > 0 && index <= this.numOfItems;
    }

    /**
     * Checks if a given date string matches the expected format (d/M/yyyy HHmm).
     *
     * @param dateTime The date string to validate.
     * @return true if the format is valid, false otherwise.
     */
    private boolean isValidDateTimeFormat(String dateTime) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            LocalDateTime.parse(dateTime, formatter);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
