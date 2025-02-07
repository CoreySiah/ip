package Nat;

import java.util.ArrayList;

/**
 * The Ui class represents the component which handles user interface (e.g. printing).
 */
public class Ui {
    private static final String HORIZONTAL_LINE = "    ____________________________________________________________";
    private static final String SPACER = "    ";

    public Ui() {
    }

    public void printErrorCommand() {
        System.out.println(SPACER + " Oh noo! I don't know what you mean; please try again!\n" + HORIZONTAL_LINE);
    }

    // Print task list
    private void printListCommand(ArrayList<Task> taskList, int numOfItems) {
        System.out.println(SPACER + " Here are the tasks in your list:");
        for (int i = 0; i < numOfItems; i++) {
            int printIndex = i + 1;
            System.out.println(SPACER + " " + printIndex + "." + taskList.get(i));
        }
        System.out.println(HORIZONTAL_LINE);
    }

    // Add TDo task
    private void printUnsuccessfulAddToDoTask() {
        System.out.println(SPACER + "Invalid format. Use: todo <task name>");
    }

    // Add Deadline task
    private void printUnsuccessfulAddDeadlineTask() {
        System.out.println(SPACER + "Invalid format. Use: deadline <task name> /by <due date>");
    }

    // Add Event task
    private void printUnsuccessfulAddEventTask() {
        System.out.println(SPACER + "Invalid format. Use: event <task name> /from <start date> /to <end date>");
    }

    // Add (General) task
    private void printSuccessfulAddTask(Task newTask, int numOfItems) {
        System.out.println(SPACER + " Got it. I've added this task:\n"
                + SPACER + "   " + newTask + "\n"
                + SPACER + " Now you have " + numOfItems + " tasks in the list.");
        System.out.println(HORIZONTAL_LINE);
    }

    // Mark
    private void printSuccessfulMark(ArrayList<Task> taskList, int index) {
        System.out.println(SPACER + " Nice! I've marked this task as done:");
        System.out.println(SPACER + "   " + taskList.get(index));
    }

    private void printUnsuccessfulMark() {
        System.out.println(SPACER + "Invalid format. Use: mark <task number>");
    }

    // Unmark
    private void printSuccessfulUnmark(ArrayList<Task> taskList, int index) {
        System.out.println(SPACER + " Boo! I've marked this task as not done yet:");
        System.out.println(SPACER + "   " + taskList.get(index));
    }

    private void printUnsuccessfulUnmark() {
        System.out.println(SPACER + "Invalid format. Use: unmark <task number>");
    }

    // Delete
    private void printSuccessfulDelete(String removedTask, int numOfItems) {
        System.out.println(SPACER + " Disappeario! I've removed this task:\n"
                + SPACER + "   " + removedTask + "\n"
                + SPACER + " Now you have " + numOfItems + " tasks in the list.");
    }

    private void printUnsuccessfulDelete() {
        System.out.println(SPACER + "Invalid format. Use: delete <task number>");
    }

    // Load
    private void printLineErrorLoad() {
        System.out.println(SPACER + " Oops! Warning: Invalid task format in data file. Skipping...");
    }

    private void printDeadlineFormatErrorLoad() {
        System.out.println(SPACER + " Oops! Warning: Invalid deadline task format. Skipping...");
    }

    private void printEventFormatErrorLoad() {
        System.out.println(SPACER + " Oops! Warning: Invalid event task format. Skipping...");
    }

    private void printUnsuccessfulLoad(String eMessage) {
        System.out.println(SPACER + " Nay! An error occurred while loading tasks: " + eMessage);
    }

    // Save
    private void printSuccessfulSave() {
        System.out.println(SPACER + " Yay! Tasks successfully saved to tasks.txt");
    }

    private void printUnsuccessfulSave(String eMessage) {
        System.out.println(SPACER + " Nay! An error occurred while saving tasks: " + eMessage);
    }

    /**
     * Prints out the big main ASCII logo
     */
    public void printLogo() {
        String logo = SPACER + " ____  _____       _     _________  \n"
                + SPACER + "|_   \\|_   _|     / \\   |  _   _  | \n"
                + SPACER + "  |   \\ | |      / _ \\  |_/ | | \\_| \n"
                + SPACER + "  | |\\ \\| |     / ___ \\     | |     \n"
                + SPACER + " _| |_\\   |_  _/ /   \\ \\_  _| |_    \n"
                + SPACER + "|_____|\\____||____| |____||_____|   \n";
        System.out.println(SPACER + "Hello from\n" + logo + HORIZONTAL_LINE);
    }

    /**
     * Prints out the introductory greeting
     */
    public void printGreeting() {
        System.out.println(SPACER + " Good day! I'm Nat!\n"
                + SPACER + " How may I assist you today?\n"
                + HORIZONTAL_LINE);
    }

    /**
     * Prints out the ending goodbye
     */
    public void printGoodbye() {
        System.out.println(SPACER + " Byebye! Come back soon!\n"
                + HORIZONTAL_LINE);
    }
}
