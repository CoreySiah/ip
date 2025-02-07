package Nat;

import java.util.ArrayList;

/**
 * The TaskList class represents the component which handles containing the primary
 * task list.
 */
public class TaskList {
    private ArrayList<Task> taskList;
    private int numOfItems;
    private static final String HORIZONTAL_LINE = "    ____________________________________________________________";
    private static final String SPACER = "    ";

    /**
     * Constructor for the TaskList class.
     * Initializes the task list, sets the number of items to zero,
     * and prepares the scanner for user input.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
        this.numOfItems = 0;
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

    public void performListCommand() {
        System.out.println(SPACER + " Here are the tasks in your list:");
        for (int i = 0; i < this.numOfItems; i++) {
            int printIndex = i + 1;
            System.out.println(SPACER + " " + printIndex + "." + this.taskList.get(i));
        }
        System.out.println(HORIZONTAL_LINE);
    }

    public void performToDoCommand(String newTask) {
        if (newTask == null || newTask.trim().isEmpty()) {
            System.out.println(SPACER + "Invalid format. Use: todo <task name>");
            return;
        }

        String taskName = newTask.trim();
        this.performAddTaskCommand(new ToDo(taskName));
    }

    public void performDeadlineCommand(String newTask) {
        String[] taskParts = newTask.split(" /by ", 2);
        if (taskParts.length < 2) {
            System.out.println(SPACER + "Invalid format. Use: deadline <task name> /by <due date>");
            return;
        }
        String taskName = taskParts[0].trim();
        String dueDate = taskParts[1].trim();
        this.performAddTaskCommand(new Deadline(taskName, dueDate));
    }

    public void performEventCommand(String newTask) {
        String[] taskParts = newTask.split(" /from ", 2);
        if (taskParts.length < 2 || !taskParts[1].contains(" /to ")) {
            System.out.println(SPACER + "Invalid format. Use: event <task name> /from <start date> /to <end date>");
            return;
        }

        String taskName = taskParts[0].trim();
        String[] dateParts = taskParts[1].split(" /to ", 2);
        String startDate = dateParts[0].trim();
        String endDate = dateParts[1].trim();
        this.performAddTaskCommand(new Event(taskName, startDate, endDate));
    }

    // Use when loading in saved tasks (w/o message)
    private void addTask(Task newTask) {
        this.taskList.add(newTask);
        this.numOfItems++;
    }

    /**
     * Unmask a specific task.
     */
    private void performAddTaskCommand(Task newTask) {
        this.taskList.add(newTask);
        this.numOfItems++;
        System.out.println(SPACER + " Got it. I've added this task:\n"
                + SPACER + "   " + newTask + "\n"
                + SPACER + " Now you have " + this.numOfItems + " tasks in the list.");
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Mark a specific task.
     */
    public void performMarkCommand(String[] commandParts) {
        if (commandParts.length == 2) {
            int index = Integer.parseInt(commandParts[1]) - 1;
            this.taskList.get(index).markAsDone();
            System.out.println(SPACER + " Nice! I've marked this task as done:");
            System.out.println(SPACER + "   " + this.taskList.get(index));
        } else {
            System.out.println(SPACER + "Invalid format. Use: mark <task number>");
        }
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Unmark a specific task.
     */
    public void performUnmarkCommand(String[] commandParts) {
        if (commandParts.length == 2) {
            int index = Integer.parseInt(commandParts[1]) - 1;
            this.taskList.get(index).unmarkAsDone();
            System.out.println(SPACER + " Boo! I've marked this task as not done yet:");
            System.out.println(SPACER + "   " + this.taskList.get(index));
        } else {
            System.out.println(SPACER + "Invalid format. Use: unmark <task number>");
        }
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Delete a specific task.
     */
    public void performDeleteCommand(String[] commandParts) {
        if (commandParts.length == 2) {
            int index = Integer.parseInt(commandParts[1]) - 1;
            String removedTask = this.taskList.get(index).toString();
            this.taskList.remove(index);
            this.numOfItems--;
            System.out.println(SPACER + " Disappeario! I've removed this task:\n"
                    + SPACER + "   " + removedTask + "\n"
                    + SPACER + " Now you have " + this.numOfItems + " tasks in the list.");

        } else {
            System.out.println(SPACER + "Invalid format. Use: delete <task number>");
        }
        System.out.println(HORIZONTAL_LINE);
    }

    public void performFindCommand(String keyword) {
        int printIndex = 1;
        System.out.println(SPACER + " Here are the matching tasks in your list:");
        for (int i = 0; i < this.numOfItems; i++) {
            if (this.taskList.get(i).getTaskName().contains(keyword)) {
                System.out.println(SPACER + " " + printIndex + "." + this.taskList.get(i));
                printIndex++;
            }
        }
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Return the entire task list as an ArrayList.
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }
}
