package nat;

import java.util.ArrayList;

/**
 * The TaskList class represents the component which handles containing the primary
 * task list.
 */
public class TaskList {
    private int numOfItems;
    private ArrayList<Task> taskList;

    /**
     * Constructor for the TaskList class.
     * Initializes the task list, sets the number of items to zero,
     * and prepares the scanner for user input.
     */
    public TaskList() {
        this.numOfItems = 0;
        this.taskList = new ArrayList<>();
    }

    /**
     * Constructor for the TaskList class.
     * Initializes the task list, and preloads it with a saved task list,
     * and prepares the scanner for user input.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
        this.numOfItems = taskList.size();
    }

    /**
     * Execute the list command and return a list of all tasks
     * @return List of tasks
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
     * Add TDo task to the task list
     * @param newTask Name of the new task
     * @return Success or unsuccessful message
     */
    public String performToDoCommand(String newTask) {
        if (newTask == null || newTask.trim().isEmpty()) {
            return "Invalid format. Use: todo <task name>";
        }

        String taskName = newTask.trim();
        return this.performAddTaskCommand(new ToDo(taskName));
    }

    /**
     * Add Deadline task to the task list
     * @param newTask Name of the new task
     * @return Success or unsuccessful message
     */
    public String performDeadlineCommand(String newTask) {
        String[] taskParts = newTask.split(" /by ", 2);
        if (taskParts.length < 2) {
            return "Invalid format. Use: deadline <task name> /by <due date>";
        }
        String taskName = taskParts[0].trim();
        String dueDate = taskParts[1].trim();
        return this.performAddTaskCommand(new Deadline(taskName, dueDate));
    }

    /**
     * Add Event task to the task list
     * @param newTask Name of the new task
     * @return Success or unsuccessful message
     */
    public String performEventCommand(String newTask) {
        String[] taskParts = newTask.split(" /from ", 2);
        if (taskParts.length < 2 || !taskParts[1].contains(" /to ")) {
            return "Invalid format. Use: event <task name> /from <start date> /to <end date>";
        }

        String taskName = taskParts[0].trim();
        String[] dateParts = taskParts[1].split(" /to ", 2);
        String startDate = dateParts[0].trim();
        String endDate = dateParts[1].trim();
        return this.performAddTaskCommand(new Event(taskName, startDate, endDate));
    }

    /**
     * Add a generic task
     * @param newTask New Task, applying polymorphism
     * @return Success or unsuccessful message
     */
    private String performAddTaskCommand(Task newTask) {
        this.taskList.add(newTask);
        this.numOfItems++;
        return "Got it. I've added this task:\n"
                + newTask + "\n"
                + "Now you have " + this.numOfItems + " tasks in the list.";
    }

    /**
     * Mark a specific task.
     */
    public String performMarkCommand(String[] commandParts) {
        if (commandParts.length == 2) {
            int index = Integer.parseInt(commandParts[1]) - 1;
            this.taskList.get(index).markAsDone();
            return "Nice! I've marked this task as done:\n" + this.taskList.get(index);
        } else {
            return "Invalid format. Use: mark <task number>";
        }
    }

    /**
     * Unmark a specific task.
     */
    public String performUnmarkCommand(String[] commandParts) {
        if (commandParts.length == 2) {
            int index = Integer.parseInt(commandParts[1]) - 1;
            this.taskList.get(index).unmarkAsDone();
            return "Boo! I've marked this task as not done yet:\n" + this.taskList.get(index);
        } else {
            return "Invalid format. Use: unmark <task number>";
        }
    }

    /**
     * Delete a specific task.
     */
    public String performDeleteCommand(String[] commandParts) {
        if (commandParts.length == 2) {
            int index = Integer.parseInt(commandParts[1]) - 1;
            String removedTask = this.taskList.get(index).toString();
            this.taskList.remove(index);
            this.numOfItems--;
            return "Disappeario! I've removed this task:\n"
                    + removedTask + "\n"
                    + "Now you have " + this.numOfItems + " tasks in the list.";

        } else {
            return "Invalid format. Use: delete <task number>";
        }
    }

    /**
     * Find a specific task from the task list
     * @param keyword Keyword to search for
     * @return Success or unsuccessful message
     */
    public String performFindCommand(String keyword) {
        int printIndex = 1;
        String foundMessage = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < this.numOfItems; i++) {
            if (this.taskList.get(i).getTaskName().contains(keyword)) {
                foundMessage = foundMessage + printIndex + "." + this.taskList.get(i);
                printIndex++;
            }
        }
        return foundMessage;
    }

    /**
     * Return the entire task list as an ArrayList.
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }
}
